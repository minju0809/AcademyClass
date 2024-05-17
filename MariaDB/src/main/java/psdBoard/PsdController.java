package psdBoard;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import pkg.BCrypt;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class PsdController
 */
@MultipartConfig(
		location="/", 
		fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, 
        maxRequestSize=1024*1024*5*5
)
public class PsdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PsdController() {
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
		
		PsdService service = new PsdServiceImpl();

		String realFolder = getServletContext().getRealPath("/psdBoard/files/"); 
			
		String contentType = request.getContentType();
		  //  Insert 와 Update 처럼  사용하는 영역 ( "multipart/form-data" )
		 if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
		 
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String k = sdf.format(now);
			int ran = (int)(Math.random()*1000) + 1;

			System.out.println("realFolder: " + realFolder);

			Part part = request.getPart("photo");
			String photo = "";
			
			String nameStr = request.getPart("photo").getSubmittedFileName();
			System.out.println("nameStr:" +nameStr);
			
			int lastIndex = nameStr.lastIndexOf(".");
			if(lastIndex == -1) {
				photo = "space.png";
			} else {
				String extension = nameStr.substring(lastIndex);
				
				photo = "T"+k+ran+extension;
				part.write(realFolder+photo);
				System.out.println("photo: " + photo);
			}

			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String age2 = request.getParameter("age");
			
			String BCpw  = BCrypt.hashpw(age2, BCrypt.gensalt());
			String etc = request.getParameter("etc");
			
			if(sw.equals("I")) {
				System.out.println("--------------------------------------------------------");
				System.out.println("getName:" + request.getPart("photo").getName());    
				System.out.println("getSize:" +request.getPart("photo").getSize());
				System.out.println("SubmittedFileName:" +request.getPart("photo").getSubmittedFileName());
				System.out.println("---------------------------------------------------------");
				
				PsdVO m = new PsdVO();
				m.setName(name);
				m.setAge(age);
				m.setAge2(BCpw);
				m.setPhoto(photo);
				m.setEtc(etc);
				
				service.insert(m);
				
				response.sendRedirect(path + "/PsdController?sw=S");
			} else if(sw.equals("U")) {
				int idx = Integer.parseInt(request.getParameter("idx"));
				PsdVO vo = service.getSelectOne(idx);
				
				vo.setIdx(idx);
				vo.setName(name);
				vo.setAge(age);
				vo.setAge2(BCpw);
				vo.setEtc(etc);
				
				if(!nameStr.equals("")) {
					if(!vo.getPhoto().equals("space.png")) {
						String delFile = realFolder + vo.getPhoto();
						File f = new File(delFile);
						f.delete();
					}
					vo.setPhoto(photo);
					service.updateFile(vo); // 첨부파일이 있는 경우
				} else {
					service.update(vo); // 첨부파일이 없는 경우
				}
				
				response.sendRedirect(path + "/PsdController?sw=S");
			}
		 
		 } else {
		 
			if(sw.equals("F")) {
			 	
				response.sendRedirect(path + "/psdBoard/form.jsp");
			} else if(sw.equals("S")) {
				request.setAttribute("li", service.getSelect(null));
				
				RequestDispatcher rd = request.getRequestDispatcher("/psdBoard/list.jsp");
				rd.forward(request, response);
			} else if(sw.equals("E")) {
				int idx = Integer.parseInt(request.getParameter("idx"));
				request.setAttribute("m", service.getSelectOne(idx));
				
				RequestDispatcher rd = request.getRequestDispatcher("/psdBoard/edit.jsp");
				rd.forward(request, response);
			} else if(sw.equals("D")) { 
				int idx = Integer.parseInt(request.getParameter("idx"));
				PsdVO vo = service.getSelectOne(idx);
				System.out.println("삭제할 파일명: " + vo.getPhoto());
				
				String delFile = realFolder + vo.getPhoto();
				File f = new File(delFile);
				
				if(f.exists()) {
					if(!vo.getPhoto().equals("space.png")) {
						f.delete();
					}
				}
				service.delete(idx);
				
				response.sendRedirect(path + "/PsdController?sw=S");
			}
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
