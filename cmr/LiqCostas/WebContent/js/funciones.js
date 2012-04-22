function valJS() {
	
}
//browser detection
valJS.strUserAgent = navigator.userAgent.toLowerCase(); 
valJS.isIE =  navigator.userAgent.toLowerCase().indexOf("msie") > -1; 
valJS.isNS6 =  navigator.userAgent.toLowerCase().indexOf("netscape6") > -1; 
valJS.isNS4 = !this.isIE && !this.isNS6  && parseFloat(navigator.appVersion) < 5; 
valJS.sepDecimal = ",";
valJS.sepMiles = ".";
valJS.CantDecimal = 0;

valJS.checkClipboardCode = function (objEvent, strKey) {
	var reClipboardChars = /[cvxz]/i;
  	if (this.isNS6)
	    	return (objEvent.ctrlKey || objEvent.altKey || objEvent.shiftKey) && reClipboardChars.test(strKey);
  	else
    		return false;
}

valJS.ValidarCaracteres=function (tipo,e) {
	var strKey=e.charCode||window.event.keyCode||e.which;		
	var key = String.fromCharCode(strKey);
	var b = this.ValidaCaracter(tipo,key);
	if (!b) {
		if (e.stopPropagation) {
			e.preventDefault();
			e.stopPropagation();
		}else if (window.event) {
			window.event.cancelBubble = true;
			window.event.returnValue=false;
		} else
			return false;
	} 
}

valJS.ValidaCaracter=function(tipo,key) {
	var n;
	if (!key) {
		return true;
	}
	switch (tipo) {
	case 0: //numerica
		n= "1234567890.";
		break;
	case 1: //solo letras y numeros
		n= "ABCDEFGHIJKLMN�OPQRSTUVWXYZabcdefghijklmn�opqrstuvwxyz1234567890.";
		break;
	case 2: //para dv rut
		n = "1234567890kK";
		break;
	case 3: //solo letras
		n= "ABCDEFGHIJKLMN�OPQRSTUVWXYZabcdefghijklmn�opqrstuvwxyz "
		break;
	case 4: //fecha
		n = "1234567890";
		break;
	case 5://para rut con dv
		n="1234567890kK.-";
		break;
	case 6://para e-mails
		n="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890_@.";
		break;	
	case 7://para telefonos (numeros con guion)
		n="1234567890-";
		break;
	case 8://para TEXTO
		n=" ABCDEFGHIJKLMN�OPQRSTUVWXYZ�����abcdefghijklmn�opqrstuvwxyz�����1234567890_@.-+�?/*�!$#(),; {}";
		break;
	case 9: //numerica con negativos
		n= "1234567890.-";
		break;
	case 10: //expresion matematica
		n= " ABCDEFGHIJKLMN�OPQRSTUVWXYZabcdefghijklmn�opqrstuvwxyz1234567890+-/*^(){}.%#";
		break;	
	case 11:
		n= " ABCDEFGHIJKLMN�OPQRSTUVWXYZabcdefghijklmn�opqrstuvwxyz1234567890+-/*^().";
	}

	if (n.indexOf(key) >= 0) {
			return true;
	}
	return false;
}

valJS.isFechaValida= function (fecha,formato) {
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

	if (valida) 
	{
		mes -= 1;
		var d = new Date(ano,mes,dia);	
			
		//alert("d=" + d);
		//alert(ano + "=" + d.getFullYear() + "," + mes + "=" + d.getMonth() + "," + dia + "=" + d.getDate())
		if (dia == d.getDate() && mes==d.getMonth() && ano == d.getFullYear()) 
			return true;
	}	
	return false;
}


valJS.isHoraValida= function (hora,formato) {
	var valida=true;
	var hor;
	var min;
	var seg;
	var i;
	
	if (!hora)
		return false;
			
	if (!formato)
		formato = "hhmmss";
	
	if (hora.length != formato.length)
		return false;
		
	//validar formato 
	for (i=0;i<formato.length;i++) {
		switch (formato.charAt(i)) {
		case 'h':
		case 'H':
				if (formato.substring(i,i+2).toUpperCase() == "HH") {
					hor = hora.substring(i,i+2)
					i++;
				} else
					valida= false;
			break;
		case 'm':
		case 'M':
				if (formato.substring(i,i+2).toUpperCase() == "MM") {
					min = hora.substring(i,i+2);
					i++;
				}else
					valida= false;		
			break;
		case 's':
		case 'S':
				if (formato.substring(i,i+2).toUpperCase() == "SS") 
				{
					seg = hora.substring(i,i+2);
					i ++;					
				} else
					valida= false;				
			break;
		}
		if (!valida)
			break;
	}

	if (valida) 
	{		
		
		var d = new Date(0,0,0,hor,min,seg);				
		//alert(d.getHours());
		//alert(d.getMinutes());
		//alert(d.getSeconds());
		if (hor == d.getHours() && min==d.getMinutes() && seg == d.getSeconds()) 
		  return true;		
	}	
	return false;
}

valJS.Trim = function(s) {
    	//return this.replace(/^\s*(\S*(\s+\S+)*)\s*$/, s);
	return this.RTrim(this.LTrim(s));
}

valJS.RTrim = function(s) {
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

valJS.LTrim = function(s) {
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

valJS.LTrimCero = function(s) {
var i;
var st;
	if (!s) return "";
	var st="";
	for (i=0;i<s.length;i++) {

		if (s.charAt(i) != "0")	{
				st = s.substring(i);
				break;
		}
	}
	if (!st)
		return "";
	return(st);
}

valJS.tabulax = function(str,num) {
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

valJS.CambiarFormatoFecha= function (Fecha,formato1,formato2) {

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
						fech += this.Mascara("00",dia);
						j++;
					}
				break;
			case 'm':
			case 'M':
					if (formato2.substring(j,j+2).toUpperCase() == "MM") {
						fech += this.Mascara("00",mes);
						j++;
					}
				break;
			case 'y':
			case 'Y':
					if (formato2.substring(j,j+4).toUpperCase() == "YYYY") {
						fech += this.Mascara("0000",ano);
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



valJS.CambiaFormatoHora= function (hora,formato1,formato2) {

	var valida=true;
	var hor;
	var min;
	var seg;
	var horaaux;
	var i;
	
	if (!formato1 || !formato2 || (formato1 == formato2))
		return hora;
	if (formato1.length != hora.length)
		return hora;
		
	//validar formato y obtener dia,mes y ano
	for (var i=0;i<formato1.length;i++) {
		switch (formato1.charAt(i)) {
		case 'h':
		case 'H':
				if (formato1.substring(i,i+2).toUpperCase() == "HH") {
					hor = hora.substring(i,i+2)
					i++;
				} else
					valida= false;
			break;
		case 'm':
		case 'M':
				if (formato1.substring(i,i+2).toUpperCase() == "MM") {
					min = hora.substring(i,i+2);
					i++;
				}else
					valida= false;		
			break;
		case 's':
		case 'S':
				if (formato1.substring(i,i+2).toUpperCase() == "SS") {
					seg = hora.substring(i,i+2);
					i++;
				} else
					valida= false;				
			break;
		}
		if (!valida)
			break;
	}//fin for
	
	horaaux=""
	if (valida) {	
		for (var j = 0;j < formato2.length ; j++) {
			switch (formato2.charAt(j)) {
			case 'h':
			case 'H':
					if (formato2.substring(j,j+2).toUpperCase() == "HH") {
						horaaux += this.Mascara("00",hor);
						j++;
					}
				break;
			case 'm':
			case 'M':
					if (formato2.substring(j,j+2).toUpperCase() == "MM") {
						horaaux += this.Mascara("00",min);
						j++;
					}
				break;
			case 's':
			case 'S':
					if (formato2.substring(j,j+2).toUpperCase() == "SS") {
						horaaux += this.Mascara("00",seg);
						j ++;
					} 
				break;
			default:
				horaaux += formato2.charAt(j)
			}
		}
		
	}
	return horaaux;
}



valJS.ComparaFecha=function(FechaI,FechaF,formato) 
{

	var FecI = FechaI;
	var FecF = FechaF;
	if (!formato)
		formato="ddmmyyyy";
	
	if (!this.isFechaValida(FecI,formato) || !this.isFechaValida(FecF,formato)) 
		return -1;
	FecI = this.CambiarFormatoFecha(FecI,formato,"yyyymmdd");
	FecF = this.CambiarFormatoFecha(FecF,formato,"yyyymmdd");
	if (FecI > FecF)	
		return 1;
	else
		if (FecI<FecF)
			return 2;
		else
			return 0;
}

valJS.Mascara = function(mask,dato) {
	
	var n = "ABCDEFGHIJKLMN�OPQRSTUVWXYZabcdefghijklmn�opqrstuvwxyz";
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

valJS.calculaFecha= function(xstrfecha,xintmeses) {
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


valJS.isEmail= function(valor) {
  if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(valor))
  {  
   return (true)
  } else {
   return (false);
  }
 }


valJS.isRutValido=function(rut) {
	
	if(rut.length==1) {
		return false;
	}
	
	while (rut.indexOf(".") >=0)
		rut = rut.replace(".","");
	while (rut.indexOf("-") >=0)
		rut = rut.replace("-","");
	
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


valJS.formatNumber=function (xintnumero){
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


valJS.bisiesto = function(year) {
  	if (year % 400 == 0)
    return true;

 	if (year % 100 == 0)
    return false;

  	return (year % 4 == 0);
}


valJS.Enter = function (form){
   var fields=form.elements, a=[];
   // add ENTER listeners
   for(var ii=fields.length; ii--;) {
     if(fields[ii].type=="text" || fields[ii].type.substring(0,6)=="select" ) {
       _e(fields[ii], "onkeypress", enterListener);
       a[a.length]=fields[ii];
     }
   }
   // init the tabIndex behavior
   a.sort(
     function(a,b){
       return a.tabIndex > b.tabIndex ? 1 : -1;
     }
   );
   for(var j=0; j<a.length; j++){
     a[j].next = a[j+1]||a[0];
   }
   // add the submission manager
   _e(
     form,
     "onsubmit",
     function(evt){
       if(form.hasEntered) {
         form.hasEntered=false;
         return false;
       }
       return true;
     }
   );
   // ENTER listener
   function enterListener(evt){
     evt=evt||window.event;
     var el=this;
     if((evt.keyCode||evt.which)==13) {
       form.hasEntered=true;
       setTimeout(
         function(){ 
         	el.next.focus(); },
         1
       );
     }
   }
}
// event manager
function _e(obj, evt, func){
   if(obj[evt]) {
     obj[evt]=(function(h){
       return function(evt){
         return func.call(this, evt) && h.call(this, evt);
       }
     })(obj[evt]);
   } else {
     obj[evt]=func;
   }
}


valJS.handleEnter =  function (field, event, mayus) {
        var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
        var a= [];
        if (keyCode == 13) {
            var i;
               //alert("entro");
            //i = field.form.elements[i].tabIndex + 1;
            i=0;
	    //alert(field.form.elements.length);	
            for( j = 0 ; j < field.form.elements.length; j++){
                //asignamos todos los objetos que estan disponibles, visibles, enabled
		if (field.form.elements[j].type)
		{
		   if (field.form.elements[j].style.visibility!="hide" && !field.form.elements[j].disabled) 
		   {             
		    //alert("type=" + field.form.elements[j].type);    
		   	if (field.form.elements[j].type=="text" || field.form.elements[j].type.substring(0,6)=="select" || field.form.elements[j].type=="checkbox" || field.form.elements[j].type=="radio" || field.form.elements[j].type=="button" || field.form.elements[j].type=="password")
		   	{	
				//alert("entro1");
                	    	a[a.length] = field.form.elements[j];                
                	}	
		   }
		}
		//alert("salio");
            }
   	// init the tabIndex behavior
	a.sort(
     		function(a,b){
     		  	return a.tabIndex > b.tabIndex ? 1 : -1;
     		}
   	);   	
   	for(var j=0; j<a.length; j++){
     		a[j].next = a[j+1]||a[0];
   	}            
   	
            if (!a.length) return false;
            for (j=0; j< a.length;j++) {
            	if (a[j].tabIndex > field.tabIndex) {            		
            		try {
            			a[j].focus();
            			break;
            		} catch(ex) {
            		}
            	}
            }
            //alert("j=" + j);
            //alert("a=" + a);
            //alert("largo a=" + a.length);
            if (j==a.length) {//no encontro control empezar desde el comienzo
	            for (j=0; j< a.length;j++)
	            	if (a[j].tabIndex < field.tabIndex) {
	            		try {	            		
	            			a[j].focus();
	            			break;
	            		} catch(ex) {}
	            	}

        	}
            
            return false;
        }
        else if (mayus){
        	if (event.keyCode) 
        		event.keyCode = String.fromCharCode(keyCode).toUpperCase().charCodeAt(0); 
        	else if (event.which)
        		event.which = String.fromCharCode(keyCode).toUpperCase().charCodeAt(0); 
        	else
        		event.charCode = String.fromCharCode(keyCode).toUpperCase().charCodeAt(0); 
        	return true;
	} else
        	return true;
    }   

valJS.addEvent = function(o, e, f, s){
    var r = o[r = "_" + (e = "on" + e)] = o[r] || (o[e] ? [[o[e], o]] : []), a, c, d;
    r[r.length] = [f, s || o], o[e] = function(e){
        try{
            (e = e || event).preventDefault || (e.preventDefault = function(){e.returnValue = false;});
            e.stopPropagation || (e.stopPropagation = function(){e.cancelBubble = true;});
            e.target || (e.target = e.srcElement || null);
            e.key = (e.which + 1 || e.keyCode + 1) - 1 || 0;
        }catch(f){}
        for(d = 1, f = r.length; f; r[--f] && (a = r[f][0], o = r[f][1], a.call ? c = a.call(o, e) : (o._ = a, c = o._(e), o._ = null), d &= c !== false));
        return e = null, !!d;
    }
};

valJS.MaskInput = function(f, m){
	
	function mask2(e){	
			
		var carac = (m.match(/[^90zZ]/g))?m.match(/[^90zZ]/g).join(""):"";
		var c = String.fromCharCode(k = e.key)
		if (e.key==37 || e.key==40) return true;
		if (m.match(/Z/gi)) {
			return true;		
		} else if (m.match(/\d+/g) && c.match(/\d/))
			return true
		else if (carac.indexOf(c)!=-1)
			return true;
			
		return false;
	}
			
	function mask(e){
		
		f.value = valJS.Mask( f.value ,m,1);		
									
	}
	//f.maxLength = m.length;
	this.addEvent(f, "keypress", mask2);
	this.addEvent(f, "keyup", mask2);		
	this.addEvent(f, "blur", mask);				
};

valJS.disabledForm = function(frm,bol) {
	if (!frm) return false;
	try {		
		for (var i=0;i<frm.elements.length;i++) 
		{				
		  if (frm.elements[i].type)
		  {			
			//alert(frm.elements[i].type);
			if (frm.elements[i].type!="hidden" && frm.elements[i].type.substring(0,6)!="select")
			{
				frm.elements[i].readOnly= true; //frm.elements[i].disabled = true;
				if (frm.elements[i].type=="checkbox"  || frm.elements[i].type=="radio")
				  frm.elements[i].disabled = true;
			}	
			else if (frm.elements[i].type.substring(0,6)=="select")
				frm.elements[i].disabled = true;
		  }		
		}
	} catch(ex) {};
		//if (frm.elements[i].type="text" || frm.elements[i].type.substring(0,6)="se"
}

valJS.DateAdd = function (timeU,byMany,fecha, formato ) {
	if (!formato) formato= "yyyymmdd";
	
	if (!this.isFechaValida(fecha,formato)) return fecha;
	var fec1 = this.CambiarFormatoFecha(fecha,formato,"yyyymmdd")		
	//alert("byMany=" + byMany);
	//alert("fec1=" + fec1);
	var millisecond=1;
	var second=millisecond*1000;
	var minute=second*60;
	var hour=minute*60;
	var day=hour*24;
	var year=day*365;

	var ano = eval(fec1.substring(0,4));
	//alert("ano=" + fec1.substring(0,4));
	var mes    = eval(fec1.substring(4,6)) - 1;
	//alert("mes=" + fec1.substring(4,6));
	var dia = eval(fec1.substring(6,8));
	//alert("dia=" + fec1.substring(6,8));
	//alert("ano=" + ano);
	//alert("mes=" + mes);
	//alert("dia=" + dia);
	
	var newDate = new Date(ano,mes,dia);
	//alert("newDate=" + newDate);
	switch(timeU) {
		case "d": newDate.setDate(dia + byMany); break;
		case "m": newDate.setMonth(mes + byMany); break;
		case "y": newDate.setFullYear(year + byMany); break;
	}
	fec1 = "" +  this.Mascara("0000","" + newDate.getFullYear()) + this.Mascara("00","" + (newDate.getMonth() + 1)) + this.Mascara("00","" + newDate.getDate());
	return this.CambiarFormatoFecha(fec1,"yyyymmdd", formato)
	
}
valJS.DateDiff = function (tipo,fecha1, fecha2, formato) {
	if (!tipo) return false;
	if (!this.isFechaValida(fecha1,formato)) return 0;
	if (!this.isFechaValida(fecha2,formato)) return 0;
	var fec1 = this.CambiarFormatoFecha(fecha1,formato,"yyyymmdd")
	var fec2 = this.CambiarFormatoFecha(fecha2,formato,"yyyymmdd")
	var ano = eval(fec2.substring(0,4));
	var anonac = eval(fec1.substring(0,4));
	var mes    = eval(fec2.substring(4,6));
	var mesnac = eval(fec1.substring(4,6));
	var dia = eval(fec2.substring(6,8));
//alert("fec2substrinf =" + fec2.substring(6,8));
//alert("fec2 parseint =" + eval(fec2.substring(6,8)));
//alert("fec2 =" + fec2  );
//alert("dia =" + dia  );
	var dianac = eval(fec1.substring(6,8));
//alert("fec1substrinf =" + fec1.substring(6,8));
//alert("fec1 parseint =" + eval(fec1.substring(6,8)));

//alert("fec1 =" + fec1  );
//alert("dianac=" + dianac );
	//return (((Date.parse(strDate2))-(Date.parse(strDate1)))/(24*60*60*1000)).toFixed(0);
	a1 = ano - anonac;
	m1 = mes - mesnac;


	d1 = dia - dianac;
//alert("a1=" + a1 );
//alert("m1=" + m1 );
//alert("d1=" + d1 );

	if (m1==0 && d1 < 0) {
	   d=d1;
	   a1 = a1 - 1;
	   m1 = 11 + m1;
	
	   if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
	      d1 = 31 + d;
	   }
	   else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
	        d1 = 30 + d;
	   } else {d1 = 29 + d;}
	
	   }
	else {
	     if (m1 < 0) {a1 = a1 - 1; m1 = 12 + m1;}
	
	     if (d1 < 0) {
	        m1 = m1 - 1;
	        d = d1;
	        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
	           d1 = 31 + d;
	        }
	        else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
	           d1 = 30 + d;
	        }
	        else {
	           d1 = 29 + d;
	        }
	   }
	}	
	tipo = valJS.Trim(tipo.toUpperCase());
	if (tipo=="Y")
		return a1;
	else if (tipo=="M")
		
		return m1; //* a1*12;
	else if (tipo=="D") {
		
		var miFecha1 = new Date( ano, mes, dia )
		var miFecha2 = new Date( anonac, mesnac, dianac )		
		//alert("miFecha1 =" + miFecha1 );
		//alert("miFecha2 =" + miFecha2 );
		var diferencia = miFecha1 - miFecha2;
		//alert("diferencia  =" + diferencia  );
		//alert("diferencia  =" + (diferencia / (1000 * 60 * 60 * 24)));
		//alert("diferencia  =" + (diferencia % (1000 * 60 * 60 * 24)));
		//d1 = Math.floor(diferencia / (1000 * 60 * 60 * 24))		
		d1 = Math.round(diferencia / (1000 * 60 * 60 * 24))

		//alert("d1  =" + d1  );
		return d1;
	}
	return 0;
	
}

valJS.escala = function(valor,sepMiles,sepDec,NumDec,signo)
{		 
	var enmascara= function (ent) {
		
		var mask="";
		var i=0;
		for (i=0;i<6;i++) {
			mask += "zzz" + sepDec; 
		}
		mask = mask.substr(0,mask.length-1);

		return "";
		//return this.Mascara(mask,valor);
	}
	
	var monto=valor;	
	var ent, tmp, dec, val, ret= "";
	var neg = "";	
	 if ( val = monto.match(/\-\d+(\,\d{3})*/) ) neg = "-";
	 //valor con decimales
	 if (val = monto.match(/(\d+)(\,\d{3})*(\.\d+)$/)) {		    
		ent = val[0].match(/(\d+)(\,\d{3})*/)[0].replace(/\,/g,"");
		dec = val[0].match(/\d+$/)[0];		
		if (dec.length>2) dec = dec.substring(0,2);
		else if (dec.length==1) dec= dec + "0";			
		ret = neg + enmascara(ent) + sepDec + dec;			
	 } else	//sin decimales 						
		if (val = monto.match(/^\.\d+$/) )  {			
			dec = monto.match(/\d+$/)[0];
			if (dec.length>2) dec = dec.substring(0,2);
			else if (dec.length==1) dec= dec + "0";		
			ret = neg + dec;	
		} else
		if (val = monto.match(/\d+(\,\d{3})*$/) ) 
				ret = neg + val[0].replace(/\,/g,"") + "00";
		 
	
	
	return (ret);
}

valJS.isNumeric = function(valor,bDec,sepMiles,sepDec,signo)
{		
	if (!bDec) bDec=false;
	if (!sepMiles) sepMiles= this.sepMiles;
	if (!sepDec) sepDec=this.sepDecimal;
	if (!signo) signo="-";
	var esp = "*(){}[]+?$^";
	var monto = "";
	for (i=0;i<valor.length;i++) {
		if (esp.indexOf(valor.substr(i,1))!=-1){
			monto += "\\" + valor.substr(i,1)
			
		}else {
			if (sepMiles==valor.substr(i,1))
				monto +=",";
			else if (sepDec==valor.substr(i,1))
				monto += ".";
			else
				monto +=valor.substr(i,1);
		}
	}			

	if (bDec)
		return monto.match(  /^(-)?((\d+){0,1}(\,\d{3})*)((\.){1}(\d+)*){0,1}$/  )? true:false;	
	else
		return monto.match(  /^(-)?((\d+){0,1}(\,\d{3})*)$/  )? true:false;	
}

valJS.Mask = function(valor, mask, orden) {
	var i=0;
	if (!orden) orden=1;
	var n="0123456789";
	var carac = "0Zz9";
	if (!valor) return "";
	if (!mask) return valor;
	if (valor.length=="") return "";

	var arrR = new Array();	
	
	var largo=0;
	//rellenar despues del punto	
	var tienePunto=false;
	
	var posm = mask.indexOf(this.sepDecimal);
	if (posm!=-1) {
		if (posm!=-1)
			posm = mask.length - (posm+1);//cantidad de caracteres despues del punto
		var pos = valor.indexOf(this.sepDecimal);	
		if (pos	!= -1 ) {
			pos = valor.length-(pos+1);  //cantidad de caracteres despues del punto	
			tienePunto = true;
		}else if (mask.substring(posm,mask.length).indexOf("0")!=-1){  //si tiene valores de mascara 0
			valor= valor +this.sepDecimal
			pos=0;
			tienePunto = true;
		}else
			posm=-1
				
		for (i=0;i<(posm-pos);i++)
			valor += " ";
	} 
	if (!tienePunto)
		if (mask.indexOf(this.sepDecimal)!=-1)
			mask=mask.substring(0,mask.indexOf(this.sepDecimal))

	//pasar string a array	
	var arrV = valor.match(/./g);
	var arrM = mask.match(/./g);

	//orden 1; se setea por la izquierda	
	if (orden==1) {
		if (arrV.length>arrM.length) {
			largo = arrV.length-arrM.length;
			for (i=0;i<largo;i++) {
				if (!arrM.splice) {
					 var arrPaso = arrM.reverse();
					 arrPaso[arrPaso.length] = ""
					 arrM = arrPaso.reverse();
				} else
					arrM.splice(0,0,"");
			}
				
		} else if (arrV.length<arrM.length) {
			
			largo =arrM.length-arrV.length;

			for (i=0;i<largo;i++){
				if (!arrV.splice) {
					 var arrPaso = arrV.reverse();
					 arrPaso[arrPaso.length] = ""
					 arrV = arrPaso.reverse();
				} else
					arrV.splice(0,0,"");
			}
			
		} 
			
		arrV = arrV.reverse();
		arrM = arrM.reverse();
		
	} else if(orden==2){ //se setea por la derecha
		if (arrV.length>arrM.length){
			largo = arrV.length-arrM.length;
			for (i=0;i<largo;i++)
				arrM[arrM.length]="";
		} else if (arrV.length<arrM.length) {			
			largo = arrM.length-arrV.length
			for (i=0;i<largo;i++) 
				arrV[arrV.length]=""
		}
		
	}	

	//proceso de mascara
	var j=0;
	for (i=0;i<arrM.length;i++) {		
			switch (arrM[i]){
			case '0':
				if (arrV[i]=="" || arrV[i]==" ")
					arrR[j++] = "0";
				else if (n.indexOf(arrV[i])!=-1) {										
					arrR[j++]=arrV[i];
				} else
					arrR[j++]="0";
				break;				
			case '9':
				if (n.indexOf(arrV[i])>=0 || (arrV[i]==" "))
					arrR[j++]=arrV[i];
				else if (arrV.length>i) {
					if (!arrM.splice) {
						largo = arrV.length;
						var arrPaso = new Array();
						for (h=0;h<largo;h++) {
							if (h!=i) arrPaso[arrPaso.length] = arrV[i];							
						}
					} else
						arrV.splice(i,1);
					arrR[j++] = arrV[i];
				}
				break;				
			case '':
				arrR[j++]= arrV[i];
				break;
			//case '.':
			//	break;
			case 'z':
			case 'Z':
				arrR[j++] = arrV[i];
				break;
			default:
				if (arrV[i]==arrM[i])
					arrR[j++] = arrV[i];
				else if (arrV[i]!=arrM[i]) {
					arrR[j++] = arrM[i];
					if (!arrM.splice) {
					 	var arrPaso = arrV.reverse();
					 	arrPaso[arrPaso.length] = ""
					 	arrV = arrPaso.reverse();
					} else
						arrV.splice(i,0,arrM[i]);
				}					
				break;
			}		
			
			//verificar si existen 0 en mascara
			if (i+1<arrV.length) {
				var l=false;
				for (h=i;h<arrM.length;h++) { 
					l = (arrM[h+1]=="0")?true:false; 
					if(l)break;
				}
				if (arrV[i+1]=="" && !l ){
					i=arrM.length
				}
			}
	}


	if (orden==1)
		arrR = arrR.reverse();
		
	var res= this.Trim(arrR.join(""));
	if (valor < 0)
		res= "-" + res;
	return res;
}

valJS.fmtMaskNumber = function(dec) {
		if (!dec)
			dec = valJS.CantDecimal;
		if (dec<0 )
			dec=0;
		//alert("fmtMaskNumber=" + dec);		
		var sDecimal = '';
		for (var i=0;i<dec;i++)
			sDecimal+='9';
		
		if (sDecimal =='')			
			var m = "999,999,999,999,999,999,999,999,999".replace(/,/g,valJS.sepMiles) 
		else
			var m = "999,999,999,999,999,999,999,999,999".replace(/,/g,valJS.sepMiles) + valJS.sepDecimal + sDecimal;
		//alert(m);
		return m;
}

valJS.MaskNumber = function(f, dec){
	if (!dec)
		dec = valJS.CantDecimal;	
	
	//alert(dec);	
	var m = valJS.fmtMaskNumber(dec);			
	function mask2(e){
		var carac = (m.match(/[^90zZ]/g))?m.match(/[^90zZ]/g).join(""):"";
		var c = String.fromCharCode(k = e.key)
		if (e.key==37 || e.key==40) return true;
		if (m.match(/Z/gi)) {
			return true;		
		} else if (m.match(/\d+/g) && c.match(/\d/))
			return true
		else if (carac.indexOf(c)!=-1)
			return true;
			
		return false;
	}
			
	function mask(e){
		
		var valor = f.value;
		var i=0;
		var valor2 = "";
		if (valor.length>0) {
			if (valor.indexOf(valJS.sepDecimal) < 0  && valor.indexOf(valJS.sepMiles)< 0 )	 {				
					valor2= valor
					//colocamos separador decimal 
					if (valor2.length<(dec+1)) for (i=0;i<=(dec - valor2.length);i++) {valor = '0' + valor};	
					if (dec >0)
						valor = valor.substring(0,valor.length-dec) + valJS.sepDecimal +  valor.substring(valor.length-dec, valor.length)					
			}			
			
			//aplicamos mascara separador de miles			
			f.value = valJS.Mask( valor ,m);
		}
	}
	
	this.addEvent(f, "keypress", mask2);
	this.addEvent(f, "keyup", mask2);		
	this.addEvent(f, "blur", mask);		
};

valJS.quitaMask= function (valor) {
	
	valor = valor.replace(/\./g, "").replace(/,/g, ".");
	return valor;
}

valJS.quitaMask2= function (valor) {
	
	valor = valor.replace(/\./g, ",");
	return valor;
}

valJS.quitaMask3= function (valor) {	
	while (valor.indexOf("/") >=0)
		valor = valor.replace("/","");
	while (valor.indexOf("-") >=0)
		valor = valor.replace("-","");
	return valor;
}

	
		
valJS.LlenarLista= function(arrLista,obj,def,datosel,xstring) 
{	
	added = 0;
	obj.options.length = 0;
	if (def=="S")
		obj.options[added++] = new Option(xstring,"0",true);
		
	for (lix = 0; lix < arrLista.length; lix++) {	
		if (arrLista[lix][0] == datosel) { 
			obj.options[added] = new Option(arrLista[lix][1],arrLista[lix][0],true); 
			obj.options[added++].selected=true; 
		} else 
			obj.options[added++] = new Option(arrLista[lix][1],arrLista[lix][0],false); 
		} 
	return added; //total de elementos 
}

valJS.MascaraRut= function(rut)
{
	var MaskRut=rut;
	while (MaskRut.indexOf(".") >=0)
		MaskRut = MaskRut.replace(".","");
	while (MaskRut.indexOf("-") >=0)
		MaskRut = MaskRut.replace("-","");
		
	MaskRut=valJS.Mask( MaskRut ,'zz.zzz.zzz-z',1);
	return MaskRut;		
} 

valJS.ValidarRut= function(obj,objMensaje)
{
	var rut=obj.value;
	if (valJS.isRutValido(rut))
	{
		rut=valJS.MascaraRut(rut);
		objMensaje.value="";
		obj.value=rut;
		return true;
	}
	else
	{
		obj.value="";
		//alert("Rut no v�lido, favor digite nuevamente.");		
		objMensaje.value="Rut no v�lido, favor digite nuevamente.";
		obj.focus();
		return false;
	}	
}

valJS.CalculoDigito= function(rut)
{
	while (rut.indexOf(".") >=0)
		rut = rut.replace(".","");
	while (rut.indexOf("-") >=0)
		rut = rut.replace("-","");

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
	//dv =  rut.substring(rut.length - 1);
	//rut = rut.substring(0,rut.length - 1);
	
	for (var i=rut.length - 1;i>=0;i--) {
			
		if (j > 7 )
			j =2;		
		//alert("charAt=" + rut.charAt(i));	
		//alert("j=" + j);	
		suma += j++ * rut.charAt(i);
		//alert("suma=" + suma);
	}
	//alert("sumafinal=" + suma);
	
	v = 11 - (suma % 11);
	if (v == 10)
		  v = "K";
	if (v == 11)
          v = "0";
	
	alert("digito=" + v);
	
	//if (dv.toUpperCase() == v) 
		return true;
	//return false;
}

valJS.isRutValidoCheque=function(numcheque) {
	
	while (numcheque.indexOf(".") >=0)
		numcheque = numcheque.replace(".","");
	while (numcheque.indexOf("-") >=0)
		numcheque = numcheque.replace("-","");

	var n = "1234567890kK"
	var suma = 0;
	var dv;
	var v;
	var j=2;
	
	if (!numcheque) 
		return false;
	
	for (var i=0;i<numcheque.length;i++) {
		if (n.indexOf(numcheque.charAt(i)) < 0 )
			return false;
	}
	dv =  numcheque.substring(numcheque.length - 2);
	numcheque = numcheque.substring(0,numcheque.length - 2);
	
	for (var i=numcheque.length - 1;i>=0;i--) 
	{			
		if (j > 7 )
			j =2;
		//alert("numcheque.charAt(i)=" + numcheque.charAt(i));	
		suma += j++ * numcheque.charAt(i);
		//alert("suma for=" + suma);
	}
	
	//alert("suma=" + suma);
	
	//alert("suma % 11=" + (suma % 11));
	
	v = 11 - (suma % 11);
	
	//alert("v=" + v);
	
	if (v == 10)
		  v = "11";
	else
	{	  
		if (v == 11)
        	  v = "00";
    	else
    	{
    		if (v.length==1)
    			v="0" + v;
    	}
    }	      
    //alert("digito valido="+ v);    
	if (dv.toUpperCase() == v) 
		return true;
	return false;
}	

valJS.CodigoBarra=function(valor) 
{
	alert("valor=" + valor);
	var i=0;
	while (i<valor.length)
	{
		alert(eval(valor.substring(i,2+i)));
		if (eval(valor.substring(i,2+i)) <50)
			alert(String.fromCharCode(eval(valor.substring(i,2+i)) + 48).toUpperCase());
		else
			alert(String.fromCharCode(eval(valor.substring(i,2+i)) + 142).toUpperCase());
		i=i+2;
	}
	
	/*
 FOR lnI = 1 TO lnLong STEP 2
    IF VAL(SUBS(lcRet,lnI,2)) < 50
      lcCar = lcCar + CHR(VAL(SUBS(lcRet,lnI,2)) + 48)
    ELSE
      lcCar = lcCar + CHR(VAL(SUBS(lcRet,lnI,2)) + 142)
    ENDIF
  ENDFOR
*/
}

