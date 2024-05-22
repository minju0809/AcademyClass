package repsd;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class RepsdController
 */
@MultipartConfig(
		location="/", 
		fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, 
        maxRequestSize=1024*1024*5*5
)
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String contentType = request.getContentType();
		
		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
            // 첨부파일이 있는 경우 
			try {
				multipartIn(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			 
		} else {
			// 첨부파일이 없는 경우  
			try {
				multipartNot(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	private void multipartIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 파일이 저장되는 물리적인 경로
		String realFolder = getServletContext().getRealPath("/repsd/files/");
		System.out.println("realFolder: " + realFolder);

		String path = request.getContextPath();
		
		RepsdService service = new RepsdServiceImpl();
		RepsdVO vo = new RepsdVO();
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String k = sdf.format(now);
		int ran=(int)(Math.random()*100)+101 ;

		String sw = request.getParameter("sw");
		
		String sname = request.getParameter("sname");
		String title = request.getParameter("title");
		String etc = request.getParameter("etc");
		
		String nameStr = request.getPart("img").getSubmittedFileName();

		Part part = request.getPart("img");

		String extension = "";
		String img = "";
		if(nameStr.lastIndexOf('.') > 0) {
			int lastIndex = nameStr.lastIndexOf('.');
			extension = nameStr.substring(lastIndex);

			part.write(realFolder + k + ran + extension);
			img = k + ran + extension;
		} else {
			img = "space.png";
		}
		
		vo.setSname(sname);
		vo.setTitle(title);
		vo.setImg(img);
		vo.setEtc(etc);
		
		if(sw.equals("I")) {
			int ref = service.ref();

			vo.setCnt(0);
			vo.setRef(ref);
			vo.setRe_step(1);
			vo.setRe_level(1);
			service.insert(vo);
			
			response.sendRedirect(path + "/RepsdController?sw=S");
		} else if(sw.equals("U")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			vo.setIdx(idx);
			
			RepsdVO m = service.getSelectOne(vo);
			// 파일 처리
			if(nameStr.lastIndexOf('.') > 0) {
				if(!m.getImg().equals("space.png")) {
					File delFileEdit = new File(realFolder + m.getImg());
					delFileEdit.delete();
				}
			} 
			
			// 레코드 처리
			if(nameStr.lastIndexOf('.') > 0) {
				service.updateFile(vo);
			} else {
				service.update(vo);
			}
			
			response.sendRedirect(path + "/RepsdController?sw=S");
		} else if(sw.equals("RE")) {
			
			int ref = Integer.parseInt(request.getParameter("ref"));
			int step = Integer.parseInt(request.getParameter("re_step"));
			int level = Integer.parseInt(request.getParameter("re_level"));
			
			vo.setCnt(0);
			vo.setRef(ref);
			vo.setRe_step(step);
			vo.setRe_level(level);
			
			service.reInsert(vo);
			
			response.sendRedirect(path + "/RepsdController?sw=S");
		} 
	}
	
	private void multipartNot(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			
			vo = service.getSelectOne(vo);
			
			String delFile = realFolder + vo.getImg();
			System.out.println("delFile: " + delFile);
			
			File f = new File(delFile);
			
			if(f.exists()) {
				if(!vo.getImg().equals("space.png")) {
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
