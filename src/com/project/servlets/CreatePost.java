package com.project.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.project.db.PostDBUtil;
import com.project.models.User;

/**
 * Servlet implementation class CreatePost
 */
@WebServlet("/CreatePost")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePost() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    @Resource(name="jdbc/project")
    private DataSource datasource;
    private PostDBUtil postdb;
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		postdb = new PostDBUtil(datasource);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String postdetails=request.getParameter("createpost");
		HttpSession session=request.getSession(); 
		int userid = (Integer) session.getAttribute("userID");
		String email = (String) session.getAttribute("email");
		
		System.out.println("ttttt"+userid);
		System.out.println("ttttt"+email);
		
		User userpost=new User(userid, email,postdetails);
		
		userpost.post(postdb);
		
	/*ArrayList<User>  outputpost=userpost.DisplayDatabasePosts(postdb);
		
		
//		ArrayList<PostDBUtil> =new ArrayList<>();
		//list.add(postdb);	
	System.out.println("ddddd");
		System.out.println(outputpost);
		System.out.println("han ethe hai");
		

		
		request.setAttribute("fied",outputpost);   */
	

		ServletContext context= getServletContext();
		RequestDispatcher rd= context.getRequestDispatcher("/profile.jsp");
		rd.forward(request, response);
		
		
		//response.sendRedirect("profile.jsp?fied="+outputpost); working
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
