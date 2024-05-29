package kakao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		
		if(sw.equals("map1")) {
			request.setAttribute("li", service.getSelect(null));
			
			RequestDispatcher rd = request.getRequestDispatcher("/kakao/map1.jsp");
			rd.forward(request, response);
		} else if(sw.equals("map2")) {
			request.setAttribute("li", service.getSelect(null));
			
			RequestDispatcher rd = request.getRequestDispatcher("/kakao/map2.jsp");
			rd.forward(request, response);
		} else if(sw.equals("map3")) {
			int custno = Integer.parseInt(request.getParameter("custno"));
			request.setAttribute("m", service.getSelectOne(custno));
			
			RequestDispatcher rd = request.getRequestDispatcher("/kakao/map3.jsp");
			rd.forward(request, response);
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
