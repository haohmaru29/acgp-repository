package cl.intranet.web;

import cl.acgp.commons.mvc.service.jpa.ServiceManager;
import cl.intranet.domain.Usuario;
import cl.intranet.service.EstadoUsuarioManager;
import cl.intranet.service.PerfilManager;
import cl.intranet.service.PublicacionManager;
import cl.intranet.service.UsuarioManager;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "usuario" })
public class usuario {
	private ModelAndView model;
	private static final Logger logger = Logger.getLogger(usuario.class);
	private UsuarioManager usuarioManager = (UsuarioManager) ServiceManager.factory("UsuarioManager");
	private PerfilManager perfilManager = (PerfilManager) ServiceManager.factory("PerfilManager");
	private PublicacionManager publicaManager = (PublicacionManager) ServiceManager.factory("PublicacionManager");
	private EstadoUsuarioManager estadoManager = (EstadoUsuarioManager) ServiceManager.factory("EstadoUsuarioManager");

	@RequestMapping(value ="form", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		if (request.getSession(false).getAttribute("usuario") != null) {
			model.setViewName("usuarios/add");
		} else {
			model.addObject("message", "1");
			model.setViewName("global/message");
		}

		return model;
	}

	@RequestMapping(value ="mantenedor" , method = {RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mantenedor(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		try {
			Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
			if (usuario.getPerfil().getIdperfil() == 1) {
				String fecha = request.getParameter("fecha");
				int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.size"));
				int start = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.index"));
				int pageSelected = (request.getParameter("page") == null)?1: Integer.parseInt(request.getParameter("page"));
				model.addObject("pagina", pageSelected);
				model.addObject("paginacion", getPaginacion(limit) );
				model.addObject("usuarios", usuarioManager.findByParams(fecha, usuario.getIdusuario(), start, limit));
				model.setViewName("usuarios/mantenedor/include");
			} else {
				model.addObject("message", "no tiene permiso para ingresar a esta sección");
				model.setViewName("global/message");
			}
		} catch (Exception e) {
			model.addObject("message", e);
			model.setViewName("global/message");
		}

		return model;
	}

	@RequestMapping(value ="load", method = {RequestMethod.GET, RequestMethod.POST })
	public ModelAndView load(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		try {
			Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
			String fecha = request.getParameter("fecha");
			int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.size"));
			int pageSelected = (request.getParameter("page") == null)?0: Integer.parseInt(request.getParameter("page"));
			model.addObject("pagina", pageSelected);
			model.addObject("paginacion", getPaginacion(limit) );
			if(pageSelected == 0 ) {
				model.addObject("usuarios",this.usuarioManager.findByParams(fecha, usuario.getIdusuario(), pageSelected, limit));
			}else {
				model.addObject("usuarios",this.usuarioManager.findByParams(fecha, usuario.getIdusuario(), pageSelected * limit- limit, pageSelected * limit));
			}
			
			model.setViewName("usuarios/mantenedor/tabla");
		} catch (Exception e) {
			model.addObject("message", e);
			model.setViewName("global/message");
		}

		return this.model;
	}
	
	@RequestMapping(value ="paginacion", method = {RequestMethod.GET, RequestMethod.POST })
	public ModelAndView paginacion(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		try {
			int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.size"));
			int pageSelected = (request.getParameter("page") == null)?0: Integer.parseInt(request.getParameter("page"));
			model.addObject("pagina", pageSelected);
			model.addObject("paginacion", getPaginacion(limit) );
			model.setViewName("usuarios/mantenedor/paginacion");
		} catch (Exception e) {
			model.addObject("message", e);
			model.setViewName("global/message");
		}

		return this.model;
	}

	@RequestMapping(value ="edit", method = {RequestMethod.GET, RequestMethod.POST })
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		int id = request.getParameter("idUsuario") == null ? 0 : Integer.parseInt(request.getParameter("idUsuario"));
		if (id != -1) {
			model.addObject("titulo", "Modificar Datos Usuario");
			model.addObject("boton", "Actualizar");
			model.addObject("usuarios", this.usuarioManager.findById(id));
			
		} else {
			model.addObject("boton", "Registrar");
			model.addObject("titulo", "Agregar nuevo Usuario");
		}
		model.addObject("estado", estadoManager.findAll() );
		model.addObject("perfiles", this.perfilManager.findAll());
		model.setViewName("usuarios/mantenedor/modificar");

		return model;
	}

	@RequestMapping(value = "delete", method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		String options = request.getParameter("options");
		options = options.substring(0, options.length() - 1);
		String[] array = options.split(",");
		try {
			for (int x = 0; x < array.length; x++) {
				usuarioManager.deleteById(array[x]);
			}
			model.addObject("message",1);
			model.setViewName("global/message");
		} catch (Exception e) {
			model.addObject("message", e);
			model.setViewName("global/message");
			logger.error("[Intranet]", e);
		}
		return model;
	}

	@RequestMapping(value ="mostrar", method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView mostrar(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		int idUsuario = request.getParameter("idUsuario") == null ? 0 : Integer.parseInt(request.getParameter("idUsuario"));
		try {
			model.addObject("usuario", this.usuarioManager.findById(idUsuario));
			model.addObject("noticias", Integer.valueOf(this.publicaManager.findByUser(idUsuario, 1, null).size()));
			model.addObject( "anuncios", Integer.valueOf(this.publicaManager.findByUser(idUsuario, 2, null).size()));
			model.setViewName("usuarios/mantenedor/datos_usuario");
		} catch (Exception e) {
			this.model.addObject("message", e);
			this.model.setViewName("global/message");
			logger.error("[Intranet]", e);
		}
		return model;
	}

	private int getPaginacion(int limit) {
		int total = usuarioManager.countAll();
		int d = total / limit;
		int remain = total % limit;
		if (remain > 0) {
			d++;
		}

		return d;
	}
}