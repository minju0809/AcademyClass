package summernote;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SummernoteController
 */
public class SummernoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SummernoteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String path = request.getContextPath();
		String sw = request.getParameter("sw");
		
		String idx = request.getParameter("idx");
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String etc = request.getParameter("etc");
		 
		SummernoteService service = new SummernoteServiceImpl();
		SummerVO  vo  =  new SummerVO();
		 
		if (sw.equals("F")) {
			
		    response.sendRedirect(path+"/summernote/summernote.jsp");
		    
		} else if(sw.equals("I")) {
			 
			vo.setName(name);
			vo.setTitle(title);
			vo.setEtc(etc);
			
			service.insert(vo);
			
			response.sendRedirect(path+"/SummernoteController?sw=S");
		} else if(sw.equals("S")) {
			request.setAttribute("li", service.select(null));

			RequestDispatcher dispatcher 
			  =request.getRequestDispatcher("/summernote/summerList.jsp");
			dispatcher.forward(request, response);
		} else if(sw.equals("E")) {
			vo.setIdx(idx);
			request.setAttribute("m", service.edit(vo));
			 
			RequestDispatcher dispatcher 
			 =request.getRequestDispatcher("/summernote/summerEdit.jsp");
			dispatcher.forward(request, response);
		} else if(sw.equals("U")) {
			vo.setIdx(idx);
			vo.setName(name);
			vo.setTitle(title);
			vo.setEtc(etc);
			
			service.update(vo);
			
			response.sendRedirect(path+"/SummernoteController?sw=S");
		} else if(sw.equals("D")) {
			vo.setIdx(idx);
			
			service.delete(vo);
			
			response.sendRedirect(path+"/SummernoteController?sw=S");
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
