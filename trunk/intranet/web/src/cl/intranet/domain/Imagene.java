package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the imagenes database table.
 * 
 */
@Entity
@Table(name="imagenes")
public class Imagene implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDIMAGEN_PUBLICACION")
	private int idimagenPublicacion;

	@Column(name="NOMBRE_IMAGEN")
	private String nombreImagen;

	//bi-directional many-to-one association to Publicacion
    @ManyToOne
	@JoinColumn(name="IDPUBLICACION")
	private Publicacion publicacion;

    public Imagene() {
    }

	public int getIdimagenPublicacion() {
		return this.idimagenPublicacion;
	}

	public void setIdimagenPublicacion(int idimagenPublicacion) {
		this.idimagenPublicacion = idimagenPublicacion;
	}

	public String getNombreImagen() {
		return this.nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public Publicacion getPublicacion() {
		return this.publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	
}