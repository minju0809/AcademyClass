package member;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.ServiceImpl;
import test.ServiceTest;
import test.TestVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String path = request.getContextPath();
		String sw = request.getParameter("sw");

		String mid = request.getParameter("mid");
		String mpassword1 = request.getParameter("mpassword1");
		String mpassword2 = request.getParameter("mpassword2");
		String mphone = request.getParameter("mphone");
		String maddr1 = request.getParameter("maddr1");
		String maddr2 = request.getParameter("maddr2");
		String maddr3 = request.getParameter("maddr3");
		String mname = request.getParameter("mname");
		String mage = request.getParameter("mage");
		String mgender = request.getParameter("mgender");
		String mgrade = request.getParameter("mgrade");
		String metc = request.getParameter("metc");

		MemberVO vo = new MemberVO();
		MemberService service = new MemberServiceImpl();
		if (sw.equals("F")) {
			response.sendRedirect(path + "/member/member.jsp");
		}
		if (sw.equals("I")) {
			vo = new MemberVO();
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
			vo.setMetc(metc);
			service.insert(vo);

			response.sendRedirect(path + "/MemberController?sw=S");

		} else if (sw.equals("S")) {

			request.setAttribute("li", service.getSelectAll(vo));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_list.jsp");
			dispatcher.forward(request, response);
		} else if (sw.equals("login")) {
			response.sendRedirect(path + "/member/login.jsp");
		} else if (sw.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect(path + "/index.jsp");
		} else if (sw.equals("loginOK")) {
			mid = request.getParameter("mid");
			mpassword1 = request.getParameter("mpassword1");
			vo.setMid(mid);
			vo.setMpassword1(mpassword1);
			String login = service.loginOK(vo);
			if (login.equals("F")) {
				request.getSession().setAttribute("login", "F");
				response.sendRedirect(path + "/member/fail.jsp");
			} else {
				request.getSession().setAttribute("mid", login);
				request.getSession().setAttribute("login", login);
				response.sendRedirect(path + "/member/success.jsp");
			}
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
