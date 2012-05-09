var dirImages = "../images/";
function mnuMenu(nombre,pagina,nodohoja,arrSubMenu) {
	this.nombre = nombre;
	this.pagina = pagina;	
	this.nodohoja = nodohoja;
	this.arrSubMenu  = arrSubMenu;
}

function creaSubMenu(arrMenu,h,i1,i2,nivel) {
	if (arrMenu.length<=0)
		return h;
	var i=0;
	
	var p=h;
	for (i=0;i<(arrMenu.length);i++) {
			//document.frm1.area1.value += "\n\r-------------" + arrMenu[i].nombre
			if (arrMenu[i].arrSubMenu.length<=0 && nivel==0) {
				//document.frm1.area1.value += "\n\rp" + h + "i" + i + ",p" + i1 + "i" + i2 + "(" + arrMenu[i].nombre + ")"
				stm_aix("p" + h + "i" + i,"p" + i1 + "i" + i2,[0,arrMenu[i].nombre,"","",-1,-1,0,arrMenu[i].pagina]);
			}else if (arrMenu[i].arrSubMenu.length<=0) {
				//document.frm1.area1.value += "\n\rp" + h + "i" + i + ",p" + i1 + "i" + i2 + "(" + arrMenu[i].nombre + ")"			
				stm_aix("p" + h + "i" + i,"p" + i1 + "i" + i2,[0,arrMenu[i].nombre,"","",-1,-1,0,arrMenu[i].pagina,"_self","","","","",6,0,0,"","",0,0,0,0,1,"#ffffff",0,"#e5e5e5",0,"","",3,3,0,0,"#fffff7","#000000","#000000","#000000","10pt Verdana","10pt Verdana"]);					
			}else {
				if (nivel==0) {
					//document.frm1.area1.value += "\n\rp" + h + "i" + i2 + "(" + arrMenu[i].nombre + ")"			
					stm_ai("p" + h + "i" + i2, [0,arrMenu[i].nombre,"","",-1,-1,0,"","_self","","","","",3,0,0,"","",3,0,0,0,1,"#003355",0,"#0f598a",0,"","",3,3,0,0,"#fffff7","#000000","#ffffff","#ffffff","bold 11pt Verdana","bold 11pt Verdana",0,0]);			
					//document.frm1.area1.value += "\n\r*p" + (h + 1);
					stm_bp("p" + (h + 1) ,[1,4,0,0,2,3,6,0,100,"progid:DXImageTransform.Microsoft.Strips(Motion=rightdown,enabled=0,Duration=0.30)",19,"progid:DXImageTransform.Microsoft.Fade(overlap=.5,enabled=0,Duration=0.30)",-2,80,2,3,"#999999","#ffffff","",3,1,1,"#999999"]);					
					h = creaSubMenu(arrMenu[i].arrSubMenu,h+1,h,i2,nivel +1) + 1;									
				}else {
					//document.frm1.area1.value += "\n\rp" + p + "i" + i +  " ( guion )"
					stm_ai("p" + p + "i" + i,[6,1,"#ffffff","",-1,-1,0]);
					//document.frm1.area1.value += "\n\rp" + p + "i" + (i+1) + ",p" + i1 + "i" + i2 + "(" + arrMenu[i].nombre + ")"			
					stm_aix("p" + p + "i" + (i+1),"p" + i1 + "i" + i2,[0,arrMenu[i].nombre,"","",-1,-1,0,"","_self","","","","",3,0,0,"","",7,7,0,0,1,"#ffffff",0,"#e5e5e5",0,"","",3,3,0,0,"#fffff7","#000000","#000000","#000000","10pt Verdana","10pt Verdana"]);										
					//document.frm1.area1.value += "\n\rp" + (h + 1) + ",p" + p + "(" + arrMenu[i].nombre + ")"			
					stm_bpx("p" + (h + 1) ,"p" + p,[1,2,-3]);
					h= creaSubMenu(arrMenu[i].arrSubMenu,(h+1),p,i+1,nivel +1) + 1;					
				}
				
				stm_ep();
			}	
	}
	
  return h;
}

function CargaMenu(arrMenu) {
   try {

	if (!arrMenu)
		return false;
	if (!arrMenu.length)
		return false;
	var dirImages = "../images/";
	//nombre menu,,,imagen,,,,,,,tiempo de demora de menu,,,,,
	stm_bm(["menu2d3c",430,"",dirImages + "blank.gif",0,"","",0,0,0,0,0,1,0,0,"","",0],this);
	stm_bp("p0",[0,4,0,0,2,3,3,3,100,"",-2,"",-2,90,0,0,"#000000","#003355","",3,0,0,"#000000"]);
	
	var h=0;
	h= creaSubMenu(arrMenu,0,0,0,0);
	stm_ep();
	h++;	
	//stm_ep();
	//stm_ep();
	//stm_aix("p0i"+ h ,"p0i0",[0,"Cerrar","","",-1,-1,0,"../jsp/cerrar.jsp"]);
	stm_ep();
	stm_ep();
	stm_em();

	return true;	
   }catch(error) {
   }
   return false;
}

