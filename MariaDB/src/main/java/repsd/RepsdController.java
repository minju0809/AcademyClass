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
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
		String sw = request.getParameter("sw");
		
		RepsdService service = new RepsdServiceImpl();
		RepsdVO vo = new RepsdVO();

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String k = sdf.format(now);
		int ran=(int)(Math.random()*100)+101 ;

		
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
		RepsdVO vo = new RepsdVO();
		
		if(sw.equals("S")) {
			String sidx = request.getParameter("start");
//			String nowPageStr = request.getParameter("nowPage");
			String ch1 = request.getParameter("ch1");
			String ch2 = request.getParameter("ch2");

		    vo.setCh1(ch1);
		    vo.setCh2(ch2);
			
			int start;
		    int pageSize = 10;
		    int pageListSize = 10;
		    
			vo.setPageSize(pageSize);
			
		    if (sidx == null) {
		    	start = 0;
//		    	nowPage = 1;
		    } else {
		        start = Integer.parseInt(sidx);
//		        nowPage = Integer.parseInt(nowPageStr);
		    }
		    
		    int nowPage = start / pageSize + 1;
		    int totalCount = service.totalCount(vo);
		    int totalPage = (int) Math.ceil((double)totalCount/ pageSize);
		    int lastPage = (totalPage - 1) * pageSize + 1;
		    int listStartPage = (nowPage - 1) / pageListSize * pageListSize + 1;
		    int listEndPage = listStartPage + pageListSize - 1;
		    
		    vo.setStart(start);
		    vo.setPageSize(pageSize);
			
			request.setAttribute("start", start);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("lastPage", lastPage);
			
			request.setAttribute("pageListSize", pageListSize);
			request.setAttribute("listStartPage", listStartPage);
			request.setAttribute("listEndPage", listEndPage);
			
			request.setAttribute("tc", totalCount);
			request.setAttribute("li", service.getSelect(vo));
			
			request.setAttribute("ch1", ch1);
			request.setAttribute("ch2", ch2);
			
			RequestDispatcher rd = request.getRequestDispatcher("/repsd/list.jsp");
			rd.forward(request, response);
		} else if(sw.equals("F")) {
			
			response.sendRedirect(path + "/repsd/form.jsp");
		} else if(sw.equals("E")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			vo.setIdx(idx);
			
			request.setAttribute("m", service.getSelectOne(vo));
			
			service.cnt(idx);
			
			RequestDispatcher rd = request.getRequestDispatcher("/repsd/edit.jsp");
			rd.forward(request, response);
		} else if(sw.equals("D")) {
			String realFolder = getServletContext().getRealPath("/repsd/files/");
			System.out.println("realFolder: " + realFolder);
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			
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
			vo.setIdx(idx);
			request.setAttribute("m", service.getSelectOne(vo));
			
			RequestDispatcher rd = request.getRequestDispatcher("/repsd/re_edit.jsp");
			rd.forward(request, response);
		} else if(sw.equals("II")) {
			System.out.println("IIIIIIIIII");
			String[] snameArray = {"김철수", "박영희", "이민호", "최지훈", "장서윤", "정지우", "한유라", "오세훈", "서진호", "윤정민"};
			String[] titleArray = {"행복한 하루", "비밀의 정원", "고양이의 모험", "여름 바다", "사랑의 시작", "겨울 이야기", "친구와의 추억", "별빛 밤하늘", "산책로에서", "도시의 야경"};
			String[] etcArray = {
			    "오늘은 정말 행복한 하루였다. 아침에 일어나서 맛있는 아침을 먹고 친구들과 공원에서 산책을 했다. 저녁에는 가족들과 함께 저녁 식사를 하며 즐거운 시간을 보냈다.",
			    "비밀의 정원에서 발견한 작은 연못에는 아름다운 연꽃이 피어 있었다. 그곳에서 만난 친구와 함께 연못 주위를 산책하며 많은 이야기를 나누었다.",
			    "고양이의 모험은 끝이 없었다. 집을 나선 고양이는 골목길을 지나 큰 길로 나갔고, 그곳에서 새로운 친구들을 만났다. 하루 종일 모험을 즐긴 고양이는 집으로 돌아와 따뜻한 우유를 마셨다.",
			    "여름 바다는 언제나 사람들로 붐빈다. 아이들은 모래성 쌓기에 열중하고, 어른들은 해변가에 누워 일광욕을 즐긴다. 저녁에는 불꽃놀이가 열려 모두가 환호성을 질렀다.",
			    "사랑의 시작은 작은 눈빛 교환에서 시작되었다. 두 사람은 서로를 바라보며 미소를 지었고, 그 순간부터 둘의 사랑은 시작되었다. 매일매일이 행복으로 가득 찼다.",
			    "겨울 이야기는 따뜻한 차 한 잔과 함께 시작되었다. 창밖에는 눈이 내리고 있었고, 벽난로 앞에서 따뜻한 담요를 덮고 책을 읽는 시간이 참 좋았다.",
			    "친구와의 추억은 언제나 소중하다. 함께 여행을 떠났던 그날의 기억은 아직도 생생하다. 바닷가에서 찍은 사진과 밤하늘을 보며 나눈 이야기들이 기억에 남는다.",
			    "별빛 밤하늘은 마치 보석상자 같았다. 수많은 별들이 반짝이며 하늘을 수놓았다. 친구와 함께 별자리를 찾아보며 밤늦게까지 이야기를 나누었다.",
			    "산책로에서 만난 강아지는 너무나 귀여웠다. 주인과 함께 걷는 모습이 참 보기 좋았다. 강아지는 사람들에게 인사를 하며 즐거운 시간을 보냈다.",
			    "도시의 야경은 언제나 화려하다. 높은 빌딩들과 네온사인들이 반짝이며 도시를 밝힌다. 길거리에는 사람들이 북적이고, 저마다의 이야기를 가지고 있다."
			};
			String[] imgArray = {"space.png", "개.png", "닭.png", "돼지.png", "말.png", "뱀.png", "소.png", "양.png", "용.png", "원숭이.png"};
			
			Random rand = new Random();

			for(int i = 0; i < 500; i++) {
				int ref = service.ref();
				
				vo.setSname(snameArray[rand.nextInt(snameArray.length)]);
				vo.setTitle(titleArray[rand.nextInt(titleArray.length)]);
				vo.setEtc(etcArray[rand.nextInt(etcArray.length)]);
				vo.setImg(imgArray[rand.nextInt(imgArray.length)]);
				vo.setCnt(0);
				vo.setRef(ref);
				vo.setRe_step(1);
				vo.setRe_level(1);
				
				service.insert(vo);
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
