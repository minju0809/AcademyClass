package majustory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class MajustoryController
 */
public class MajustoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MajustoryController() {
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
		
		MajustoryService service = new MajustoryServiceImpl();
		
		if(sw.equals("F")) {
			
			response.sendRedirect(path + "/member/form.jsp");
		} else if(sw.equals("I")) {
			
			String mid = request.getParameter("mid");
			String mpassword1 = request.getParameter("mpassword1");
			String mpassword2 = request.getParameter("mpassword2");
			String mphone = request.getParameter("mphone");
			String maddr1 = request.getParameter("maddr1");
			String maddr2 = request.getParameter("maddr2");
			String maddr3 = request.getParameter("maddr3");
			String mname = request.getParameter("mname");
			int mage = Integer.parseInt(request.getParameter("mage"));
			String mgender = request.getParameter("mgender");
			String mgrade = "일반";
			
			MemberVO vo = new MemberVO();
			vo.setMid(mid);
			vo.setMpassword1(mpassword1);
			vo.setMpassword2(mpassword2);
			vo.setMphone(mphone);
			vo.setMaddr1(maddr1);
			vo.setMaddr2(maddr2);
			vo.setMaddr3(maddr3);
			vo.setMname(mname);
			vo.setMage(mage);
			vo.setMgender(mgender);
			vo.setMgrade(mgrade);
			
			service.insert(vo);
			
			response.sendRedirect(path + "/MajustoryController?sw=S");
		} else if(sw.equals("S")) {
			request.setAttribute("li", service.getSelect());
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/list.jsp");
			rd.forward(request, response);
		} else if(sw.equals("E")) {
			String mid = request.getParameter("mid");
			MemberVO vo = new MemberVO();
			vo.setMid(mid);
			
			request.setAttribute("m", service.getSelectOne(vo));
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/edit.jsp");
			rd.forward(request, response);
		} else if(sw.equals("U")) {
			String mid = request.getParameter("mid");
			String mphone = request.getParameter("mphone");
			String maddr1 = request.getParameter("maddr1");
			String maddr2 = request.getParameter("maddr2");
			String maddr3 = request.getParameter("maddr3");
			String mgrade = request.getParameter("mgrade");
			String metc = request.getParameter("metc");
			
			MemberVO vo = new MemberVO();
			vo.setMid(mid);
			vo.setMphone(mphone);
			vo.setMaddr1(maddr1);
			vo.setMaddr2(maddr2);
			vo.setMaddr3(maddr3);
			vo.setMgrade(mgrade);
			vo.setMetc(metc);
			
			System.out.println("vo: " + vo);
			
			service.update(vo);
			
			response.sendRedirect(path + "/MajustoryController?sw=S");
		} else if(sw.equals("pay")) {
			
			response.sendRedirect(path + "/kakao/form.jsp");
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
