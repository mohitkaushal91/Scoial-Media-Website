<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.project.models.User" %>
    
    <%--  <%ArrayList useremployees=(ArrayList)request.getAttribute("useremployees"); %>--%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
hello profile

<br />
 <%
// String fied=request.getParameter("fied");
 ArrayList<User> list = (ArrayList<User>) request.getAttribute("fied");
 for(User item : list) 
 {
	 out.println("name:"+item.getName()+"\n");  
	 out.println("id:"+item.getEmail()+"\n");
	 //out.println("post:"+item.getPost()+"\n");
	
	/*  out.println("email:"+item.getEmail()+"\n"); */
	
 }

 %>
     
     
     <table>
     <td>
      <tr>
    
      </tr>  
     
     </td>
     
     </table>
     
      
    
      
  


</body>
</html>