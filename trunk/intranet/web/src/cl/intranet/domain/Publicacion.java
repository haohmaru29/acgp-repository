package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the publicacion database table.
 * 
 */
@Entity
public class Publicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idpublicacion;

    @Lob()
	@Column(name="CONTENIDO_PUBLICACION")
	private String contenidoPublicacion;

	@Column(name="FECHA_INGRESO")
	private Timestamp fechaIngreso;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_MODIFICACION")
	private Date fechaModificacion;

	@Column(name="TITULO_PUBLICACION")
	private String tituloPublicacion;

	//bi-directional many-to-one association to Imagene
	@OneToMany(mappedBy="publicacion")
	private List<Imagene> imagenes;

	//bi-directional many-to-one association to Categoria
    @ManyToOne
	@JoinColumn(name="IDCATEGORIA")
	private Categoria categoria;

	//bi-directional many-to-one association to PublicTemporal
    @ManyToOne
	@JoinColumn(name="IDPUBLIC_TEMPORAL")
	private PublicTemporal publicTemporal;

	//bi-directional many-to-one association to TipoPublicacion
    @ManyToOne
	@JoinColumn(name="IDTIPO_PUBLICACION")
	private TipoPublicacion tipoPublicacion;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;

    public Publicacion() {
    }

	public int getIdpublicacion() {
		return this.idpublicacion;
	}

	public void setIdpublicacion(int idpublicacion) {
		this.idpublicacion = idpublicacion;
	}

	public String getContenidoPublicacion() {
		return this.contenidoPublicacion;
	}

	public void setContenidoPublicacion(String contenidoPublicacion) {
		this.contenidoPublicacion = contenidoPublicacion;
	}

	public Timestamp getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getTituloPublicacion() {
		return this.tituloPublicacion;
	}

	public void setTituloPublicacion(String tituloPublicacion) {
		this.tituloPublicacion = tituloPublicacion;
	}

	public List<Imagene> getImagenes() {
		return this.imagenes;
	}

	public void setImagenes(List<Imagene> imagenes) {
		this.imagenes = imagenes;
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public PublicTemporal getPublicTemporal() {
		return this.publicTemporal;
	}

	public void setPublicTemporal(PublicTemporal publicTemporal) {
		this.publicTemporal = publicTemporal;
	}
	
	public TipoPublicacion getTipoPublicacion() {
		return this.tipoPublicacion;
	}

	public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}