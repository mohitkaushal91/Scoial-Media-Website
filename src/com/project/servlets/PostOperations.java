package com.project.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.project.db.PostDBUtil;
import com.project.db.UserDBUtil;
import com.project.models.User;

/**
 * Servlet implementation class PostOperations
 */
@WebServlet("/PostOperations")
public class PostOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostOperations() {
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
		postdb  = new PostDBUtil(datasource);
		
	}
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
		User user = (User)session.getAttribute("user");
//		user.GetUserPost(userdb);
		user.GetUserPost(postdb);
		
		System.out.println("user get all posts");
		session.setAttribute("user", user);	
		
		
		response.sendRedirect("profile.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
