package cl.intranet.repository;

import cl.acgp.commons.helper.DateUtils;
import cl.acgp.commons.mvc.repository.jpa.AbstractJpaController;
import cl.intranet.domain.Publicacion;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

public class PublicacionController extends AbstractJpaController<Publicacion> {

	public List<?> findPublicacionTipo(int idTipoPublicacion, int index, int limit) {
		List<?> l = null;
		Query q = this.jpaConnection.getEntityManager().createNativeQuery("SELECT * FROM PUBLICACION WHERE IDTIPO_PUBLICACION="
						+ idTipoPublicacion + " ORDER BY FECHA_INGRESO DESC"
						+ " LIMIT " + index + "," + limit, Publicacion.class);

		l = q.getResultList();

		return l;
	}

	public List<?> findPublicacionByParams(int idTipoPublicacion, Date fecha, int idCategoria) {
		List<?> l = null;
		Query q = this.jpaConnection.getEntityManager().createNativeQuery("SELECT * from PUBLICACION WHERE IDTIPO_PUBLICACION="
						+ idTipoPublicacion + " AND FECHA_INGRESO like '%"
						+ DateUtils.DateToString(fecha)
						+ "%' ORDER BY FECHA_INGRESO DESC", Publicacion.class);
		l = q.getResultList();

		return l;
	}

	public Publicacion findByTemporal(int idTemporal) {
		Publicacion pub = null;
		try {
			Query q = this.jpaConnection.getEntityManager().createNativeQuery("SELECT * FROM PUBLICACION  WHERE IDPUBLIC_TEMPORAL=" + idTemporal, Publicacion.class);
			pub = (Publicacion) q.getSingleResult();
		} catch (Exception e) {
		}
		return pub;
	}

	public int countAll() {
		Query q = this.jpaConnection.getEntityManager().createNativeQuery(
				"select COUNT(IDPUBLICACION) as total from PUBLICACION ");
		int result = Integer.parseInt(q.getSingleResult().toString());

		return result;
	}

	public List<?> findByUser(int idUsuario, int idTipoPublicacion, String fecha) {
		String query = "SELECT p FROM Publicacion p WHERE p.usuario.idusuario="
				+ idUsuario;
		if ((idTipoPublicacion != 0) && (idTipoPublicacion != -1)) {
			query = query + " AND p.tipoPublicacion.idtipoPublicacion="
					+ idTipoPublicacion;
		}
		if ((fecha != null) && (!"".equals(fecha))) {
			query = query + " AND p.fechaIngreso  like '%"
					+ DateUtils.DateToString(DateUtils.stringToDate(fecha))
					+ "%'";
		}
		query = query + " ORDER BY p.fechaIngreso DESC";
		Query q = this.jpaConnection.createQuery(query);
		return q.getResultList();
	}

	public List<?> findTodas(int idTipoPublicacion, String fecha) {
		String query = "SELECT p FROM Publicacion p ";
		if ((idTipoPublicacion != 0) && (idTipoPublicacion != -1)) {
			query = query + " WHERE p.tipoPublicacion.idtipoPublicacion="
					+ idTipoPublicacion;
		}

		if ((fecha != null) && (!"".equals(fecha))) {
			if ((idTipoPublicacion != 0) && (idTipoPublicacion != -1))
				query = query + "AND p.fechaIngreso  like '%" + DateUtils.DateToString(DateUtils.stringToDate(fecha)) + "%'";
			else {
				query = query + "WHERE p.fechaIngreso  like '%" + DateUtils.DateToString(DateUtils.stringToDate(fecha)) + "%'";
			}
		}
		query = query + " ORDER BY p.fechaIngreso DESC";
		Query q = this.jpaConnection.createQuery(query);
		return q.getResultList();
	}
}