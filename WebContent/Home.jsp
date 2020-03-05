<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  <form action="CreatePost" method="post">
 
  
  <div>
  
  <label for="post" > WHATS ON YOUR MIND </label>   <br>
       <%  
       session.getAttribute("name");
		  System.out.println(session.getAttribute("name"));
       %>
       
       
  <input type="text" for="createpost" name="createpost">

  </div>
   <div id="bottomnav">
  <input type="submit"  value="Home" for="home">
  </div>
  
  </form>
  
</body>
</html>