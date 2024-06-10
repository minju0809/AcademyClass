package dataset;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class StockController
 */
public class StockController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockController() {
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

		String path = request.getContextPath();
		
		StockService s = new StockServiceImpl();
		
		String sw = request.getParameter("sw");
        
		if (sw.equals("I")) {

		String URL = "http://api.odcloud.kr/api/3070507/v1/uddi:c3ae1435-165c-42f4-bf8d-cc26f68e71f6";
		String SERVICEKEY = "01bKb3tfPlkFegj0dKIICfPK781tVONOmQydOqqV2ZPau6b5hyDMUOZgzjjqABW88IJI082%2BFsbDuow5kDqIcA%3D%3D";
        String RESULTTYPE ="XML" ;
        String PAGENO = request.getParameter("PAGENO");
        String NUMOfRows ="100";
		
		StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICEKEY ); /*Service Key (일반인증키)*/
        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode(PAGENO, "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode(NUMOfRows, "UTF-8")); 
        // urlBuilder.append("&" + URLEncoder.encode("addr","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8")); 
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/XML");
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
        System.out.println(sb.toString());
        	        
	        
	        try {
	        	DocumentBuilderFactory	dbFactory = DocumentBuilderFactory.newInstance();
	        	DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
	        	
	        	FileOutputStream output = new FileOutputStream("./ApiExplorer");
	        	output.write(sb.toString().getBytes("UTF-8"));  // 전체 데이터 읽어 오기 
	        	output.close();
	        	
	        	Document doc = dBuilder.parse("./ApiExplorer");
	        	doc.getDocumentElement().normalize();
	        	
	        	Element items =(Element) doc.getElementsByTagName("data").item(0);

	        	    // 전체삭제 후 insert
	        	    s.delete();
	    		
		        	for(int i=0 ; i< Integer.parseInt(NUMOfRows) ; i++ ) {
		        		
			        	Element item =(Element) items.getElementsByTagName("item").item(i);
			        	
			        	NodeList  cols = item.getElementsByTagName("col") ;
			        	
			        	String [] str = {"","","","",""}; 
			        	for (int j =0 ; j < cols.getLength() ; j++) {
			        	  Element	col = (Element) cols.item(j);
			        	   
			        	  if ( col.getAttribute("name").equals("종목명")) {
			        		  str[0] =  col.getTextContent() ;		        		  
			        	  }
			        	  if ( col.getAttribute("name").equals("지분율(퍼센트)")) {
			        		  str[1] =  col.getTextContent() ;		        		  
			        	  }
			        	  if ( col.getAttribute("name").equals("평가액(억 원)")) {
			        		  str[2] =  col.getTextContent() ;		        		  
			        	  }
			        	  if ( col.getAttribute("name").equals("번호")) {
			        		  str[3] =  col.getTextContent() ;		        		  
			        	  }
			        	  if ( col.getAttribute("name").equals("자산군 내 비중(퍼센트)")) {
			        		  str[4] =  col.getTextContent() ;		        		  
			        	  }	        	  
			        	 
			        	} 	
			        	
			        	StockVO vo =new StockVO();
			        	vo.setIdx(str[3]);
			        	vo.setCol1(str[0]);
			        	vo.setCol2(str[1]);
			        	vo.setCol3(str[2]);
			        	vo.setCol4(str[4]);
			        	s = new StockServiceImpl();
			        	s.insert(vo);
			        	System.out.println(str[0] +" ," + str[1] +" ,"  +str[2] +" ,"  +str[3] +" ,"  +str[4] );
			            
		        	}
	        	
	        	} catch (Exception e) {
	            	e.printStackTrace();
	            }
	              	        
			response.sendRedirect(path+"/index.jsp");		
			System.out.println("===> 공공데이터 INSERT 사용하기 ");
			
		} else if (sw.equals("S")) {
		  				
			request.setAttribute("li", s.selectAll(null));
			 
			RequestDispatcher 	dispatcher 
			    = request.getRequestDispatcher("/dataset/stock_list.jsp");
			dispatcher.forward(request, response);
			
			
		} else if (sw.equals("JSON")) {
			

			String URL = "http://api.odcloud.kr/api/3070507/v1/uddi:c3ae1435-165c-42f4-bf8d-cc26f68e71f6";
			String SERVICEKEY = "01bKb3tfPlkFegj0dKIICfPK781tVONOmQydOqqV2ZPau6b5hyDMUOZgzjjqABW88IJI082%2BFsbDuow5kDqIcA%3D%3D";
	        String RESULTTYPE ="JSON" ;
	        String PAGENO = request.getParameter("PAGENO");
	        String NUMOfRows ="60";
			
			StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICEKEY ); /*Service Key (일반인증키)*/
	        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode(PAGENO, "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode(NUMOfRows, "UTF-8")); 
	        // urlBuilder.append("&" + URLEncoder.encode("addr","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8")); 
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/XML");
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
	        System.out.println(sb.toString());
		     
	        
	        List<StockVO> li = new ArrayList<StockVO>();
	         try {
	        	 
	             JSONParser jsonParser = new JSONParser();
	              
	             //  JSON 데이터 예제 
	             // {"currentCount":100,"data":[
	             // {"번호":1,"자산군 내 비중(퍼센트)":"20.04","종목명":"삼성전자","지분율(퍼센트)":"7.53","평가액(억 원)":248521},
	             // {"번호":2,"자산군 내 비중(퍼센트)":"4.42","종목명":"LG에너지솔루션","지분율(퍼센트)":"5.37","평가액(억 원)":54757},
	             
	             // JSON데이터를 넣어 JSON Object 로 만들어 준다.
	             JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
	             JSONArray item = (JSONArray) jsonObject.get("data");
	             // 최종 데이터를 묶어 주는 이름 	          
	             System.out.println("* items *");
	             
	             StockVO  m ;
	             for(int i=0; i< item.size(); i++){
	            	 
	            	 m = new StockVO();
	            	 
	                 System.out.println("=item_"+i+" ===========================================");
	                 //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
	                 JSONObject object = (JSONObject) item.get(i);
	                 //JSON name으로 추출
	                 
	                 /*
	                 System.out.println("Info: 번호==>"+object.get("번호"));
	                 System.out.println("Info: 종목명==>"+object.get("종목명"));
	                 System.out.println("Info: 지분율(퍼센트)==>"+object.get("지분율(퍼센트)"));
	                 System.out.println("Info: 평가액(억 원)==>"+object.get("평가액(억 원)"));
	                 System.out.println("Info: 자산군 내 비중(퍼센트)==>"+object.get("자산군 내 비중(퍼센트)"));
	                 */
	                 
	                String a1 =  String.valueOf(object.get("번호"));
	                String a2 =  String.valueOf(object.get("종목명"));
	                String a3 =  String.valueOf(object.get("지분율(퍼센트)"));
	                String a4 =  String.valueOf(object.get("평가액(억 원)"));
	                String a5 =  String.valueOf(object.get("자산군 내 비중(퍼센트)"));
	                 
	                 m.setIdx(a1);
	                 m.setCol1(a2);
	                 m.setCol2(a3);
	                 m.setCol3(a4);
	                 m.setCol4(a5);
	                 
	                 System.out.println("==> " + m);
	                 
	                 li.add(m);
	             } 
	     
	         } catch (Exception e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	         }
	            
				request.setAttribute("li", li);
				 
				RequestDispatcher 	dispatcher 
				    = request.getRequestDispatcher("/dataset/stock_list.jsp");
				dispatcher.forward(request, response);
			
		} else if (sw.equals("JSON2")) {			

			String URL = "http://api.odcloud.kr/api/3070507/v1/uddi:c3ae1435-165c-42f4-bf8d-cc26f68e71f6";
			String SERVICEKEY = "01bKb3tfPlkFegj0dKIICfPK781tVONOmQydOqqV2ZPau6b5hyDMUOZgzjjqABW88IJI082%2BFsbDuow5kDqIcA%3D%3D";
	        String RESULTTYPE ="JSON" ;
	        String PAGENO = request.getParameter("PAGENO");
	        String NUMOfRows ="60";
			
			StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICEKEY ); /*Service Key (일반인증키)*/
	        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode(PAGENO, "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode(NUMOfRows, "UTF-8")); 
	        // urlBuilder.append("&" + URLEncoder.encode("addr","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8")); 
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/XML");
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
	        System.out.println(sb.toString());
		     
	        
	        List<StockVO> li = new ArrayList<StockVO>();
	         try {
	        	 
	             JSONParser jsonParser = new JSONParser();
	              
	             //  JSON 데이터 예제 
	             // {"currentCount":100,"data":[
	             // {"번호":1,"자산군 내 비중(퍼센트)":"20.04","종목명":"삼성전자","지분율(퍼센트)":"7.53","평가액(억 원)":248521},
	             // {"번호":2,"자산군 내 비중(퍼센트)":"4.42","종목명":"LG에너지솔루션","지분율(퍼센트)":"5.37","평가액(억 원)":54757},
	             
	             // JSON데이터를 넣어 JSON Object 로 만들어 준다.
	             JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
	             JSONArray item = (JSONArray) jsonObject.get("data");
	             // 최종 데이터를 묶어 주는 이름 	          
	             System.out.println("* items *");
	             
	             StockVO  m ;
	             for(int i=0; i< item.size(); i++){
	            	 
	            	 m = new StockVO();
	            	 
	                 System.out.println("=item_"+i+" ===========================================");
	                 //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
	                 JSONObject object = (JSONObject) item.get(i);
	                 //JSON name으로 추출
	                 
	                 /*
	                 System.out.println("Info: 번호==>"+object.get("번호"));
	                 System.out.println("Info: 종목명==>"+object.get("종목명"));
	                 System.out.println("Info: 지분율(퍼센트)==>"+object.get("지분율(퍼센트)"));
	                 System.out.println("Info: 평가액(억 원)==>"+object.get("평가액(억 원)"));
	                 System.out.println("Info: 자산군 내 비중(퍼센트)==>"+object.get("자산군 내 비중(퍼센트)"));
	                 */
	                 
	                String a1 =  String.valueOf(object.get("번호"));
	                String a2 =  String.valueOf(object.get("종목명"));
	                String a3 =  String.valueOf(object.get("지분율(퍼센트)"));
	                String a4 =  String.valueOf(object.get("평가액(억 원)"));
	                String a5 =  String.valueOf(object.get("자산군 내 비중(퍼센트)"));
	                 
	                 m.setIdx(a1);
	                 m.setCol1(a2);
	                 m.setCol2(a3);
	                 m.setCol3(a4);
	                 m.setCol4(a5);
	                 
	                 System.out.println("==> " + m);
	                 
	                 li.add(m);
	             } 
	     
	         } catch (Exception e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	         }
	            
				request.setAttribute("li", li);
				 
				RequestDispatcher 	dispatcher 
				    = request.getRequestDispatcher("/dataset/stock_list2.jsp");
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
