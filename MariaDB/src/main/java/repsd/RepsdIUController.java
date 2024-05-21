package repsd;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class RepsdIUController
 */
@MultipartConfig(
		location="/", 
		fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, 
        maxRequestSize=1024*1024*5*5
)
public class RepsdIUController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepsdIUController() {
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
		
		String path =request.getContextPath();
		String sw = request.getParameter("sw");
		
		RepsdService service = new RepsdServiceImpl();
		
		// 파일이 저장되는 물리적인 경로
		String realFolder = getServletContext().getRealPath("/repsd/files/");
		System.out.println("realFolder: " + realFolder);
		
		String sname = request.getParameter("sname");
		String title = request.getParameter("title");
		String etc = request.getParameter("etc");
		
		String nameStr = request.getPart("img").getSubmittedFileName();
		int lastIndex = nameStr.lastIndexOf('.');
	    String extension = nameStr.substring(lastIndex);
	    System.out.println("Extension: " + extension);
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String k = sdf.format(now);
	
		int ran=(int)(Math.random()*100)+101 ;
		
		Part part = request.getPart("img");
		part.write(realFolder + k + ran + extension);
		
		String img = k + ran + extension;
		
		RepsdVO vo = new RepsdVO();
		
	    System.out.println("--------------------------------------------------------");
	    System.out.println("getName:" + request.getPart("img").getName());    
	    System.out.println("getSize:" +request.getPart("img").getSize());
	    System.out.println("SubmittedFileName:" +request.getPart("img").getSubmittedFileName());
	    System.out.println("---------------------------------------------------------");
	    
//	    InputStream inputstream = null;
//	    inputstream = part.getInputStream();
//	    System.out.println("inputstream :" + inputstream);
//	    
//	    String str = realFolder + k + ran + 1 + extension;
//	    
//	    FileOutputStream outputStream = new FileOutputStream(str);
//	    byte[] buffer = new byte[4096];
//	    int bytesRead;
//	    // Read from the input stream and write to the output stream
//	    while ((bytesRead = inputstream.read(buffer)) != -1) {
//	        outputStream.write(buffer, 0, bytesRead);
//	    }
//	    outputStream.close();
//	    inputstream.close();	      	  
		
		if(sw.equals("I")) {
			int ref = service.ref();
			
			vo.setSname(sname);
			vo.setTitle(title);
			vo.setImg(img);
			vo.setEtc(etc);
			vo.setCnt(0);
			vo.setRef(ref);
			vo.setRe_step(1);
			vo.setRe_level(1);
			service.insert(vo);
			
			response.sendRedirect(path + "/RepsdController?sw=S");
		} else if(sw.equals("U")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			response.sendRedirect(path + "/RepsdController?sw=E&idx="+idx);
		} else if(sw.equals("RE")) {
			
			int ref = Integer.parseInt(request.getParameter("ref"));
			int step = Integer.parseInt(request.getParameter("re_step"));
			int level = Integer.parseInt(request.getParameter("re_level"));
			
			vo.setSname(sname);
			vo.setTitle(title);
			vo.setImg(img);
			vo.setEtc(etc);
			vo.setCnt(0);
			vo.setRef(ref);
			vo.setRe_step(step);
			vo.setRe_level(level);
			
			service.reInsert(vo);
			
			response.sendRedirect(path + "/RepsdController?sw=S");
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
