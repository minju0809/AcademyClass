package majustory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

/**
 * Servlet implementation class ApproveServlet
 */
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String ADMIN_KEY;  //  발급받은 Admin 키
    private static final String CID = "TC0ONETIME"; // 테스트 CID

    @Override
    public void init() throws ServletException {
        super.init();
        Properties properties = new Properties();
        InputStream input = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
        if (input != null) {
            try {
                properties.load(input);
                ADMIN_KEY = properties.getProperty("kakaoApiKey");
            } catch (IOException e) {
                throw new ServletException("Could not load properties file", e);
            }
        } else {
            throw new ServletException("Properties file not found");
        }
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServlet() {
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
		
		String path = "http://localhost:8090/majustory";
		
		System.out.println("===> ApproveServlet 넘어옴 !!" );
		
        String pgToken = request.getParameter("pg_token");
        
        String tid = (String) request.getSession().getAttribute("tid");
        
        System.out.println("===> ApproveServlet pgToken 확인" +  pgToken);
        System.out.println("===> ApproveServlet tid 확인:" + tid );
        
        
        String apiUrl = "https://kapi.kakao.com/v1/payment/approve";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK " + ADMIN_KEY);
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);

        System.out.println("===>ApproveServlet conn 확인: " + conn );
        
        String params = "cid=" + CID +
                        "&tid=" + tid +
                        "&partner_order_id=1001" +
                        "&partner_user_id=user_123" +
                        "&pg_token=" + pgToken;

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        Scanner sc;
        if (code == 200) {
            sc = new Scanner(conn.getInputStream());
            System.out.println("===>ApproveServlet sc 성공 확인: " + sc );
        } else {
            sc = new Scanner(conn.getErrorStream());
            System.out.println("===>ApproveServlet sc 실패 확인: " + sc );
        }
        
        StringBuilder result = new StringBuilder();
        while (sc.hasNext()) {
            result.append(sc.nextLine());
        }
        
        System.out.println("===>ApproveServlet result 확인: " + result );
        
        request.getSession().setAttribute("result", result);
        
        request.getSession().getAttribute("partner_order_id");
        request.getSession().getAttribute("partner_user_id");
        request.getSession().getAttribute("total_amount");
        
        System.out.println("partner_order_id:" + request.getSession().getAttribute("partner_order_id"));
        System.out.println("partner_user_id:" + request.getSession().getAttribute("partner_user_id"));
        System.out.println("total_amount:" + request.getSession().getAttribute("total_amount"));
        
        sc.close();
        
        // response.setContentType("application/json;charset=UTF-8");
        // response.getWriter().print(result.toString());
        
        response.sendRedirect(path + "/kakao/list.jsp");
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
