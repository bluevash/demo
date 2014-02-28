package prim.connection;

import prim.resources.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
 * Servlet implementation class WriteWorker
 */
public class WriteWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Database dataConnect = new Database();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteWorker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection connection = null;

		String name 		= request.getParameter("name");  
		int rut 			= Integer.valueOf(request.getParameter("rut"));
		String phone 		= request.getParameter("phone");  
		int sendStatus 		= Integer.valueOf(request.getParameter("sendStatus"));  
	    
	    PrintWriter out = response.getWriter();

	    try {
	    	// Load the database driver
	    	Class.forName("org.postgresql.Driver");
	    	// Get a Connection to the database
	    	connection = DriverManager.getConnection(dataConnect.getConectionUrl(), dataConnect.getPostgresUser(), dataConnect.getPostgresPass() );
	    	//Add the data into the database
	    	
	    	
	    	PreparedStatement pst = connection.prepareStatement(
	    			"INSERT INTO " +
	    			"WORKER " +
	    			"(WORKER_ID_CODE, WORKER_NAME, WORKER_MAIN_PHONE, WORKER_STATUS) " +
	    			"VALUES ( ?, ?, ?, (SELECT STATUS_CODE FROM STATUS WHERE STATUS_CUSTOM_CODE = ?) )"
	    			);
	    	 
	    	pst.setInt(1,rut);
	    	pst.setString(2,name);
	    	pst.setString(3,phone);
	    	pst.setInt(4,sendStatus);
	    	
	    	int numRowsChanged = pst.executeUpdate();
	    	if(numRowsChanged!=0){
	
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
				out.println("Información almacenada.");
				out.println("</div>");
	
	
				out.println("</body>");
				out.println("</html>");
	    	}
	    	else{
	    	out.println("failed to insert the data");
	    	}	
	    	pst.close();
    	}
    	catch(ClassNotFoundException e){
    		out.println("Couldn't load database driver: " + e.getMessage());
    	}
    	catch(SQLException e){
    		out.println("SQLException caught: " + e.getMessage());
    	}
    	catch (Exception e){
    		out.println(e);
    	}
    	finally {
	    	// Always close the database connection.
	    	try {
	    		if (connection != null) connection.close();
	    	}
	    	catch (SQLException ignored){
	    		out.println(ignored);
	    	}
    	}
	
	//
	}
}