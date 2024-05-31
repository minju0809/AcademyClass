package dataset;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.*;
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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = request.getContextPath();
		String sw = request.getParameter("sw");
		
		Service service = new ServiceImpl();
		
		if(sw.equals("SI")) {
			List<StockVO> li = new ArrayList<>();
			
            String URL = "https://api.odcloud.kr/api/3070507/v1/uddi:c3ae1435-165c-42f4-bf8d-cc26f68e71f6";
            String SERVICEKEY = "Rlv%2FLwLil1lc5xKQg7CBwm%2BcvuksrkTpcQuQozGJvXsRYmOryjgMjlL4ou%2BfxTjfI%2F%2BJAO7uBo1vVrIWnAhSmQ%3D%3D";
            String RESULTTYPE = "XML";
            String PAGENO = "1";
            String NUMOfRows = "20";
            
            StringBuilder urlBuilder = new StringBuilder(URL);
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + SERVICEKEY);
            urlBuilder.append("&" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode(PAGENO, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode(NUMOfRows, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8"));
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
            System.out.println("API Response: " + sb.toString());

            try {
                // XML 파싱
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                InputSource is = new InputSource(new StringReader(sb.toString()));
                Document doc = dBuilder.parse(is);
                doc.getDocumentElement().normalize();

                NodeList items = doc.getElementsByTagName("item");

                for (int i = 0; i < items.getLength(); i++) {
                    Element item = (Element) items.item(i);

                    String strData1 = getColValue(item, "평가액(억 원)");
                    String strData2 = getColValue(item, "번호");
                    String strData3 = getColValue(item, "자산군 내 비중(퍼센트)");
                    String strData4 = getColValue(item, "종목명");
                    String strData5 = getColValue(item, "지분율(퍼센트)");

//                    if(i % 10 == 0) {
//                    	System.out.println("3초 휴식");
//                    	Thread.sleep(3000);
//                    }
                    
                    System.out.println("==>" + (i + 1) + "번 :" + strData1 + " " + strData2 + " " + strData3 + " " + strData4 + " " + strData5);

                    StockVO vo = new StockVO();
                    vo.setPrice(strData1);
                    vo.setIdx(strData2);
                    vo.setImportance(strData3);
                    vo.setName(strData4);
                    vo.setShare(strData5);
                    li.add(vo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("li", li);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dataset/stock.jsp");
            dispatcher.forward(request, response);
        } else if(sw.equals("I")) {
//        	service.stockDeleteAll();
//        	
//            String URL = "https://api.odcloud.kr/api/3070507/v1/uddi:c3ae1435-165c-42f4-bf8d-cc26f68e71f6";
//            String SERVICEKEY = "Rlv%2FLwLil1lc5xKQg7CBwm%2BcvuksrkTpcQuQozGJvXsRYmOryjgMjlL4ou%2BfxTjfI%2F%2BJAO7uBo1vVrIWnAhSmQ%3D%3D";
//            String RESULTTYPE = "XML";
//            String PAGENO = "1";
//            String NUMOfRows = "1175";
//            
//            StringBuilder urlBuilder = new StringBuilder(URL);
//            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + SERVICEKEY);
//            urlBuilder.append("&" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode(PAGENO, "UTF-8"));
//            urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode(NUMOfRows, "UTF-8"));
//            urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8"));
//            URL url = new URL(urlBuilder.toString());
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-type", "application/json");
//            System.out.println("Response code: " + conn.getResponseCode());
//
//            BufferedReader rd;
//            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            } else {
//                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//            }
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                sb.append(line);
//            }
//            rd.close();
//            conn.disconnect();
//            System.out.println("API Response: " + sb.toString());
//
//            try {
//                // XML 파싱
//                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//                InputSource is = new InputSource(new StringReader(sb.toString()));
//                Document doc = dBuilder.parse(is);
//                doc.getDocumentElement().normalize();
//
//                NodeList items = doc.getElementsByTagName("item");
//
//                for (int i = 0; i < items.getLength(); i++) {
//                    Element item = (Element) items.item(i);
//
//                    String strData1 = getColValue(item, "평가액(억 원)");
//                    String strData2 = getColValue(item, "번호");
//                    String strData3 = getColValue(item, "자산군 내 비중(퍼센트)");
//                    String strData4 = getColValue(item, "종목명");
//                    String strData5 = getColValue(item, "지분율(퍼센트)");
//
////                    if(i % 10 == 0) {
////                    	System.out.println("3초 휴식");
////                    	Thread.sleep(3000);
////                    }
//                    
//                    System.out.println("==>" + (i + 1) + "번 :" + strData1 + " " + strData2 + " " + strData3 + " " + strData4 + " " + strData5);
//
//                    StockVO vo = new StockVO();
//                    vo.setPrice(strData1);
//                    vo.setIdx(strData2);
//                    vo.setImportance(strData3);
//                    vo.setName(strData4);
//                    vo.setShare(strData5);
//                    
//                    service.stockInsert(vo);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            
            response.sendRedirect(path + "/StockController?sw=S");
        } else if(sw.equals("S")) {
        	request.setAttribute("li", service.stockSelectAll());
        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/dataset/stockList.jsp");
            dispatcher.forward(request, response);
        }
    }

    private String getColValue(Element item, String colName) {
        NodeList colList = item.getElementsByTagName("col");
        for (int i = 0; i < colList.getLength(); i++) {
            Element col = (Element) colList.item(i);
            if (colName.equals(col.getAttribute("name"))) {
                return col.getTextContent();
            }
        }
        return "";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
