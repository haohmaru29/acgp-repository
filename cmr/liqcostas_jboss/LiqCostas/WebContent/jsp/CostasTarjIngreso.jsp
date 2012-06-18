<%@ page language='java' contentType="text/html" errorPage="error.jsp?ERR=ERR.GENERAL.PAGE"%>
<%getServletContext().getRequestDispatcher("/jsp/acceso.jsp").include(request,response);%>
<%@page import='java.util.ArrayList'%>
<%@page import='cl.cmr.Proc.General'%>
<%@page import='cl.cmr.Proc.Costas'%>
<%@page import='cl.cmr.Seguridad.SessionUsuario'%>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>

<%  
	int Grupo=5;
	int TipoProducto=3;
		
	RequestDispatcher dispatcher;
	HttpSession sesion = request.getSession(false);
    SessionUsuario  oSes =(SessionUsuario)sesion.getAttribute("usuario");
	
	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
	unusualSymbols.setDecimalSeparator(',');
	unusualSymbols.setGroupingSeparator('.');
		
	DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,##0;(###,###,###,###,###,###,##0)",unusualSymbols);	

	String iAno = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
	String iDia = new java.text.SimpleDateFormat("dd").format(new java.util.Date());
	String iMes = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
				
	String DescripEstado="En Ingreso";
	
	String Origen = request.getParameter("HDDORIGEN")!=null?request.getParameter("HDDORIGEN").trim():"INGRESO";
	String Titulo= request.getParameter("HDDTITULO")!=null?request.getParameter("HDDTITULO").trim():"";	
	if (Titulo.equals(""))
		Titulo=Origen;		
	String IdEntrada = request.getParameter("HDDENTRADA")!=null?request.getParameter("HDDENTRADA").trim():"";
	String NumCosta = request.getParameter("HDDNUMCOSTA")!=null?request.getParameter("HDDNUMCOSTA").trim():"0";
	String CodAbogado = request.getParameter("HDDCODABO")!=null?request.getParameter("HDDCODABO").trim():"";
	String CodSucAbogado = request.getParameter("HDDCODABOSUC")!=null?request.getParameter("HDDCODABOSUC").trim():"";
	String NomAbogado = request.getParameter("HDDNOMABO")!=null?request.getParameter("HDDNOMABO").trim():"";
	String sFechaIng = request.getParameter("HDDFechaIng")!=null?request.getParameter("HDDFechaIng").trim():"";
	String Moneda = request.getParameter("HDDMONEDA")!=null?request.getParameter("HDDMONEDA").trim():"";
	String MontoCosta= request.getParameter("HDDMonto")!=null?request.getParameter("HDDMonto").trim():"0";
	String NumDocumento = request.getParameter("HDDNUMDOCUMENTO")!=null?request.getParameter("HDDNUMDOCUMENTO").trim():"";
	String TipoDocumento = request.getParameter("HDDTIPODOCM")!=null?request.getParameter("HDDTIPODOCM").trim():"";
	String RutPrestador= request.getParameter("HDDRutPrestador")!=null?request.getParameter("HDDRutPrestador").trim():"";
	
	//out.println("Moneda=" + Moneda);	
	//out.println("TipoDocumento=" + TipoDocumento);	
		
	if (sFechaIng.equals(""))
	 sFechaIng=iDia + "/" + iMes + "/" + iAno;
	
	//Datos que vienen desde la consulta
	String CFechaDesde = request.getParameter("CTxtFechaDesde")!=null?request.getParameter("CTxtFechaDesde").trim():"";
	String CFechaHasta = request.getParameter("CTxtFechaHasta")!=null?request.getParameter("CTxtFechaHasta").trim():"";
	String CNumCosta = request.getParameter("CNumCosta")!=null?request.getParameter("CNumCosta").trim():"0";
	String CSucursal = request.getParameter("CSUCURSAL")!=null?request.getParameter("CSUCURSAL").trim():"";
	String CAbogado = request.getParameter("CABOGADO")!=null?request.getParameter("CABOGADO").trim():"";
	String CEstado = request.getParameter("CESTADO")!=null?request.getParameter("CESTADO").trim():"0";	
	String CProducto = request.getParameter("CPRODUCTO")!=null?request.getParameter("CPRODUCTO").trim():"0";
	String CTipoDocumento = request.getParameter("CTIPODOC")!=null?request.getParameter("CTIPODOC").trim():"";
	String CNumProducto = request.getParameter("CNumDocumento")!=null?request.getParameter("CNumDocumento").trim():"0";
	String TopeMonto="0";
	
	General objGeneral = General.getInstance();

	ArrayList resMoneda = new ArrayList();
	ArrayList resAbogado = new ArrayList();
	ArrayList resAux = new ArrayList();
	ArrayList resTipoDoc = new ArrayList();
	ArrayList resEncCosta = new ArrayList();
	ArrayList resDetCosta = new ArrayList();	
				
	boolean retD =false ;
	
	//out.println("Origen=" + Origen);	
	//out.println("IdEntrada=" + IdEntrada);
	//out.println("CTipoDocumento=" + CTipoDocumento);
	//out.println("sFechaIng=" + sFechaIng);
	
	if (IdEntrada.equals("GRABACOSTA") || Origen.equals("INGRESO"))
	{
		retD = objGeneral.ObtieneElemLista("MONEDALQ","0",resMoneda);
		retD = objGeneral.ObtenerListaAbogados(TipoProducto,resAbogado);
		retD = objGeneral.ObtieneElemLista("TIPODOC","0",resTipoDoc);
		TopeMonto=objGeneral.ObtenerParametro("TOPEMONTO");
		
		if (IdEntrada.equals("GRABACOSTA"))
		{
			//obtener el detalle
			Costas objCostas = Costas.getInstance();			
			int Iret = objCostas.ObtieneEncDetCostas(NumCosta,TipoProducto,resEncCosta,resDetCosta);
			//colocar encabezado
			resAux = new ArrayList();
	        resAux = (ArrayList)resEncCosta.get(0);
	        sFechaIng=resAux.get(0).toString().trim();
	        CodAbogado=resAux.get(1).toString().trim();
	        NomAbogado=resAux.get(2).toString().trim();
	        Moneda=resAux.get(3).toString().trim();
	        DescripEstado=resAux.get(5).toString().trim();
	        MontoCosta=df.format(Double.parseDouble(resAux.get(6).toString().trim()));
	        CodSucAbogado=resAux.get(7).toString().trim();
	        TipoDocumento=resAux.get(9).toString().trim();
	        NumDocumento=resAux.get(10).toString().trim();	  
	        RutPrestador=resAux.get(12).toString().trim();	                		
	    }
	}
	else
	{	
		if (IdEntrada.equals("VOLVERBUSQ"))
		{		
			if (Origen.equals("CONSULTAR"))
			{
				dispatcher = getServletContext().getRequestDispatcher("/jsp/CostasConsultar.jsp?TIPOPROD=" + TipoProducto);
				dispatcher.forward(request,response);
			}
		}		
		else
		{
			if (IdEntrada.equals("ERRORSESION"))
			{
				dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp?ERR=ERR.ACCESO.SESSION");
				dispatcher.forward(request,response);
			}
		}
	}
%>

<jsp:include page="principal.jsp" flush="false"/>	
 
  <SCRIPT LANGUAGE="JavaScript">		
  		var costaSaldo= 0;
	 	function InicioPag() 
	 	{		 
	 		document.FRMINGRESOCOS.TxtMensajeError.value="";	 		
	 		<%if (Origen.equals("INGRESO"))
			{%>	
				f.NOMBREOBJETO.value="TxtRutPrestador";
	 			document.FRMINGRESOCOS.TxtRutPrestador.focus();	 										
	 		<%}%> 
	 		document.getElementById("TipoGrilla").value="NOEDITABLE";
	 		document.getElementById("noeditable").style.display='';
			document.getElementById("editable").style.display='none';	
	 	}
		  		  		
		//carga de arreglos		  		
		 var arrMoneda = new Array();
		 var arrPaso;
		<%for(int i=0; i<resMoneda.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resMoneda.get(i);%>
             
    	        arrPaso = new Array("<%=resAux.get(0).toString().trim()%>","<%=resAux.get(1).toString().trim()%>");
        	    arrMoneda[<%=i%>]=arrPaso;        	    	    
		<%
		}%>	
		
		var arrAbogado = new Array();
		<%for(int i=0; i<resAbogado.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resAbogado.get(i);%>
             	//alert("<%=i%>");
             	//alert("<%=resAux.get(0).toString().trim()%>");
        	    arrAbogado[<%=i%>]={codigo: "<%=resAux.get(0).toString().trim()%>", sucursal: "<%=resAux.get(1).toString().trim()%>", nombre: "<%=resAux.get(2).toString().trim()%>"};
		<%}%>	
		
		var arrTipoDoc= new Array();
		<%for(int i=0; i<resTipoDoc.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resTipoDoc.get(i);%>
             
    	        arrPaso = new Array("<%=resAux.get(0).toString().trim()%>","<%=resAux.get(1).toString().trim()%>");
        	    arrTipoDoc[<%=i%>]=arrPaso;        	    	    
		<%
		}%>				
		//fin carga de arreglos	

		function ValidarRutPrestador(obj,confoco)
		{
			obj.TxtMensajeError.value="";
			if (obj.TxtRutPrestador.value!="")
			{
				if (valJS.ValidarRut(obj.TxtRutPrestador,obj.TxtMensajeError)==false)
			    {				    
			    	f.NOMBREOBJETO.value="TxtRutPrestador"; 			    	
					obj.TxtMensajeError.value="Rut Prestador no válido, favor digite nuevamente."; 
					obj.TxtRutPrestador.value="";
					obj.TxtRutPrestador.select();	
					obj.TxtRutPrestador.focus();					
					return false;			
				}
				else
				{
					if (confoco)
					{					
						f.NOMBREOBJETO.value="ABOGADO"; 
						obj.ABOGADO.select();
						obj.ABOGADO.focus();						
					}
					return true;
				}					
			}
			else
			{
				f.NOMBREOBJETO.value="TxtRutPrestador";
				obj.TxtMensajeError.value="Debe ingresar Rut Prestador.";
				obj.TxtRutPrestador.select();	
				obj.TxtRutPrestador.focus();					 		
				return false;
			}
		}
		
		function ValidarNumDocumento(obj,confoco)
		{
			obj.TxtMensajeError.value="";
			if(obj.TxtNumDocum.value=="")
			{	
				f.NOMBREOBJETO.value="TxtNumDocum";
				obj.TxtMensajeError.value="Debe ingresar Número de Documento.";
				obj.TxtNumDocum.select();		
				obj.TxtNumDocum.focus();							
				return false;
			}
			else
			{
				var valor=valJS.quitaMask(obj.TxtNumDocum.value + '');
				if (isNaN(valor/1))
				{
				 	f.NOMBREOBJETO.value="TxtNumDocum"; 			    	
					obj.TxtMensajeError.value="Número Documento debe ser numérico."; 
					obj.TxtNumDocum.value="";
					obj.TxtNumDocum.select();	
					obj.TxtNumDocum.focus();	
					return false;
				}
				else
				{ 
					if (confoco)
					{	
						f.NOMBREOBJETO.value="TxtMonto";
						obj.TxtMonto.select();	
						obj.TxtMonto.focus();								
					}
					return true;
				}	
			}
		}
		
		function ValidarMonto(obj,confoco)
		{
			obj.TxtMensajeError.value="";
			var MontoDoc=obj.TxtMonto.value;
			MontoDoc=valJS.quitaMask(MontoDoc);	
			if (isNaN(MontoDoc/1))
			{
				f.NOMBREOBJETO.value="TxtMonto";
				obj.TxtMensajeError.value="Monto del Documento debe ser númerico.";
				obj.TxtMonto.value="";
				obj.TxtMonto.select();	
				obj.TxtMonto.focus();
				return false;				
			}
			else
			{
				var TopeMonto=parseFloat(<%=TopeMonto%>);	
				if (MontoDoc==0)
				{
					f.NOMBREOBJETO.value="TxtMonto";
					obj.TxtMensajeError.value="Monto del Documento debe ser mayor a cero.";
					obj.TxtMonto.select();
					obj.TxtMonto.focus();
					return false;
				}							
				else
				{
					if (MontoDoc>TopeMonto)
					{
						f.NOMBREOBJETO.value="TxtMonto";
						obj.TxtMensajeError.value="Monto del Documento debe ser menor a $" + valJS.Mask(TopeMonto + '',"9.999.999");
						obj.TxtMonto.select();
						obj.TxtMonto.focus();
						return false;
					}
					else
						return true;
				}
			}					
		}
		
		function ValidaCabecera(obj,confoco)
		{
			if (obj.TxtRutPrestador.disabled==false) 
			{
				if (ValidarRutPrestador(obj,confoco))
					if (validarCodAbogado(confoco))
						if (validarNombreAbogado(confoco))
							if (ValidarNumDocumento(obj,confoco))
								if (ValidarMonto(obj,confoco))
									return true;
								else
									return false;
							else
								return false;
						else
							return false;		
					else
						return false;
				else
					return false;
			}
			else
				return true;		
		}
		
		function VolverConsulta(obj)
		{
			f.action = "CostasTarjIngreso.jsp";
			f.target = "_self";
			f.HDDENTRADA.value="VOLVERBUSQ";
			f.HDDORIGEN.value="CONSULTAR";
			f.submit();
				
		}
		
		function getPosAbogado(abo) 
		{
		try {
		   abo = valJS.Trim(abo);
		   if (!arrAbogado) return -1;
		   for (i=0;i<arrAbogado.length;i++){
		  	  if (Math.abs(arrAbogado[i].codigo.replace(/^\D*/i,'')) == Math.abs(abo.replace(/^\D*/i,''))) {
		  	  	return i;
		  	  }
		   }
		  } catch (ex){}
		  return -1;
		}	
		
		function getPosNombreAbogado(abo) 
		{
		try {
		   abo = valJS.Trim(abo);
		   var cadena="";
		   if (!arrAbogado) return -1;
		   for (i=0;i<arrAbogado.length;i++){		   	  
		   	  cadena=arrAbogado[i].nombre + ' - ' + arrAbogado[i].sucursal;
		   	  if (cadena == abo) {
		  	  	return i;
		  	  }
		   }
		  } catch (ex){}
		  return -1;
		}	
				
		function validarCodAbogado(confoco)
		{
			var oAbo = f.ABOGADO;
		    if (oAbo.value!="")
			{	
				var iPos = getPosAbogado(oAbo.value);
				if (iPos<0) {	
					f.NOMBREOBJETO.value="ABOGADO";
					f.TxtMensajeError.value = "Código Abogado no se encuentra definido.";
				    f.TxtNomAbo.value="";
					f.HDDNOMABO.value="";
					f.HDDCODABO.value="";
					f.HDDCODABOSUC.value="";
					f.ABOGADO.value="";	
					f.ABOGADO.select();			
					f.ABOGADO.focus();				
					return false;		    
				} else {
					f.ABOGADO.value=arrAbogado[iPos].codigo;
					f.TxtNomAbo.value=arrAbogado[iPos].nombre + ' - ' + arrAbogado[iPos].sucursal;
					f.HDDNOMABO.value=arrAbogado[iPos].nombre;
					f.HDDCODABO.value=arrAbogado[iPos].codigo;
					f.HDDCODABOSUC.value=arrAbogado[iPos].sucursal;	
					if (confoco)
					{
						f.NOMBREOBJETO.value="TxtNomAbo";
						f.TxtNomAbo.select();
						f.TxtNomAbo.focus();
					}
					return true;
					}
				}
			else
			{
				if (confoco)
				{
					f.NOMBREOBJETO.value="TxtNomAbo";
					f.TxtNomAbo.select();
					f.TxtNomAbo.focus();
				}
				return true;
			}
		}
		
		function validarNombreAbogado(confoco)
		{
		    var oAbo = f.TxtNomAbo;
		 	if (oAbo.value!="")
			{
				var iPos = getPosNombreAbogado(oAbo.value);
				if (iPos<0) {					
					f.NOMBREOBJETO.value="TxtNomAbo";
				    f.TxtMensajeError.value = "Nombre Abogado no se encuentra definido.";
				    f.TxtNomAbo.value="";
					f.HDDNOMABO.value="";
					f.HDDCODABO.value="";
					f.HDDCODABOSUC.value="";
					f.ABOGADO.value="";	
					f.TxtNomAbo.select();		
					f.TxtNomAbo.focus();			
					return false;		    
			} else {
					f.ABOGADO.value=arrAbogado[iPos].codigo;
					f.TxtNomAbo.value=arrAbogado[iPos].nombre + ' - ' + arrAbogado[iPos].sucursal;
					f.HDDNOMABO.value=arrAbogado[iPos].nombre;
					f.HDDCODABO.value=arrAbogado[iPos].codigo;
					f.HDDCODABOSUC.value=arrAbogado[iPos].sucursal;	
					if (confoco)
					{
						f.NOMBREOBJETO.value=""; 
						f.TIPDOC.focus();
					}
					return true;
				}
			}
			else
			{
				if (f.ABOGADO.value=="")
				{
					f.NOMBREOBJETO.value="ABOGADO";
					f.TxtMensajeError.value = "Debe ingresar Código Abogado.";
					f.ABOGADO.select();	
					f.ABOGADO.focus();			
					return false;
				}				
				else
					return true;		
			}
		}
		//fin funciones de validacion
		
		//funciones de seleccion
		function SelLista(obj,objarr,objvalor)
		 { 	
			var Dato="";	
			f.TxtMensajeError.value="";			
			for (added = 0; added <obj.options.length; added++) 
			{
				if (obj.options[added].selected==true)
				{
					for (lix = 0; lix < objarr.length; lix++) 
					{
						if (objarr[lix][0] == obj.options[added].value)
						{
							Dato=objarr[lix][0];
							break;
						}	
					}
					break;
				}
			}
			objvalor.value=	Dato;					
		}
			
		//fin funciones de seleccion
						
		//imprimir
	    function ImprimirDetalle(flag) { 
			f.RESPBD.value=flag;
			f.TxtMensajeError.value="";
	  		f.target = "printInforme";
			f.action = "CostasImprimeCosta.jsp";			
			f.submit();		
		}
  		
  		function printPage(ifr) 
 		{
			if (ifr.location.href != "about:blank")
			{
	   		 ifr.focus(); 
	   		 ifr.print();	   		
			}
  		}
		//fin imprimir		
		
		
		//DATOS GRILLA		
		var TypeMaskNum="999.999.999.999";
		var opciones= {'': ''};
		var vardefault='';
		var __TEST_DATA__=[];
		
		<%if (resDetCosta.size()>0)
		 {
			for(int i=0; i<resDetCosta.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resDetCosta.get(i);
             	if (i < resDetCosta.size()-1)
        		{%>             
    	        arrPaso = new Array("<%=resAux.get(4).toString().trim()%>",valJS.MascaraRut("<%=resAux.get(0).toString().trim()%>"),"<%=resAux.get(1).toString().trim()%>","<%=df.format(Double.parseDouble(resAux.get(2).toString().trim()))%>","<%=resAux.get(3).toString().trim()%>","<%=resAux.get(5).toString().trim()%>","<%=resAux.get(6).toString().trim()%>","<%=resAux.get(7).toString().trim()%>");
        	    __TEST_DATA__[<%=i%>]=arrPaso;        	    	    
		
			  <%}
			}	  
		}
		else {%>	
			__TEST_DATA__=
					[
					["1","","",0,"","","",""]
					];		
		<%}%>
	
		var grid_demo_id = "myGrid11" ;
		
		var dsOption= {
			uniqueField : 'ide' ,	
			fields :[
				{name : 'ide'  , type: 'int' },
				{name : 'rut'  },
				{name : 'operacion'	},
				{name : 'ValCosta' },
				{name : 'TipCargo' },
				{name : 'SubTipCargo' },
				{name : 'NumJuicio' },
				{name : 'TipoJuicio' },
				{name : 'respaldoSelect' }
			],
	
			recordType : 'array',
			data : __TEST_DATA__
		}
		
		
		var colsOption = [
				 {id: 'ide' , header: "Id" , width :30, sortable:false,editable:false, editor:{type:"text"}},	
			     {id: 'rut' , header: "Rut Cliente" , width :200, sortable:false, editable:false, editor:{type:"text"} },
			     {id: 'operacion' , header: "Numero Operacion" , width :300, sortable:false, editable:false,
			      editor : { type :"select" , options : opciones, defaultText : ''}},
	  	     {id: 'ValCosta' , header: "Valor Costa" , width :200, sortable:false, editable:false, editor:{type:"text"} },
				   {id: 'TipCargo' , header: "Tipo Cargo" , width :150, sortable:false, editable:false, 
				   	editor:{type:"text"} },
				   {id: 'SubTipCargo' , header: "Descripción Costa" , width :305, sortable:false, editable:false, 
				   	editor:{type:"text"}},
				   {id: 'NumJuicio' , header: "N° Juicio" , width :1, sortable:false, editable:false, 
				   	editor:{type:"text"}},
				   {id: 'TipoJuicio' , header: "Tipo Juicio" , width :1, sortable:false, editable:false, 
				   	editor:{type:"text"}}		
			];
			
			var gridOption={
				id : grid_demo_id,
				width: "1190px",  //"100%", // 700,
				height: "300px",  //"100%", // 330,
				container : 'gridbox1', 
				replaceContainer : true, 
				dataset : dsOption ,
				columns : colsOption,
				pageSize : 400,
				//toolbarContent : 'nav | reload | del save',
				stripeRows: false,
				toolbarContent:false,
				autoEditNext: false,
				showIndexColumn:false,
				showPageState : false ,
				allowResizeColumn : false,
				resizable : false,
				multiSort : false ,
				multiGroup : false ,
				multiSelect : false,
				showAddTool    : true ,
				showDelTool    : true ,
				showReloadTool : true ,
				submitUpdatedFields : false,
				allowCustomSkin	: true ,
				editable : false,	
				skin : "vista",
			 	defaultRecord : {
						Ide : '-',
						rut : '',	
						operacion : '',						
						ValCosta : 0,
						TipCargo : '',
						SubTipCargo : '',
						NumJuicio:'',	
						TipoJuicio:'',
						respaldoSelect: ''					
				}				
			};
			
			var mygrid1=new Sigma.Grid( gridOption );		
			Sigma.Util.onLoad( Sigma.Grid.render(mygrid1));
			

/*function my_renderer(value ,record,columnObj,grid,colNo,rowNo)
{
	var no= record[columnObj.fieldIndex] + '';
	var color = "black";
	return "<span style=\"color:" + color +";\"><strong>" + no + "</strong></span>";
}*/

		__TEST_DATA__=
		[
		["1","","",0,"","","",""]
		];

		dsOption= {
			uniqueField : 'ide' ,	
			fields :[
				{name : 'ide'  , type: 'int' },
				{name : 'rut'  },
				{name : 'operacion'	},
				{name : 'ValCosta' },
				{name : 'TipCargo' },
				{name : 'SubTipCargo' },
				{name : 'NumJuicio' },
				{name : 'TipoJuicio' },
				{name : 'respaldoSelect' }
			],
	
			recordType : 'array',
			data : __TEST_DATA__
		}
		
		
		colsOption = [
				 {id: 'ide' , header: "Id" , width :30, sortable:false,editable:false, editor:{type:"text"}},	
			     {id: 'rut' , header: "Rut Cliente" , width :200, sortable:false, editable:true, editor:{type:"text",
			     	validator : function(value,record,colObj,grid)
			     	{
			     	if (event.keyCode!=Sigma.Const.Key.UP && event.keyCode!=0) {					     		
			     			var obj=document.getElementById("TxtMensajeError");			     		
			     			var flag=true;
			     		    if (valJS.isRutValido(value)==false) {		
									validResult=[].concat("Rut no válido, favor digite nuevamente."); 
									obj.value=validResult.join('\n');	
									f.NOMBREOBJETO.value="";
									flag=false;							
							} else {
								if(event.keyCode==Sigma.Const.Key.LEFT || event.keyCode==Sigma.Const.Key.DOWN ) {
									mygrid.CambiaValor(value);
									return true;
								} else if(event.keyCode==Sigma.Const.Key.RIGHT) {
									mygrid.CambiaValor(value);
									mygrid.NroCuentasClienteCostasLOCAL(record[8]);
									mygrid.ColocarDataSelectOperacion("R");		
									document.getElementById("RESPBD").value="";
									return true;
								} 
								//validar que tenga juicio asignado el rut ingresado
								mygrid.ObtenerJuicio(value);
								if (obj.value!="") {
									f.NOMBREOBJETO.value="";
									flag=false;	
								}
								else
								{
									if (document.getElementById("RESPBD").value=="-1")
									{
										//error de sesion
										f.action = "CostasTarjIngreso.jsp";
										f.target = "_self";
										f.HDDORIGEN.value="";
										f.HDDENTRADA.value="ERRORSESION";
										f.submit();
									}
									else
									{
										//sacar numero y tipo de juicio y colocarlo en el arreglo	
										//alert("juicio=" + document.getElementById("RESPBD").value);
										var f1=document.getElementById("RESPBD").value.split(",");
										record[6]=f1[0];
										//alert('--->' + f1[1]);
											//record[7]=f1[1];										
										//validar que exista en base de datos	
										mygrid.NroCuentasClienteCostasX(value,document.getElementById("ABOGADO").value,'<%=Grupo%>');
										if (obj.value==document.getElementById("ErrorMen").value || obj.value=='Relación Cuenta Juicio no existe, validar con Sistemas')
										{
											//record[1]="";
											f.NOMBREOBJETO.value="";
											flag=false;	
										}
										else 
										{
											if (document.getElementById("RESPBD").value=="-1")
											{
												//error de sesion
												f.action = "CostasTarjIngreso.jsp";
												f.target = "_self";
												f.HDDORIGEN.value="";
												f.HDDENTRADA.value="ERRORSESION";
												f.submit();
											}
											else
											{
												record[1]=valJS.MascaraRut(value);	
												//cargar datos de numero de operacion			
												mygrid.ColocarDataSelectOperacion("R");		
												document.getElementById("RESPBD").value="";	
												
												//obtener el numero y tipo de juicio del ru ingresado 
											}							
										}
									}
								}		
							}
							return flag;
						}
						 else
						 {
						 		mygrid.CambiaValor(value);
								return true;
						 }		
						}		
			     	} },
			     {id: 'operacion' , header: "Numero Operacion" , width :300, sortable:false, editable:true,
			      editor : { type :"select" , options : opciones, defaultText : '',
			      validator : function(value,record,colObj,grid) {			       
			       	if (event.keyCode!=Sigma.Const.Key.LEFT) { 
			       		if (record[1]!="") {
				  			var obj=document.getElementById("TxtMensajeError");
				  			if(value=="") {
				  				 validResult=[].concat("Debe seleccionar N°operación.");
				  				 obj.value=validResult.join('\n');
				  				 record[2]="";
				  				 f.NOMBREOBJETO.value="";
				  				 return false;
				  			} else {
				  				  if(event.keyCode==Sigma.Const.Key.LEFT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.RIGHT) {
									return true;
								  }
					    		  var datastring = 'rutCliente=' + record[1] + "&codAbogado=" + document.getElementById("ABOGADO").value;
					    		  datastring = datastring + "&codProducto="+value;
					    		  flag=false;
					    		  jQuery.ajax({ 	 	
									   timeout: 60000,
									   dataType: 'JSON',
									   url: "../jsp/AJAX_VALIDA_COSTA_CASEACCT.jsp",
									   data: datastring,
									   beforeSend:function(){},
									   cache: false,
									   success: function(data) {
									    	var objCaseAcct = jQuery.parseJSON(data);
									    	record[7]=objCaseAcct.total;
									    	if(objCaseAcct.total == '-2') {
									    		 validResult=[].concat("Relación Cuenta Juicio no existe ( " + value  +" ), validar con Sistemas");
								  				 obj.value=validResult.join('\n');
								  				 var option2 = record[0];
							     				 record[1] = "";
							     				 record[2] = "";
							     				 record[3] = "";
							     				 record[4] = "";
							     				 record[5] = "";
							     				 record[6] = "";
							     				 record[7] = "";
							     				 costaSaldo=0;
							     				 mygrid.CambiaValor('');
								  				 f.NOMBREOBJETO.value="";
								  				 try {
							     					grid.editCell(grid.gridTable.children[0].rows[(parseInt(option2)-1)].cells[1]);
							     				 } catch(e) {
							     				 }
								  				return false;
									    		
									    	} else {
									    		 jQuery.ajax({ 	 	
													   timeout: 60000,
													   dataType: 'JSON',
													   url: "../jsp/AJAX_ValidaSaldoCosta.jsp",
													   data: datastring,
													   beforeSend:function(){},
													   cache: false,
													   success: function(data) {
													    	var obj5 = jQuery.parseJSON(data);
													    	if(obj5.total==1 ) {
													    		costaSaldo=1;
													    	} else {
													    		costaSaldo=0;
													    	}
															for(cont=0; cont<100;cont++) {}
															if(costaSaldo == 0 ) {
											     				validResult=[].concat("No puede ingresar Costa, Saldo en cero."); 
											     				obj.value=validResult.join('\n');
											     				option = record[0];
											     				record[0] = record[0];
											     				record[1] = "";
											     				record[2] = "";
											     				record[3] = "";
											     				record[4] = "";
											     				record[5] = "";
											     				record[6] = "";
											     				record[7] = "";
											     				costaSaldo=0;
											     				mygrid.CambiaValor('');
											     				try {
											     					grid.editCell(grid.gridTable.children[0].rows[(parseInt(option)-1)].cells[1]);
											     				} catch(e) {
											     				}
											     				
											  				 	f.NOMBREOBJETO.value="";
											  				 	flag=false;
											     				
											     			} else { 
											  					flag=true;
											  				}
										  				return flag;
													  }
										      	 });
									    	}
									  }
						      	 });
				  			}
				  		}		
				    } else {
				       mygrid.CambiaValor(value);	
				       return true; 	
					}				  
				  }     
				  }},
			     {id: 'ValCosta' , header: "Valor Costa" , width :200, sortable:false, editable:true, editor:{type:"text",
			     		validator : function(value,record,colObj,grid) {
			     			if (event.keyCode!=Sigma.Const.Key.LEFT ) { 
				     		  var obj=document.getElementById("TxtMensajeError");
				     		  var flag=true;
				     		  validResult=[].concat("Valor debe ser numérico.");   
				     		  var valor=valJS.quitaMask(value + '');	  
				     		  if (isNaN(valor/1)) {
				     		  	flag=false;	
								obj.value=validResult.join('\n');	
								f.NOMBREOBJETO.value="";
				     		  }
				     		  else {
				     		  	if (valor>0)
				     		  		record[3]=valJS.Mask (value,TypeMaskNum,1);		
				     		  	else { 
				     		  		if (record[1]!="" ) {
				     		  			validResult=[].concat("Valor Costa debe ser mayor a cero."); 
				     		  			obj.value=validResult.join('\n');	
				     		  			record[3]="0";
				     		  			f.NOMBREOBJETO.value="";
				     		  			flag=false;
				     		  		}
				     		  	}	
				     		  }		
				  	  			return flag;
				  	  		}
				  	  		else {
				  	  			mygrid.CambiaValor(value);
				  	  		 	return true;
				  	  		}			
						}		
			     	} },
				   {id: 'TipCargo' , header: "Tipo Cargo" , width :150, sortable:false, editable:true, 
				   	editor:{type:"text",
				   	validator : function(value,record,colObj,grid){
				   	Sigma.tipoCosta = '1';
				   	if (event.keyCode!=Sigma.Const.Key.LEFT) {
				     	var flag=true;
				     	if(costaSaldo == 0 ) {
		     				record[3]=0;
		     			} 
				     	if(record[1]!="" && (value=="" || value=="0")) {
			 				validResult=[].concat("Debe ingresar Tipo de Cargo");   
			 				document.getElementById("TxtMensajeError").value=validResult.join('\n');	
			 				f.NOMBREOBJETO.value="";
			 				flag=false;
			 			}
			 			else {
			 				if(value!="" && value!="0") { 		
				 				mygrid.ValidaTipoCargo('<%=TipoProducto%>',value,record[7]);
				 				if (document.getElementById("RESPBD").value=="-1") {
				 					//error de sesion
									f.action = "CostasTarjIngreso.jsp";
									f.target = "_self";
									f.HDDORIGEN.value="";
									f.HDDENTRADA.value="ERRORSESION";
									f.submit();
				 				}
				 				else {
					 				if (document.getElementById("RESPBD").value=="-2") {
										//record[4]="";record[5]="";
										f.NOMBREOBJETO.value="";
										flag=false;	
									}
									else {
										record[4]=value;	
										record[5]=document.getElementById("RESPBD").value;
										document.getElementById("RESPBD").value="";
									}
								}	
							}	
			 			}	
				  	  	return flag;
				  	 } 			
				  	 else {
				  	 	mygrid.CambiaValor(value);
				  	 	return true; 	
					 }					
					}
				   	} },
				   {id: 'SubTipCargo' , header: "Descripción Costa" , width :305, sortable:false, editable:false, 
				   	editor:{type:"text"}},
				   	{id: 'NumJuicio' , header: "N° Juicio" , width :1, sortable:false, editable:false, 
				   	editor:{type:"text"}},
				   	{id: 'TipoJuicio' , header: "Tipo Juicio" , width :1, sortable:false, editable:false, 
				   	editor:{type:"text"}}
			];
			
			grid_demo_id = "myGrid1" ;
			
			gridOption={
				id : grid_demo_id,
				width: "1190px",  //"100%", // 700,
				height: "300px",  //"100%", // 330,
				container : 'gridbox', 
				replaceContainer : true, 
				dataset : dsOption ,
				columns : colsOption,
				pageSize : 400,
				//toolbarContent : 'nav | reload | del save',
				stripeRows: false,
				toolbarContent:false,
				autoEditNext: true,
				//cambio realizado 01/12/2011
				// false por true
				showIndexColumn: false,
				showPageState : false ,
				allowResizeColumn : false,
				resizable : false,
				multiSort : false ,
				multiGroup : false ,
				multiSelect : false,
				showAddTool    : true ,
				showDelTool    : true ,
				showReloadTool : true ,
				submitUpdatedFields : true,
				allowCustomSkin	: true ,
				skin : "vista",
			 	defaultRecord : {
						Ide : '-',
						rut : '',	
						operacion : '',						
						ValCosta : 0,
						TipCargo : '',
						SubTipCargo : '',
						NumJuicio:'',
						TipoJuicio:''								
				}				
			};
			
			var mygrid=new Sigma.Grid( gridOption );
			Sigma.Util.onLoad( Sigma.Grid.render(mygrid));			
			
			
			function SeleccionarPrimerRegGrilla()
  			{
	  			Sigma.U.stopEvent(event);
				mygrid.unselectAllRow(); //deselecciona la fila le quita el color de seleccion
				mygrid.activeMe();	
				var rows=mygrid.getAllRows();
				mygrid.selectRow(rows[0],true);
				mygrid.activeMe();
				mygrid.editCell(rows[0].cells[1]);
	  		}

//FIN GRILLA
			
			function InhabilitarCampos(valor)
			{
				f.TxtRutPrestador.value=valJS.MascaraRut(f.TxtRutPrestador.value);
				if (f.TxtRutPrestador.disabled==false) 
				{				
					f.TxtRutPrestador.disabled=valor;
					f.ABOGADO.disabled=valor;
					f.TxtNomAbo.disabled=valor;
					f.TIPDOC.disabled=valor;	
					f.MONEDA.disabled=valor;	
					f.TxtNumDocum.disabled=valor;
					f.TxtMonto.disabled=valor;										
				}
			}
		
			function GrabarCosta(obj,event)
			{
				var flagsigue=true;				
				if (ValidaCabecera(f,true))
					flagsigue=true;						
				else
					flagsigue=false;
				
				if (flagsigue)
				{
					//mygrid.printGrid();
					f.NOMBREOBJETO.value=="";	
					var total= 0;
					if(mygrid.validarDatosGrillaRemesa(event) == -1 ){
						SeleccionarPrimerRegGrilla();
						return false;
					}
					
					total=mygrid.validarDatosGrilla(event);
					if (total!=-1)
					{
						var MontoDoc=obj.TxtMonto.value;
						MontoDoc=valJS.quitaMask(MontoDoc);
						if(MontoDoc==total)
						{
							mygrid.GrabarCosta(event,'<%=Grupo%>');	
							var respcosta=parseFloat(f.RESPBD.value);
							if(respcosta>0) {
								f.TxtNumCostas.value=respcosta;
								f.HDDNUMCOSTA.value=respcosta;
								f.HDDRutPrestador.value=f.TxtRutPrestador.value;
								f.HDDMonto.value=MontoDoc;
								f.HDDNUMDOCUMENTO.value=obj.TxtNumDocum.value;		
								f.TxtEstado.value="Ingresada";
								if(MontoDoc!=mygrid.validarDatosGrilla(event) ) {
									alert('Total costas ingresadas es distinto al total de el detalle, favor vuelva a ingresar');
								} else {
									//alert('antes');
									var data = "idSec=" + f.TxtNumCostas.value; 
									jQuery.ajax({ 								
										timeout: 60000,
										dataType: 'JSON',					
							            url: "../jsp/AJAX_ActualizaEncabezadoCosta.jsp",
							            data: data,	
							            async:false,		        
							            success:function(data){	
							            	var obj = jQuery.parseJSON(data);
							            	if (obj.total==-1) {			            	    	
						            	    	validResult=[].concat("Error al grabar Costa, favor vuelva a intentar.");   
						            	    	document.getElementById("TxtMensajeError").value=validResult.join('\n');   
						            	    	return false;
						            	    } 	
							            }, 
							            cache:false
							        });
								
									if (confirm("Desea Imprimir Costa Procesal?"))
										ImprimirDetalle(1);  
									else {	
										f.action = "CostasTarjIngreso.jsp";
										f.target = "_self";
										f.HDDORIGEN.value="";
										f.HDDENTRADA.value="GRABACOSTA";
										f.submit();
									}										
								}
							}
							else
							{
								if (respcosta==-2)//error session
								{								
									f.action = "CostasTarjIngreso.jsp";
									f.target = "_self";
									f.HDDORIGEN.value="";
									f.HDDENTRADA.value="ERRORSESION";
									f.submit();
								}
								else
									SeleccionarPrimerRegGrilla();
							}				
						}
						else
						{
							document.getElementById("TipoGrilla").value="EDITABLE";
							document.getElementById("noeditable").style.display='none';
							document.getElementById("editable").style.display='';
							MontoDoc=MontoDoc -total
							f.TxtMensajeError.value="Total del detalle debe ser igual al Monto total del Documento. Diferencia $ " + valJS.Mask(MontoDoc + '',"999.999.999.999");
							InhabilitarCampos("disabled");	
							SeleccionarPrimerRegGrilla();
						}
					}
				}		
				return false;
			}		
			
			
			function SetearObjeto(valor)
		    {
		    	f.NOMBREOBJETO.value=valor;
		    	return false;
		    }
		    
			document.onclick = function()
			{				
				if (f.NOMBREOBJETO.value!="ABOGADO" && f.NOMBREOBJETO.value.substring(0,3)!="Txt")
					f.NOMBREOBJETO.value="";
			}
			
			document.onkeydown = function()
			{ 
				
		    	if (window.event.keyCode == Sigma.Const.Key.TAB)
		    	{
		    		if (confirm("Desea abandonar ingreso de Costas, se perderán todos los datos ingresados?"))
		    		{	
		    			f.action = "CostasTarjIngreso.jsp";
						f.target = "_self";
						f.HDDORIGEN.value="INGRESO";
						f.HDDENTRADA.value="";
						f.HDDFechaIng.value="";
						f.HDDCODABO.value="";
						f.HDDNOMABO.value="";
						f.HDDCODABOSUC.value="";
						f.HDDMONEDA.value="";
						f.HDDTIPODOCM.value="";
						f.HDDNUMCOSTA.value="";
						f.HDDRutPrestador.value="";
						f.HDDMonto.value="";
						f.HDDNUMDOCUMENTO.value="";
						f.submit();
		    			return true;	
		    		}		    			
		    		else		    	
		    			return false;
		    		
				}
				else
				{
					if (window.event)
					{
						//alert(window.event.keyCode);
						switch (window.event.keyCode){
							case Sigma.Const.Key.ESC: 
							case Sigma.Const.Key.F1: 
							case Sigma.Const.Key.NUMLOCK: 
							case Sigma.Const.Key.F2: 
							case Sigma.Const.Key.F3: 
							case Sigma.Const.Key.ASTE: 
							case Sigma.Const.Key.F4:
							case Sigma.Const.Key.F5:
							case Sigma.Const.Key.F6:
							case Sigma.Const.Key.F7:
							case Sigma.Const.Key.F8:
							case Sigma.Const.Key.F9:
							case Sigma.Const.Key.F10:
							case Sigma.Const.Key.F11:		
							case Sigma.Const.Key.NUMLOCK: 				
							case Sigma.Const.Key.F12:													
								window.event.keyCode = 505; 
								break;
							case Sigma.Const.Key.BACKSPACE:	
								//alert(f.NOMBREOBJETO.value);
								if (f.NOMBREOBJETO.value=="" || f.NOMBREOBJETO.value.substring(0,5)=="rollo")
									window.event.keyCode = 505; 
								break;	
							default:
								if(window.event.keyCode == 97 || window.event.keyCode == 98 || window.event.keyCode == 99 || window.event.keyCode == 100 || window.event.keyCode == 101 || window.event.keyCode == 102 || window.event.keyCode == 103 || window.event.keyCode == 104 || window.event.keyCode == 105 ) {
									if(costaSaldo==0) {
										document.getElementById("TxtMensajeError").value='';										
									}	
								} else if(window.event.keyCode == 49 || window.event.keyCode == 50 || window.event.keyCode == 51 || window.event.keyCode == 52 || window.event.keyCode == 53 || window.event.keyCode == 54 || window.event.keyCode == 55 || window.event.keyCode == 56 || window.event.keyCode == 57 ) {
									if(costaSaldo==0) {
										document.getElementById("TxtMensajeError").value='';										
									}
								}
							break;
						}
						if(window.event && window.event.keyCode == 505) 
	     				{ 
	     					return false;    
	     				}
					}
	     		}	 
			 } 					 				
		</SCRIPT>

<body leftmargin="0" topmargin="0" onload="javascript:InicioPag()" onhelp="return false;" >

<script type="text/javascript">
		 	  	
		  $(document).ready(function () {
		  			//funcion donde llega la selección de la lista de autocompletación
					function findValueCallback(event, data, formatted) {
						//$("<li>").html( !data ? "No match!" : "Selected: " + data.nombre + ' - ' + data.sucursal).appendTo("#result");
						f.TxtMensajeError.value="";
						if (event.target.id=="ABOGADO") {
								$("#TxtNomAbo").attr("value", data.nombre + ' - ' + data.sucursal);	
								f.HDDNOMABO.value=data.nombre;
								f.HDDCODABO.value=data.codigo;
								f.HDDCODABOSUC.value=data.sucursal;	
								f.NOMBREOBJETO.value="TxtNomAbo";   
								$("#TxtNomAbo").select();
								$("#TxtNomAbo").focus();								  	
						}else if (event.target.id=="TxtNomAbo"){
								f.HDDNOMABO.value=data.nombre;
								f.HDDCODABO.value=data.codigo;
								f.HDDCODABOSUC.value=data.sucursal;
								$("#ABOGADO").attr("value", data.codigo);								
						}
					}
					//inicializa autocomplete con códigos del arreglo de abogados 
					$("#ABOGADO").autocomplete(arrAbogado, {
								minChars: 0,
								width: 400,
								//matchContains: "word",
								matchContains: false,
								max: 10, 
								mustMatch: false,
								autoFill: false,
								formatItem: function(row, i, max) {
									//formato de lo que se va a presentar en la lista
									//alert(i);
									//alert(row.nombre);	
									return Math.abs(row.codigo.substring(3)) + " - " + row.nombre + " - " + row.sucursal;
								},
								formatMatch: function(row, i, max) {
									//indica que la busqueda del código debe iniciarse sin ABO
									return (Math.abs(row.codigo.replace(/^\D*/i,'')) + ''); //+  " - " + row.nombre + " - " + row.sucursal;
								},
								formatResult: function(row) {
									//indica como presentar la información, sólo devuelve el código sin caracteres alfabeticos
									return row.codigo; //.replace(/^\D*/i,'');
								}                    						
		    	}).bind($.browser.opera ? "keypress" : "keydown" , function(event){
		    		if ((event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.TAB) && event.target.id=="ABOGADO"){
		    			validarCodAbogado(true);		    				
		    			event.preventDefault();	
	      			}
	      			
      		}).bind("blur", function (){      			
      		     //validarCodAbogado(true);
      		});
      		   	//inicializa el autompletar con el nombre-sucursal del arreglo de abogados
		    	$("#TxtNomAbo").autocomplete(arrAbogado, {
								minChars: 0,
								width: 420,
								matchContains: "word",
								max: 10, 
								mustMatch: false,
								autoFill: false,
								formatItem: function(row, i, max) {
									//formatea lo que aparecerá en la lista
									//alert(i);
									//alert(row.nombre);
									return row.nombre + " - " + row.sucursal;
								},
								formatMatch: function(row, i, max) {
									//indica como debe buscar
									return row.nombre + " - " + row.sucursal;
								},
								formatResult: function(row) {
								    //indica como debe presentar la selección
									return row.nombre + " - " + row.sucursal;
								}                    														
		    	}).bind($.browser.opera ? "keypress" : "keydown" , function(event){
		    		if ((event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT  || event.keyCode==Sigma.Const.Key.TAB ) && event.target.id=="TxtNomAbo"){
		    			//si no existe selección pasar el foco solamente	
		    			validarNombreAbogado(true);				    			 		
		    			event.preventDefault();
	      		}
      		});
		    	$(":text").result(findValueCallback);
				
				
				jQuery("#TxtRutPrestador").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarRutPrestador(f,true);						
						event.preventDefault();
					}
				});
				
				jQuery("#TIPDOC").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT  || event.keyCode==Sigma.Const.Key.TAB )
					{
						f.NOMBREOBJETO.value="TxtNumDocum"; 						
						f.TxtNumDocum.select();
						f.TxtNumDocum.focus();						
						event.preventDefault();
					}
				});
				
				jQuery("#TxtNumDocum").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarNumDocumento(f,true)
						event.preventDefault();
					}
				});
				
				jQuery("#TxtMonto").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT  || event.keyCode==Sigma.Const.Key.TAB )
					{
						if (event.keyCode==Sigma.Const.Key.ENTER)
						{
							if (ValidarMonto(f,true))
							{
								event.preventDefault();
								if (ValidaCabecera(f,true))
								{			
									//COLOCO MASCARA DEL MONTO									
									document.getElementById("TipoGrilla").value="EDITABLE";
									document.getElementById("noeditable").style.display='none';
									document.getElementById("editable").style.display='';
									f.TxtMonto.value=valJS.Mask(f.TxtMonto.value,"9.999.999")
									document.getElementById("NUMFILA").value=0;
									mygrid.refresh();																		
									InhabilitarCampos("disabled");																			
								}
							}	
							else
								f.TxtMonto.select();
						}
					}
				});
				
		    	function prev(arr, index) {
				  	arr.sort(
     					function(a,b){
     		  				return Math.abs(a.attr('tabindex')) < Math.abs(b.attr('tabindex')) ? 1 : -1;
     					}
   					);
   					for (var i=0;i<arr.length;i++){
   					
   						if (Math.abs(arr[i].attr('tabindex'))<Math.abs(index)) return arr[i];
   					}
   					
   					return null;
				}
				
				function next(arr, index) {
				  	arr.sort(
     					function(a,b){
							
     		  				return Math.abs(a.attr('tabindex')) > Math.abs(b.attr('tabindex')) ? 1 : -1;
     					}
   					);
   					for (var i=0;i<arr.length;i++){
   						if (Math.abs(arr[i].attr('tabindex'))>Math.abs(index)) return arr[i];
   					}
   					return null;
				}
				
					
					jQuery(':*[tabindex]').each( function () {
					//if (jQuery(this).attr('type')!='hidden'  && jQuery(this).attr('type') !="select-one" && jQuery(this).attr('type') !="radio" && jQuery(this).attr('type') !="form")
					if (jQuery(this).attr('type')!='hidden'  && jQuery(this).attr('type') !="radio" && jQuery(this).attr('type') !="form")
					if ('select-one,button, text, checkbox'.indexOf(jQuery(this).attr('type'))>=0  || jQuery(this).get(0).nodeName.toUpperCase()=="A") 
					   if (jQuery(this).attr('id').substring(0,5)=="rollo" || (jQuery(this).attr('id')=="TIPDOC") || (jQuery(this).attr('id')=="TxtMonto")) //si son los botones
					   {
					   		var variable1=Sigma.Const.Key.LEFT; 
					   		var variable2=Sigma.Const.Key.RIGHT;  
					   }
					   else
					   {
					  		var variable1=Sigma.Const.Key.LEFT; 
					  		var variable2=Sigma.Const.Key.LEFT; 
					   
				  	   }	
				  	  jQuery(this).bind("keydown", function(event) 
					  {					  	 
					      if (event.keyCode==variable1 || event.keyCode==variable2)
					      {
								event.preventDefault();
								var arr = [];								
								var index = jQuery(this).attr("tabindex");
								if (!index) return false;
								jQuery(':*[tabindex]').each( function () {
								 		if (jQuery(this).attr('id')!=null && jQuery(this).attr('tabindex')!=null && jQuery(this).attr('id')!="TxtMensajeError" && jQuery(this).attr('type')!='hidden' && jQuery(this).attr("disabled")==false)
								 		{							 		
								 		   if ('select-one,button, text, checkbox'.indexOf(jQuery(this).attr('type'))>=0 || jQuery(this).get(0).nodeName.toUpperCase()=="A" )
								 		   {
										   	if (jQuery(this).attr('id')!="")
										   		arr[arr.length]=jQuery(this);										   
										   }
										}
								});	
								
								try {
												  
									if (event.keyCode==Sigma.Const.Key.LEFT)
									{
										 var obj = prev(arr,index );										 
										 if (obj!=null)
										 	if (!obj.focus);
										 	else
										 	{		
										 		f.TxtMensajeError.value ="";
										 		f.NOMBREOBJETO.value=obj.attr('id');	
										 		if (f.NOMBREOBJETO.value=="rollovergrabar")
										 			f.TxtMensajeError.focus();
										 		obj.select();
										 		obj.focus();											 				
										 	}
										 else
										 {										
										 	f.TxtMensajeError.value ="";	
										 	f.NOMBREOBJETO.value=arr[0].attr('id');
										 	if (f.NOMBREOBJETO.value=="rollovergrabar")
										 			f.TxtMensajeError.focus();
										 	arr[0].select();		 	
										 	arr[0].focus();											 				
										 }					  				 
									}									
									else
									{ 
										if (event.keyCode==Sigma.Const.Key.RIGHT)
										{
										 var obj = next(arr,index );
										 if (obj!=null)
										 	if (!obj.focus);
										 	else
										 	{			
										 		f.TxtMensajeError.value ="";	
										 		f.NOMBREOBJETO.value=obj.attr('id');	
										 		obj.select();									 								 		
										 		obj.focus();										 		
										 	}										 
										 else										 
										 {										   
										   f.TxtMensajeError.value ="";		
										   f.NOMBREOBJETO.value=arr[0].attr('id');		
										   arr[0].select();									   
										   arr[0].focus();										  	
									  	 }					  				 
									    }
									}   
								}catch(ex){}
					  	  }//if (event.keyCode==variable1 || event.keyCode==variable2)
					  }); //jQuery(this).bind("keydown", function(event) 					  
				});			
		    }); 		    
		  
		 </script>
<jsp:include page="ColocaMenu.jsp" flush="false"/>
		
<table width="100%" border="0" cellspacing="0" cellpadding="0">

<FORM METHOD="POST" NAME="FRMINGRESOCOS">		
<INPUT TYPE="HIDDEN" ID="HDDENTRADA" NAME="HDDENTRADA" VALUE="<%=IdEntrada%>">
<INPUT TYPE="HIDDEN" ID="HDDORIGEN" NAME="HDDORIGEN" VALUE="<%=Origen%>">
<INPUT TYPE="HIDDEN" ID="HDDTITULO" NAME="HDDTITULO" VALUE="<%=Titulo%>">
<INPUT TYPE="HIDDEN" ID="HDDGRUPO" NAME="HDDGRUPO" VALUE="<%=Grupo%>">
<INPUT TYPE="HIDDEN" ID="HDDFechaIng" NAME="HDDFechaIng" VALUE="<%=sFechaIng%>">
<INPUT TYPE="HIDDEN" ID="HDDCODABO" NAME="HDDCODABO" VALUE="<%=CodAbogado%>">
<INPUT TYPE="HIDDEN" ID="HDDNOMABO" NAME="HDDNOMABO" VALUE="<%=NomAbogado%>">
<INPUT TYPE="HIDDEN" ID="HDDCODABOSUC" NAME="HDDCODABOSUC" VALUE="<%=CodSucAbogado%>">
<INPUT TYPE="HIDDEN" ID="HDDMONEDA" NAME="HDDMONEDA" VALUE="<%=Moneda%>">
<INPUT TYPE="HIDDEN" ID="HDDTIPODOCM" NAME="HDDTIPODOCM" VALUE="<%=TipoDocumento%>">
<INPUT TYPE="HIDDEN" ID="HDDNUMCOSTA" NAME="HDDNUMCOSTA" VALUE="<%=NumCosta%>">
<INPUT TYPE="HIDDEN" ID="HDDRutPrestador" NAME="HDDRutPrestador" VALUE="<%=RutPrestador%>">
<INPUT TYPE="HIDDEN" ID="HDDMonto" NAME="HDDMonto" VALUE="<%=MontoCosta%>">
<INPUT TYPE="HIDDEN" ID="HDDNUMDOCUMENTO" NAME="HDDNUMDOCUMENTO" VALUE="<%=NumDocumento%>">
<INPUT TYPE="HIDDEN" ID="CTxtFechaDesde" NAME="CTxtFechaDesde" VALUE="<%=CFechaDesde%>">
<INPUT TYPE="HIDDEN" ID="CTxtFechaHasta" NAME="CTxtFechaHasta" VALUE="<%=CFechaHasta%>">
<INPUT TYPE="HIDDEN" ID="CNumCosta" NAME="CNumCosta" VALUE="<%=CNumCosta%>">
<INPUT TYPE="HIDDEN" ID="CSUCURSAL" NAME="CSUCURSAL" VALUE="<%=CSucursal%>">
<INPUT TYPE="HIDDEN" ID="CABOGADO" NAME="CABOGADO" VALUE="<%=CAbogado%>">
<INPUT TYPE="HIDDEN" ID="CESTADO" NAME="CESTADO" VALUE="<%=CEstado%>">
<INPUT TYPE="HIDDEN" ID="CPRODUCTO" NAME="CPRODUCTO" VALUE="<%=CProducto%>">
<INPUT TYPE="HIDDEN" ID="CTIPODOC" NAME="CTIPODOC" VALUE="<%=CTipoDocumento%>">
<INPUT TYPE="HIDDEN" ID="CNumDocumento" NAME="CNumDocumento" VALUE="<%=CNumProducto%>">


<INPUT TYPE="HIDDEN" id="NUMFILA"  NAME="NUMFILA" VALUE="-1">
<INPUT TYPE="HIDDEN" id="NUMREG"  NAME="NUMREG" VALUE="1">
<INPUT TYPE="HIDDEN" id="NUMREGTOTAL"  NAME="NUMREGTOTAL" VALUE="0">
<INPUT TYPE="HIDDEN" id="CampoEnter"  NAME="CampoEnter" VALUE="3">
<INPUT TYPE="HIDDEN" id="ErrorMen"  NAME="ErrorMen" VALUE="No existen cuentas asignadas al Abogado para Rut Cliente Ingresado.">
<INPUT TYPE="HIDDEN" id="RESPBD"  NAME="RESPBD" VALUE="">
<INPUT TYPE="HIDDEN" id="TIPOPROD"  NAME="TIPOPROD" VALUE="<%=TipoProducto%>">
<INPUT TYPE="HIDDEN" id="TIPOPRODFORMULARIO"  NAME="TIPOPRODFORMULARIO" VALUE="1">
<INPUT TYPE="HIDDEN" id="NOMBREOBJETO"  NAME="NOMBREOBJETO" VALUE="">
<INPUT TYPE="HIDDEN" id="CantidadFilas"  NAME="CantidadFilas" VALUE="300">
<INPUT TYPE="HIDDEN" id="TipoGrilla"  NAME="TipoGrilla" VALUE="NOEDITABLE">
<INPUT TYPE="HIDDEN" id="Seleccion"  NAME="Seleccion" VALUE="ACTIVA">
	
 <% 
	String MontoStr=MontoCosta.replace(".","").replace(",","");
	if (MontoStr.equals(""))
		MontoStr="";
	else
		MontoStr=df.format(Double.parseDouble(MontoStr.trim()));	
%>		
<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablaprincipal">
  <tr>
   <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
       	<tr><td align="CENTER" class="titulo"><%=Titulo%> COSTAS PROCESALES TARJETA</td></tr>
        <tr>
          <td align="center">
            <div class="tablainterior2">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablagris">
                 <tr>
		            <td class="labelUno" width="10%">User</td>
		            <td class="labelDos" width="10%"><input tabindex="1" id="TxtUser" name="TxtUser" style="width: 100%" type="text" disabled="disabled" value="<%=oSes.getUsuario()%>"/></td>	            
		            <td class="labelUno" width="10%">Nombre Usuario</td>
		            <td class="labelDos" width="25%" colspan="3"><input tabindex="2" id="TxtNomUser" name="TxtNomUser" style="width: 100%" type="text" disabled="disabled" value="<%=oSes.getNombre()%>"/></td>
		            <td class="labelUno" width="12%" >Rut Prestador</td>
		            <td class="labelDos" width="8%"><input tabindex="3" id="TxtRutPrestador" name="TxtRutPrestador" style="width: 100%" type="text" maxlength="15" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtRutPrestador');" value="<%=RutPrestador%>"/></td>		            
						   
				</tr>				
				<tr>
		            <td class="labelUno" width="10%" >Código Abogado</td>
		            <td class="labelDos" width="10%"><input type="text" name="ABOGADO" id="ABOGADO" tabindex="5" maxlength="10" style="width: 100%;margin:auto;" value="<%=CodAbogado%>" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('ABOGADO');"></td>	            
		            <td class="labelUno" width="10%">Nombre Abogado</td>
		            <td class="labelDos" width="25%" colspan="3" >
		            <%if (NomAbogado.equals(""))
		            {%>
		            	<input id="TxtNomAbo" tabindex="6" name="TxtNomAbo" maxlength="60" style="width: 100%" type="text" value="" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtNomAbo');"/></td>
		            <%}		            
		            else		            
		            {%>
		            	<input id="TxtNomAbo" name="TxtNomAbo" style="width: 100%" maxlength="60" type="text" value="<%=(NomAbogado + " - " + CodSucAbogado)%>" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtNomAbo');"/></td>
		            <%}%>
		            </td>
		          	<td class="labelUno" width="12%" >Fecha Digitación</td>
		            <td class="labelDos" width="8%"><input tabindex="7" id="TxtFechaDig" name="TxtFechaDig" style="width: 100%" type="text" disabled="disabled" value="<%=sFechaIng%>"/></td>	   
				</tr>
				
				<tr>
		            <td class="labelUno" width="10%" >Tipo Documento</td>
		            <td class="labelDos" width="10%">
		               <SELECT NAME="TIPDOC" ID="TIPDOC" tabindex="8" class="select1" onChange="javascript:SelLista(this,arrTipoDoc,window.document.FRMINGRESOCOS.HDDTIPODOCM);"></SELECT>
						<SCRIPT LANGUAGE="JavaScript">
								valJS.LlenarLista(arrTipoDoc,window.document.FRMINGRESOCOS.TIPDOC,"N","<%=TipoDocumento%>",""); 
						</SCRIPT>
		            </td>	            
		            <td class="labelUno" width="10%" >N° Documento</td>
		            <td class="labelDos" width="25%" colspan="3" ><input tabindex="9" id="TxtNumDocum" name="TxtNumDocum" type="text" maxlength="10" size="16" value="<%=NumDocumento%>" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtNumDocum');"/></td>
		            <td class="labelUno" width="12%" >Monto Documento</td>
		            <td class="labelDos" width="8%"><input tabindex="10" id="TxtMonto" name="TxtMonto" style="width: 100%"  type="text" maxlength="9" size="16" value="<%=MontoStr%>" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtMonto');"/></td>			            
					
				</tr>
				
				<tr>
					<td class="labelUno" width="10%">Moneda</td>
		            <td class="labelDos" width="10%">
		            		<SELECT NAME="MONEDA"  tabindex="11" class="select1" onChange="javascript:SelLista(this,arrMoneda,window.document.FRMINGRESOCOS.HDDMONEDA);"></SELECT>
							<SCRIPT LANGUAGE="JavaScript">
								valJS.LlenarLista(arrMoneda,window.document.FRMINGRESOCOS.MONEDA,"N","<%=Moneda%>","Seleccione..."); <!--SELECCIONA PESOS-->
							</SCRIPT>
		            </td>
							            
		            <td class="labelUno" width="10%">N° Costa</td>
		            <td class="labelDos" width="25%" colspan="3"><input tabindex="12" id="TxtNumCostas" name="TxtNumCostas" type="text" maxlength="15" size="16" disabled="disabled" value="<%=NumCosta%>"/></td>
		            <td class="labelUno" width="12%" >Estado</td>
		            <td class="labelDos" width="8%"><input tabindex="13" id="TxtEstado" name="TxtEstado" type="text" style="width: 100%"  disabled="disabled" maxlength="15" value="<%=DescripEstado%>"/></td>			            
				</tr>			
				
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablagris">
			<tr>
		          <td width="100%" class="clsTitleError" >
		          <input id="TxtMensajeError" name="TxtMensajeError" size="100%" readonly type="text" style="font size:14; color:red ;TEXT-ALIGN: center;FONT-WEIGHT: bold;BORDER-TOP: none; BORDER-RIGHT: none; BORDER-LEFT: none;BORDER-BOTTOM: none;BACKGROUND-COLOR: #f5f5f5;"  value=""/></td>
		    </tr>
		    </table> 
			</div>
			
			<br />
			<div class="tablabotones">
			<table width="262px" border="0" cellspacing="0" cellpadding="0" class="tablagris">
						<%if (IdEntrada.equals("") && Origen.equals("INGRESO")) // en ingreso
					  	{%>
							<tr>
							<td width="216px">&nbsp;</td>
						    <td width="46px" align="left"><a tabindex="14" href="#" onClick="JavaScript:GrabarCosta(window.document.FRMINGRESOCOS,event);" id="rollovergrabar" title="Grabar Costa"><span>grabar</span></a></td>
				        	</tr>	
			        	<%}
			        	else
			        	{
			        		if (IdEntrada.equals("GRABACOSTA") && Origen.equals("")) 
			        		{%>
				        		<tr>
								<td width="216px">&nbsp;</td>
							    <td width="46px"><a tabindex="14" href="#" onClick="JavaScript:ImprimirDetalle(0);" id="rolloverimprimir" title="Imprimir Costa"><span>imprimir</span></a></td>
					        	</tr>				        		
			        		<%}
			        		else //VIENE DE CONSULTA OMDE INFORME
			        		{%>
				        		<tr>
								<td width="170px">&nbsp;</td>
							    <td width="46px"><a tabindex="14" href="#" onClick="JavaScript:ImprimirDetalle(0);" id="rolloverimprimir" title="Imprimir Costa"><span>imprimir</span></a></td>
							    <td width="46px" align="left"><a tabindex="15" href="#" onClick="JavaScript:VolverConsulta(window.document.FRMINGRESOCOS);" id="rollovervolver" title="Volver"><span>Volver</span></a></td>
					        	</tr>
			        	<%}
			        	}%>				    
			</table>
			</div>    
	
			<table width="262px" border="0" cellspacing="0" cellpadding="0" class="tablagris">
			<tr><td>
			<div id="noeditable" >
				<div id="gridbox1"></div> 
			</div> 
			<div id="editable">
			<div id="gridbox"></div> 
			</div> 
			</td></tr></table>		
				
		</td>
	  </tr>
	</table>
	</td>
  </tr>
</table>
<div style="display">	    
	<iframe name="printInforme" src="black.html" style="width:0px;height:0px;" frameborder="0" width="0" height="0" align="left"></iframe>    
</div>    
</FORM>
</table>

<SCRIPT LANGUAGE="JavaScript">		
    var f=document.FRMINGRESOCOS;	
	valJS.MaskNumber(f.TxtMonto);
	valJS.MaskInput(f.TxtNumDocum,'999999999999999');		
	
	<%if (Origen.equals("INGRESO")) // en ingreso
	{%>
		InhabilitarCampos("");
		f.MONEDA.disabled="disabled";
	  	f.HDDMONEDA.value=arrMoneda[0][0]; 
	  	f.HDDTIPODOCM.value=arrTipoDoc[0][0];
	<%}
	 else
	  {%>
	  	InhabilitarCampos("disabled");
	<%}%>
	
</SCRIPT>	
</BODY>
</HTML>