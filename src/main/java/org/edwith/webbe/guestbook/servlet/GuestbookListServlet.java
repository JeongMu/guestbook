package org.edwith.webbe.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

@WebServlet("/guestbooks")
public class GuestbookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		GuestbookDao guestbookDao = new GuestbookDao();
        List<Guestbook> list = guestbookDao.getGuestbooks();
        request.setAttribute("list", list);
		
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/guestbook/guestbooks.jsp");
		rd.include(request, response);
		
		
    }

}
