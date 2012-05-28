package cl.intranet.repository;

import cl.acgp.commons.mvc.repository.jpa.AbstractJpaController;
import cl.intranet.domain.CategoriasTipopublicacion;
import java.util.List;
import javax.persistence.Query;

public class CategoriasTipopublicacionController extends AbstractJpaController<CategoriasTipopublicacion> {
	public List<?> findByTipoPublicacion(int tipoPublicacion) {
		Query q = this.jpaConnection.createQuery("SELECT c FROM CategoriasTipopublicacion c WHERE c.tipoPublicacion.idtipoPublicacion=:tipoPublicacion ORDER BY categoria.nombreCategoria");
		q.setParameter("tipoPublicacion", Integer.valueOf(tipoPublicacion));

		return q.getResultList();
	}
}
