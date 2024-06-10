package kakao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shopping.ShoppingService;
import shopping.ShoppingServiceImpl;

import java.io.IOException;

/**
 * Servlet implementation class MapController
 */
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapController() {
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
		 ShoppingService service = new ShoppingServiceImpl();
		if (sw.equals("map1")) {
			
			request.setAttribute("li", service.getMemberSelect(null));
			
			RequestDispatcher	dispatcher=request.getRequestDispatcher("/kakaoMap/map1.jsp");
			dispatcher.forward(request, response);
			
		} else if (sw.equals("map2")) {
			request.setAttribute("li", service.getMemberSelect(null));
			
			RequestDispatcher	dispatcher=request.getRequestDispatcher("/kakaoMap/map2.jsp");
			dispatcher.forward(request, response);	
		} else if (sw.equals("login")) {

			response.sendRedirect(path+"/kakaoMap/login.jsp");
		
		} else if (sw.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(path+"/kakaoMap/login.jsp");
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
