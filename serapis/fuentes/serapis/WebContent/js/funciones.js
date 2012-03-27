function ValidarCaracteres(tipo,e) {
	var n;
	var perdido;
	if (!e) {
		e = window.event;
		if (e.keyCode) codigo = e.keyCode;
	} else if (e.keyCode) {
		codigo = e.keyCode;
	} else if (e.which) {
		codigo = e.which;
	};
	key = String.fromCharCode(codigo);
	switch (tipo) {
	case 0: //numerica
		n= "1234567890.";
		break;
	case 1: //solo letras y numeros
		n= "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz1234567890";
		break;
	case 2: //para dv rut
		n = "1234567890kK";
		break;
	case 3: //solo letras
		n= "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz "
		break;
	case 4: //fecha
		n = "1234567890";
		break;
	case 5://para rut con dv
		n="1234567890.-Kk";
		break;
	case 6://para e-mails
		n="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890_@.";
		break;	
	case 7://para telefonos (numeros con guion)
		n="1234567890-";
		break;
	case 8://para TEXTO
		n="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890_@.-,; " ;
		break;
	case 9://para TEXTO TOTAL
		n="ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz1234567890áéíóúÁÉÍÓÚ .;,+-*/><=äëïöüÄËÏÖÜ()#¿?¡!%$@ ";
		break;
	case 10://números y K para rut con DV
		n="1234567890kK" ;
		break;
	case 11://Fechas
		n="1234567890/" ;
		break;
	case 12://sólo mayúsculas
		n="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		break;
	case 13://enteros con signo
		n="1234567890-+";
		break;	
	}
	var bRet=true;
	if (n.indexOf(key) < 0) 
	{
		bRet=false
	}
	if (codigo == 13)
	{
		bRet=false;
	}
	if(!bRet){
	        try{
	            e.returnValue = false;
	            e.cancelBubble = true;
	
	            if(document.all){ //IE
	                e.keyCode = 0;
	            }else{ //NS
	                e.preventDefault();
	                e.stopPropagation();
	            }
	            window.status = msg; 
	        }catch(ex){
	            //alert(ex);
	        }
	}
	
	return bRet;
}



function isFechaValida(fecha,formato) {
	var valida=true;
	var dia;
	var mes;
	var ano;
	var i;
	if (!fecha)
		return false;
		
	if (!formato)
		formato = "ddmmyyyy";
	
	if (fecha.length != formato.length)
		return false;
		
	//validar formato 
	for (i=0;i<formato.length;i++) {
		switch (formato.charAt(i)) {
		case 'd':
		case 'D':
				if (formato.substring(i,i+2).toUpperCase() == "DD") {
					dia = fecha.substring(i,i+2)
					i++;
				} else
					valida= false;
			break;
		case 'm':
		case 'M':
				if (formato.substring(i,i+2).toUpperCase() == "MM") {
					mes = fecha.substring(i,i+2);
					i++;
				}else
					valida= false;		
			break;
		case 'y':
		case 'Y':
				if (formato.substring(i,i+4).toUpperCase() == "YYYY") {
					ano = fecha.substring(i,i+4);
					i += 3;
					if (parseInt(ano) < 1000) {
						valida= false;				
					}	
				} else
					valida= false;				
			break;
		}
		if (!valida)
			break;
	}
	
	if (valida) {
		mes -= 1;
		var d = new Date(ano,mes,dia);
		if (dia == d.getDate() && mes==d.getMonth() && ano == d.getFullYear()) 
			return true;
	}	
	return false;
}

function EsFecha(caja)
{ 
   var ret=1;
   if (caja)
   {  
      borrar = caja;
      if ((caja.substr(2,1) == "/") && (caja.substr(5,1) == "/"))
      {      
         for (i=0; i<10; i++)
	     {	
            if (((caja.substr(i,1)<"0") || (caja.substr(i,1)>"9")) && (i != 2) && (i != 5))
			{
               borrar = '';
               break;  
			}  
         }
	     if (borrar)
	     { 
	        a = caja.substr(6,4);
		    m = caja.substr(3,2);
		    d = caja.substr(0,2);
		    if((a < 1900) || (a > 2050) || (m < 1) || (m > 12) || (d < 1) || (d > 31))
		       borrar = '';
		    else
		    {
		       if((a%4 != 0) && (m == 2) && (d > 28))	   
		          borrar = ''; // Año no viciesto y es febrero y el dia es mayor a 28
			   else	
			   {
		          if ((((m == 4) || (m == 6) || (m == 9) || (m==11)) && (d>30)) || ((m==2) && (d>29)))
			         borrar = '';	      				  	 
			   }  // else
		    } // fin else
         } // if (error)
      } // if ((caja.substr(2,1) == "/") && (caja.substr(5,1) == "/"))			    			
	  else
	     borrar = '';
	  if (borrar == '')
	     ret=0;
   } // if (caja)   
   return(ret);
} // FUNCION

function Trim(s) {
	return RTrim(LTrim(s));
}

function RTrim(s) {
var i;
var st;
	if (!s) return "";
	var st="";
	for (i=s.length - 1;i>=0;i--) {
		if (s.charAt(i) != " ")	{
				st = s.substring(0,i+1);
				break;
		}
	}
	if (!st)
		return "";
	return(st);
}

function LTrim(s) {
var i;
var st;
	if (!s) return "";
	var st="";
	for (i=0;i<s.length;i++) {
		if (s.charAt(i) != " ")	{
				st = s.substring(i);
				break;
		}
	}
	if (!st)
		return "";
	return(st);
}

function tabulax(str,num) {
var i=0;
var n=0;
n = (num-str.length);
	if (str.length < num){
		for (i=0; i<n; i++){
			str = str.concat(" ");
		}	
	} else {
		str = str.substring(0,num);
	}
return(str);
}

function CambiarFormatoFecha(Fecha,formato1,formato2) {
	var valida=true;
	var dia;
	var mes;
	var ano;
	var fech;
	
	if (!formato1 || !formato2 || (formato1 == formato2))
		return Fecha;
	if (formato1.length != Fecha.length)
		return Fecha;
		
	//validar formato y obtener dia,mes y ano
	for (var i=0;i<formato1.length;i++) {
		switch (formato1.charAt(i)) {
		case 'd':
		case 'D':
				if (formato1.substring(i,i+2).toUpperCase() == "DD") {
					dia = Fecha.substring(i,i+2)
					i++;
				} else
					valida= false;
			break;
		case 'm':
		case 'M':
				if (formato1.substring(i,i+2).toUpperCase() == "MM") {
					mes = Fecha.substring(i,i+2);
					i++;
				}else
					valida= false;		
			break;
		case 'y':
		case 'Y':
				if (formato1.substring(i,i+4).toUpperCase() == "YYYY") {
					ano = Fecha.substring(i,i+4);
					i += 3;
				} else
					valida= false;				
			break;
		}
		if (!valida)
			break;
	}//fin for
	
	fech=""
	if (valida) {	
		for (var j = 0;j < formato2.length ; j++) {
			switch (formato2.charAt(j)) {
			case 'd':
			case 'D':
					if (formato2.substring(j,j+2).toUpperCase() == "DD") {
						fech += Mascara("00",dia);
						j++;
					}
				break;
			case 'm':
			case 'M':
					if (formato2.substring(j,j+2).toUpperCase() == "MM") {
						fech += Mascara("00",mes);
						j++;
					}
				break;
			case 'y':
			case 'Y':
					if (formato2.substring(j,j+4).toUpperCase() == "YYYY") {
						fech += Mascara("0000",ano);
						j += 3;
					} 
				break;
			default:
				fech += formato2.charAt(j)
			}
		}
		
	}
	return fech;
}

function ComparaFecha(FechaI,FechaF,formato) 
{

	var FecI = FechaI;
	var FecF = FechaF;
	if (!formato)
		formato="ddmmyyyy";
	
	if (!isFechaValida(FecI,formato) || !isFechaValida(FecF,formato)) 
		return -1;
	FecI = CambiarFormatoFecha(FecI,formato,"yyyymmdd");
	FecF = CambiarFormatoFecha(FecF,formato,"yyyymmdd");
	if (FecI > FecF)	
		return 1;
	else
		if (FecI<FecF)
			return 2;
		else
			return 0;
}

function Mascara(mask,dato) {
	
	var n = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
	var a = "1234567890";
	var outSalida="";
	var j;
	var i;
	var d;
	if (!dato)
		return "";

	
	//verificar si caracteres de string de datos coinciden con n o a
	//sino devuelve dato
	for (i=0;i<dato.length;i++)	{
		d = dato.charAt(i);
		if ((n.indexOf(d) < 0 &&  a.indexOf(d) < 0)) {
		 	return dato;
		 	break;
		}
	}	 
	 
	var salida = "";
	var carac;
		
	if (dato.length <= 0)
		return "";
	
	var j=dato.length - 1;	
	for (i=mask.length;i>=0;i--) {
		if (j>=0)
			carac = dato.charAt(j);  //asignar ultimo caracter del dato		
		switch (mask.charAt(i)){
		case '0':
			if (j<0)
				salida += '0';
			else
				salida += carac;
			j--;
			break;
			
		case '9':
			if (j>=0)
				salida += carac;
			j--;
			break;
		case 'z':
		case 'Z':
			if (j>= 0)
				salida += carac;					
			j--;
			break;
		default:
			if (j>=0)
				salida += mask.charAt(i);
			break;
		}
	}	
	for (i=salida.length-1;i>=0;i--)
		outSalida += salida.charAt(i); 
	
	return outSalida;
}

function calculaFecha(xstrfecha,xintmeses) {
	var lintdia = parseFloat(xstrfecha.substring(0,2));
	var lintmes = parseFloat(xstrfecha.substring(3,5));
	var lintano = parseFloat(xstrfecha.substring(6,10));
	var lintaux = 0;
	var lintind = 0;
	var lstrretorno = "";
	
	lintmes = parseFloat(lintmes) + parseFloat(xintmeses);
	for(lintind=0; lintind<parseInt(xintmeses/12); lintind++) {
		lintano++;
		lintmes -= 12;
	}
	if (lintmes > 12) {
		lintano++;
		lintmes -= 12;
	} 

	if (lintdia.toString().length < 2)
		lintdia = "0" + lintdia.toString();
	if (lintmes.toString().length < 2)
		lintmes = "0" + lintmes.toString();
	
	lstrretorno = lintdia+"-"+lintmes+"-"+lintano;	
	
	if (!isFechaValida(lstrretorno,"dd-mm-yyyy")) {
		lintdia = (parseFloat(lintdia) - 1);
	}
	
	if (lintdia.toString().length < 2)
		lintdia = "0" + lintdia.toString();
	if (lintmes.toString().length < 2)
		lintmes = "0" + lintmes.toString();

	lstrretorno = lintdia+"-"+lintmes+"-"+lintano;	
	
	return lstrretorno;	
}	

function isemailValido(email) 
{
	var punto = ".";
	var arroba = "@";
	
	var s1 = email.indexOf(punto);
	var s2 = email.indexOf(arroba);	
	var s3 = email.indexOf(punto,s2);
	
	if ((s1 == -1) || (s2 == -1) || (s3 == -1)) 
		return false;
	else
		return true;	
}

function isRutValido(rut) {
	
	while (rut.indexOf(".") >=0)
		rut = rut.replace(".","");

	var n = "1234567890kK"
	var suma = 0;
	var dv;
	var v;
	var j=2;
	
	if (!rut) 
		return false;
	
	for (var i=0;i<rut.length;i++) {
		if (n.indexOf(rut.charAt(i)) < 0 )
			return false;
	}
	dv =  rut.substring(rut.length - 1);
	rut = rut.substring(0,rut.length - 1);

	
	for (var i=rut.length - 1;i>=0;i--) {
			
		if (j > 7 )
			j =2;
		suma += j++ * rut.charAt(i);
	}
	
	v = 11 - (suma % 11);
	if (v == 10)
		  v = "K";
	if (v == 11)
          v = "0";
	if (dv.toUpperCase() == v) 
		return true;
	return false;
}	

function formatNumber(xintnumero){
	if (!isNaN(xintnumero)) {
		var lstrnumret = "";
		var lstrnumero = xintnumero.toString();
		var lintlargo  = lstrnumero.length; 
		var lintind    = 0;
		var lintcan    = parseInt(lintlargo/3);
		var lintaux    = parseFloat(lintlargo/3) - lintcan;
		
		for (lintind=0;lintind<lintcan;lintind++){
			lstrnumret = "." + lstrnumero.substring(lintlargo - 3, lintlargo) + lstrnumret; 
			lintlargo = lintlargo - 3;
		}
		lstrnumret = lstrnumero.substring(0, lintlargo) + lstrnumret; 
		
		if (lintaux == 0) {
			lstrnumret = lstrnumret.substring(1,lstrnumret.length);
		}
		return lstrnumret;
	}
	else {
		return xintnumero;
	}
}

//***** FUNCIONES DE VALIDACION DE RUT *****
function checkRutField(ObjRut)
{
  var tmpstr = "";
  var rut = ObjRut.value;
  if(rut.length<6)
  {
	alert("El R.U.T. debe ser de largo mínimo 6");
    ObjRut.focus();
    ObjRut.select();
    return false;
  }
  for ( i=0; i < rut.length ; i++ )
    if ( rut.charAt(i) != ' ' && rut.charAt(i) != '.' && rut.charAt(i) != '-' )
      tmpstr = tmpstr + rut.charAt(i);
  rut = tmpstr;
  largo = rut.length;
// [VARM+]
  tmpstr = "";
  for ( i=0; rut.charAt(i) == '0' ; i++ );
  for (; i < rut.length ; i++ )
     tmpstr = tmpstr + rut.charAt(i);
  rut = tmpstr;
  largo = rut.length;
// [VARM-]
  if ( largo < 2 )
  {
    alert("Debe ingresar el rut completo.");
    ObjRut.focus();
    return false;
  }
  for (i=0; i < largo ; i++ )
  {
    if( (rut.charAt(i) != '0') && (rut.charAt(i) != '1') && (rut.charAt(i) !='2') && (rut.charAt(i) != '3') && (rut.charAt(i) != '4') && (rut.charAt(i) !='5') && (rut.charAt(i) != '6') && (rut.charAt(i) != '7') && (rut.charAt(i) != '8') && (rut.charAt(i) != '9') && (rut.charAt(i) !='k') && (rut.charAt(i) != 'K') )
    {
      alert("El valor ingresado no corresponde a un R.U.T valido.");
      ObjRut.focus();
      ObjRut.select();
      return false;
    }
  }
  var invertido = "";
  for ( i=(largo-1),j=0; i>=0; i--,j++ )
    invertido = invertido + rut.charAt(i);
  var drut = "";
  drut = drut + invertido.charAt(0);
  drut = drut + '-';
  cnt = 0;
  for ( i=1,j=2; i<largo; i++,j++ )
    {
    if ( cnt == 3 )
    {
      drut = drut + '.';
      j++;
      drut = drut + invertido.charAt(i);
      cnt = 1;
    }
    else
    {
      drut = drut + invertido.charAt(i);
      cnt++;
    }
  }
  invertido = "";
  for ( i=(drut.length-1),j=0; i>=0; i--,j++ )
    invertido = invertido + drut.charAt(i);
  ObjRut.value = invertido;
  if(!checkDV(rut))
    return false;
  return true;
}

function checkDV(crut)
{
  largo = crut.length;
  if(largo < 2)
  {
    alert("Debe ingresar el rut completo.");
    crut.select();
    crut.focus();
    return false;
  }
  if(largo > 2)
  {
    rut = crut.substring(0, largo - 1);
  }
  else
  {
    rut = crut.charAt(0);
  }
  dv = crut.charAt(largo-1);
  if(!checkCDV(dv))
     return false;
  if(rut == null || dv == null){
      return false;
  }
  var dvr = '0';
  suma = 0;
  mul  = 2;
  for (i= rut.length -1 ; i >= 0; i--){
    suma = suma + rut.charAt(i) * mul;
    if(mul == 7)
    {
      mul = 2;
    }
    else
    {
      mul++;
    }
  }
  res = suma % 11;
  if (res==1)
  {
    dvr = 'k';
  }
  else
  {
    if(res==0)
    {
      dvr = '0';
    }
    else
    {
      dvi = 11-res;
      dvr = dvi + "";
    }
  }
  if(dvr != dv.toLowerCase())
  {
    alert("EL rut es incorrecto.");
    return false;
  }
  return true;
}

function checkCDV(dvr)
{
  dv = dvr + "";
  if(dv != '0' && dv != '1' && dv != '2' && dv != '3' && dv != '4' && dv != '5' && dv != '6' && dv != '7' && dv != '8' && dv != '9' && dv != 'k'  && dv != 'K'){
    alert("Debe ingresar un digito verificador valido.");
    document.login.rut_aux.select();
    document.login.rut_aux.focus();
    return false;
  }
  return true;
}

function quitaPuntos(Objrut) 
{
	var lstrrut = Objrut.value;
	while (lstrrut.indexOf(".") >= 0)
		lstrrut = lstrrut.replace(".","");
	while (lstrrut.indexOf("-") >= 0)
		lstrrut = lstrrut.replace("-","");
	Objrut.value = lstrrut;
}

//verifica que el objeto no este vacio campos input
function isEmpty(obj) {
	if (!obj)
		return false;
	if (Trim(obj.value)=="") 
		return true;
	obj.focus();
	return false;
}

//obtiene el valor seleccionado de un objeto select e input
function getValor(obj) {
	if (!obj)
		return "";
	if (!obj.length)
		return obj.value;
	if (obj.selectedIndex) {	
		return obj.options[obj.selectedIndex].value;
	} else {
		for (i=0;i<obj.length;i++)
			if (obj.options[i].selected)
				return obj.options[i].value;
	}
}

function getTexto(obj) {
	if (!obj)
		return "";
	if (!obj.length)
		return obj.text;
	if (obj.selectedIndex) {	
		return obj.options[obj.selectedIndex].text;
	} else {
		for (i=0;i<obj.length;i++)
			if (obj.options[i].selected)
				return obj.options[i].text;
	}
}

//devuleve true si el valor numero es un numero entero
function isEntero(numero) {
	valor = Trim(numero);
	var num = "0123456789";
	if (valor=="")
		return false;
		
	for (i=0;i<valor.length;i++)
		if (num.indexOf(valor.charAt(i)) == -1) {	
			return false;
		}
	return true;
}

//busca texto en obj select y lo posiciona en el obj
function PosicionaTextoEnObj(obj,texto) {
	if (!obj)
		return false;
	if (!obj.length)
		obj.selectedIndex=-1;
	for (i=0;i<obj.length;i++) {
		if (obj.options[i].text.toLowerCase().indexOf(texto.toLowerCase()) == 0) {
			obj.options[i].selected=true;
			break;
		}
	}
}
//busca value en obj select y lo posiciona en el obj
function PosicionaValorEnObj(obj,texto) {
	if (!obj)
		return false;
	if (!obj.length)
		obj.selectedIndex=-1;
	for (i=0;i<obj.length;i++) {
		if (obj.options[i].value.toLowerCase().indexOf(texto.toLowerCase()) == 0) {
			obj.options[i].selected=true;
			break;
		}
	}
}

//Saca el substring hasta el caracter indicado
function SacaSubStringAntesDe(dato,caracter)
{
	var datopaso;
	
	datopaso = dato;
	for (i=0;  datopaso.length -1 ; i++)
	{
		if(datopaso.charAt(i) == caracter)
		{
			dato = datopaso.substring(0,i);
			break;
		}
	}
	return dato;
}

//Saca el substring hasta el caracter indicado
function SacaSubStringDespuesDe(dato,caracter)
{
	var datopaso;
	
	datopaso = dato;
	for (i=0;  datopaso.length -1 ; i++)
	{
		if(datopaso.charAt(i) == caracter)
		{
			dato = datopaso.substring(i+1,datopaso.length);
			break;
		}
	}
	return dato;
}







