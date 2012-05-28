<style>
.principal{
background-color:#DBE3F7;
border:1px solid ##DBE3F7;
  border-radius:5px;
  -moz-border-radius:3px;
width:400px;
height:auto;
margin:5px;
}
#icono{ 
float:left;
background-image:url(public/img/1334332416_warning_64.png);
width:64px; height:64px;
margin:13px;
}
#advertencia{ 
float:right;
/*border:1px solid #ccc; */
width:280px; height:auto;
margin:13px;
}
#boton{
clear:both;
padding:10px;
}
</style>
<body>
<div class="principal">
	<fieldset style="border: 2px; ">
		<legend>Atenci&oacute;n</legend>
		 <div id="icono"></div>
		 <div id="advertencia">
		   <div align="center">${message }</div>
		 </div>
		 <div id="boton">
		   <div align="center">
		     <input name="cerrar" type="button" id="cerrar" value="cerrar">
		     </div>
		 </div>
	 </fieldset>
</div>