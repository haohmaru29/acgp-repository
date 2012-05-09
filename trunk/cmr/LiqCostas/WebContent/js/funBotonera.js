function LimpiarCamposConsulta(obj,valor)
{
	//alert(valor);
	switch (valor)
	{
		case 1: 
		case 5: //consulta REMESA tarjeta
			obj.CTxtFechaDesde.value="";
			obj.CTxtFechaHasta.value="";
			obj.CSUCURSAL.options[0].selected=true;
			obj.CABOGADO.options[0].selected=true;
			obj.CESTADO.options[0].selected=true;
			obj.CNumRemesa.value="";			
			break;
		case 2: //consulta REMESA cheque
			obj.CTxtFechaDesde.value="";
			obj.CTxtFechaHasta.value="";
			obj.CTxtNumCheque.value="";
			obj.CSUCURSAL.options[0].selected=true;
			obj.CABOGADO.options[0].selected=true;
			obj.CESTADO.options[0].selected=true;
			obj.CNumRemesa.value="";			
			break;
		case 3: //consulta COSTAS PROCESALES TARJETA
			obj.CTxtFechaDesde.value="";
			obj.CTxtFechaHasta.value="";
			obj.CSUCURSAL.options[0].selected=true;
			obj.CABOGADO.options[0].selected=true;
			obj.CESTADO.options[0].selected=true;
			obj.CTIPODOC.options[0].selected=true;
			obj.CNumCosta.value="";			
			obj.CNumDocumento.value="";	
			break;	
		case 4: //consulta COSTAS PROCESALES CHEQUE
			obj.CTxtFechaDesde.value="";
			obj.CTxtFechaHasta.value="";
			obj.CTxtNumCheque.value="";
			obj.CSUCURSAL.options[0].selected=true;
			obj.CABOGADO.options[0].selected=true;
			obj.CESTADO.options[0].selected=true;
			obj.CTIPODOC.options[0].selected=true;
			obj.CNumCosta.value="";		
			obj.CNumDocumento.value="";				
			break;				
		case 7: //resumen costa procesal
			obj.CTxtFechaDesde.value="";
			obj.CTxtFechaHasta.value="";
			obj.CSUCURSAL.options[0].selected=true;
			break;	
		case 6: //detalle costa procesal
			obj.CTxtFechaDesde.value="";
			obj.CTxtFechaHasta.value="";
			obj.CSUCURSAL.options[0].selected=true;
			obj.CABOGADO.options[0].selected=true;
			obj.CPRODUCTO.options[0].selected=true;
			break;		
	}		
}

function LimpiarCamposInforme(obj,valor)
{
	switch (valor)
	{
		case 1: //Informe estado
			obj.CTxtFechaDesde.value="";
			obj.CTxtFechaHasta.value="";
			obj.CSUCURSAL.options[0].selected=true;
			obj.CABOGADO.options[0].selected=true;
			obj.CESTADO.options[0].selected=true;
			obj.CPRODUCTO.options[0].selected=true;
			obj.CNumRemesa.value="";			
			break;
			
		case 2: //informe contable
			obj.CTxtFechaDesde.value="";
			obj.CTxtFechaHasta.value="";
			obj.CABOGADO.options[0].selected=true;
			obj.CNumRemesa.value="";			
			obj.TxtMayorA.value="0";
			obj.TxtMenorA.value="0";
			obj.TxtBoleta.value="";
			break;	
	}		
}

function ValidarSeleccion(objSel)
{
 var valseleccion="0";
 var added=0;
 for (added = 0; added <objSel.options.length; added++) 
 {
	if (objSel.options[added].selected==true)
	{	
		valseleccion=objSel.options[added].value;
		break;
	}
 }
 return valseleccion;
}

function SeleccionarElemento(objSel,valdato)
{
	var added=0;
    for (added = 0; added <objSel.options.length; added++) 
	{
		if (valdato==objSel.options[added].value)
		{
	 		objSel.options[added].selected=true;
	 		break;
	 	}
	}
	return true;
}

function ValidaFechasdeBusqueda(obj)
{
    var oFecIni = obj.CTxtFechaDesde;
    var oFecFin = obj.CTxtFechaHasta;
    
    var f1=obj.CTxtFechaDesde.value.split("/");
    var f2=obj.CTxtFechaHasta.value.split("/");

    if (oFecIni.value=="" && oFecFin.value!="")
    {
        if (valJS.isFechaValida(f2[0]+f2[1]+f2[2],"ddmmyyyy")==false) 
        {
           alert("Fecha Hasta no válida");           
           oFecFin.value="";
           oFecFin.focus();   
           return false;
        }else{            
            alert("Para la búsqueda debe ingresar Fecha Desde");
            oFecIni.focus();    
            return false;
        }        
    }
    else
    {
        if (oFecIni.value!="" && oFecFin.value=="")
        {
            if (valJS.isFechaValida(f1[0]+f1[1]+f1[2],"ddmmyyyy")==false) 
            {
               alert("Fecha Desde no válida");
               oFecIni.value="";
               oFecIni.focus();    
               return false;
            }
            else {              
                alert("Para la búsqueda debe ingresa Fecha Hasta");
                oFecFin.focus();    
                return false;
            }        
        }
        else {    
            if (oFecIni.value!="" && oFecFin.value!="")
            {
                if (valJS.isFechaValida(f1[0]+f1[1]+f1[2],"ddmmyyyy")==false) {
                    alert("Fecha Desde no válida");
                    oFecIni.value="";
                    oFecIni.focus();    
                    return false;       
                }else if (valJS.isFechaValida(f2[0]+f2[1]+f2[2],"ddmmyyyy")==false) {
                    alert("Fecha Hasta no válida");
                    oFecFin.value="";
                    oFecFin.focus();   
                    return false;        
                }else if (valJS.ComparaFecha(oFecIni.value, oFecFin.value,"dd/mm/yyyy")==1) {
                    alert("Fecha Hasta debe ser mayor o igual a la Fecha Desde");
                    oFecFin.select(); 
                    oFecFin.focus(); 
                    return false;      
                }
                else
                    return true;
            }  
            else  
            {
            	if (oFecIni.value=="" && oFecFin.value=="")
            	{
            		alert("Para la búsqueda debe ingresa Fecha Desde");
            		oFecIni.select(); 
            		oFecIni.focus();    
            		return false;
            	}
            	else
            		return true;
            }
                
        }    
    }        
}

function ValidaMontosBusqueda(obj)
{
    var oMonto1 = obj.TxtMayorA;
    var oMonto2 = obj.TxtMenorA;
    var flag=true;
    oMonto1=valJS.quitaMask(oMonto1);
    oMonto2=valJS.quitaMask(oMonto2);
    
    //alert(oMonto1);
    //alert(oMonto2);
    
    if (oMonto1>0 && oMonto2>0)
    {
    	if (oMonto2<oMonto1)
    	{
    		alert("Monto Menor a debe ser mayor sa Monto Mayor a");
    		obj.TxtMenorA.select();
    		obj.TxtMenorA.focus();
    		flag=false;
    	}
    }    
    return flag;    
}

function ValidaNumChequeConsulta(obj,rangoini,rangofin)
{
	var flag=true;
	var numcheque=obj.value;	
	if (numcheque.length != 10)
	{
		alert("Número de Cheque debe ser de largo 10");
		obj.select();
		obj.focus();
		flag=false;
	}
	else
	{
		var valdigito=parseFloat(numcheque);
		if ((valdigito >= parseFloat(rangoini)) && (valdigito <= parseFloat(rangofin))) 
		{
			if (valJS.isRutValidoCheque(numcheque)==false)
			{
				alert("Digito verificador del Cheque es incorrecto.");
				obj.select();
				obj.focus();
				flag=false;
			}	
		}
		else
		{
			alert("Número de Cheque no se encuentra dentro de los rangos definidos.");
			obj.select();
			obj.focus();
			flag=false;
		}
	}
	return flag;   
}

function ValidaNumCheque(obj,rangoini,rangofin,objMensaje)
{
	var flag=true;
	var numcheque=obj.value;
	objMensaje.value="";	
	if (numcheque.length != 10)
	{
		//alert("Número de Cheque debe ser de largo 10");
		objMensaje.value="Número de Cheque debe ser de largo 10";
		obj.value="";
		obj.select();
		obj.focus();
		flag=false;
	}
	else
	{
		var valdigito=parseFloat(numcheque);
		if ((valdigito >= parseFloat(rangoini)) && (valdigito <= parseFloat(rangofin))) 
		{
			if (valJS.isRutValidoCheque(numcheque)==false)
			{
				//alert("Digito verificador del Cheque es incorrecto.");
				objMensaje.value="Digito verificador del Cheque es incorrecto.";
				obj.value="";
				obj.select();
				obj.focus();
				flag=false;
			}	
		}
		else
		{
			//alert("Número de Cheque no se encuentra dentro de los rangos definidos.");
			objMensaje.value="Número de Cheque no se encuentra dentro de los rangos definidos.";
			obj.value="";
			obj.select();
			obj.focus();
			flag=false;
		}
	}
	return flag;   
}

