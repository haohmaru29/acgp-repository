package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estado_usuario database table.
 * 
 */
@Entity
@Table(name="estado_usuario")
public class EstadoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDESTADO_USUARIO")
	private int idestadoUsuario;

	@Column(name="NOMBRE_ESTADOUSUARIO")
	private String nombreEstadousuario;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="estadoUsuario")
	private List<Usuario> usuarios;

    public EstadoUsuario() {
    }

	public int getIdestadoUsuario() {
		return this.idestadoUsuario;
	}

	public void setIdestadoUsuario(int idestadoUsuario) {
		this.idestadoUsuario = idestadoUsuario;
	}

	public String getNombreEstadousuario() {
		return this.nombreEstadousuario;
	}

	public void setNombreEstadousuario(String nombreEstadousuario) {
		this.nombreEstadousuario = nombreEstadousuario;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}