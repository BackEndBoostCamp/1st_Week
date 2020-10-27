package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guestbooks")
public class GuestbookListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset=\"UTF-8\"><title>방명록</title></head>");
		out.println("<body>");
		
		try {
			
			GuestbookDao dao = new GuestbookDao();
			List<Guestbook> list = dao.getGuestbooks();
			
			for (int i = 0; i < list.size(); i++) {
				out.println("id : " + list.get(i).getId() + "<br>");
				out.println("name : " + list.get(i).getName() + "<br><br>");
				out.println(list.get(i).getContent() + "<br><br>");
				out.println("regdate : " + list.get(i).getRegdate() + "<br>");
				out.println("<hr>");
			}
		} catch (NullPointerException e) { 
			
		}
		out.println("<form method=\"post\" action=\"guestbooks/write\">");
		out.println("이름 : <input type=\"text\" name=\"name\"><br>");
		out.println("내용 : <textarea name=\"content\" cols=\"50\" rows=\"5\"></textarea><br>");
		out.println("<input type=\"submit\" value=\"확인\">");
		out.println("</form>");
		out.println("<form method=\"post\" action=\"guestbooks/delete\">");
		out.println("삭제 id : <input type=\"text\" name=\"Delete_id\"><br>");
		out.println("<input type=\"submit\" value=\"삭제\">");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
}
