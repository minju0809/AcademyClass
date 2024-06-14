package cart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CartController
 */
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String path = request.getContextPath();
		String sw = request.getParameter("sw");

		String mid = (String) request.getSession().getAttribute("mid");
		String pid = request.getParameter("pid");
		String amount = request.getParameter("amount");

		CartService service = new CartServiceImpl();
		CartVO cvo = new CartVO();

		cvo.setMid(mid);
		cvo.setPid(pid);

		if (sw.equals("I")) {
			cvo.setAmount(Integer.parseInt(amount));
			service.insert(cvo);
			response.sendRedirect(path + "/ProductController?sw=S");
		} else if (sw.equals("D")) {

			cvo.setMid(mid);
			service.delete(cvo);
			response.sendRedirect(path + "/CartController?sw=S");

		} else if (sw.equals("S")) {
			cvo.setMid(mid);

			request.setAttribute("li", service.cartSelect(cvo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cart/cart_list.jsp");
			dispatcher.forward(request, response);
		} else if (sw.equals("U")) {

			String[] cart_id = request.getParameterValues("cart_id");
			String[] amounts = request.getParameterValues("amount");

			for (int i = 0; i < cart_id.length; i++) {
				System.out.println(cart_id[i] + ":" + amounts[i]);
				cvo.setCart_id(cart_id[i]);
				cvo.setAmount(Integer.parseInt(amounts[i]));
				service.update(cvo);
			}
			response.sendRedirect(path + "/CartController?sw=S");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
