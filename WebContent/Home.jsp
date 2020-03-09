<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
 
  
</body>
</html>