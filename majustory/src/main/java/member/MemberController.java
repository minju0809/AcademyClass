package member;

import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class MemberController
 */
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
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
		
		MemberService service = new MemberServiceImpl();
		
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
		} else if(sw.equals("loginF")) {
			
			response.sendRedirect(path + "/member/login.jsp");
		} else if(sw.equals("login")) {
			String mid = request.getParameter("mid");
			String mpassword1 = request.getParameter("mpassword1");
			System.out.println("id: " + mid + ", pw: " + mpassword1);
			
			MemberVO vo = new MemberVO();
			vo.setMid(mid);
			vo.setMpassword1(mpassword1);

			String id = service.login(vo);
			if(!id.equals("")) {
				System.out.println("로그인 성공." + id);
				
				session.setAttribute("id", id);
			} else {
				System.out.println("로그인 실패" + id);
			}
			
			PrintWriter out = response.getWriter();
			out.print(id);
			
//			response.sendRedirect(path + "/member/login.jsp");
		} else if(sw.equals("loginOK")) {
			
			response.sendRedirect(path + "/member/success.jsp");
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
