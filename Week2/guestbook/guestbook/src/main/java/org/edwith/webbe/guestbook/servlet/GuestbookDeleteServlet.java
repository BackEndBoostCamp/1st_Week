package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/guestbooks/delete")
public class GuestbookDeleteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
    	
    	GuestbookDao dao = new GuestbookDao();
    	String id = request.getParameter("Delete_id");
    	java.util.List<Guestbook> list = dao.getGuestbooks();
    	dao.DeleteGuestbook(Long.parseLong(id));
    	for(int i = Integer.parseInt(id) + 1; i <= list.size(); i++) {
    		dao.SortIDGuestbook((long) i);
    	}
    	
    	response.sendRedirect("http://localhost:8080/guestbook/guestbooks");
    }
}
