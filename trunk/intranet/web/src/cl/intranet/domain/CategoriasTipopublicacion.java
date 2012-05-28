package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categorias_tipopublicacion database table.
 * 
 */
@Entity
@Table(name="categorias_tipopublicacion")
public class CategoriasTipopublicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDCAREGORIAS_TIPOPUBLICACION")
	private int idcaregoriasTipopublicacion;

	//bi-directional many-to-one association to Categoria
    @ManyToOne
	@JoinColumn(name="IDCATEGORIA")
	private Categoria categoria;

	//bi-directional many-to-one association to TipoPublicacion
    @ManyToOne
	@JoinColumn(name="IDTIPO_PUBLICACION")
	private TipoPublicacion tipoPublicacion;

    public CategoriasTipopublicacion() {
    }

	public int getIdcaregoriasTipopublicacion() {
		return this.idcaregoriasTipopublicacion;
	}

	public void setIdcaregoriasTipopublicacion(int idcaregoriasTipopublicacion) {
		this.idcaregoriasTipopublicacion = idcaregoriasTipopublicacion;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public TipoPublicacion getTipoPublicacion() {
		return this.tipoPublicacion;
	}

	public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}
	
}