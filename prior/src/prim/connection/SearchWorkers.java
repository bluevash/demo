package prim.connection;

import prim.resources.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class SearchWorkers
 */
public class SearchWorkers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Database dataConnect = new Database();
	JavaBean eBean = new JavaBean();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchWorkers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		eBean.resetListOfWorkers();
		
		try {
			  File file 					= new File("C:/sandBox/introduccion_1/prior/conf/config.xml");
			  DocumentBuilderFactory dbf 	= DocumentBuilderFactory.newInstance();
			  DocumentBuilder db 			= dbf.newDocumentBuilder();
			  Document doc 					= db.parse(file);
			  
			  doc.getDocumentElement().normalize();
			  
			  //System.out.println("Root element " + doc.getDocumentElement().getNodeName());
			  
			  NodeList nodeLst 				= doc.getElementsByTagName("database-configuration");
			  
			  for (int s = 0; s < nodeLst.getLength(); s++) {

			    Node fstNode 				= nodeLst.item(s);
			    
			    if (fstNode.getNodeType() == Node.ELEMENT_NODE) 
			    {
			      Element  fstElmnt 		= (Element) fstNode;
			      
			      NodeList NmElmntLst 	= fstElmnt.getElementsByTagName("postgres-user");
			      Element  NmElmnt 		= (Element) NmElmntLst.item(0);
			      NodeList Nm 			= NmElmnt.getChildNodes();
			      dataConnect.setPostgresUser( ((Node) Nm.item(0)).getNodeValue() );
			      
			      NmElmntLst 	= fstElmnt.getElementsByTagName("postgres-pass");
			      NmElmnt 		= (Element) NmElmntLst.item(0);
			      Nm 			= NmElmnt.getChildNodes();
			      dataConnect.setPostgresPass( ((Node) Nm.item(0)).getNodeValue() );
			      
			      NmElmntLst 	= fstElmnt.getElementsByTagName("db-ip");
			      NmElmnt 		= (Element) NmElmntLst.item(0);
			      Nm 			= NmElmnt.getChildNodes();
			      dataConnect.setDbIp( ((Node) Nm.item(0)).getNodeValue() );

			      NmElmntLst 	= fstElmnt.getElementsByTagName("db-port");
			      NmElmnt 		= (Element) NmElmntLst.item(0);
			      Nm 			= NmElmnt.getChildNodes();
			      dataConnect.setDbPort( ((Node) Nm.item(0)).getNodeValue() );

			      NmElmntLst 	= fstElmnt.getElementsByTagName("db-name");
			      NmElmnt 		= (Element) NmElmntLst.item(0);
			      Nm 			= NmElmnt.getChildNodes();
			      dataConnect.setDbName( ((Node) Nm.item(0)).getNodeValue() );
			      
			      //System.out.println(dataConnect.getConectionUrl());
			    }

			  }
			  } catch (Exception e) {
			    e.printStackTrace();
			  }
		//
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		ResultSet rs;	 
		Worker tempWorker = new Worker();
		ArrayList<Worker> workerListDelete = new ArrayList<Worker>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(dataConnect.getConectionUrl(), dataConnect.getPostgresUser(), dataConnect.getPostgresPass() );
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement st;
		try {
			st = conn.createStatement();

			//Get all values
			rs = st.executeQuery(
					"SELECT W.WORKER_ID_CODE, W.WORKER_NAME, W.WORKER_MAIN_PHONE, ST.STATUS_NAME " +
					"FROM WORKER W " +
					"INNER JOIN STATUS ST " +
					"ON ST.STATUS_CODE = W.WORKER_STATUS;");

			int k = 0;
			while (rs.next())
			{
				//fill worker
				tempWorker.setRut(rs.getString(1));
				tempWorker.setName(rs.getString(2));
				tempWorker.setPhone(rs.getString(3));
				tempWorker.setStatus(rs.getString(4));
				
				
				//TODO Fill worker list
				//eBean.addWorkerByIndex( k, tempWorker); //Not working!
				workerListDelete.add(k, tempWorker);
				
				tempWorker = new Worker();
				k++;
			} 
			
			rs.close();
			st.close();	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.println("<body>");
		out.println("<body>");
		out.println("<head><link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\"></head>");
		out.println("<html>");
		out.println("<body>");
		out.println(" <br>");
		out.println(" <br>");

		out.println("<center>");
		out.println("<a class=\"btn btn-large btn-primary\" href=\"./worker.jsp\"><i class=\"icon-road\"></i> Volver</a>");
		out.println("</center>");
		
		out.println("<div class=\"alert alert-info\">");
		out.println("Lista generada.");
		out.println("</div>");
		
		//do delete
		out.println("<br>");
		out.println("<br>");
		out.println("<center>");
		
		//workerListDelete = eBean.getAllWorkers(); //duplicates eBean 
		
		for(int i = 0; i < workerListDelete.size(); i++)
		{
			out.println(workerListDelete.get(i).getName());
			out.println(" | ");
			out.println(workerListDelete.get(i).getStatus());
			out.println("<br>");
		}
		out.println("</center>");
		//

		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
