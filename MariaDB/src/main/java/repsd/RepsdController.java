package repsd;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

/**
 * Servlet implementation class RepsdController
 */
public class RepsdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepsdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String sw = request.getParameter("sw");
		String path = request.getContextPath();
		
		RepsdService service = new RepsdServiceImpl();
		
		if(sw.equals("S")) {
			request.setAttribute("li", service.getSelect(null));
			
			RequestDispatcher rd = request.getRequestDispatcher("/repsd/list.jsp");
			rd.forward(request, response);
		} else if(sw.equals("F")) {
			
			response.sendRedirect(path + "/repsd/form.jsp");
		} else if(sw.equals("E")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			RepsdVO vo = new RepsdVO();
			vo.setIdx(idx);
			
			request.setAttribute("m", service.getSelectOne(vo));
			
			service.cnt(idx);
			
			RequestDispatcher rd = request.getRequestDispatcher("/repsd/edit.jsp");
			rd.forward(request, response);
		} else if(sw.equals("D")) {
			String realFolder = getServletContext().getRealPath("/repsd/files/");
			System.out.println("realFolder: " + realFolder);
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			RepsdVO vo = new RepsdVO();
			vo.setIdx(idx);
			
			RepsdVO m = service.getSelectOne(vo);
			
			String delFile = realFolder + m.getImg();
			System.out.println("delFile: " + delFile);
			
			File f = new File(delFile);
			
			if(f.exists()) {
				if(!m.getImg().equals("space.png")) {
					f.delete();
				}
			}
			
			service.delete(idx);
			
			response.sendRedirect(path + "/RepsdController?sw=S");
		} else if(sw.equals("R")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			System.out.println("idx: " + idx);
			RepsdVO vo = new RepsdVO();
			vo.setIdx(idx);
			request.setAttribute("m", service.getSelectOne(vo));
			
			RequestDispatcher rd = request.getRequestDispatcher("/repsd/re_edit.jsp");
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
