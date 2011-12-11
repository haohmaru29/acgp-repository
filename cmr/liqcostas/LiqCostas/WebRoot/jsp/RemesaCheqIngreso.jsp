<%@ page language='java' contentType="text/html" errorPage="error.jsp?ERR=ERR.GENERAL.PAGE"%>
<%getServletContext().getRequestDispatcher("/jsp/acceso.jsp").include(request,response);%>
<%@page import='java.util.ArrayList'%>
<%@page import='java.text.DecimalFormat'%>
<%@page import='java.text.DecimalFormatSymbols'%>
<%@page import='Proc.General' %>
<%  
	int Grupo=5;
	int TipoProducto=2;
	
	RequestDispatcher dispatcher;
	HttpSession sesion = request.getSession(true);
    Seguridad.SessionUsuario  oSes =(Seguridad.SessionUsuario)sesion.getAttribute("usuario");
	
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
	String NumRemesa = request.getParameter("HDDNumRemesa")!=null?request.getParameter("HDDNumRemesa").trim():"0";
	String CodAbogado = request.getParameter("HDDCODABO")!=null?request.getParameter("HDDCODABO").trim():"";
	String CodSucAbogado = request.getParameter("HDDCODABOSUC")!=null?request.getParameter("HDDCODABOSUC").trim():"";
	String NomAbogado = request.getParameter("HDDNOMABO")!=null?request.getParameter("HDDNOMABO").trim():"";
	String sFechaIng = request.getParameter("HDDFechaIng")!=null?request.getParameter("HDDFechaIng").trim():"";
	String Moneda = request.getParameter("HDDMONEDA")!=null?request.getParameter("HDDMONEDA").trim():"";
	String MontoRemesa= request.getParameter("HDDMonto")!=null?request.getParameter("HDDMonto").trim():"0";
	String FechaRemesa= request.getParameter("HDDFechaRemesa")!=null?request.getParameter("HDDFechaRemesa").trim():"";
	String NumInterno= request.getParameter("HDDNumInterno")!=null?request.getParameter("HDDNumInterno").trim():"";
	
	//out.println("Moneda=" + Moneda);	
	//out.println("TipoDocumento=" + TipoDocumento);	
		
	if (sFechaIng.equals(""))
	 sFechaIng=iDia + "/" + iMes + "/" + iAno;
	
	String sFechaActual=iDia + "/" + iMes + "/" + iAno;
	
	//Datos que vienen desde la consulta
	String CFechaDesde = request.getParameter("CTxtFechaDesde")!=null?request.getParameter("CTxtFechaDesde").trim():"";
	String CFechaHasta = request.getParameter("CTxtFechaHasta")!=null?request.getParameter("CTxtFechaHasta").trim():"";
	String CNumRemesa = request.getParameter("CNumRemesa")!=null?request.getParameter("CNumRemesa").trim():"0";
	String CSucursal = request.getParameter("CSUCURSAL")!=null?request.getParameter("CSUCURSAL").trim():"";
	String CAbogado = request.getParameter("CABOGADO")!=null?request.getParameter("CABOGADO").trim():"";
	String CEstado = request.getParameter("CESTADO")!=null?request.getParameter("CESTADO").trim():"0";	
	String CProducto = request.getParameter("CPRODUCTO")!=null?request.getParameter("CPRODUCTO").trim():"0";
	String NumCheque = request.getParameter("CTxtNumCheque")!=null?request.getParameter("CTxtNumCheque").trim():"0";
	
	String TopeMonto="0";
	
	
	General objGeneral = General.getInstance();

	ArrayList resMoneda = new ArrayList();
	ArrayList resAbogado = new ArrayList();
	ArrayList resAux = new ArrayList();
	ArrayList resEncRemesa = new ArrayList();
	ArrayList resDetRemesa = new ArrayList();	
				
	boolean retD =false ;
	
	//out.println("Origen=" + Origen);	
	//out.println("IdEntrada=" + IdEntrada);
	//out.println("sFechaIng=" + sFechaIng);
	//out.println("sFechaActual=" + sFechaActual);
	
	if (IdEntrada.equals("GRABAREMESA") || Origen.equals("INGRESO"))
	{
		retD = objGeneral.ObtieneElemLista("MONEDALQ","0",resMoneda);
		retD = objGeneral.ObtenerListaAbogados(TipoProducto,resAbogado);
		TopeMonto=objGeneral.ObtenerParametro("TOPEMONTO");
		
		if (IdEntrada.equals("GRABAREMESA"))
		{
			//obtener el detalle
			Proc.Remesa objRemesa = new Proc.Remesa();			
			int Iret = objRemesa.ObtieneEncDetRemesa(NumRemesa,TipoProducto,resEncRemesa,resDetRemesa);
			//colocar encabezado
			resAux = new ArrayList();
	        resAux = (ArrayList)resEncRemesa.get(0);
	        sFechaIng=resAux.get(0).toString().trim();
	        CodAbogado=resAux.get(1).toString().trim();
	        NomAbogado=resAux.get(2).toString().trim();
	        Moneda=resAux.get(3).toString().trim();
	        DescripEstado=resAux.get(5).toString().trim();
	        MontoRemesa=df.format(Double.parseDouble(resAux.get(6).toString().trim()));
	        CodSucAbogado=resAux.get(7).toString().trim();
	        FechaRemesa=resAux.get(9).toString().trim();	     
	        NumInterno =resAux.get(10).toString().trim();		                  		
	    }
	}
	else
	{	
		if (IdEntrada.equals("VOLVERBUSQ"))
		{		
			if (Origen.equals("INFORME"))
			{
				dispatcher = getServletContext().getRequestDispatcher("/jsp/RemesaInfEstado.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				if (Origen.equals("CONSULTAR"))
				{
					dispatcher = getServletContext().getRequestDispatcher("/jsp/RemesaConsultar.jsp?TIPOPROD=" + TipoProducto);
					dispatcher.forward(request,response);
				}
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
	 	function InicioPag() 
	 	{		 
	 		document.FRMINGRESOREM.TxtMensajeError.value="";	 		
	 		<%if (Origen.equals("INGRESO"))
			{%>	
				f.NOMBREOBJETO.value="TxtFechaRemesa";
	 			document.FRMINGRESOREM.TxtFechaRemesa.focus();	 										
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
		//fin carga de arreglos	

		function ValidarFechaRemesa(obj,confoco)
		{
			var flag=true;
			if (obj.TxtFechaRemesa.value!="")
			{
				var numfecha=valJS.quitaMask3(obj.TxtFechaRemesa.value);	
				//alert("numfecha=" + numfecha);	
				if (valJS.isFechaValida(numfecha,"ddmmyyyy")==false) 
				{
					f.NOMBREOBJETO.value="TxtFechaRemesa"; 			    	
					obj.TxtMensajeError.value="Fecha Remesa ingresada no válida, favor digite nuevamente."; 
					obj.TxtFechaRemesa.value="";
					obj.TxtFechaRemesa.select();		
					obj.TxtFechaRemesa.focus();					
					flag=false;	
				}
				else
				{
					obj.TxtFechaRemesa.value=numfecha.substring(0,2) + "/" + numfecha.substring(2,4) + "/" + numfecha.substring(4,8);
					obj.TxtMensajeError.value="";
					if (confoco)
					{					
						f.NOMBREOBJETO.value="TxtNumInterno"; 
						obj.TxtNumInterno.select();
						obj.TxtNumInterno.focus();						
					}					
				}
			}	
			else
			{
				if (confoco)
				{					
					f.NOMBREOBJETO.value="TxtNumInterno"; 
					obj.TxtNumInterno.select();
					obj.TxtNumInterno.focus();						
				}	
			}
			return flag;
		}	
		
		function ValidarNumeroInterno(obj,confoco)
		{
			var flag=true;
			if (obj.TxtNumInterno.value!="")
			{
				//validar que sea numerico
				 var valor=valJS.quitaMask(obj.TxtNumInterno.value + '');
				 if (isNaN(valor/1))
				 {
				 	f.NOMBREOBJETO.value="TxtNumInterno"; 			    	
					obj.TxtMensajeError.value="Número Interno debe ser numérico."; 
					obj.TxtNumInterno.value="";
					obj.TxtNumInterno.select();	
					obj.TxtNumInterno.focus();					
					flag=false;	
				 }
				 else
				 {
				 	 obj.TxtMensajeError.value="";
					 if (confoco)
					 {					
						f.NOMBREOBJETO.value="ABOGADO"; 						
						f.ABOGADO.select();
						f.ABOGADO.focus();		
					 }
				 }				     		 
			}	
			else
			{
				obj.TxtMensajeError.value="";
				if (confoco)
				{					
					f.NOMBREOBJETO.value="ABOGADO"; 						
					f.ABOGADO.select();
					f.ABOGADO.focus();		
				}	
			}
			return flag;
		}
		
		function ValidarMonto(obj,confoco)
		{
			obj.TxtMensajeError.value="";
			var MontoDoc=obj.TxtMonto.value;
			MontoDoc=valJS.quitaMask(MontoDoc);	
			if (isNaN(MontoDoc/1))
			{
				f.NOMBREOBJETO.value="TxtMonto";
				obj.TxtMensajeError.value="Monto de la Remesa debe ser númerico.";
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
					obj.TxtMensajeError.value="Monto de la Remesa debe ser mayor a cero.";
					obj.TxtMonto.select();
					obj.TxtMonto.focus();
					return false;
				}							
				else
				{
					if (MontoDoc>TopeMonto)
					{
						f.NOMBREOBJETO.value="TxtMonto";
						obj.TxtMensajeError.value="Monto de la Remesa debe ser menor a $" + valJS.Mask(TopeMonto + '',"9.999.999");
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
			if (obj.TxtFechaRemesa.disabled==false) 
			{
				if (ValidarFechaRemesa(obj,confoco))
					if (ValidarNumeroInterno(obj,confoco))				
						if (validarCodAbogado(confoco))
							if (validarNombreAbogado(confoco))
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
			f.action = "RemesaCheqIngreso.jsp";
			f.target = "_self";
			f.HDDENTRADA.value="VOLVERBUSQ";		
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
						f.NOMBREOBJETO.value="TxtMonto"; 
						f.TxtMonto.select();
						f.TxtMonto.focus();						
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
		function ImprimirCupon(flag)  
  		{ 
  			f.RESPBD.value=flag;
  			f.TxtMensajeError.value="";
  			f.target = "printInforme"
			f.action = "RemesaImprimeCupon.jsp"				
			f.submit();								
		}
		
	    function ImprimirDetalle(flag)  
  		{ 
			f.RESPBD.value=flag;
			f.TxtMensajeError.value="";
	  		f.target = "printInforme"
			f.action = "RemesaImprimeTarjeta.jsp"			
			f.submit();		
		}
  		
  		function printPageCupon(ifr) 
 		{
			if (ifr.location.href != "about:blank")
			{
	   		 ifr.focus(); 
	   		 ifr.print();	   		
			}
			
			if (f.RESPBD.value=="1")
			{
				if (confirm("Desea Imprimir Remesa?"))
					ImprimirDetalle(1)  
				else
				{
					f.action = "RemesaCheqIngreso.jsp";
					f.target = "_self";
					f.HDDORIGEN.value="";
					f.HDDENTRADA.value="GRABAREMESA";
					f.submit();
				}
			}				
  		}
  		function printPageDetalle(ifr) 
 		{
			if (ifr.location.href != "about:blank")
			{
	   		 ifr.focus(); 
	   		 ifr.print();	   		
			}
			
			if (f.RESPBD.value=="1")
			{
				f.action = "RemesaCheqIngreso.jsp";
				f.target = "_self";
				f.HDDORIGEN.value="";
				f.HDDENTRADA.value="GRABAREMESA";
				f.submit();
			}	
  		}
		//fin imprimir		
		
		
		//DATOS GRILLA		
		var TypeMaskNum="999.999.999.999";
		var opciones= {'': ''};
		var vardefault='';
		var __TEST_DATA__=[];
		
		<%if (resDetRemesa.size()>0)
		 {
			for(int i=0; i<resDetRemesa.size(); i++) {
				resAux = new ArrayList();
             	resAux = (ArrayList)resDetRemesa.get(i);
             	if (i < resDetRemesa.size()-1)
        		{%>             
    	        arrPaso = new Array("<%=resAux.get(8).toString().trim()%>",valJS.MascaraRut("<%=resAux.get(0).toString().trim()%>"),"<%=resAux.get(1).toString().trim()%>","<%=df.format(Double.parseDouble(resAux.get(2).toString().trim()))%>","<%=df.format(Double.parseDouble(resAux.get(3).toString().trim()))%>","<%=df.format(Double.parseDouble(resAux.get(4).toString().trim()))%>","<%=df.format(Double.parseDouble(resAux.get(5).toString().trim()))%>","<%=resAux.get(6).toString().trim()%>","<%=df.format(Double.parseDouble(resAux.get(7).toString().trim()))%>");
        	    __TEST_DATA__[<%=i%>]=arrPaso;        	    	    
		
			  <%}
			}	  
		}
		else {%>	
			__TEST_DATA__=
					[
					["1","","",0,0,0,0,0,0]
					];		
		<%}%>
	
		var grid_demo_id = "myGrid11" ;
		
		var dsOption= {
			
			uniqueField : 'ide' ,	
			fields :[
				{name : 'ide'  , type: 'int' },
				{name : 'rut'  },
				{name : 'cheque'},
				{name : 'Capital' },
				{name : 'Interes' },
				{name : 'Costa' },
				{name : 'Honorario' },
				{name : 'NumBoleta' },
				{name : 'Total' }
			],
	
			recordType : 'array',
			data : __TEST_DATA__
		}
		
		
		var colsOption = [
				 {id: 'ide' , header: "Id" , width :1, sortable:false,editable:false, editor:{type:"text"}},	
			     {id: 'rut' , header: "Rut Cliente" , width :150, sortable:false, editable:false, editor:{type:"text"} },
			     {id: 'cheque' , header: "N° Único" , width :250, sortable:false, editable:false, editor : { type :"text"}},
	  	    	 {id: 'Capital' , header: "Capital" , width :110, sortable:false, editable:false, editor:{type:"text"} },
				 {id: 'Interes' , header: "Interés" , width :110, sortable:false, editable:false, editor:{type:"text"} },
				 {id: 'Costa' , header: "Costas Procesales" , width :150, sortable:false, editable:false, editor:{type:"text"} },
				 {id: 'Honorario' , header: "Honorario Abogado" , width :160, sortable:false, editable:false, editor:{type:"text"} },	
		   	     {id: 'NumBoleta' , header: "N° Boleta" , width :110, sortable:false, editable:false,editor:{type:"text"} },
				 {id: 'Total' , header: "Total" , width :145, sortable:false, editable:false,editor:{type:"text"}}
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
						cheque : '',		
						Capital : 0,			
						Interes : 0,	
						Costa : 0,
						Honorario : 0,
						NumBoleta : 0,
						Total : 0						
				}				
			};
			
			var mygrid1=new Sigma.Grid( gridOption );		
			Sigma.Util.onLoad( Sigma.Grid.render(mygrid1));
			

		function my_renderer(value ,record,columnObj,grid,colNo,rowNo)
		{
			var no= record[columnObj.fieldIndex] + '';
			var color = "black";
			return "<span style=\"color:" + color +";\"><strong>" + no + "</strong></span>";
		}

		__TEST_DATA__=
		[
		["1","","",0,0,0,0,0,0]
		];

		dsOption= {
			uniqueField : 'ide' ,	
			fields :[
				{name : 'ide'  , type: 'int' },
				{name : 'rut'  },
				{name : 'cheque'	},
				{name : 'Capital' },
				{name : 'Interes' },
				{name : 'Costa' },
				{name : 'Honorario' },
				{name : 'NumBoleta' },
				{name : 'Total' , 
					initValue : function(record){
						if (record[3]=='')
							record[3]=0;
						if (record[4]=='')
							record[4]=0;
						if (record[5]=='')
							record[5]=0;	
						if (record[6]=='')
							record[6]=0;		
						var avg2=record[3] + '';
						avg2=valJS.quitaMask(avg2);
						var avg3=record[4] + '';
						avg3=valJS.quitaMask(avg3);
						var avg4=record[5] + '';
						avg4=valJS.quitaMask(avg4);						
						var avg5=record[6] + '';
						avg5=valJS.quitaMask(avg5);
						var avg =(parseFloat(avg2) + parseFloat(avg3) + parseFloat(avg4) + parseFloat(avg5));	
						return valJS.Mask (valJS.Trim(avg+''),TypeMaskNum,1);		
					}
			   }
			],
			recordType : 'array',
			data : __TEST_DATA__
		}

		colsOption = [
				 {id: 'ide' , header: "Id" , width :1, sortable:false,editable:false, editor:{type:"text"}},	
			     {id: 'rut' , header: "Rut Cliente" , width :150, sortable:false, editable:true, editor:{type:"text",
			     	validator : function(value,record,colObj,grid)
			     	{ 	
			     	if (event.keyCode!=Sigma.Const.Key.UP && event.keyCode!=0)
			     	{					     		
			     			var obj=document.getElementById("TxtMensajeError");			     		
			     			var flag=true;
			     		    if (valJS.isRutValido(value)==false)
			     			{		
								validResult=[].concat("Rut no válido, favor digite nuevamente."); 
								obj.value=validResult.join('\n');	
								//record[1]="";
								f.NOMBREOBJETO.value="";
								flag=false;							
							}
							else
							{
								record[1]=valJS.MascaraRut(value);	
								document.getElementById("RESPBD").value="";	
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
			     	
			     {id: 'cheque' , header: "N° Único" , width :250, sortable:false, editable:true,editor : { type :"text" ,
			      validator : function(value,record,colObj,grid)
			      {	
			        if (event.keyCode!=Sigma.Const.Key.LEFT)
			     	{		       
				      	var obj=document.getElementById("TxtMensajeError");					      
				     	if(value=="")
					  	{
					  		 validResult=[].concat("Debe ingresar N° Unico.");
					  		 obj.value=validResult.join('\n');
					  		 //record[2]="";
					  		 f.NOMBREOBJETO.value="";
					  		 document.getElementById("RESPBD").value="";
					  		 return false;
					  	}
					  	else
					  	{
					  		mygrid.ValidaNumCheque(value);
							var respcosta=parseFloat(f.RESPBD.value);
							if(respcosta<0)
							{	
							 	if (respcosta==-1)
								{
									//error de sesion
									f.action = "RemesaCheqIngreso.jsp";
									f.target = "_self";
									f.HDDORIGEN.value="";
									f.HDDENTRADA.value="ERRORSESION";
									f.submit();
								}
								else
								{
									switch (respcosta)
									{
										case -2:
											validResult=[].concat("N° Unico debe ser numérico.");
											break;
										case -3:
											validResult=[].concat("N° Unico debe ser de largo 10.");
											break;
										case -4:
											validResult=[].concat("Digito verificador de N° Unico es incorrecto.");
											break;
										case -5:
											validResult=[].concat("N° Unico no se encuentra dentro de los rangos definidos.");
											break;		
									}									
							 		obj.value=validResult.join('\n');
									//record[2]="";
							 		f.NOMBREOBJETO.value="";					  		 		
									document.getElementById("RESPBD").value="";	
									return false;
								}
							}		
						    else
						    {
								if (mygrid.ValidarNumeroCheque(event,record[0],value))
					  			{					  					  				
							    	record[2]=value;	
									document.getElementById("RESPBD").value="";	
									return true;
					  			}
						    	else
						    	{	validResult=[].concat("N° Unico ya se encuentra ingresado.");
									obj.value=validResult.join('\n');
									//record[2]="";
							  		f.NOMBREOBJETO.value="";					  		 		
									document.getElementById("RESPBD").value="";	
									return false;
								}	
							}
					  	}
					 }
					 else
					 {
						mygrid.CambiaValor(value);
						return true;
					 }	 			  
				  }     
				  }},
				  
	  			 {id: 'Capital' , header: "Capital" , width :110, sortable:false, editable:true, editor:{type:"text",
			     		validator : function(value,record,colObj,grid)
			     		{
			     			if (event.keyCode!=Sigma.Const.Key.LEFT)
			     			{ 
				     		  var obj=document.getElementById("TxtMensajeError");
				     		  var flag=true;
				     		  validResult=[].concat("Valor debe ser numérico.");   
				     		  var valor=valJS.quitaMask(value + '');
				     		  if (isNaN(valor/1))
				     		  {
				     		  	flag=false;	
								obj.value=validResult.join('\n');	
								f.NOMBREOBJETO.value="";
				     		  }
				     		  else
				     		  {
				     		  	if (valor>0)
				     		  		record[3]=valJS.Mask (value,TypeMaskNum,1);	
				     		  	else
				     		  		record[3]="0";		
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
			     	
			    {id: 'Interes' , header: "Interés" , width :110, sortable:false, editable:true, editor:{type:"text",
			     		validator : function(value,record,colObj,grid)
			     		{
			     			if (event.keyCode!=Sigma.Const.Key.LEFT)
			     			{ 
				     		  var obj=document.getElementById("TxtMensajeError");
				     		  var flag=true;
				     		  validResult=[].concat("Valor debe ser numérico.");   
				     		  var valor=valJS.quitaMask(value + '');
				     		  if (isNaN(valor/1))
				     		  {
				     		  	flag=false;	
								obj.value=validResult.join('\n');	
								f.NOMBREOBJETO.value="";
				     		  }
				     		  else
				     		  {
				     		  	if (valor>0)
				     		  		record[4]=valJS.Mask (value,TypeMaskNum,1);	
				     		  	else
				     		  		record[4]="0";		
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
			     		
			     {id: 'Costa' , header: "Costas Procesales" , width :150, sortable:false, editable:true, editor:{type:"text",
			     		validator : function(value,record,colObj,grid)
			     		{
			     			if (event.keyCode!=Sigma.Const.Key.LEFT)
			     			{ 
				     		  var obj=document.getElementById("TxtMensajeError");
				     		  var flag=true;
				     		  validResult=[].concat("Valor debe ser numérico.");   
				     		  var valor=valJS.quitaMask(value + '');
				     		  if (isNaN(valor/1))
				     		  {
				     		  	flag=false;	
								obj.value=validResult.join('\n');	
								f.NOMBREOBJETO.value="";
				     		  }
				     		  else
				     		  {
				     		  	if (valor>0)
				     		  		record[5]=valJS.Mask (value,TypeMaskNum,1);		
				     		  	else
				     		  		record[5]="0";	
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
			    
			     {id: 'Honorario' , header: "Honorario Abogado", width :160, sortable:false, editable:true, editor:{type:"text",
			     		validator : function(value,record,colObj,grid)
			     		{
			     			if (event.keyCode!=Sigma.Const.Key.LEFT)
			     			{ 
				     		  var obj=document.getElementById("TxtMensajeError");
				     		  var flag=true;
				     		  validResult=[].concat("Valor debe ser numérico.");   
				     		  var valor=valJS.quitaMask(value + '');
				     		  if (isNaN(valor/1))
				     		  {
				     		  	flag=false;	
								obj.value=validResult.join('\n');	
								f.NOMBREOBJETO.value="";
				     		  }
				     		  else
				     		  {
				     		  	if (value==0 && record[7]!=0) //hay boleta y no ingreso honorario
				     		  	{
				     		  		validResult=[].concat("Debe ingresar Honorario Abogado para N° Boleta ingresado."); 
				     		  		flag=false;	
									obj.value=validResult.join('\n');	
									f.NOMBREOBJETO.value="";
				     		  	}
				     		  	else
				     		  	{	     	  	   
					     		  	if (valor>0)
					     		  		record[6]=valJS.Mask (value,TypeMaskNum,1);		
					     		  	else
					     		  		record[6]="0";	
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
			     	
				   {id: 'NumBoleta' , header: "N° Boleta" , width :110, sortable:false, editable:true,editor:{type:"text",
				   		validator : function(value,record,colObj,grid)
			     		{
			     			if (event.keyCode!=Sigma.Const.Key.LEFT)
			     			{ 
				     		  var obj=document.getElementById("TxtMensajeError");
				     		  var flag=true;
				     		  validResult=[].concat("Valor debe ser numérico.");   
				     		  var valor=valJS.quitaMask(value + '');
				     		  if (isNaN(valor/1))
				     		  {
				     		  	flag=false;	
								obj.value=validResult.join('\n');	
								f.NOMBREOBJETO.value="";
				     		  }
				     		  else
				     		  {
				     		  	if (record[6]!=0 && value==0) //hay honorario y no ingreso boleta
				     		  	{
				     		  		validResult=[].concat("Debe ingresar N° Boleta para Honorario Abogado Ingresado."); 
				     		  		flag=false;	
									obj.value=validResult.join('\n');	
									f.NOMBREOBJETO.value="";
				     		  	}
				     		  	else
				     		  	{	     	  	   
					     		  	if (valor>0)
					     		  		record[7]=value;		
					     		  	else
					     		  		record[7]="0";	
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
				   	{id: 'Total' , header: "Total" , width :145, sortable:false,editable:false,renderer:my_renderer}
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
				submitUpdatedFields : true,
				allowCustomSkin	: true ,
				skin : "vista",
			 	defaultRecord : {
						Ide : '-',
						rut : '',	
						cheque : '',		
						Capital : 0,			
						Interes : 0,	
						Costa : 0,
						Honorario : 0,
						NumBoleta : 0,
						Total : 0							
				}				
			};
			
			var mygrid=new Sigma.Grid( gridOption );
			Sigma.Util.onLoad( Sigma.Grid.render(mygrid));			
			
			
			function SeleccionarPrimerRegGrilla()
  			{
  				mygrid.endEdit(1)
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
				if (f.TxtFechaRemesa.disabled==false) 
				{				
					f.TxtFechaRemesa.disabled=valor;
					f.TxtNumInterno.disabled=valor;
					f.ABOGADO.disabled=valor;
					f.TxtNomAbo.disabled=valor;
					f.MONEDA.disabled=valor;						
					f.TxtMonto.disabled=valor;										
				}
			}
		
			function GrabarRemesa(obj,event)
			{
				var flagsigue=true;				
				if (ValidaCabecera(f,true))
					flagsigue=true;						
				else
					flagsigue=false;
				
				if (flagsigue)
				{
					f.NOMBREOBJETO.value==""	
					var total=mygrid.validarDatosGrilla(event);	
					if (total!=-1)
					{
						var MontoDoc=obj.TxtMonto.value;
						MontoDoc=valJS.quitaMask(MontoDoc);
						if(MontoDoc==total)
						{
							mygrid.GrabarRemesa(event,'<%=Grupo%>');	
							var respcosta=parseFloat(f.RESPBD.value);
							if(respcosta>0)
							{
								f.TxtNumRemesa.value=respcosta;
								f.HDDNumRemesa.value=respcosta;
								f.HDDFechaRemesa.value=f.TxtFechaRemesa.value;
								f.HDDNumInterno.value=f.TxtNumInterno.value;								
								f.HDDMonto.value=obj.TxtMonto.value;								
								f.TxtEstado.value="Ingresada";
								
								if(MontoDoc!=mygrid.validarDatosGrilla(event) ) {
									alert('Total Remesas ingresada es distinto al total de el detalle, favor vuelva a ingresar');
								} else {
									//alert('antes');
									var data = "idSec=" + f.TxtNumRemesa.value; 
									jQuery.ajax({ 								
										timeout: 10000,
										dataType: 'JSON',					
							            url: "../jsp/AJAX_ActualizaEncabezadoRemesa.jsp",
							            data: data,	
							            async:false,		        
							            success:function(data){	
							            	var obj = jQuery.parseJSON(data);
							            	if (obj.total==-1) {			            	    	
						            	    	validResult=[].concat("Error al grabar Remesa, favor vuelva a intentar.");   
						            	    	document.getElementById("TxtMensajeError").value=validResult.join('\n');   
						            	    	return false;
						            	    } 	
							            }, 
							            cache:false
							        });
							
									if (confirm("Desea Imprimir Cupón de Pago?"))
										ImprimirCupon(1)  
									else
									{
										if (confirm("Desea Imprimir Remesa?"))
											ImprimirDetalle(1)  
										else
										{
											f.action = "RemesaCheqIngreso.jsp";
											f.target = "_self";
											f.HDDORIGEN.value="";
											f.HDDENTRADA.value="GRABAREMESA";
											f.submit();
										}
									}	
								}	
							} else {
								if (respcosta==-2) {								
									f.action = "RemesaCheqIngreso.jsp";
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
							f.TxtMensajeError.value="Total del detalle debe ser igual al Monto Total de la Remesa. Diferencia $ " + valJS.Mask(MontoDoc + '',"999.999.999.999");
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
		    		if (confirm("Desea abandonar ingreso de Remesa, se perderán todos los datos ingresados?"))
		    		{	
		    			f.action = "RemesaCheqIngreso.jsp";
						f.target = "_self";
						f.HDDORIGEN.value="INGRESO";
						f.HDDENTRADA.value="";
						f.HDDFechaIng.value="";
						f.HDDCODABO.value="";
						f.HDDNOMABO.value="";
						f.HDDCODABOSUC.value="";
						f.HDDMONEDA.value="";						
						f.HDDNumRemesa.value="";
						f.HDDFechaRemesa.value="";
						f.HDDNumInterno.value="";
						f.HDDMonto.value="";						
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
							case Sigma.Const.Key.F12:													
								window.event.keyCode = 505; 
								break;
							case Sigma.Const.Key.BACKSPACE:	
								//alert(f.NOMBREOBJETO.value);
								if (f.NOMBREOBJETO.value=="" || f.NOMBREOBJETO.value.substring(0,5)=="rollo")
									window.event.keyCode = 505; 
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
				
				
				jQuery("#TxtFechaRemesa").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarFechaRemesa(f,true);						
						event.preventDefault();
					}
				});
				
				jQuery("#TxtNumInterno").bind($.browser.opera ? "keypress" : "keydown" , function(event){
					if (event.keyCode==Sigma.Const.Key.ENTER || event.keyCode==Sigma.Const.Key.RIGHT || event.keyCode==Sigma.Const.Key.DOWN || event.keyCode==Sigma.Const.Key.TAB ) 
					{
						ValidarNumeroInterno(f,true);
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
					   if (jQuery(this).attr('id').substring(0,5)=="rollo" || (jQuery(this).attr('id')=="TxtMonto")) //si son los botones
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

<FORM METHOD="POST" NAME="FRMINGRESOREM">		
<INPUT TYPE="HIDDEN" ID="HDDENTRADA" NAME="HDDENTRADA" VALUE="<%=IdEntrada%>">
<INPUT TYPE="HIDDEN" ID="HDDORIGEN" NAME="HDDORIGEN" VALUE="<%=Origen%>">
<INPUT TYPE="HIDDEN" ID="HDDTITULO" NAME="HDDTITULO" VALUE="<%=Titulo%>">
<INPUT TYPE="HIDDEN" ID="HDDGRUPO" NAME="HDDGRUPO" VALUE="<%=Grupo%>">
<INPUT TYPE="HIDDEN" ID="HDDFechaIng" NAME="HDDFechaIng" VALUE="<%=sFechaIng%>">
<INPUT TYPE="HIDDEN" ID="HDDCODABO" NAME="HDDCODABO" VALUE="<%=CodAbogado%>">
<INPUT TYPE="HIDDEN" ID="HDDNOMABO" NAME="HDDNOMABO" VALUE="<%=NomAbogado%>">
<INPUT TYPE="HIDDEN" ID="HDDCODABOSUC" NAME="HDDCODABOSUC" VALUE="<%=CodSucAbogado%>">
<INPUT TYPE="HIDDEN" ID="HDDMONEDA" NAME="HDDMONEDA" VALUE="<%=Moneda%>">
<INPUT TYPE="HIDDEN" ID="HDDNumRemesa" NAME="HDDNumRemesa" VALUE="<%=NumRemesa%>">
<INPUT TYPE="HIDDEN" ID="HDDFechaRemesa" NAME="HDDFechaRemesa" VALUE="<%=FechaRemesa%>">
<INPUT TYPE="HIDDEN" ID="HDDNumInterno" NAME="HDDNumInterno" VALUE="<%=NumInterno%>">
<INPUT TYPE="HIDDEN" ID="HDDMonto" NAME="HDDMonto" VALUE="<%=MontoRemesa%>">

<INPUT TYPE="HIDDEN" ID="CTxtFechaDesde" NAME="CTxtFechaDesde" VALUE="<%=CFechaDesde%>">
<INPUT TYPE="HIDDEN" ID="CTxtFechaHasta" NAME="CTxtFechaHasta" VALUE="<%=CFechaHasta%>">
<INPUT TYPE="HIDDEN" ID="CNumRemesa" NAME="CNumRemesa" VALUE="<%=CNumRemesa%>">
<INPUT TYPE="HIDDEN" ID="CSUCURSAL" NAME="CSUCURSAL" VALUE="<%=CSucursal%>">
<INPUT TYPE="HIDDEN" ID="CABOGADO" NAME="CABOGADO" VALUE="<%=CAbogado%>">
<INPUT TYPE="HIDDEN" ID="CESTADO" NAME="CESTADO" VALUE="<%=CEstado%>">
<INPUT TYPE="HIDDEN" ID="CPRODUCTO" NAME="CPRODUCTO" VALUE="<%=CProducto%>">
<INPUT TYPE="HIDDEN" ID="CTxtNumCheque" NAME="CTxtNumCheque" VALUE="<%=NumCheque%>">


<INPUT TYPE="HIDDEN" id="NUMFILA"  NAME="NUMFILA" VALUE="-1">
<INPUT TYPE="HIDDEN" id="NUMREG"  NAME="NUMREG" VALUE="1">
<INPUT TYPE="HIDDEN" id="NUMREGTOTAL"  NAME="NUMREGTOTAL" VALUE="8">
<INPUT TYPE="HIDDEN" id="CampoEnter"  NAME="CampoEnter" VALUE="3">
<INPUT TYPE="HIDDEN" id="ErrorMen"  NAME="ErrorMen" VALUE="No existen cuentas asignadas al Abogado para Rut Cliente Ingresado.">
<INPUT TYPE="HIDDEN" id="RESPBD"  NAME="RESPBD" VALUE="">
<INPUT TYPE="HIDDEN" id="TIPOPROD"  NAME="TIPOPROD" VALUE="<%=TipoProducto%>">
<INPUT TYPE="HIDDEN" id="NOMBREOBJETO"  NAME="NOMBREOBJETO" VALUE="">
<INPUT TYPE="HIDDEN" id="CantidadFilas"  NAME="CantidadFilas" VALUE="300">
<INPUT TYPE="HIDDEN" id="TipoGrilla"  NAME="TipoGrilla" VALUE="NOEDITABLE">
	
 <% 
	String MontoStr=MontoRemesa.replace(".","").replace(",","");
	if (MontoStr.equals(""))
		MontoStr="";
	else
		MontoStr=df.format(Double.parseDouble(MontoStr.trim()));	
%>		
<table width="994px" border="0" cellspacing="0" cellpadding="0" class="tablaprincipal">
  <tr>
   <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
       	<tr><td align="CENTER" class="titulo"><%=Titulo%> REMESAS CHEQUES PROTESTADOS</td></tr>
        <tr>
          <td align="center">
            <div class="tablainterior2">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablagris">
                 <tr>
		            <td class="labelUno" width="13%">User</td>
		            <td class="labelDos" width="10%"><input tabindex="1" id="TxtUser" name="TxtUser" style="width: 100%" type="text" disabled="disabled" value="<%=oSes.getUsuario()%>"/></td>	            
		            <td class="labelUno" width="12%">Nombre Usuario</td>
		            <td class="labelDos" width="21%"><input tabindex="2" id="TxtNomUser" name="TxtNomUser" style="width: 100%" type="text" disabled="disabled" value="<%=oSes.getNombre()%>"/></td>
		            <td class="labelUno" width="10%" >Fecha Remesa</td>
		            <td class="labelDos" width="8%"><input tabindex="3" id="TxtFechaRemesa" name="TxtFechaRemesa" style="width: 100%" type="text" maxlength="10" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtFechaRemesa');" value="<%=FechaRemesa%>"/></td>		            
					<td class="labelUno" width="12%" >N° Interno</td>
		            <td class="labelDos" width="8%"><input tabindex="4" id="TxtNumInterno" name="TxtNumInterno" style="width: 100%" type="text" maxlength="10" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtNumInterno');" value="<%=NumInterno%>"/></td>		            
				</tr>				
			
				<tr>
		            <td class="labelUno" width="13%" >Código Abogado</td>
		            <td class="labelDos" width="10%"><input type="text" name="ABOGADO" id="ABOGADO" tabindex="5" maxlength="10" style="width: 100%;margin:auto;" value="<%=CodAbogado%>" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('ABOGADO');"></td>	            
		            <td class="labelUno" width="12%">Nombre Abogado</td>
		            <td class="labelDos" width="21%" colspan="3" >
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
					<td class="labelUno" width="13%">Moneda</td>
		            <td class="labelDos" width="10%">
		            		<SELECT NAME="MONEDA"  tabindex="8" class="select1" onChange="javascript:SelLista(this,arrMoneda,window.document.FRMINGRESOREM.HDDMONEDA);"></SELECT>
							<SCRIPT LANGUAGE="JavaScript">
								valJS.LlenarLista(arrMoneda,window.document.FRMINGRESOREM.MONEDA,"N","<%=Moneda%>","Seleccione..."); <!--SELECCIONA PESOS-->
							</SCRIPT>
		            </td>							            
		            <td class="labelUno" width="12%">N° Remesa</td>
		            <td class="labelDos" width="21%" ><input tabindex="9" id="TxtNumRemesa" name="TxtNumRemesa" type="text" style="width: 100%" maxlength="15" size="16" disabled="disabled" value="<%=NumRemesa%>"/></td>
		             <td class="labelUno" width="10%" >Monto Remesa</td>
		            <td class="labelDos" width="8%"><input tabindex="10" id="TxtMonto" name="TxtMonto" style="width: 100%"  type="text" maxlength="9" size="16" value="<%=MontoStr%>" onblur="return SetearObjeto('');" onfocus="return SetearObjeto('TxtMonto');"/></td>			            
				    <td class="labelUno" width="12%" >Estado</td>
		            <td class="labelDos" width="8%"><input tabindex="11" id="TxtEstado" name="TxtEstado" type="text" style="width: 100%"  disabled="disabled" maxlength="15" value="<%=DescripEstado%>"/></td>			            
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
						    <td width="46px" align="left"><a tabindex="14" href="#" onClick="JavaScript:GrabarRemesa(window.document.FRMINGRESOREM,event);" id="rollovergrabar" title="Grabar Remesa"><span>grabar</span></a></td>
				        	</tr>	
			        	<%}
			        	else
			        	{
			        		if (IdEntrada.equals("GRABAREMESA") && Origen.equals("")) 
			        		{  
			        		   if (sFechaActual.equals(sFechaIng)) 
			        		   {%>
					        		<tr>
									<td width="170px">&nbsp;</td>
									<td width="46px"><a tabindex="14" href="#" onClick="JavaScript:ImprimirCupon(0);" id="rolloverimprimirCupon" title="Imprimir Cupón"><span>imprimirCupon</span></a></td>
								    <td width="46px"><a tabindex="15" href="#" onClick="JavaScript:ImprimirDetalle(0);" id="rolloverimprimir" title="Imprimir Remesa"><span>imprimir</span></a></td>
						        	</tr>	
						       <%}
						       else
						       {%> 		
						       		<tr>
									<td width="216px">&nbsp;</td>
									<td width="46px"><a tabindex="15" href="#" onClick="JavaScript:ImprimirDetalle(0);" id="rolloverimprimir" title="Imprimir Remesa"><span>imprimir</span></a></td>
						        	</tr>	    		
			        		<% }
			        		}
			        		else //VIENE DE CONSULTA O DE INFORME
			        		{
			        			if (sFechaActual.equals(sFechaIng)) 
			        			{%>
					        		<tr>
									<td width="124px">&nbsp;</td>
									<td width="46px"><a tabindex="14" href="#" onClick="JavaScript:ImprimirCupon(0);" id="rolloverimprimirCupon" title="Imprimir Cupón"><span>imprimirCupon</span></a></td>
								    <td width="46px"><a tabindex="15" href="#" onClick="JavaScript:ImprimirDetalle(0);" id="rolloverimprimir" title="Imprimir Remesa"><span>imprimir</span></a></td>
								    <td width="46px"><a tabindex="16" href="#" onClick="JavaScript:VolverConsulta(window.document.FRMINGRESOREM);" id="rollovervolver" title="Volver"><span>Volver</span></a></td>
						        	</tr>
						        <%}
						        else
						        {%>	
			        				<tr>
									<td width="170px">&nbsp;</td>
									<td width="46px"><a tabindex="15" href="#" onClick="JavaScript:ImprimirDetalle(0);" id="rolloverimprimir" title="Imprimir Remesa"><span>imprimir</span></a></td>
								    <td width="46px"><a tabindex="16" href="#" onClick="JavaScript:VolverConsulta(window.document.FRMINGRESOREM);" id="rollovervolver" title="Volver"><span>Volver</span></a></td>
						        	</tr>
			        		  <%}
			        		}
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
    var f=document.FRMINGRESOREM;	
	valJS.MaskNumber(f.TxtMonto);
	valJS.MaskInput(f.TxtNumInterno,'999999999999999');	
	<%if (Origen.equals("INGRESO")) // en ingreso
	{%>
		InhabilitarCampos("");
		f.MONEDA.disabled="disabled";
	  	f.HDDMONEDA.value=arrMoneda[0][0]; 	  
	<%}
	 else
	  {%>
	  	InhabilitarCampos("disabled");
	<%}%>
	
</SCRIPT>	
</BODY>
</HTML>