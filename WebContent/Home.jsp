<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    
    <%@ page import="com.project.models.*" %>
    <%@page import="java.util.ArrayList" %>
    
    <%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <% 
    if(session.getAttribute("user") == null)
    {
    	response.sendRedirect("Login.jsp");
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
/*.unlike
{
display:none;
}*/
</style>
<body>

 <form action="Addfriend" method="post">
  <input type="text" name="searchfriend" placeholder="searchfriend">
  <input type="submit" name="displayfriendprofile" value="showfriendprofile">
  </form>

  <form action="CreatePost" method="post">
  <div>
  <label for="post" > WHAT'S ON YOUR MIND </label>   <br>
   
  <input type="text" name="createpost">
  </div>
  
  <input type="submit" value="Post anything" for="homepost"><br>
 
  </form>
  
  <form action="PostOperations" method="post">
  <input type="submit"  value="Personal Profile" name="profile">
  </form>
  
  <form action="Logout" method="post">
  <input type="submit"  value="Logout" name="logout">
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
	    	 <% 	out.println("Date:"+item.getDate()+"\n");         %>   <br>
	    	 <% 	out.println("Likes:"+item.getLikes()+"\n");         %>   <br>
	    	 <form action="LikePost" method="post">
	    	 	<input type="hidden" value="<%= item.getPostId() %>" name="postid">
	    	 	<% 
	    	 	if(session.getAttribute("likedpost"+item.getPostId()) == null)
	    	 	{
	    	 		%>
	    	 		<input type="submit" value="like" name="likeaction" id="like<%= item.getPostId() %>" onclick="hidelike(<%= item.getPostId() %>)">
	    	 	<% }
	    	 	else if(session.getAttribute("likedpost"+item.getPostId()) != null){
	    	 	if(session.getAttribute("likedpost"+item.getPostId()).equals("unlike"))
	    	 			{
	    	 			%>
	    	 	<input type="submit" value="like" name="likeaction" id="like<%= item.getPostId() %>" onclick="hidelike(<%= item.getPostId() %>)">
	    	 	<%}
	    	 	else
	    	 	{%>
	    	 	<input type="submit" class="unlike" name="likeaction" value="unlike" id="unlike<%= item.getPostId() %>" onclick="hideunlike(<%= item.getPostId() %>)">
	    	 	<%}} %>
	    	 </form>
		</div>  <br>	<br> <%   } 
 	%>
 
 <div>   
 </div>
 
 <script>
 /*
 function hidelike(id)
 {
	 document.getElementById('like'+ id ).style.display = "none";
	 document.getElementById('unlike'+ id ).style.display = "block";
 }
 
 function hideunlike(id)
 {
	 document.getElementById('unlike'+ id ).style.display = "none";
	 document.getElementById('like'+ id ).style.display = "block";
 }
 */
 </script>
</body>
</html>