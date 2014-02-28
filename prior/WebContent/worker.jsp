<%@page import="sun.awt.geom.AreaOp.AddOp"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page 
		import="java.util.*,prim.resources.*" 
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="es">
<head>
	<title>Empleados</title>
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
		<!--   -->
	</jsp:useBean>
<%
	ArrayList<String> barList = new ArrayList<String>();
	
		String bar00 = " ";
		String bar01 = "class=\"active\"";
		String bar02 = " ";
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
		<%
		ArrayList<Worker> workerList = eBean.getAllWorkers();
		
		if ( workerList.size() == 0 ){
			//Lista de empleados
		
				Worker worker01 = new Worker();
				worker01.setName("Juan");
				worker01.setPhone("443");
				worker01.setRut("12312312-1");
				worker01.setStatus("Activo");
				
				Worker worker02 = new Worker();
				worker02.setName("Pedro");
				worker02.setPhone("512");
				worker02.setRut("12312313-9");
				worker02.setStatus("Pendiente");
				
				Worker worker03 = new Worker();
				worker03.setName("Jose");
				worker03.setPhone("612");
				worker03.setRut("12312313-8");
				worker03.setStatus("Pendiente");
				
			eBean.addWorker(worker01);
			eBean.addWorker(worker01);
			eBean.addWorker(worker01);
			eBean.addWorker(worker01);
			eBean.addWorker(worker01);
			eBean.addWorker(worker02);
			eBean.addWorker(worker02);
			eBean.addWorker(worker02);
			eBean.addWorker(worker02);
			eBean.addWorker(worker02);
			eBean.addWorker(worker03);
			eBean.addWorker(worker03);
			eBean.addWorker(worker03);
			//eBean.addWorker(worker03);
			workerList = eBean.getAllWorkers();
		}
		%>
		<br>
		<!-- Add -->
		
		<section>
			<a class="btn btn-small btn-info" data-toggle="modal" href="#myModal">Agregar empleados</a> 
			<!-- Search -->
			<a class="btn btn-small btn-success" href="SearchWorkers">Refrescar lista</a> 
		</section>
		
		
		<h3>Lista de empleados</h3>
		
		<table class="table table-bordered table-hover">
			<tbody>
			  <thead>
				<tr>
					<th width="30px">#</th>
					<th width="150px">Nombre</th>
					<th width="150px">RUT</th>
					<th width="150px">Teléfono</th>
					<th width="150px">Estatus</th>
					<th width="150px">Seleccionado</th>
				<tr>
			  </thead>
				
			<%
			//Do Place in propper place
			int leftIndex = 0;
			int pageIndex = workerList.size()/5 ;
			int currentPage = 1;
			if ( (workerList.size()%5) != 0 ){
				pageIndex++;
			}
			
			for(int i = leftIndex; i < 5; i++)
			{   %>
				<tr>
					<td><%= i+1 %></td>
					<td><%= workerList.get(i).getName() %></td>
					<td><%= workerList.get(i).getRut() %></td>
					<td><%= workerList.get(i).getPhone() %></td>
					<td><%= workerList.get(i).getStatus() %></td>
					<td><input type="checkbox" name="check" value=<%= i %>></td>
				<tr>
				<% } %>
				
				</tbody>
		</table>
		<div class="pagination pagination-right">
			<ul>
			    <% 				
    			if( currentPage == 1 ){ %>
				<li class="disabled"><a href="#">«</a></li>
			    <%} else { %>
				<li ><a href="#">«</a></li>
			    <%} 
				
				for(int i = 0; i < pageIndex; i++)
				{   
					if(currentPage == i + 1){
					%>
    					<li class="active"><a href="#"><%= i+1 %></a></li>
					<% } else { %> 
						<li><a href="#"><%= i+1 %></a></li>
					<%}
				}
				
    			if( currentPage == pageIndex ){ %>
				<li class="disabled"><a href="#">»</a></li>
				<%} else { %>
				<li ><a href="#">»</a></li>
				<%} %>
    		</ul>
		</div>
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