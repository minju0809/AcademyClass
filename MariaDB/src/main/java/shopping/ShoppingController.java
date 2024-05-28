package shopping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ShoppingController
 */
public class ShoppingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingController() {
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
		
		if(sw.equals("F")) {
			
			response.sendRedirect(path + "/shopping/member.jsp");
		} else if(sw.equals("I")) {
			int custno = Integer.parseInt(request.getParameter("custno"));
			String custname = request.getParameter("custname");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String detailAddress = request.getParameter("detailAddress");
			String joindate = request.getParameter("joindate");
			String grade = request.getParameter("grade");
			String city = request.getParameter("city");
			
			MemberVO vo = new MemberVO(custno, custname, phone, address, joindate, grade, city);
			vo.setCustno(custno);
			vo.setCustname(custname);
			vo.setPhone(phone);
			vo.setAddress(address + " " + detailAddress);
			vo.setJoindate(joindate);
			vo.setGrade(grade);
			vo.setCity(city);
			
			service.insert(vo);
			
			System.out.println("vo: "+vo);
			
			response.sendRedirect(path + "/ShoppingController?sw=L");
		} else if(sw.equals("L")) {
			request.setAttribute("li", service.getSelect(null));
			
			RequestDispatcher rd = request.getRequestDispatcher("/shopping/list.jsp");
			rd.forward(request, response);
		} else if(sw.equals("E")) {
			int custno = Integer.parseInt(request.getParameter("custno"));
			request.setAttribute("m", service.getSelectOne(custno));
			
			RequestDispatcher rd = request.getRequestDispatcher("/shopping/edit.jsp");
			rd.forward(request, response);
		} else if(sw.equals("M")) {
			request.setAttribute("li", service.getMoneySelect());
			
			RequestDispatcher rd = request.getRequestDispatcher("/shopping/money.jsp");
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
