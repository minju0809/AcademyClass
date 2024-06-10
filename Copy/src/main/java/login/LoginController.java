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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String  sw = request.getParameter("sw");
		String path = request.getContextPath();
		
		HttpSession session = request.getSession();
		
		if(sw.equals("F")) {
			response.sendRedirect(path + "/login/form.jsp");
		} else if(sw.equals("success")) {
			response.sendRedirect(path + "/login/success.jsp");
		}if(sw.equals("logout")) {
			
			// session.invalidate();
			session.setAttribute("id", "");
			response.sendRedirect(path + "/index.jsp");
				
		} else if(sw.equals("login")) {
			String  id = request.getParameter("id");
			String  password = request.getParameter("password");
			LoginService service = new LoginServiceImpl();
			LoginVO  vo = new LoginVO();
			vo.setId(id);
			vo.setPassword(password);			
			
			id = service.login(vo);
			if ( !id.equals("")) {
			    System.out.println("===> 로그인 성공:"+id);
			    
			    LoginHashMap   P = new LoginHashMapImpl();
			    Map<String, Object> str = P.login();

			    if (str.containsKey("code")) {
				    Object  code = str.get("code");
				    System.out.println("code:" + code);			    
				    Object  msg = str.get("msg");
				    System.out.println("msg:" + msg);
				    Object  method = str.get("method");
				    System.out.println("msg:" + method);
			    } else {
			    	 System.out.println("접속실패");
			    }
			    			    
			    session.setAttribute("id", id);
			}else {
				System.out.println("===> 로그인 실패"); 
			}
			
			System.out.println("session:" + session);
			
			PrintWriter out = response.getWriter();
			out.print(id);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
