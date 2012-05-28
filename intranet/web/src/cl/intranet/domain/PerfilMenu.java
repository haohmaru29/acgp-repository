package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the perfil_menu database table.
 * 
 */
@Entity
@Table(name="perfil_menu")
public class PerfilMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDPERFIL_MENU")
	private int idperfilMenu;

	//bi-directional many-to-one association to Menu
    @ManyToOne
	@JoinColumn(name="IDMENU")
	private Menu menu;

	//bi-directional many-to-one association to Perfil
    @ManyToOne
	@JoinColumn(name="IDPERFIL")
	private Perfil perfil;

    public PerfilMenu() {
    }

	public int getIdperfilMenu() {
		return this.idperfilMenu;
	}

	public void setIdperfilMenu(int idperfilMenu) {
		this.idperfilMenu = idperfilMenu;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}