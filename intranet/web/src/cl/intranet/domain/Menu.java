package cl.intranet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idmenu;

	private int idpadre;

	@Column(name="NOMBRE_MENU")
	private String nombreMenu;

	@Column(name="URL_MENU")
	private String urlMenu;

	//bi-directional many-to-one association to PerfilMenu
	@OneToMany(mappedBy="menu")
	private List<PerfilMenu> perfilMenus;

    public Menu() {
    }

	public int getIdmenu() {
		return this.idmenu;
	}

	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
	}

	public int getIdpadre() {
		return this.idpadre;
	}

	public void setIdpadre(int idpadre) {
		this.idpadre = idpadre;
	}

	public String getNombreMenu() {
		return this.nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getUrlMenu() {
		return this.urlMenu;
	}

	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
	}

	public List<PerfilMenu> getPerfilMenus() {
		return this.perfilMenus;
	}

	public void setPerfilMenus(List<PerfilMenu> perfilMenus) {
		this.perfilMenus = perfilMenus;
	}
	
}