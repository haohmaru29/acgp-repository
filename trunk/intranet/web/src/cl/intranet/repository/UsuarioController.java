package cl.intranet.repository;

import cl.acgp.commons.helper.DateUtils;
import cl.acgp.commons.mvc.repository.jpa.AbstractJpaController;
import cl.intranet.domain.Usuario;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class UsuarioController extends AbstractJpaController<Usuario> {
	private static final Logger logger = Logger
			.getLogger(UsuarioController.class);

	public Usuario login(String nickname, String clave) {
		Usuario usuario = null;
		try {
			String query = "SELECT * FROM USUARIO  WHERE NICKNAME='" + nickname
					+ "' AND clave_usuario='" + clave + "'";
			Query q = this.jpaConnection.getEntityManager().createNativeQuery(
					query, Usuario.class);
			usuario = (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			logger.error("[Intranet]", e);
		}
		return usuario;
	}

	public List<?> findByParams(String fecha, int idUsuario, int start,
			int limit) {
		String query = "SELECT u FROM Usuario u ";
		if ((fecha != null) && (!"".equals(fecha))) {
			query = query + " WHERE u.fechaCreacion  like '%"
					+ DateUtils.DateToString(DateUtils.stringToDate(fecha))
					+ "%'";
		}
		query = query + " ORDER BY u.fechaCreacion DESC";
		query = query + " LIMIT " + start + ", " + limit;
		Query q = this.jpaConnection.createQuery(query);
		return q.getResultList();
	}

	public int countAll() {
		Query q = this.jpaConnection.getEntityManager().createNativeQuery(
				"select COUNT(IDUSUARIO) as total from USUARIO ");
		int result = Integer.parseInt(q.getSingleResult().toString());

		return result;
	}
}