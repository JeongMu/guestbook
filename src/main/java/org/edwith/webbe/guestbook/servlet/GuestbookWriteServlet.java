package org.edwith.webbe.guestbook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name = new String(request.getParameter("name").getBytes("8859_1"), "UTF-8");
    	String content = new String(request.getParameter("content").getBytes("8859_1"), "UTF-8");

    	Guestbook guestbook = new Guestbook(name, content);
    	
        GuestbookDao guestbookDao = new GuestbookDao();
        guestbookDao.addGuestbook(guestbook);
        
        response.sendRedirect("http://localhost:8080/guestbook/guestbooks");
    }

}
