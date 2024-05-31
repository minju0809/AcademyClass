package dataset;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Servlet implementation class DatasetJSONController
 */
public class DatasetJSONController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasetJSONController() {
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
		String sw = request.getParameter("sw");
		
		if(sw.equals("SI")) {
			System.out.println("===> SI");
			
			String URL = "https://api.odcloud.kr/api/3070507/v1/uddi:c3ae1435-165c-42f4-bf8d-cc26f68e71f6";
			String SERVICEKEY = "Rlv%2FLwLil1lc5xKQg7CBwm%2BcvuksrkTpcQuQozGJvXsRYmOryjgMjlL4ou%2BfxTjfI%2F%2BJAO7uBo1vVrIWnAhSmQ%3D%3D";
	        String RESULTTYPE ="json" ;
	        String PAGENO = "1";
	        String NUMOfRows ="30";
			
			StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICEKEY ); /*Service Key (일반인증키)*/
	        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode(PAGENO, "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode(NUMOfRows, "UTF-8")); 
	        // urlBuilder.append("&" + URLEncoder.encode("addr","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8")); 
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        
	        System.out.println("Response code: " + conn.getResponseCode());
	        
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	       
	        System.out.println(sb);
	        
	        List<StockVO> li = new ArrayList<>();

	         try {
	        	 
	            JSONParser jsonParser = new JSONParser();
	             
	            //  JSON 데이터 예제 
	            // {"response":{"body":{"totalCount":20,"items":[{"imageUrl4":"https:a.png","informCode":"PM10",
	            
	            // JSON데이터를 넣어 JSON Object 로 만들어 준다.
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
	            
	            // 최종 데이터를 묶어 주는 이름 
	            JSONArray item = (JSONArray) jsonObject.get("data");
	 
	            System.out.println("* items *");
	 
	            for(int i=0; i< item.size(); i++){
	 
	                System.out.println("=item_"+i+" ===========================================");
	                 
	                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
	                JSONObject object = (JSONObject) item.get(i);
	                 
	                //JSON name으로 추출
	                System.out.println("Info: 평가액(억 원)==>"+object.get("평가액(억 원)"));
	                System.out.println("Info: 번호==>"+object.get("번호"));
	                System.out.println("Info: 자산군 내 비중(퍼센트)==>"+object.get("자산군 내 비중(퍼센트)"));
	                System.out.println("Info: 종목명==>"+object.get("종목명"));
	                System.out.println("Info: 지분율(퍼센트)==>"+object.get("지분율(퍼센트)"));
	                
	                String idx = String.valueOf(object.get("번호"));
	                String price = String.valueOf(object.get("평가액(억 원)"));
	            	String importance = String.valueOf(object.get("자산군 내 비중(퍼센트)"));
	            	String name = String.valueOf(object.get("종목명"));
	            	String share = String.valueOf(object.get("지분율(퍼센트)"));
	                StockVO m = new StockVO();
	                m.setIdx(idx);
	                m.setPrice(price);
	                m.setImportance(importance);
	                m.setName(name);
	                m.setShare(share);
	                li.add(m);
	                
	            } 
	    
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        request.setAttribute("li", li);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/dataset/stockList.jsp");
	        dispatcher.forward(request, response);
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
