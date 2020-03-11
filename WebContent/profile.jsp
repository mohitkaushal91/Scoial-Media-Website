<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.project.models.User" %>
    <%@page import="com.project.models.Post" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
hello <%

User temp1 = (User)session.getAttribute("user");

out.println(temp1.getName());

%>

 <%
         User usertemp = (User)session.getAttribute("user");
		  ArrayList<Post> userpostitem = usertemp.getUserPosts();
		 
		  
		 for(Post items : userpostitem)
		 {
			 %><div>  
			 <% 	out.println("Email:"+items.getEmail()+"\n");       %>       <br>
		     <%     out.println("Content:"+items.getContent()+"\n");   %>   <br> 		
	    	 <% 	out.println("Date:"+items.getDate()+"\n");         %>   <br>
	</div>  <br>	<br> <%   } 
		 
		 //User temp = (User) session.getAttribute("user");
 %>

<br />

     
     
     <table>
     <td>
      <tr>
    
      </tr>  
     
     </td>
     
     </table>
     
      
    
      
  


</body>
</html>