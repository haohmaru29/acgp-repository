package Seguridad;

import java.io.Serializable;

public class SessionUsuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String sUsuario;
	String sNombre;
	String sCodAgencia;
	String sAgencia;
	String sMail;
	String sTelefono;
	String sIp;
	String sSupv;
	
	public SessionUsuario() {
		this.sUsuario = "";
		this.sNombre  = "";
		this.sCodAgencia = "";
		this.sAgencia = "";
		this.sMail = "";
		this.sTelefono = "";
		this.sIp = "";
		this.sSupv = "";
	}
	public SessionUsuario(String sUsuario,String sNombre,String sCodAgencia,String sAgencia, String sMail,String sTelefono,String sIp,String sSupv) {
		this.sUsuario =sUsuario;
		this.sNombre  = sNombre;
		this.sCodAgencia = sCodAgencia;
		this.sAgencia = sAgencia;
		this.sMail = sMail;
		this.sTelefono = sTelefono;
		this.sIp = sIp;
		this.sSupv = sSupv;
	}
	
	public void setUsuario(String sUsuario) {
		this.sUsuario =sUsuario;
	}
	public void setNombre(String sNombre) {
		this.sNombre =sNombre;
	}
	public void setCodAgencia(String sCodAgencia) {
		this.sCodAgencia =sCodAgencia;
	}
	public void setAgencia(String sAgencia) {
		this.sAgencia =sAgencia;
	}
	public void setMail(String sMail) {
		this.sMail =sMail;
	}
	public void setTelefono(String sTelefono) {
		this.sTelefono =sTelefono;
	}
	public void setIp(String sIp) {
		this.sIp =sIp;
	}
	public void setSupv(String sSupv) {
		this.sSupv =sSupv;
	}


	public String getUsuario(){
		return this.sUsuario;
	}
	public String getNombre(){
		return this.sNombre;
	}
	public String getCodAgencia(){
		return this.sCodAgencia;
	}
	public String getAgencia(){
		return this.sAgencia;
	}
	public String getMail(){
		return this.sMail;
	}
	public String getTelefono(){
		return this.sTelefono;
	}
	public String getIp(){
		return this.sIp;
	}
	public String getSupv(){
		return this.sSupv;
	}

}

