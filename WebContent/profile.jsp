<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.project.models.User" %>
    <%@page import="com.project.models.Post" %>
    
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
	    	 <input type="button" value="Edit Post" onclick="showEditBox(<%=items.getPostId()%>)"><br>
	    	 <form action="DeletePost" method="post"><input type="hidden" name="postid" value="<%= items.getPostId() %>"><input type="submit" value="Delete Post"></form><br>
	    	 
	    	 <form action="EditPost" method="post">
		    	 <div class="editPost" id="edit<%=items.getPostId()%>">
		    	 
		    	 	<textarea rows="4" cols="40" name="newpostcontent"><% out.println("Content:"+items.getContent()+"\n"); %></textarea>
		    	 	
		    	 	<input type="hidden" name="postid" value="<%= items.getPostId() %>">
		    	 	
		    	 	<input type="submit" value="Save Post" onclick="hideEditBox(<%=items.getPostId()%>)">
		    	 
		    	 </div>
	    	 </form>
	    	 
	    	 
	    	 
	    	    <br>
	</div>  <br>	<br> <%   } 
		 
 %>

<br />
     
      
    <script>
    
    function showEditBox(id)
    {
        document.getElementById('edit'+ id ).style.display = "block";
    }
    function hideEditBox(a){
        document.querySelector('#edit'+ id ).style.display = "none";

    }
    </script>
      
  


</body>
</html>