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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Servlet implementation class DatasetController
 */
public class DatasetXMLController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasetXMLController() {
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
		
		DatasetService	service = new DatasetServiceImpl();
		
		String sw = request.getParameter("sw");

		String URL = "https://apis.data.go.kr/1383000/gmis/mtpcltFamSpcnServiceV2/getMtpcltFamSpcnListV2";
		String SERVICEKEY = "01bKb3tfPlkFegj0dKIICfPK781tVONOmQydOqqV2ZPau6b5hyDMUOZgzjjqABW88IJI082%2BFsbDuow5kDqIcA%3D%3D";
        String RESULTTYPE ="xml" ;
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
        // System.out.println(sb.toString());
        		
		if(sw.equals("I")) {
      
	        Node data1 = null;   
	        Node data2 = null;   
	        Node data3 = null;
	        Node data4 = null;
	        Node data5 = null;
	        Node data6 = null;
	        
	        try {
	        	DocumentBuilderFactory	dbFactory = DocumentBuilderFactory.newInstance();
	        	DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
	        	
	        	FileOutputStream output = new FileOutputStream("./ApiExplorer");
	        	output.write(sb.toString().getBytes("UTF-8"));  // 전체 데이터 읽어 오기 
	        	output.close();
	        	
	        	Document doc = dBuilder.parse("./ApiExplorer");
	        	doc.getDocumentElement().normalize();
	        	
	        	Element body =(Element) doc.getElementsByTagName("body").item(0);
	        	Element items =(Element) body.getElementsByTagName("items").item(0);
	        	
	        	// 배열의 시작은 0번 부터 
	        	
	        	service.deleteAll();
	    		
	        	for(int i=0 ; i<=228 ; i++ ) {
		        	Element item =(Element) items.getElementsByTagName("item").item(i);
		        	
		        	data1 = item.getElementsByTagName("cnterNm").item(0);  // 필요한 데이터 값 추출하기 
		        	data2 = item.getElementsByTagName("cnterChNm").item(0);
		        	data3 = item.getElementsByTagName("roadNmAddr").item(0);
		        	data4 = item.getElementsByTagName("lat").item(0);
		        	data5 = item.getElementsByTagName("lot").item(0);
		        	data6 = item.getElementsByTagName("hmpgAddr").item(0);
		         	
		        	String strData1 = data1.getChildNodes().item(0).getNodeValue();
		        	String strData2 = data2.getChildNodes().item(0).getNodeValue();
		        	String strData3 = data3.getChildNodes().item(0).getNodeValue();
		        	String strData4 = data4.getChildNodes().item(0).getNodeValue();
		        	String strData5 = data5.getChildNodes().item(0).getNodeValue();
		        	String strData6 = data6.getChildNodes().item(0).getNodeValue();
		        	
		        	System.out.println("==>" +(i+1) +"번 :" +strData1+" "+strData2+" "+strData3+" "+strData4+" "+strData5+" "+strData6);
		        			        	
		        	DataVo vo = new DataVo();
		        	vo.setIdx(i+1);
		        	vo.setCnterNm(strData1);
		        	vo.setCnterChNm(strData2);
		        	vo.setHmpgAddr(strData6);
		        	vo.setLat(strData4);
		        	vo.setLot(strData5);
		        	vo.setRoadNmAddr(strData3);		        		        	
		        	service.insert(vo);
	        	}
	        	
	        	} catch (Exception e) {
	            	e.printStackTrace();
	            }
	              	        
			response.sendRedirect(path+"/index.jsp");		
			System.out.println("===> 공공데이터 INSERT 사용하기 ");
			
		}else if(sw.equals("S")) {
			
			  request.setAttribute("li", service.selectAll());
			  
			  RequestDispatcher 	dispatcher 
			    = request.getRequestDispatcher("/dataset/list.jsp");
			  dispatcher.forward(request, response);
						
			System.out.println("===> 공공데이터 Select 사용하기 ");
		}else if(sw.equals("SI")) {
			
			List<DataVo>	li = new ArrayList<>();
		    
	        Node data1 = null;   
	        Node data2 = null;   
	        Node data3 = null;
	        Node data4 = null;
	        Node data5 = null;
	        Node data6 = null;
	        
	        try {
	        	DocumentBuilderFactory	dbFactory = DocumentBuilderFactory.newInstance();
	        	DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
	        	
	        	FileOutputStream output = new FileOutputStream("./ApiExplorer");
	        	output.write(sb.toString().getBytes("UTF-8"));  // 전체 데이터 읽어 오기 
	        	output.close();
	        	
	        	Document doc = dBuilder.parse("./ApiExplorer");
	        	doc.getDocumentElement().normalize();
	        	
	        	Element body =(Element) doc.getElementsByTagName("body").item(0);
	        	Element items =(Element) body.getElementsByTagName("items").item(0);
	        	
	        	// 배열의 시작은 0번 부터 
	        	
	        	service.deleteAll();
	    		
	        	for(int i=0 ; i<=228 ; i++ ) {
		        	Element item =(Element) items.getElementsByTagName("item").item(i);
		        	
		        	data1 = item.getElementsByTagName("cnterNm").item(0);  // 필요한 데이터 값 추출하기 
		        	data2 = item.getElementsByTagName("cnterChNm").item(0);
		        	data3 = item.getElementsByTagName("roadNmAddr").item(0);
		        	data4 = item.getElementsByTagName("lat").item(0);
		        	data5 = item.getElementsByTagName("lot").item(0);
		        	data6 = item.getElementsByTagName("hmpgAddr").item(0);
		         	
		        	String strData1 = data1.getChildNodes().item(0).getNodeValue();
		        	String strData2 = data2.getChildNodes().item(0).getNodeValue();
		        	String strData3 = data3.getChildNodes().item(0).getNodeValue();
		        	String strData4 = data4.getChildNodes().item(0).getNodeValue();
		        	String strData5 = data5.getChildNodes().item(0).getNodeValue();
		        	String strData6 = data6.getChildNodes().item(0).getNodeValue();
		        	
		        	System.out.println("==>" +(i+1) +"번 :" +strData1+" "+strData2+" "+strData3+" "+strData4+" "+strData5+" "+strData6);
		        			        	
		        	DataVo vo = new DataVo();
		        	vo.setIdx(i+1);
		        	vo.setCnterNm(strData1);
		        	vo.setCnterChNm(strData2);
		        	vo.setHmpgAddr(strData6);
		        	vo.setLat(strData4);
		        	vo.setLot(strData5);
		        	vo.setRoadNmAddr(strData3);
		        	li.add(vo);        	
		        	
	        	}
	        	
	        	} catch (Exception e) {
	            	e.printStackTrace();
	            }
	        
	          request.setAttribute("li", li);
			  
			  RequestDispatcher 	dispatcher 
			    = request.getRequestDispatcher("/dataset/list.jsp");
			  dispatcher.forward(request, response);
			
		}else if(sw.equals("E")) {
		 int idx = Integer.parseInt(request.getParameter("idx"));	
		 DataVo vo =new DataVo();
		 vo.setIdx(idx);
		 		 
		 request.setAttribute("m", service.getEdit(vo));
		 request.setAttribute("key", "108fd5969e1c36f2b7cbd5a913507317");
		 
		 RequestDispatcher 	dispatcher 
		    = request.getRequestDispatcher("/dataset/edit.jsp");
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
