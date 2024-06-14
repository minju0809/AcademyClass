package test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Servlet implementation class TestController
 */
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestController() {
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
	  String  sw = request.getParameter("sw");
	  if (sw.equals("F") ) {
		  response.sendRedirect(path + "/Test1004/form.jsp");
	  }  if (sw.equals("kakao") ) {
		  response.sendRedirect(path + "/Test1004/pay.jsp");
	  } else if (sw.equals("I")) {
		  
		   // 체크 박스를 이용한 선택값만 받아 오기 시작  
		   System.out.println("==> 배열처리하기  ");
	   
		   String[] selectedNames = request.getParameter("selectedNames").split(",");
		   String[] selectedAmounts = request.getParameter("selectedAmounts").split(",");		   
		   System.out.println("선택개수:"+selectedAmounts.length);
		   
		   for (int i =0 ; i < selectedNames.length; i++) {			   
			   System.out.println(selectedNames[i] +" : "+ selectedAmounts[i]);
		   }
		   // 체크 박스를 이용한 선택값만 받아 오기 끝  
		   
		   
		   // 전체 배열값 다 가져오기 시작 
		   String [] name = request.getParameterValues("name");
		   String [] amount = request.getParameterValues("amount");
		   
		   TestVO vo =null;
		   
		   List<HashMap<String, Object>> li =null;
		               li = new ArrayList<HashMap<String, Object>>();
		   HashMap<String, Object>  map =null;
		   for (int i =0 ; i < name.length; i++) {			   
			   vo = new TestVO();
			   vo.setName(name[i]);
			   vo.setAmount(amount[i]);
			   
			   ServiceTest   service = new ServiceImpl();
			   service.insert(vo);
			   
			   map = new HashMap<String, Object>();
			   map.put("name",name[i]);
			   map.put("amount", amount[i]);			   
			   li.add(map);
		   }
		   
		    // 전체 배열값 다 가져오기 끝 
		   
		    // 값 넘기기 
	        request.setAttribute("li", li);
		
			RequestDispatcher	dispatcher=request.getRequestDispatcher("/Test1004/list.jsp");
			dispatcher.forward(request, response);	
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
