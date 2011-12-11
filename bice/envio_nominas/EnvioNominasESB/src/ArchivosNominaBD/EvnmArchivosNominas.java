package ArchivosNominaBD;

import java.sql.Timestamp;

public class EvnmArchivosNominas {

	private String nombreArchivo;
	private Double referenciaBancoProceso;
	private Timestamp fechaCreacion;
	private String cuerpo;

public EvnmArchivosNominas() {
	super();
}

public String getCuerpo() {
	return this.cuerpo;
}

public Timestamp getFechaCreacion() {
	return this.fechaCreacion;
}

public String getNombreArchivo() {
	return this.nombreArchivo;
}

public Double getReferenciaBancoProceso() {
	return this.referenciaBancoProceso;
}

public void setCuerpo(String cuerpo) {
	this.cuerpo = cuerpo;
}

public void setFechaCreacion(Timestamp fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}

public void setNombreArchivo(String nombreArchivo) {
	this.nombreArchivo = nombreArchivo;
}

public void setReferenciaBancoProceso(Double referenciaBancoProceso) {
	this.referenciaBancoProceso = referenciaBancoProceso;
}

}
