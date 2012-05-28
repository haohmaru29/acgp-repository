package cl.intranet.service;

import cl.acgp.commons.mvc.service.jpa.AbstractServiceManager;
import cl.intranet.domain.Publicacion;
import cl.intranet.repository.PublicacionController;
import java.util.Date;
import java.util.List;

public class PublicacionManager extends AbstractServiceManager<Publicacion> {
	public List<?> findPublicacionTipo(int idTipoPublicacion, int index,int limit) {
		return ((PublicacionController) this.jpaController).findPublicacionTipo(idTipoPublicacion, index, limit);
	}

	public List<?> findPublicacionByParams(int idTipoPublicacion, Date fecha, int idCategoria) {
		return ((PublicacionController) this.jpaController).findPublicacionByParams(idTipoPublicacion, fecha, idCategoria);
	}

	public Publicacion findByTemporal(int idTemporal) {
		return ((PublicacionController) this.jpaController).findByTemporal(idTemporal);
	}

	public int countAll() {
		return ((PublicacionController) this.jpaController).countAll();
	}

	public List<?> findByUser(int idUsuario, int idTipoPublicacion, String fecha) {
		return ((PublicacionController) this.jpaController).findByUser(idUsuario, idTipoPublicacion, fecha);
	}

	public List<?> findTodas(int idTipoPublicacion, String fecha) {
		return ((PublicacionController) this.jpaController).findTodas(idTipoPublicacion, fecha);
	}
	
	public List<?> findPaginacion(int idTipoPublicacion, String fecha, int start, int limit) {
		return ((PublicacionController) this.jpaController).findPaginacion(idTipoPublicacion, fecha, start, limit);
	}
}