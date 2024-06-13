package product;

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
import java.util.HashMap;

/**
 * Servlet implementation class ProductController
 */

//톰캣은 기본 설정이 maxFileSize : 5M 로 설정 되어 있다.
@MultipartConfig(
	 location="/", 
	 fileSizeThreshold=1024*1024,
   maxFileSize=1024*1024*5, 
   maxRequestSize=1024*1024*5*5
)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
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
		response.setContentType("text/html;charset=UTF-8");
		
		 String contentType = request.getContentType();
		  //  Insert 와 Update 처럼  사용하는 영역 ( "multipart/form-data" )
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
		
	

	private void multipartNot(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String path = request.getContextPath();
		String realFolder = getServletContext().getRealPath("/product/files/");
		System.out.println("realFolder:" + realFolder );
		
		String sw = request.getParameter("sw");
		ProductVO vo =new ProductVO();
		ProductService service = new ProductServiceImpl();
		
		if (sw.equals("D")) {
			
			String pid = request.getParameter("pid");
			vo.setPid(pid);
						
			// (1) 삭제할 파일명 찾아오기
			HashMap<String, Object> m=service.edit(vo);
			System.out.println("삭제대상 파일명:" + m.get("pimg"));
			
			// (2) 파일 삭제 
			File delFile = new File(realFolder+m.get("pimg"));
			if (delFile.exists()) {
			  if(!m.get("pimg").equals("space.png")) {	
			    delFile.delete();
			  }
			}
			
			// (3) 레코드 삭제하기
			service.delete(vo);
			response.sendRedirect(path + "/ProductController?sw=S");
			
			
		}else if (sw.equals("S")) {
					
			request.setAttribute("li", service.select(vo));
			
			RequestDispatcher dispatcher 
			  =request.getRequestDispatcher("/product/product_list.jsp");
			dispatcher.forward(request, response);
			
		}else if (sw.equals("E")) {		
			
			String pid = request.getParameter("pid");
			vo.setPid(pid);
			
			request.setAttribute("m", service.edit(vo));
						
			RequestDispatcher dispatcher 
			  =request.getRequestDispatcher("/product/product_edit.jsp");
			dispatcher.forward(request, response);
			
		}else if (sw.equals("F")) {
		   response.sendRedirect(path + "/product/product.jsp"); 	
		}	
	}

	private void multipartIn(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 파일이 저장되는 물리적인 경로
		String realFolder = getServletContext().getRealPath("/product/files/");
		System.out.println("realFolder:" + realFolder );
		
		// 웹사이트 root 경로 
		String path = request.getContextPath();
		
		// 파일명 난수 처리하기 
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String k = sdf.format(now);	
		int ran=(int)(Math.random()*100)+101 ;
		
		ProductVO  vo = new  ProductVO();
		ProductService service = new ProductServiceImpl();
		
		String sw = request.getParameter("sw");				
		String pid = request.getParameter("pid");
		String pname = request.getParameter("pname");
		
		int pprice =Integer.parseInt(request.getParameter("pprice")) ;
		int pbaesongbi = Integer.parseInt(request.getParameter("pbaesongbi"));
		
		String pdesc = request.getParameter("pdesc");
		String pimgStr = request.getPart("pimg").getSubmittedFileName();

		Part part = request.getPart("pimg");
		
		//  파일첨부 유무 확인 후 확장자 만들기 
		String extension ="";
		String img ="";
		
		if ( pimgStr.lastIndexOf('.') > 0) {
		  int lastIndex = pimgStr.lastIndexOf('.');
	      extension = pimgStr.substring(lastIndex);

	      img =  k + ran + extension; // 파일명 만들기
	      part.write(realFolder + img ); // 파일저장			  
		} else {
		  img = "space.png";
		}
		
		 vo.setPid(pid);
		 vo.setPname(pname);
		 vo.setPprice(pprice);
		 vo.setPbaesongbi(pbaesongbi);
		 vo.setPdesc(pdesc);
		 vo.setPimg(img);		
		
		if (sw.equals("I")) {
			
		 service.insert(vo);
		 response.sendRedirect(path + "/ProductController?sw=S");
		 
		}else if (sw.equals("U")) {
			
		 System.out.println("==> sw.equals(U)");
		 
		 pid = request.getParameter("pid");
		 vo.setPid(pid);		 		 
		 HashMap<String, Object> m = service.edit(vo);
		 
		 // 1. 파일처리 : 첨부파일이 있고, 기존의 이름이 space.png가 아니면 삭제
		 if (pimgStr.lastIndexOf('.') > 0) {
			if (!m.get("pimg").equals("space.png")) { 
			 File delFileEdit = new File(realFolder + m.get("pimg"));
			 delFileEdit.delete();
			}
		 }
		 		 
		 // 2. 레코드 처리
		 // idx, sname, title, etc, img
		 if (pimgStr.lastIndexOf('.') > 0) {
			 service.UpdateFileIn(vo);
		 }else {
			 service.UpdateFileNot(vo);
		 }			
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
