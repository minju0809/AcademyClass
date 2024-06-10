package dataset;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String URL = "https://apis.data.go.kr/1383000/gmis/mtpcltFamSpcnServiceV2/getMtpcltFamSpcnListV2";
		String SERVICEKEY = "01bKb3tfPlkFegj0dKIICfPK781tVONOmQydOqqV2ZPau6b5hyDMUOZgzjjqABW88IJI082%2BFsbDuow5kDqIcA%3D%3D";
        String RESULTTYPE ="json" ;
        String PAGENO = "1";
        String NUMOfRows ="229";
		
		StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICEKEY ); /*Service Key (일반인증키)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(PAGENO, "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(NUMOfRows, "UTF-8")); 
        // urlBuilder.append("&" + URLEncoder.encode("addr","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8")); 
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

         try {
        	 
            JSONParser jsonParser = new JSONParser();
             
            //  JSON 데이터 예제 
            // {"response":{"body":{"totalCount":20,"items":[{"imageUrl4":"https:a.png","informCode":"PM10",
            
            // JSON데이터를 넣어 JSON Object 로 만들어 준다.
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            JSONObject res = (JSONObject) jsonObject.get("response"); 
            JSONObject body = (JSONObject) res.get("body");
            JSONObject items = (JSONObject) body.get("items");
            
            // 최종 데이터를 묶어 주는 이름 
            JSONArray item = (JSONArray) items.get("item");
 
            System.out.println("* items *");
 
            for(int i=0; i< item.size(); i++){
 
                System.out.println("=item_"+i+" ===========================================");
                 
                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                JSONObject object = (JSONObject) item.get(i);
                 
                //JSON name으로 추출
                System.out.println("Info: cnterNm==>"+object.get("cnterNm"));
                System.out.println("Info: cnterChNm==>"+object.get("cnterChNm"));
                System.out.println("Info: roadNmAddr==>"+object.get("roadNmAddr"));
                System.out.println("Info: lat==>"+object.get("lat"));
                System.out.println("Info: lot==>"+object.get("lot"));
                System.out.println("Info: hmpgAddr==>"+object.get("hmpgAddr"));

            } 
    
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }              

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		
	}

}
