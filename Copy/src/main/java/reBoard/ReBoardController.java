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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String  path = request.getContextPath();
		
		String sw = request.getParameter("sw");
		
		ExamService service =  new ExamServiceImpl();
		
		if (sw.equals("S") ) {
			
		  request.setAttribute("li", service.getSelectAll());
		  RequestDispatcher 	dispatcher 
		    = request.getRequestDispatcher("/reBoard/list.jsp");
		  dispatcher.forward(request, response);
			
		} else if (sw.equals("E") ) {
			
		  String sno = request.getParameter("sno");		
		  
		  request.setAttribute("m", service.getSelectOne(sno));
		  request.setAttribute("li", service.getReSelectAll(sno));
		  
		  RequestDispatcher 	dispatcher 
		    = request.getRequestDispatcher("/reBoard/edit.jsp");
		  dispatcher.forward(request, response);
				
		}else if (sw.equals("U") ) {
			String sno = request.getParameter("sno");
			String sname = request.getParameter("sname");
			String kor = request.getParameter("kor");
			String eng = request.getParameter("eng");
			String math = request.getParameter("math");
			String hist = request.getParameter("hist");
			String etc = request.getParameter("etc");
			ExamVo vo =  new ExamVo();
			vo.setSno(sno);
			vo.setSname(sname);
			vo.setKor(Integer.parseInt(kor));
			vo.setEng(Integer.parseInt(eng));
			vo.setMath(Integer.parseInt(math));
			vo.setHist(Integer.parseInt(hist));
			vo.setEtc(etc);
			service.update(vo);
			
			response.sendRedirect("ReBoardController?sw=E&sno="+sno);
				
		} else if (sw.equals("Re") ) {
			
			  System.out.println("===> Re 확인 ");
			  String sno = request.getParameter("sno");
			  String name = request.getParameter("name");
			  String title = request.getParameter("title");
			  String details = request.getParameter("details");
			  
			  ReExamVo   vo = new ReExamVo();
			  vo.setSno(sno);
			  vo.setName(name);
			  vo.setTitle(title);
			  vo.setDetails(details);
			  
			  service.ReInsert(vo);
			  
			  PrintWriter out = response.getWriter();
			  out.print("T");
		} else if (sw.equals("D") ) {
			  String idx = request.getParameter("idx");
			  String sno = request.getParameter("sno");
			  System.out.println("===> D 확인, idx : " + idx );
			  
			  service.ReDelete(idx);
			  
			  response.sendRedirect("ReBoardController?sw=E&sno="+sno);
			  
		}else if (sw.equals("F") ) {
				
			 response.sendRedirect(path + "/reBoard/form.jsp");
				
		}else if (sw.equals("ckID") ) {
			 String sno = request.getParameter("sno");
			 String str = service.ckID(sno);
			 // System.out.println("===> ckID 확인, sno : " + sno );
			 PrintWriter out = response.getWriter();
			 out.print(str);	
		} else if (sw.equals("I") ) {
			
			  System.out.println("===> I 확인 ");
			  String sno = request.getParameter("sno");
			  String sname = request.getParameter("sname");
			  String kor = request.getParameter("kor");
			  String eng = request.getParameter("eng");
			  String math = request.getParameter("math");
			  String hist = request.getParameter("hist");
			  String etc = request.getParameter("etc");
			  
			  ExamVo   vo = new ExamVo();
			  vo.setSno(sno);
			  vo.setSname(sname);
			  vo.setKor(Integer.parseInt(kor));
			  vo.setEng(Integer.parseInt(eng));
			  vo.setMath(Integer.parseInt(math));
			  vo.setHist(Integer.parseInt(hist));
			  vo.setEtc(etc);
					  
			  service.insert(vo);
			  
			  response.sendRedirect("ReBoardController?sw=S");
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
