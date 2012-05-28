package cl.intranet.repository;

import java.util.List;

import javax.persistence.Query;

import cl.acgp.commons.mvc.repository.jpa.AbstractJpaController;
import cl.intranet.domain.Menu;

public class MenuController extends AbstractJpaController<Menu> {
	@SuppressWarnings("unchecked")
	public List<Menu> findMenu() {
		List<Menu> l = null;
		Query q = this.jpaConnection.createQuery("SELECT m FROM Menu m WHERE m.idpadre=-1");
		l = q.getResultList();
		return l;
	}

	public List<?> findMenuByPerfil(int idPerfil) {
		List<?> l = null;
		Query q = this.jpaConnection.createQuery("SELECT m FROM Menu m WHERE m.idpadre=-1 and m.perfilMenus.idperfilMenu="
						+ idPerfil);
		l = q.getResultList();
		return l;
	}

	public List<?> findChilds() {
		List<?> l = null;
		Query q = this.jpaConnection
				.createQuery("SELECT m FROM Menu m WHERE m.idpadre!=-1");
		l = q.getResultList();
		return l;
	}
}