package com.SPMS.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SPMS.beans.Author;
import com.SPMS.exceptions.NonUniqueUsernameException;
import com.SPMS.services.AuthorService;
import com.SPMS.services.AuthorServiceImpl;
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
public class AuthorLoginDelegate implements FrontControllerDelegate {
	private AuthorService authorServ = 
			new AuthorServiceImpl();
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
				Author a = (Author)req.getSession().getAttribute("user");
				//System.out.println(a);
				resp.getWriter().write(om.writeValueAsString(a));
				} else {
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
		}
		else if (path.contains("register")) {
			// register a user
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Author a = new Author();
			a.setUsername(username);
			a.setPassword(password);
			try {
				authorServ.create(a);
			} catch (NonUniqueUsernameException e) {
				resp.sendError(HttpServletResponse.SC_CONFLICT, "Username already exists");
			}
			if (a.getId() == 0) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			} else {
				resp.getWriter().write(om.writeValueAsString(a));
				resp.setStatus(HttpServletResponse.SC_CREATED);
			}
		}
	}
	
	private void logIn(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Author a = authorServ.getByUsername(username);
		if (a != null) {
			if (a.getPassword().equals(password)) {
				req.getSession().setAttribute("user", a);
				resp.getWriter().write(om.writeValueAsString(a));
				//req.getRequestDispatcher("AuthorDashboard.html").forward(req, resp);
			} else {
				resp.sendError(400, "Incorrect password.");
			}
		} else {
			resp.sendError(404, "No user found with that username.");
		}
	}
	

}
