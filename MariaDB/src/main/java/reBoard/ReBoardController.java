package reBoard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ReBoardController
 */
public class ReBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReBoardController() {
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
		
		ExamService service = new ExamServiceImpl();
		
		if(sw.equals("S")) {
			request.setAttribute("li", service.getSelect());
			
			RequestDispatcher rd = request.getRequestDispatcher("/reBoard/list.jsp");
			rd.forward(request, response);
		} else if(sw.equals("E")) {
			String sno = request.getParameter("sno");
			request.setAttribute("m", service.getSelectOne(sno));
			request.setAttribute("li", service.getReExamSelect(sno));
			
			RequestDispatcher rd = request.getRequestDispatcher("/reBoard/edit.jsp");
			rd.forward(request, response);
		} else if(sw.equals("Re")) {
			System.out.println("Re 확인");
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			String details = request.getParameter("details");
			
			ReExamVO vo = new ReExamVO();
			vo.setSno(sno);
			vo.setName(name);
			vo.setTitle(title);
			vo.setDetails(details);
			service.reExamInsert(vo);
			
			PrintWriter out = response.getWriter();
			out.print("T");
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
