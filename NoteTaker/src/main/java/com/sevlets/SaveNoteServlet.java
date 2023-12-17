package com.sevlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.entities.Note;
import com.helper.FactoryProvider;


public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SaveNoteServlet() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//To fetch the title and content data
			
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			//passing the data into Note object
			Note note=new Note(title,content,new Date());
			
			
			//To save in DB using hibernate confguration
			
			Session session=FactoryProvider.getFactory().openSession();
			Transaction tx= session.beginTransaction();
			session.save(note);
			tx.commit();
			
			//To get response on the server page
			
			response.setContentType("text/HTML");
			PrintWriter pt=response.getWriter();
			pt.println("<h1 style='text-align:center'>Note is added Successfully</h1>");
			pt.println("<h1 style='text-align:center'><a href='all_notes.jsp'>View All notes </a></h1>");

			session.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
