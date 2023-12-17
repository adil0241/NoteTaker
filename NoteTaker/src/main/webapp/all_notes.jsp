<%@page import="com.entities.Note"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="com.helper.FactoryProvider"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Notes</title>
<%@include file="All_js_css.jsp"%>

</head>
<body>
	<div class="container">
		<%@include file="navbar.jsp"%>
		<br>
		<h1 class="text-uppercase">All Notes:</h1>

		<div class="row">
			<div class="col-12">
				<!--scripted tag used to fetch the data from DB to server page-->
				<%
				Session s = FactoryProvider.getFactory().openSession();
				Query q = s.createQuery("from Note");
				List<Note> list = q.list();
				for (Note note : list) {
				%>
				
				<!-- To make a card.copy from bootstrap -->
				
				<div class="card mt-3">
					<img class="card-img-top mx-auto"style="width:2.5cm; height:2.5cm" src="img/notepad.png" alt="Card image cap">
					<div class="card-body px-5">
						<h5 class="card-title"><%=note.getTitle() %></h5>
						<p class="card-text"><%=note.getContent() %></p>
						<p><b class="text-primary"><%= note.getAddedDate() %></b></p>
						
						<div class="conatiner text-center mt-2">
						<a href="DeleteServlet?note_id=<%= note.getId() %>" class="btn btn-danger" >Delete</a>
						<a href="edit.jsp?note_id=<%= note.getId()%>" class="btn btn-primary">Update</a>
						</div>
					</div>
				</div>
				<%

				}
				s.close();
				%>

			</div>

		</div>





	</div>

</body>
</html>