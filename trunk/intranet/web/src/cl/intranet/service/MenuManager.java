package cl.intranet.service;

import cl.acgp.commons.mvc.service.jpa.AbstractServiceManager;
import cl.intranet.domain.Menu;
import cl.intranet.repository.MenuController;
import java.util.List;

public class MenuManager extends AbstractServiceManager<Menu> {
	public List<Menu> findMenu() {
		return ((MenuController) this.jpaController).findMenu();
	}

	public List<?> findChilds() {
		return ((MenuController) this.jpaController).findChilds();
	}

	public List<?> findMenuByPerfil(int idPerfil) {
		return ((MenuController) this.jpaController).findMenuByPerfil(idPerfil);
	}
}