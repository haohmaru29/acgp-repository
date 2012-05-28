package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_publicacion database table.
 * 
 */
@Entity
@Table(name="tipo_publicacion")
public class TipoPublicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDTIPO_PUBLICACION")
	private int idtipoPublicacion;

	@Column(name="NOMBRE_TIPOPUBLICACION")
	private String nombreTipopublicacion;

	//bi-directional many-to-one association to CategoriasTipopublicacion
	@OneToMany(mappedBy="tipoPublicacion")
	private List<CategoriasTipopublicacion> categoriasTipopublicacions;

	//bi-directional many-to-one association to Publicacion
	@OneToMany(mappedBy="tipoPublicacion")
	private List<Publicacion> publicacions;

    public TipoPublicacion() {
    }

	public int getIdtipoPublicacion() {
		return this.idtipoPublicacion;
	}

	public void setIdtipoPublicacion(int idtipoPublicacion) {
		this.idtipoPublicacion = idtipoPublicacion;
	}

	public String getNombreTipopublicacion() {
		return this.nombreTipopublicacion;
	}

	public void setNombreTipopublicacion(String nombreTipopublicacion) {
		this.nombreTipopublicacion = nombreTipopublicacion;
	}

	public List<CategoriasTipopublicacion> getCategoriasTipopublicacions() {
		return this.categoriasTipopublicacions;
	}

	public void setCategoriasTipopublicacions(List<CategoriasTipopublicacion> categoriasTipopublicacions) {
		this.categoriasTipopublicacions = categoriasTipopublicacions;
	}
	
	public List<Publicacion> getPublicacions() {
		return this.publicacions;
	}

	public void setPublicacions(List<Publicacion> publicacions) {
		this.publicacions = publicacions;
	}
	
}