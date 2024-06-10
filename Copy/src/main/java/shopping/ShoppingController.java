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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path = request.getContextPath();
	 	String sw = request.getParameter("sw");
	 	
	 	ShoppingService service = new ShoppingServiceImpl();
	 	
		if (sw.equals("I")) {
			
			response.sendRedirect(path+"/shopping/member.jsp");
		}else if(sw.equals("OK")) {
			int custno = Integer.parseInt(request.getParameter("custno"))  ;
			String custname = request.getParameter("custname");
			String phone = request.getParameter("phone");
			String address = request.getParameter("roadAddress") + " " +request.getParameter("detailAddress") ;
			String joindate = request.getParameter("joindate");
			String grade = request.getParameter("grade");
			String city = request.getParameter("city");
			
			MemberVO vo 
			= new MemberVO(custno, custname, phone, address,joindate,grade , city );
						
			service.MemberInsert(vo);
			System.out.println("vo확인:" + vo);
			
			response.sendRedirect(path+"/ShoppingController=L");
		}else if(sw.equals("L")) {
						
			request.setAttribute("li", service.getMemberSelect(null));
			
			RequestDispatcher	dispatcher=request.getRequestDispatcher("/shopping/list.jsp");
			dispatcher.forward(request, response);
		}else if(sw.equals("M")) {
			request.setAttribute("li", service.getMoney());
			RequestDispatcher	dispatcher=request.getRequestDispatcher("/shopping/money.jsp");
			dispatcher.forward(request, response);		
		}else if(sw.equals("E")) {
			int custno = Integer.parseInt(request.getParameter("custno"))  ;
						
			request.setAttribute("m", service.getMember(custno));
			RequestDispatcher	dispatcher=request.getRequestDispatcher("/shopping/edit.jsp");
			dispatcher.forward(request, response);	
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
