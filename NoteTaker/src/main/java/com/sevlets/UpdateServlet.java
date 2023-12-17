package com.sevlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;


public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//To fetch the data when we click on update
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			//To fetch the above one by using id
			int noteId=Integer.parseInt(request.getParameter("noteId").trim());
			
			//To work with hibernate
			Session s=FactoryProvider.getFactory().openSession();
			Transaction tx=s.beginTransaction();
			
			//To fetch the data from DB using id
		    Note note=s.get(Note.class, noteId);
		    
		    //After upadting in server page we need to set in DB so that it can retrive
		    note.setTitle(title);
		    note.setContent(content);
		    note.setAddedDate(new Date());
			
			tx.commit();
			s.close();
			
			//To get respnse like redirecting
			response.sendRedirect("all_notes.jsp");
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	

}
