package replyBoard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ReplyBoardController
 */
public class ReplyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyBoardController() {
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
		
		ReplyService service = new ReplyServiceImpl();
		
		if(sw.equals("F")) {
			request.setAttribute("ref", service.maxRef());
		
			RequestDispatcher rd = request.getRequestDispatcher("/replyBoard/form.jsp");
			rd.forward(request, response);
		} else if(sw.equals("I")) {
			int ref = Integer.parseInt(request.getParameter("ref"));
			String sname = request.getParameter("sname");
			String title = request.getParameter("title");
			
			ReplyVO m = new ReplyVO();
			m.setRef(ref);
			m.setSname(sname);
			m.setTitle(title);
			
			service.insert(m);
			
			response.sendRedirect(path + "/ReplyBoardController?sw=S");
		} else if(sw.equals("S")) {
			request.setAttribute("li", service.getSelect());
			
			RequestDispatcher rd = request.getRequestDispatcher("/replyBoard/list.jsp");
			rd.forward(request, response);
		} else if(sw.equals("E")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			request.setAttribute("m", service.getSelectOne(idx));
			
			RequestDispatcher rd = request.getRequestDispatcher("/replyBoard/edit.jsp");
			rd.forward(request, response);
		} else if(sw.equals("U")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String sname = request.getParameter("sname");
			String title = request.getParameter("title");
			
			System.out.println("==> " + idx + ", " + sname + ", " + title);
			ReplyVO m = new ReplyVO();
			m.setIdx(idx);
			m.setSname(sname);
			m.setTitle(title);
			service.update(m);
			
			response.sendRedirect(path + "/ReplyBoardController?sw=S");
		} else if(sw.equals("D")) {
			
			response.sendRedirect(path + "/ReplyBoardController?sw=S");
		} else if(sw.equals("RE")) {
			int ref = Integer.parseInt(request.getParameter("ref"));
			int re_step = Integer.parseInt(request.getParameter("re_step"));
			int re_level = Integer.parseInt(request.getParameter("re_level"));
			String sname = request.getParameter("sname");
			String title = request.getParameter("title");

			ReplyVO m = new ReplyVO();
			m.setSname(sname);
			m.setTitle(title);
			m.setRef(ref);
			m.setRe_step(re_step);
			m.setRe_level(re_level);
			service.reInsert(m);
			
			System.out.println("==> " + ref + ", " + re_step + ", " + re_level + ", " + sname + ", " + title);
			
			response.sendRedirect(path + "/ReplyBoardController?sw=S");
			// RequestDispatcher rd = request.getRequestDispatcher("/replyBoard/list.jsp");
			// rd.forward(request, response);
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
