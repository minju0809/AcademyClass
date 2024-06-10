package repsd;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

//톰캣은 기본 설정이 maxFileSize : 5M 로 설정 되어 있다.
@MultipartConfig(
	 location="/", 
	 fileSizeThreshold=1024*1024,
     maxFileSize=1024*1024*5, 
     maxRequestSize=1024*1024*5*5
)
public class RepsdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

    public RepsdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
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
		String realFolder = getServletContext().getRealPath("/rePsd/files/");
		System.out.println("realFolder:" + realFolder );
		
		String sw = request.getParameter("sw");
		RepsdVo vo =new RepsdVo();
		RepsdService service = new RepsdServiceImpl();
		
		if (sw.equals("D")) {
			String idx = request.getParameter("idx");
			System.out.println("삭제(idx):" + idx);
			vo.setIdx(Integer.parseInt(idx));
			
			// (1) 삭제할 파일명 찾아오기
			HashMap<String, Object> m=service.getRePsdSelectOne(vo);
			System.out.println("삭제대상 파일명:" + m.get("img"));
			
			// (2) 파일 삭제 
			File delFile = new File(realFolder+m.get("img"));
			if (delFile.exists()) {
			  if(!m.get("img").equals("space.png")) {	
			    delFile.delete();
			  }
			}
			
			// (3) 레코드 삭제하기
			service.reDelete(vo);
			response.sendRedirect(path + "/RepsdController?sw=S");
			
			
		}else if (sw.equals("S")) {
			String ch1 = request.getParameter("ch1");
			String ch2 = request.getParameter("ch2");
			String sidxStr = request.getParameter("sidx");
			String nowPageStr = request.getParameter("nowPage");
		
			vo.setCh1(ch1);
			vo.setCh2(ch2);	
						
			int pageSize = 10 ;  // 페이지 사이즈 		
			int pageListSize = 10 ;  // 페이지List사이즈		 
			
			int  sidx = 0 ;
			if (sidxStr == null || sidxStr.equals("")) {  
				sidx = 0;
				
			}else{
				sidx = Integer.parseInt(sidxStr);
				
			}	
			
			vo.setSidx(sidx);
			vo.setPageSize(pageSize);	
			
			// list.jsp 로 넘어가는 영역
			request.setAttribute("sidx", sidx);	
			request.setAttribute("pageSize", pageSize);
			
			int nowPage = sidx / pageSize + 1 ;
		    int totalCount  = service.totalCount(vo); // ch1, ch2 
		    
		    // 전체페이지 수 
		    int totalPage =(int) Math.ceil((double)totalCount / pageSize) ;
		    
                                //   1 ~ 10 = 1  , 11 ~20 = 11 , 21 ~ 30 = 21
		    int  listStartPage = ( nowPage - 1) / pageListSize * pageListSize + 1 ;
		    
		                        //  1 + 10 -1 = 10 , 11 + 10 -1 = 20 ...  
		    int  listEndPage =  listStartPage + pageListSize -1  ;
		    
		    request.setAttribute("ch1", ch1);
			request.setAttribute("ch2", ch2);	
		    request.setAttribute("totalPage", totalPage);
			request.setAttribute("nowPage", nowPage);	
			request.setAttribute("totalCount", totalCount);		
			request.setAttribute("pageListSize", pageListSize);
			request.setAttribute("listStartPage", listStartPage);
			request.setAttribute("listEndPage", listEndPage);
			request.setAttribute("li", service.getRePsdSelect(vo));
			
			RequestDispatcher dispatcher 
			  =request.getRequestDispatcher("/rePsd/list.jsp");
			dispatcher.forward(request, response);
			
		}else if (sw.equals("E")) {			
			vo.setIdx(Integer.parseInt(request.getParameter("idx")));
			request.setAttribute("m", service.getRePsdSelectOne(vo));
			
			service.cnt(Integer.parseInt(request.getParameter("idx")));
			
			RequestDispatcher dispatcher 
			  =request.getRequestDispatcher("/rePsd/edit.jsp");
			dispatcher.forward(request, response);
			
		}else if (sw.equals("F")) {
		   response.sendRedirect(path + "/rePsd/form.jsp"); 	
		}else if (sw.equals("RE")) {			
			vo.setIdx(Integer.parseInt(request.getParameter("idx")));
			request.setAttribute("m", service.getRePsdSelectOne(vo));
			RequestDispatcher dispatcher 
			  =request.getRequestDispatcher("/rePsd/Re_Edit.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private void multipartIn(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 파일이 저장되는 물리적인 경로
		String realFolder = getServletContext().getRealPath("/rePsd/files/");
		System.out.println("realFolder:" + realFolder );
		
		// 웹사이트 root 경로 
		String path = request.getContextPath();
		
		// 파일명 난수 처리하기 
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String k = sdf.format(now);	
		int ran=(int)(Math.random()*100)+101 ;
		
		RepsdVo  vo = new  RepsdVo();
		RepsdService service = new RepsdServiceImpl();
		String sw = request.getParameter("sw");				
		String sname = request.getParameter("sname");
		String title = request.getParameter("title");
		String etc = request.getParameter("etc");
		String nameStr = request.getPart("img").getSubmittedFileName();

		Part part = request.getPart("img");
		
		//  파일첨부 유무 확인 후 확장자 만들기 
		String extension ="";
		String img ="";
		if ( nameStr.lastIndexOf('.') > 0) {
		  int lastIndex = nameStr.lastIndexOf('.');
	      extension = nameStr.substring(lastIndex);

	      img =  k + ran + extension; // 파일명 만들기
	      part.write(realFolder + img ); // 파일저장			  
		} else {
		  img = "space.png";
		}
		
		 vo.setSname(sname); 
		 vo.setTitle(title); 
		 vo.setImg(img); 
		 vo.setEtc(etc);
		
		if (sw.equals("I")) {
			
		 int ref = service.ref();

		 vo.setCnt(0);
		 vo.setRef(ref);
		 vo.setRe_step(1);
		 vo.setRe_level(1);
		 service.rePsdInsert(vo);
		 response.sendRedirect(path + "/RepsdController?sw=S");
		 
		}else if (sw.equals("U")) {
		 System.out.println("==> sw.equals(U)");	
		 String idx = request.getParameter("idx");
		 vo.setIdx(Integer.parseInt(idx));
		 
		 HashMap<String, Object> m = service.getRePsdSelectOne(vo);
		 
		 // 1. 파일처리 : 첨부파일이 있고, 기존의 이름이 space.png가 아니면 삭제
		 if (nameStr.lastIndexOf('.') > 0) {
			if (!m.get("img").equals("space.png")) { 
			 File delFileEdit = new File(realFolder + m.get("img"));
			 delFileEdit.delete();
			}
		 }
		 
		 
		 // 2. 레코드 처리
		 // idx, sname, title, etc, img
		 if (nameStr.lastIndexOf('.') > 0) {
			 service.reUpdateFileIn(vo);
		 }else {
			 service.reUpdateFileNot(vo);
		 }			
			
		 response.sendRedirect(path + "/RepsdController?sw=S");
		 
		}else if (sw.equals("Replay")) {			
			 
			 String ref = request.getParameter("ref");
			 String re_step = request.getParameter("re_step");
			 String re_level = request.getParameter("re_level");
			 
			 vo.setSname(sname); 
			 vo.setTitle(title); 
			 vo.setImg(img); 
			 vo.setEtc(etc);
			 vo.setCnt(0);
			 vo.setRef(Integer.parseInt(ref));
			 vo.setRe_step(Integer.parseInt(re_step));
			 vo.setRe_level(Integer.parseInt(re_level));
			 service.reIsert(vo);
			 response.sendRedirect(path + "/RepsdController?sw=S");			 
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
