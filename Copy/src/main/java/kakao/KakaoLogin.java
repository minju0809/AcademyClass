package kakao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

/**
 * Servlet implementation class KakaoLogin
 */
public class KakaoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakaoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path = request.getContextPath();
		
		String code = request.getParameter("code");
		System.out.println("====>code :" +  code);
		
		KakaoLoginService kakao = new KakaoLoginService();
		
		HttpSession session = request.getSession();
		
		String access_Token = kakao.getKakaoAccessToken(code);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
			System.out.println("====>controller access_token :" +  access_Token);
			System.out.println("====>controller userInfo :" +  userInfo);
			if (userInfo.get("email") != null) {
		        session.setAttribute("email", userInfo.get("email"));
		        session.setAttribute("nickname", userInfo.get("nickname"));
		        session.setAttribute("profile_image", userInfo.get("profile_image"));
		        session.setAttribute("access_Token", access_Token);
		        
		    }				
			
			response.sendRedirect(path + "/kakaoMap/loginOK.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
