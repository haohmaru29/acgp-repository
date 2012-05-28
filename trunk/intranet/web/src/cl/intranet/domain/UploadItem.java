package cl.intranet.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadItem {
	private CommonsMultipartFile files;
	private String tituloPublicacion;
	private String contenidoPublicacion;
	private String idtipoPublicacion;
	private String idCategoria;
	private int idpublicTemporal;

	public int getIdpublicTemporal() {
		return this.idpublicTemporal;
	}

	public void setIdpublicTemporal(int idpublicTemporal) {
		this.idpublicTemporal = idpublicTemporal;
	}

	public CommonsMultipartFile getFiles() {
		return this.files;
	}

	public void setFiles(CommonsMultipartFile files) {
		this.files = files;
	}

	public String getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getTituloPublicacion() {
		return this.tituloPublicacion;
	}

	public void setTituloPublicacion(String tituloPublicacion) {
		this.tituloPublicacion = tituloPublicacion;
	}

	public String getContenidoPublicacion() {
		return this.contenidoPublicacion;
	}

	public void setContenidoPublicacion(String contenidoPublicacion) {
		this.contenidoPublicacion = contenidoPublicacion;
	}

	public String getIdtipoPublicacion() {
		return this.idtipoPublicacion;
	}

	public void setIdtipoPublicacion(String idtipoPublicacion) {
		this.idtipoPublicacion = idtipoPublicacion;
	}
}