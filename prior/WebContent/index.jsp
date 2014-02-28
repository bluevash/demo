<%@page import="sun.awt.geom.AreaOp.AddOp"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page 
		import="java.util.*,prim.resources.*" 
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="es">
<head>
	<title>Inicio</title>
	<link rel="icon" type="image/x-icon" href="/img/fav.ico" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" media="screen">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="bootstrap/js/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" media="screen">	
	
</head>

<body>
<jsp:include page="nav/header.jsp" />
	<jsp:useBean id="eBean" class="prim.resources.JavaBean" scope="session">
	    <%-- --%>
	</jsp:useBean>
<%	
	
	//Menu
	ArrayList<String> barList = new ArrayList<String>();
	
		String bar00 = " ";
		String bar01 = " ";
		String bar02 = "class=\"active\"";
		String bar03 = " ";
		
		barList.add(bar00);
		barList.add(bar01);
		barList.add(bar02);
		barList.add(bar03);		
%>

<div class="container-fluid">
    <div class="row-fluid">

      <div class="span10" style="height:400px">
      	<!-- body -->
        <div class="well well-small pull-right" style="margin-top:360px">Bienvenido - XYZ </div>
      </div>
      
      <div class="span2">
      	<!-- bar -->
      	<!--jsp:include page="nav/navBar.jsp" /-->
      	<%@include file="nav/navBar.jsp"%>
      </div>
    
    </div>
</div>


<jsp:include page="nav/footer.jsp" />
</body>
</html>