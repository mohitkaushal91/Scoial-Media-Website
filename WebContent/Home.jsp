<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    
    <%@ page import="com.project.models.*" %>
    <%@page import="java.util.ArrayList" %>
    
    <%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <form action="Addfriend" method="post">
  <input type="text" name="searchfriend" placeholder="searchfriend">
  <input type="submit" name="displayfriendprofile" value="showfriendprofile">
  </form>

  <form action="CreatePost" method="post">
  <div>
  <label for="post" > WHAT'S ON YOUR MIND </label>   <br>
   
  <input type="text" for="PostOperations" name="createpost">
  </div>
  
  <input type="submit"  value="Post anything" for="homepost"><br>
 
  </form>
  
  <form action="PostOperations" method="post">
  <input type="submit"  value="Personal Profile" name="profile">
  </form>
  
   <%
         User temp = (User)session.getAttribute("user");
		  ArrayList<Post> postitem = temp.getPosts();
		 
		  
		 for(Post item : postitem)
		 {
			 %><div>  
			 <% 	out.println("Email:"+item.getEmail()+"\n");       %>       <br>
		     <%     out.println("Content:"+item.getContent()+"\n");   %>   <br> 		
	    	 <% 	out.println("Date:"+item.getDate()+"\n");         %>   <br>
	</div>  <br>	<br> <%   } 
		 
		 //User temp = (User) session.getAttribute("user");
 %>
 
 
 <div>   
 
 
 </div>
 

 <!--   
 <tag:forEach var="post" items="${temp.getPosts()}">
 <div>
 <h1>${post.getContent()}}</h1>
 	<h1>Helo</h1>
 </div>
  </tag:forEach> -->
</body>
</html>