package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idusuario;

	@Column(name="CLAVE_USUARIO")
	private String claveUsuario;

	@Column(name="CORREO_USUARIO")
	private String correoUsuario;

	@Column(name="FECHA_CREACION")
	private Timestamp fechaCreacion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	private String nickname;

	@Column(name="NOMBRE_USUARIO")
	private String nombreUsuario;

	@Column(name="RUT_USUARIO")
	private String rutUsuario;

	private String telefono;

	//bi-directional many-to-one association to Publicacion
	@OneToMany(mappedBy="usuario")
	private List<Publicacion> publicacions;

	//bi-directional many-to-one association to Perfil
    @ManyToOne
	@JoinColumn(name="IDPERFIL")
	private Perfil perfil;

	//bi-directional many-to-one association to EstadoUsuario
    @ManyToOne
	@JoinColumn(name="IDESTADO_USUARIO")
	private EstadoUsuario estadoUsuario;

    public Usuario() {
    }

	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getClaveUsuario() {
		return this.claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public String getCorreoUsuario() {
		return this.correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getRutUsuario() {
		return this.rutUsuario;
	}

	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario = rutUsuario;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Publicacion> getPublicacions() {
		return this.publicacions;
	}

	public void setPublicacions(List<Publicacion> publicacions) {
		this.publicacions = publicacions;
	}
	
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public EstadoUsuario getEstadoUsuario() {
		return this.estadoUsuario;
	}

	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	
}