package com.SPMS.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SPMS.beans.Author;
import com.SPMS.beans.Editor;
import com.SPMS.services.EditorService;
import com.SPMS.services.EditorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Endpoints:
 *  /user/login - (POST) log in a user
 *  			- (DELETE) log out a user
 *  /user - (POST) register a user
 * 	/user/:id - (GET) get user by id
 *            - (PUT) update a user
 *            - (DELETE) deletes a user
 */
public class EditorLoginDelegate implements FrontControllerDelegate {
	private EditorService editorServ = 
			new EditorServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		
		if (path == null || path.equals("")) {
			if ("POST".equals(req.getMethod())) {
				
			} else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} else if (path.contains("login")) {
			if ("POST".equals(req.getMethod()))
				logIn(req, resp);
			else if ("DELETE".equals(req.getMethod()))
				req.getSession().invalidate();
			else
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		else if (path.contains("getuser")) {
			if ("GET".equals(req.getMethod())) {	
				Editor e = (Editor)req.getSession().getAttribute("user");
				//System.out.println(a);
				resp.getWriter().write(om.writeValueAsString(e));
				} else {
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
		}
	}
	
	private void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Editor e = editorServ.getByUsername(username);
		if (e != null) {
			if (e.getPassword().equals(password)) {
				req.getSession().setAttribute("user", e);
				resp.getWriter().write(om.writeValueAsString(e));
			} else {
				resp.sendError(400, "Incorrect password.");
			}
		} else {
			resp.sendError(404, "No user found with that username.");
		}
	}

}
