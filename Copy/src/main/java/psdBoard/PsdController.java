package psdBoard;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import pkg.BCrypt;

/**
 * Servlet implementation class MariaDBController
 */

// 필수 설정 
@MultipartConfig(
		location="/", 
		fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, 
        maxRequestSize=1024*1024*5*2
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String k = sdf.format(now);	
		int ran=(int)(Math.random()*1000)+1 ;
		
		String photo ="";
		
		String realFolder = getServletContext().getRealPath("/psdBoard/files/"); 
		System.out.println("realFolder:" + realFolder);
		
		String path = request.getContextPath();
		String sw =  request.getParameter("sw");

		 String contentType = request.getContentType();
		  //  Insert 와 Update 처럼  사용하는 영역 ( "multipart/form-data" )
		 if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			 System.out.println("multipart 영역");
			 
				String nameStr = request.getPart("photo").getSubmittedFileName();
				PsdService service = new PsdServiceImpl();
				PsdVo vo = new PsdVo();
				
				String name	= request.getParameter("name");
				String age	= request.getParameter("age");
				String etc	= request.getParameter("etc");
							
				vo.setName(name);
				vo.setAge(Integer.parseInt(age));
				vo.setEtc(etc);
		    		
				if (sw.equals("I")) {
				    if (nameStr.equals("")) {
				    	System.out.println("파일없음");
				    	photo = "space.png";
				    }else {	    	
				    	Part part = request.getPart("photo");		    
					    int lastIndex = nameStr.lastIndexOf('.');
					    String extension = nameStr.substring(lastIndex);		    
					    photo = "T"+k+ran+extension;  // 저장할 이름 만들기
						part.write(realFolder+photo); // 실제 파일이 저장되는 명령문.
				    }			
					vo.setPhoto(photo);	
					
					age = BCrypt.hashpw(age, BCrypt.gensalt());
					
					vo.setAge2(age);
					service.insert(vo);				
					response.sendRedirect(path + "/PsdController?sw=S");	
				} else if (sw.equals("U")) {
					System.out.println("===> U확인");
					String idx	= request.getParameter("idx");
					PsdVo m = service.edit(Integer.parseInt(idx));
					// 기본 파일 삭제하는 경우 
					if ( !nameStr.equals("") ) { 				
						if (!m.getPhoto().equals("space.png")) {
						  String  delFile  = realFolder + m.getPhoto()	;
						  File f = new File(delFile);
						  f.delete();
						}					
						// 파일 첨부 
				    	Part part = request.getPart("photo");		    
					    int lastIndex = nameStr.lastIndexOf('.');
					    String extension = nameStr.substring(lastIndex);		    
					    photo = "T"+k+ran+extension;  // 저장할 이름 만들기
						part.write(realFolder+photo); // 실제 파일이 저장되는 명령문.
					} //  기본 파일 삭제 끝
					
					vo.setIdx(Integer.parseInt(idx));
					
					age = BCrypt.hashpw(age, BCrypt.gensalt());
					if (!nameStr.equals("")) {
						// 첨부파일이 있는 경우 Update
						vo.setPhoto(photo);
						vo.setAge2(age);
						service.updateFile(vo);
						System.out.println("VO:" + vo);
					}else {
						// 첨부파일이 없는 경우  Update
						vo.setPhoto(photo);
						vo.setAge2(age);
						service.update(vo);
						System.out.println("VO:" + vo);
					}	
					response.sendRedirect(path + "/PsdController?sw=S");	
				 }
			 
			 
		   } else {
			 System.out.println("multipart영역 아님");				
	
				String  idx =  request.getParameter("idx");
			
				PsdService service = new PsdServiceImpl();
				
				if (sw.equals("F")) {
					response.sendRedirect(path + "/psdBoard/form.jsp");
							
				}else if(sw.equals("S")) {				
					
					request.setAttribute("li", service.select());
					RequestDispatcher 	dispatcher 
					    = request.getRequestDispatcher("/psdBoard/list.jsp");
					dispatcher.forward(request, response);
					
				} else if(sw.equals("E")) {	
					
					request.setAttribute("m", service.edit(Integer.parseInt(idx)));
					RequestDispatcher 	dispatcher 
					    = request.getRequestDispatcher("/psdBoard/edit.jsp");
					dispatcher.forward(request, response);	
					
				}else if(sw.equals("D")) {				
				
				 PsdVo  vo  =  service.edit(Integer.parseInt(idx));
				 System.out.println("삭제할 파일명:" + vo.getPhoto()) ;
				 
				 String delFile = realFolder + vo.getPhoto();
				 File f = new File(delFile);
				 
				 // 파일이 존재하면 
				 if (f.exists()) { 
					f.delete(); //  삭제하시오.
				 }
				 
				 // 레코드 삭제 
				 service.delete(Integer.parseInt(idx));
				 
				 response.sendRedirect(path + "/PsdController?sw=S");
				}
					
			 
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
