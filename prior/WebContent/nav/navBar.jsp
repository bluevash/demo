<!-- Modal Add Workers -->
<div id="myModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" class="modal hide fade">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 id="myModalLabel">Agregar Empleado</h3>
	</div>
	<form class="form-horizontal" action="WriteWorker" method="post">
		<div class="control-group">
		<label class="control-label" for="inputName">Nombre</label>
		<div class="controls">
		<input type="text" id="inputName" name="name">
		</div>
		</div>
		<div class="control-group">
		<label class="control-label" for="inputRut">RUT</label>
		<div class="controls">
		<input type="text" id="inputRut" name="rut">
		</div>
		</div>
		<div class="control-group">
		<label class="control-label" for="phone">Teléfono</label>
		<div class="controls">
		<input type="text" id="inputLastName" name="phone">
		</div>
		</div>
	  	
		<div class="control-group">
		<label class="control-label" for="inputStatus">Estatus</label>
		<div class="controls">
		<select name="sendStatus">
		<option value="0"> </option>
		<option value="1">Activo</option>
		<option value="2">Inactivo</option>
		</select>
		</div>
		</div>
		<div class="modal-footer">
		<button class="btn" data-dismiss="modal">Cancelar</button>
		<button class="btn btn-primary" type="submit">Guardar</button>
		</div>

	</form>
 </div>
 
<!-- Help -->
<div id="helpModal"  tabindex="-1" role="dialog" aria-labelledby="helpLabel" aria-hidden="true" class="modal hide fade">
	<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h3 id="helpLabel">Ayuda</h3>
	</div>
		<div class="modal-body">
	  		<p>Ayuda variada.</p>
		</div>
	<form class="form-horizontal" action="addWorker.jsp">
	 	<div class="modal-footer">
		<button class="btn btn-primary" data-dismiss="modal">Aceptar</button>
		</div>
	</form>
</div>
	
<!-- menu -->

<ul class="nav nav-pills nav-list nav-stacked">
	<li class="nav-header"><h3>Menu</h3></li>
	
	<li <%= barList.get(0) %>><a href="./user.jsp"><i class="icon-user"></i> Usuarios</a></li>
	<li <%= barList.get(1) %>><a href="./worker.jsp"><i class="icon-road"></i> Empleados</a></li>
	<br><br>
	<li class="divider"></li>
	
	<li <%= barList.get(2) %>><a href="./">
		<i class="icon-home"></i> Inicio</a>
	</li>
	<li <%= barList.get(3) %>><a data-toggle="modal" href="#helpModal"><i class="icon-book"></i> Ayuda</a></li>
</ul>