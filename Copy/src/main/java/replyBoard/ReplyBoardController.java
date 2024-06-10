package replyBoard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
	   String  sw = request.getParameter("sw");
	   System.out.println("==> "+ sw);
	   String path = request.getContextPath();
	   ReplyBoardVo  vo =new ReplyBoardVo();
	   ReplyBoardService  service = new ReplyBoardServiceImpl();
	   if (sw.equals("I")) {
		   
		   String  ref = request.getParameter("ref");
		   String  sname = request.getParameter("sname");
		   String  title = request.getParameter("title");
		   
		  
		   vo.setRef(Integer.parseInt(ref));
		   vo.setSname(sname);
		   vo.setTitle(title);
		   service.insert(vo);
		   
		   response.sendRedirect(path + "ReplyBoardController?sw=S");
		   
	   } else if (sw.equals("F")) {

		   int  maxRef = service.maxRef();
		   request.setAttribute("ref", maxRef);
		   
		   RequestDispatcher dispatcher 
		   = request.getRequestDispatcher("/replayBoard/form.jsp") ;
		   dispatcher.forward(request, response);
		   
		   
	   } else if (sw.equals("S")) {

		   List<ReplyBoardVo>  li = service.getSelectAll(null);
		   request.setAttribute("li", li);
		   
		   RequestDispatcher dispatcher 
		   = request.getRequestDispatcher("/replayBoard/list.jsp") ;
		   dispatcher.forward(request, response);
		   
		   
	   } else if (sw.equals("E")) {
		   
		   String  idx = request.getParameter("idx");
		   vo.setIdx(Integer.parseInt(idx));
		   ReplyBoardVo  m = service.getSelectOne(vo);
		   request.setAttribute("m", m);
		   
		   RequestDispatcher dispatcher 
		   = request.getRequestDispatcher("/replayBoard/edit.jsp") ;
		   dispatcher.forward(request, response);		   
	   } else if (sw.equals("U")) {
		   String  idx = request.getParameter("idx");
		   String  sname = request.getParameter("sname");
		   String  title = request.getParameter("title");
		   System.out.println("==> U ");
		   System.out.println("==>" + idx +"," + sname+ "," + title);
		   response.sendRedirect(path + "/ReplyBoardController?sw=S");
		   
	   } else if (sw.equals("RE")) {
		   String  ref = request.getParameter("ref");
		   String  re_step = request.getParameter("re_step");
		   String  re_level = request.getParameter("re_level");		   
		   String  sname = request.getParameter("sname");
		   String  title = request.getParameter("title");
		   
		   vo.setRef(Integer.parseInt(ref));
		   vo.setRe_step(Integer.parseInt(re_step));
		   vo.setRe_level(Integer.parseInt(re_level));
		   vo.setSname(sname);
		   vo.setTitle(title);
		   
		   service.reIsert(vo);
		   
		   // System.out.println("==> Re");
		   // System.out.println("==>" +  ref+ "," + re_step + "," + re_level + ","+ sname+ "," + title);
		   response.sendRedirect(path + "/ReplyBoardController?sw=S");
		   
	   } else if (sw.equals("D")) {
		   
		   System.out.println("==> D");
		   response.sendRedirect(path + "/ReplyBoardController?sw=S");
		   
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
