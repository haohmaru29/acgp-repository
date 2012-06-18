<%
	request.getSession(false).invalidate();
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
	dispatcher.forward(request,response);
%>