package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorias database table.
 * 
 */
@Entity
@Table(name="categorias")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idcategoria;

	@Column(name="NOMBRE_CATEGORIA")
	private String nombreCategoria;

	//bi-directional many-to-one association to CategoriasTipopublicacion
	@OneToMany(mappedBy="categoria")
	private List<CategoriasTipopublicacion> categoriasTipopublicacions;

	//bi-directional many-to-one association to Publicacion
	@OneToMany(mappedBy="categoria")
	private List<Publicacion> publicacions;

    public Categoria() {
    }

	public int getIdcategoria() {
		return this.idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
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