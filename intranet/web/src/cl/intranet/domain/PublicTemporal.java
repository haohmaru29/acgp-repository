package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the public_temporal database table.
 * 
 */
@Entity
@Table(name="public_temporal")
public class PublicTemporal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDPUBLIC_TEMPORAL")
	private int idpublicTemporal;

	private String estado;

	//bi-directional many-to-one association to Publicacion
	@OneToMany(mappedBy="publicTemporal")
	private List<Publicacion> publicacions;

    public PublicTemporal() {
    }

	public int getIdpublicTemporal() {
		return this.idpublicTemporal;
	}

	public void setIdpublicTemporal(int idpublicTemporal) {
		this.idpublicTemporal = idpublicTemporal;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Publicacion> getPublicacions() {
		return this.publicacions;
	}

	public void setPublicacions(List<Publicacion> publicacions) {
		this.publicacions = publicacions;
	}
	
}