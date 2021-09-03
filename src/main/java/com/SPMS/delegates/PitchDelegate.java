package com.SPMS.delegates;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.SPMS.beans.Author;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Genre;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.PitchReview;
import com.SPMS.beans.StoryType;
import com.SPMS.beans.relationaltables.PitchProxy;
import com.SPMS.services.AuthorService;
import com.SPMS.services.AuthorServiceImpl;
import com.SPMS.services.EditorService;
import com.SPMS.services.EditorServiceImpl;
import com.SPMS.services.GenreService;
import com.SPMS.services.GenreServiceImpl;
import com.SPMS.services.PitchService;
import com.SPMS.services.PitchServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Endpoints:
 *  /pitch/update/ - (PUT) update a pitch
 					-(GET) get a certain pitch to prepare for an update
 *  /pitch - (POST) create a pitch
 * 	/pitch/approve/ - (PUT) approve a pitch
 *	/pitch/reject (PUT) reject a pitch
 *	/pitch/getApproved (GET) get approved pitches
 *	/pitch/seniorEditor (GET) get senior editor pitches
 *	/pitch/onHold (GET) get pitches placed on hold
 *	/pitch/resubmit (PUT) resubmit a pitch that was on hold
*
 *
 */
public class PitchDelegate implements FrontControllerDelegate {
	private PitchService pitchServ = new PitchServiceImpl();
	private EditorService editorServ = new EditorServiceImpl();
	private AuthorService authorServ = new AuthorServiceImpl();
	private GenreService genreServ = new GenreServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	Logger log = Logger.getLogger(PitchDelegate.class);
	
	private static Set<PitchProxy> makeProxies(Set<Pitch> pitches){
		Set<PitchProxy> proxies = new HashSet<>();
		for(Pitch p :pitches) {
			PitchProxy pp = new PitchProxy();
			pp.setId(p.getId());
			pp.setAuthorInfo(p.getAuthorInfo());
			pp.setGenre(p.getGenre().getName());
			pp.setOnHold(p.isOnHold());
			pp.setStoryTitle(p.getStoryTitle());
			pp.setStoryType(p.getStoryType().getStoryType());
			pp.setTagLine(p.getTagLine());
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String date = df.format(p.getCompletionDate());
			pp.setCompletionDate(date);
			proxies.add(pp);
			
		}
		return proxies;
	}
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		
		if (path == null || path.equals("")) {
			if ("POST".equals(req.getMethod())) {
				//Pitch p = (Pitch) om.readValue(req.getInputStream(), Pitch.class);
				Pitch p = new Pitch();
				String authorInfo = req.getParameter("authorInfo");
				String storyTitle = req.getParameter("storyTitle");
				String completionDate = req.getParameter("completionDate");
				log.debug(completionDate);
				String storyType = req.getParameter("storyType");
				String genre = req.getParameter("genre");
				String tagLine = req.getParameter("tagLine");
				String description = req.getParameter("description");
				//Part attachments = req.getPart("attachments");
				java.util.Date utilDate = new java.util.Date();
				log.debug(utilDate);
				try {
					utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(completionDate);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				log.debug(sqlDate);
				
				StoryType st = pitchServ.getStoryType(storyType);
				Genre g = genreServ.getGenre(genre);
				
				p.setAuthorInfo(authorInfo);
				p.setStoryTitle(storyTitle);
				p.setCompletionDate(sqlDate);
				p.setStoryType(st);
				p.setGenre(g);
				p.setTagLine(tagLine);
				p.setDescription(description);
				//create Pitch checks the author's points and will
				//set this to true if their points aren't sufficient
				//but this cannot be null
				p.setOnHold(false);
				
//				InputStream is = null;
//				ByteArrayOutputStream os = null;
//
//				try {
//					is = attachments.getInputStream();
//					os = new ByteArrayOutputStream();
//
//					byte[] buffer = new byte[1024];
//
//					while (is.read(buffer) != -1) {
//						os.write(buffer);
//					}
//					
//					p.setAttachments(os.toByteArray());
//
//				} catch (IOException e) {
//					System.out.println("Could not upload file!");
//					e.printStackTrace();
//				} finally {
//					if (is != null)
//						is.close();
//					if (os != null)
//						os.close();
//				}
				
				Author a = (Author)req.getSession().getAttribute("user");
				p = pitchServ.createPitch(a, p);
				//Refresh author object that's saved in the session
				String username = a.getUsername();
				a = authorServ.getByUsername(username);
				req.getSession().setAttribute("user", a);
				String reply = "";
				if(p.isOnHold()) {
					reply = "Your pitch was set on hold.";
				}else {
					reply = "Your pitch was successfully created. It is waiting for review.";
				}
				//resp.getWriter().write(om.writeValueAsString(p));
				//resp.getWriter().write(om.writeValueAsString(a));
				resp.getWriter().write(reply);
				resp.setStatus(HttpServletResponse.SC_CREATED);
				
			} else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} 
		else if (path.contains("approve")) {
			if ("PUT".equals(req.getMethod())) {	
				String idString = req.getParameter("id");
				Integer id = Integer.parseInt(idString);
				Pitch p = pitchServ.getPitch(id);
				pitchServ.approvePitch(p);
				//Need to refresh editor object that is saved in the session
				Editor e = (Editor) req.getSession().getAttribute("user");
				String username = e.getUsername();
				e = editorServ.getByUsername(username);
				req.getSession().setAttribute("user", e);
				resp.getWriter().write(om.writeValueAsString(e));
				resp.getWriter().write(om.writeValueAsString(p));
				resp.setStatus(HttpServletResponse.SC_ACCEPTED);
				} else {
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
		}
		else if (path.contains("reject")) {
			if ("PUT".equals(req.getMethod())) {	
				String idString = req.getParameter("id");
				Integer id = Integer.parseInt(idString);
				Pitch p = pitchServ.getPitch(id);
				pitchServ.rejectPitch(p);
				//Need to refresh editor object that is saved in the session
				Editor e = (Editor) req.getSession().getAttribute("user");
				String username = e.getUsername();
				e = editorServ.getByUsername(username);
				req.getSession().setAttribute("user", e);
				resp.getWriter().write(om.writeValueAsString(e));
				resp.getWriter().write(om.writeValueAsString(p));
				resp.setStatus(HttpServletResponse.SC_ACCEPTED);
				} else {
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
		}
		else if (path.contains("getApproved")) {
			if("GET".equals(req.getMethod())){
				Author a = (Author)req.getSession().getAttribute("user");
				Set<Pitch> pitches = pitchServ.getApprovedPitches(a);
				resp.getWriter().write(om.writeValueAsString(pitches));
			}else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}
		else if (path.contains("onHold")) {
			if("GET".equals(req.getMethod())){
				Author a = (Author)req.getSession().getAttribute("user");
				Set<Pitch> pitches = pitchServ.getPitchesOnHold(a);
				resp.getWriter().write(om.writeValueAsString(pitches));
			}else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}
		else if (path.contains("resubmit")) {
			if("PUT".equals(req.getMethod())){
				Author a = (Author)req.getSession().getAttribute("user");
				String pitchId = req.getParameter("id");
				Integer id = Integer.parseInt(pitchId);
				Pitch p = pitchServ.getPitch(id);
				p = pitchServ.createPitch(a, p);
				//Refresh author object that's saved in the session
				String username = a.getUsername();
				a = authorServ.getByUsername(username);
				req.getSession().setAttribute("user", a);
				String reply = "";
				if(p.isOnHold()) {
					reply = "Your pitch was set on hold.";
				}else {
					reply = "Your pitch was successfully created. It is waiting for review.";
				}
				//resp.getWriter().write(om.writeValueAsString(p));
				//resp.getWriter().write(om.writeValueAsString(a));
				resp.getWriter().write(reply);
				resp.setStatus(HttpServletResponse.SC_CREATED);
			}else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}
		else if (path.contains("seniorEditor")) {
			if("GET".equals(req.getMethod())){
				Editor e = (Editor)req.getSession().getAttribute("user");
				Set<Pitch> pitches = pitchServ.getSeniorEditorPitches(e);
				
				Set<PitchProxy> proxies = makeProxies(pitches);
				
				
//				utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(completionDate);
//				sqlDate = new java.sql.Date(utilDate.getTime());
		 
				resp.getWriter().write(om.writeValueAsString(proxies));
			}else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}
		else if (path.contains("getAuthorPitches")) {
			if("GET".equals(req.getMethod())){
				Author a = (Author)req.getSession().getAttribute("user");
				Set<PitchProxy> proxies = makeProxies(a.getPitches());
				resp.getWriter().write(om.writeValueAsString(proxies));
			}else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}
		else if (path.contains("getEditorPitches")) {
			if("GET".equals(req.getMethod())){
				Editor e = (Editor)req.getSession().getAttribute("user");
				Set<PitchProxy> proxies = makeProxies(e.getPitches());
				resp.getWriter().write(om.writeValueAsString(proxies));
			}else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}
		else if (path.contains("update")) {
			if("PUT".equals(req.getMethod())){
				//any of these parameters can be null
				String pitchId = req.getParameter("id");
				Integer id = Integer.parseInt(pitchId);
				Pitch p = pitchServ.getPitch(id);
				
				String title = req.getParameter("title");
				String tagline = req.getParameter("tagline");
				String completionDate = req.getParameter("completionDate");
				if(title.equals("") == false) {
					p.setStoryTitle(title);
				}
				if(tagline.equals("") == false) {
					p.setTagLine(tagline);
				}
				java.util.Date utilDate;
				java.sql.Date sqlDate;
				if(completionDate.equals("") == false) {
					try {
						utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(completionDate);
						sqlDate = new java.sql.Date(utilDate.getTime());
						p.setCompletionDate(sqlDate);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				
				pitchServ.updatePitch(p);
				resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			}
			else if("GET".equals(req.getMethod())){
				String pitchId = req.getParameter("id");
				Integer id = Integer.parseInt(pitchId);
				Pitch p = pitchServ.getPitch(id);
				resp.getWriter().write(om.writeValueAsString(p));
			}
			else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}
		
	}
}

