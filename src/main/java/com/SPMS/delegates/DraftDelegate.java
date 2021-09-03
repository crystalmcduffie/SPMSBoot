package com.SPMS.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Set;

import com.SPMS.beans.Author;
import com.SPMS.beans.Draft;
import com.SPMS.beans.Editor;
import com.SPMS.beans.Pitch;
import com.SPMS.beans.relationaltables.PitchProxy;
import com.SPMS.services.AuthorService;
import com.SPMS.services.AuthorServiceImpl;
import com.SPMS.services.DraftService;
import com.SPMS.services.DraftServiceImpl;
import com.SPMS.services.EditorService;
import com.SPMS.services.EditorServiceImpl;
import com.SPMS.services.GenreService;
import com.SPMS.services.GenreServiceImpl;
import com.SPMS.services.PitchService;
import com.SPMS.services.PitchServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Endpoints:
 *  /draft/update/ - (PUT) update a draft
 *  			-(GET) get a certain draft to prepare for an update
 *  /draft - (POST) submit a draft
 *  	-(GET) get drafts that the editor hasn't approved
 * 	/draft/approve/ - (PUT) approve a draft
 *
 */
public class DraftDelegate implements FrontControllerDelegate {
	private DraftService draftServ = new DraftServiceImpl();
	private EditorService editorServ = new EditorServiceImpl();
	private PitchService pitchServ = new PitchServiceImpl();
	private AuthorService authorServ = new AuthorServiceImpl();
	private GenreService genreServ = new GenreServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		
		if (path == null || path.equals("")) {
			if ("POST".equals(req.getMethod())) {
				//Draft d = (Draft) om.readValue(req.getInputStream(), Draft.class);
				String pitchId = req.getParameter("pitchId");
				Integer id = Integer.parseInt(pitchId);
				Pitch p = pitchServ.getPitch(id);
				String draft = req.getParameter("draft");
				Draft d = new Draft();
				d.setDraft(draft);
				d.setGenre(p.getGenre());
				d.setStoryType(p.getStoryType());
				Author a = (Author)req.getSession().getAttribute("user");
				draftServ.submitDraft(a, d, p);
				//Refresh author object that is currently saved in the session
				String username = a.getUsername();
				a = authorServ.getByUsername(username);
				req.getSession().setAttribute("user", a);
				resp.getWriter().write(om.writeValueAsString(a));
				resp.getWriter().write(om.writeValueAsString(d));
				resp.setStatus(HttpServletResponse.SC_CREATED);
				
			} 
			else if("GET".equals(req.getMethod())) {
				Editor e = (Editor)req.getSession().getAttribute("user");
				resp.getWriter().write(
						om.writeValueAsString(draftServ.getPendingDraftsForEditor(e)));
			}
			else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} 
		else if (path.contains("approve")) {
			if ("PUT".equals(req.getMethod())) {	
				String idString = req.getParameter("id");
				Integer id = Integer.parseInt(idString);
				Draft d = draftServ.getDraft(id);
				Editor e = (Editor)req.getSession().getAttribute("user");
				draftServ.approveDraft(e, d);
				//Refresh editor object that is currently saved in the session
				String username = e.getUsername();
				e = editorServ.getByUsername(username);
				req.getSession().setAttribute("user", e);
				resp.getWriter().write(om.writeValueAsString(e));
				resp.getWriter().write(om.writeValueAsString(d));
				resp.setStatus(HttpServletResponse.SC_ACCEPTED);
				} else {
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
		}
		else if (path.contains("getDraftsInGenre")) {
			if("GET".equals(req.getMethod())){
				Editor e = (Editor)req.getSession().getAttribute("user");
				Set<Draft> relevantDrafts = genreServ.getDraftsInGenre(e);
				resp.getWriter().write(om.writeValueAsString(relevantDrafts));
			}else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}
		else if (path.contains("update")) {
			if("GET".equals(req.getMethod())){
				String idString = req.getParameter("id");
				Integer id = Integer.parseInt(idString);
				Draft d = draftServ.getDraft(id);
				resp.getWriter().write(om.writeValueAsString(d));
			}
			else if ("PUT".equals(req.getMethod())) {	
				Draft d = (Draft) om.readValue(req.getInputStream(), Draft.class);
				draftServ.updateDraft(d);
				resp.getWriter().write(om.writeValueAsString(d));
				resp.setStatus(HttpServletResponse.SC_ACCEPTED);
				} else {
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
		}
	}

}
