package pkg;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PwdController
 */
public class PwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path = request.getContextPath();
		String sw = request.getParameter("sw");
		
		UsersService service = new UsersServiceImpl();
		
		if(sw.equals("H")) {
			
			response.sendRedirect(path + "/index.jsp");
		} else if(sw.equals("F")) {
			
			response.sendRedirect(path + "/form.jsp");
		} else if(sw.equals("I")) {
			String userid = request.getParameter("userid");
			String username = request.getParameter("username");
			String userpassword = request.getParameter("userpassword");
			int userage = Integer.parseInt(request.getParameter("userage"));
			
			UsersVO m = new UsersVO();
			m.setUserid(userid);
			m.setUsername(username);
			m.setUserpassword(userpassword);
			m.setUserage(userage);
			
			service.insert(m);
			
			response.sendRedirect(path + "/index.jsp");
		} else if(sw.equals("S")) {
			request.setAttribute("li", service.getSelect());
			
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
		} else if(sw.equals("In")) {
			
			response.sendRedirect(path + "/login.jsp");
		} else if(sw.equals("Out")) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
