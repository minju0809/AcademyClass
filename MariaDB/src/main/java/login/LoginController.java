package login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		
		HttpSession session = request.getSession();
		
		LoginService service = new LoginServiceImpl();
		
		if(sw.equals("F")) {
			
			response.sendRedirect(path + "/login/form.jsp");
		} else if(sw.equals("L")) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			System.out.println("id: " + id + ", pw: " + password);
			
			LoginVO vo = new LoginVO();
			vo.setId(id);
			vo.setPassword(password);

			String m = service.login(vo);
			if(!m.equals("")) {
				System.out.println("로그인 성공." + m);
				
				LoginHashMap map = new LoginHashMapImpl();
				Map<String, String> str = map.login();
				String pwd = str.get("ppk");
				System.out.println("pwd: " + pwd);
				
				session.setAttribute("id", m);
			} else {
				System.out.println("로그인 실패" + m);
			}
			
			PrintWriter out = response.getWriter();
			out.print(m);
		} else if(sw.equals("success")) {
			
			response.sendRedirect(path + "/login/success.jsp");
		} else if(sw.equals("logout"))  {
			// session.setAttribute("login", null);
			// session.removeAttribute("login");
			session.invalidate();
			response.sendRedirect(path+"/index.jsp");
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
