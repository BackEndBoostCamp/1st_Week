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

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
    	
    	String name = request.getParameter("name");
    	String content = request.getParameter("content");
    	
    	Date regdate = new Date();
    
    	GuestbookDao dao = new GuestbookDao();
    	java.util.List<Guestbook> list = dao.getGuestbooks();
    	long id = list.size() + 1;
    
    	Guestbook book = new Guestbook(id, name, content, regdate);
    	
    	dao.addGuestbook(book);
    	
    	response.sendRedirect("http://localhost:8080/guestbook/guestbooks");
    }
}
