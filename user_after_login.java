package com.cybage.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybage.bean.Event;
import com.cybage.dao.EventDao;

/**
 * Servlet implementation class user_after_login
 */
@WebServlet("/user_after_login")
public class user_after_login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private EventDao eventDAO;
	
	public void init() {
		eventDAO = new EventDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choice = request.getParameter("choice");
		ServletContext context = getServletContext();
		if(choice.equals("event")) {
			
			doGet(request, response);
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().println("hi get");
		
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertEvent(request, response);
				break;
			case "/delete":
				deleteEvent(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateEvent(request, response);
				break;
			default:
				listEvent(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listEvent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Event> listEvent = eventDAO.selectAllEvents();
		request.setAttribute("listUser", listEvent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("event-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("event-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int event_id = Integer.parseInt(request.getParameter("event_id"));
		Event existingEvent = eventDAO.selectUser(event_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Event-form.jsp");
		request.setAttribute("event", existingEvent);
		dispatcher.forward(request, response);
	}

	private void insertEvent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String venue = request.getParameter("venue");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		Event newEvent = new Event(name, venue, price, category);
		eventDAO.insertEvent(newEvent);
		response.sendRedirect("list");
	}

	private void updateEvent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int event_id = Integer.parseInt(request.getParameter("event_id"));
		String name = request.getParameter("name");
		String venue = request.getParameter("venue");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));

		Event book = new Event(event_id, name, venue, price, category);
		eventDAO.updateEvent(book);
		response.sendRedirect("list");
	}

	private void deleteEvent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int event_id = Integer.parseInt(request.getParameter("event_id"));
		eventDAO.deleteEvent(event_id);
		response.sendRedirect("list");

	}
		
	}