package cl.cmr.Config;
import java.util.ArrayList;

public class ConfigItem {
  	protected String sNombre="";
  	protected String sDescripcion="";
  	protected String sPagina="";
  	protected boolean bEditable=true;
  	protected boolean bFinal=true;
  	protected String sFuncionalidad="";
  	protected ArrayList arrMenu;
  	
  	public ConfigItem() {
  		arrMenu = new ArrayList();
  	}
	
  	public ConfigItem(String sNombre) {
  		this.sNombre = sNombre;
  	}
  	
  	public void setNombre(String sNombre) { 		
  		this.sNombre = sNombre;
  	}
  	public void setPagina(String sPagina) { 		
  		this.sPagina = sPagina;
  	}
  	public void setDescripcion(String sDescrip) { 		
  		this.sDescripcion = sDescrip;
  	}
  	public void setEditable(boolean bEdit) {
  		this.bEditable = bEdit;
  	}
  	public void setFinal(boolean bFinal) {
  		this.bFinal = bFinal;
  	}
  	public void setFuncionalidad(String sFunc) {
  		this.sFuncionalidad = sFunc;
  	}
  	
  	public boolean isEditable() {
  		return this.bEditable;
  	}
  	public boolean isFinal() {
  		return this.bFinal;
  	}
  	public String getFuncionalidad() {
  		return this.sFuncionalidad;
  	}  	
  	public String getNombre() {
  		return this.sNombre;
  	}
  	public String getPagina(){
  		return this.sPagina;
  	}
  	public String getDescripcion() {
  		return this.sDescripcion;
  	}
  	public void addMenu(ConfigItem oItem) {
  		if (oItem!=null)
  		  this.arrMenu.add(oItem);
  	}
  	public void setArrayMenu(ArrayList arrMenu) {
  		if (arrMenu!=null)
  		  this.arrMenu = (ArrayList)arrMenu.clone();
  	}
  	
  	public ArrayList getArrayMenus() {
  		return (ArrayList)this.arrMenu.clone();
  	}
  	public int getSizeConfigItem() {
  		return this.arrMenu.size();
  	}
  	public ConfigItem[] getMenus() {
  		return (ConfigItem[])this.arrMenu.toArray() ;
  	}
}
  
