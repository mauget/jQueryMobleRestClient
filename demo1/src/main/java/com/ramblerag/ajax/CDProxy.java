package com.ramblerag.ajax;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

import com.ramblerag.xslt.TransformService;
 
/**
* Servlet implementation class CDProxy
*/
public class CDProxy extends HttpServlet {
       private static final long serialVersionUID = 1L;
 
    /**
     * Default constructor.
     */
    public CDProxy() {
    }
 
       /**
       * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
       */
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              doProxy(request, response);
       }
 
       /**
       * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
       */
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              doProxy(request, response);
       }
 
       private void doProxy(HttpServletRequest request,
                     HttpServletResponse response) throws IOException, ServletException {

           response.setContentType("text/xml; charset=\"utf-8\"");
           PrintWriter out = response.getWriter();
           
//              URL url = new URL("http://datasvcs.railinc.com/rest/1.0/eq/equipment/base/inspection/CP42354-42399/ABT");
              URL url = new URL("http://www.thomas-bayer.com/sqlrest/INVOICE/");
              URLConnection conn = url.openConnection();
              InputStream xmlInput = (InputStream) conn.getContent();

              StreamResult xslResult = new StreamResult(out);
              
              try {
				TransformService.getInstance().transform("xml/transform.xsl", xmlInput, xslResult);
			} catch (TransformerException e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
             
       }
 
}
 