package cl.intranet.web;

import cl.acgp.commons.helper.DateUtils;
import cl.acgp.commons.library.json.JsonParser;
import cl.acgp.commons.mvc.service.jpa.ServiceManager;
import cl.intranet.domain.PublicTemporal;
import cl.intranet.domain.Publicacion;
import cl.intranet.domain.Usuario;
import cl.intranet.service.CategoriaManager;
import cl.intranet.service.CategoriasTipopublicacionManager;
import cl.intranet.service.PublicTemporalManager;
import cl.intranet.service.PublicacionManager;
import cl.intranet.service.TipoPublicacionManager;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "publica" })
public class publica {
	private ModelAndView model;
	private static final Logger logger = Logger.getLogger(publica.class);
	private PublicacionManager publicaManager = (PublicacionManager) ServiceManager.factory("PublicacionManager");
	private TipoPublicacionManager tipoPublicacionManager = (TipoPublicacionManager) ServiceManager.factory("TipoPublicacionManager");
	private CategoriaManager categoriaManager = (CategoriaManager) ServiceManager.factory("CategoriaManager");
	private CategoriasTipopublicacionManager catTipoPublicManager = (CategoriasTipopublicacionManager) ServiceManager.factory("CategoriasTipopublicacionManager");
	private PublicTemporalManager temporalManager = (PublicTemporalManager) ServiceManager.factory("PublicTemporalManager");

	@RequestMapping(value = { "show" }, method = {RequestMethod.GET,RequestMethod.POST })
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.pageSize"));
		int start = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.pageIndex"));
		model.addObject("anuncios", publicaManager.findPublicacionTipo(2, start, limit));
		model.addObject("noticias", publicaManager.findPublicacionTipo(1, start, limit));
		model.addObject("paginacion", Integer.valueOf(getPaginacion(limit)));
		if (request.getSession(false).getAttribute("usuario") != null) {
			model.addObject("tipoPublicacion", tipoPublicacionManager.findAll());
			model.addObject("categorias", categoriaManager.findAll());
		}
		model.setViewName("publica/show");

		return model;
	}

	@RequestMapping(value ="categorias", method = {RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public String categorias(HttpServletRequest request, HttpServletResponse response) {
		int tipoPublicacion = request.getParameter("tipoPublicacion") == null ? 0 : Integer.parseInt(request.getParameter("tipoPublicacion"));
		List<?> list = this.catTipoPublicManager.findByTipoPublicacion(tipoPublicacion);
		return JsonParser.parseList(list);
	}

	@RequestMapping(value = "anuncio" , method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView anuncio(HttpServletRequest request, HttpServletResponse response) {
		this.model = new ModelAndView();
		int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.pageSize"));
		int start = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.pageIndex"));
		this.model.addObject("anuncios", this.publicaManager.findPublicacionTipo(2, start, limit));
		this.model.setViewName("publica/anuncios/anuncio");
		return this.model;
	}

	@RequestMapping(value = "noticia", method = {RequestMethod.POST,RequestMethod.GET })
	public ModelAndView noticia(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		int pageSelected = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.pageSize"));
		model.addObject("paginacion", Integer.valueOf(getPaginacion(limit)));
		model.addObject("pagina", pageSelected);
		model.addObject("noticias", this.publicaManager.findPublicacionTipo(1, pageSelected * limit- limit, pageSelected * limit));
		model.setViewName("publica/noticias/noticia");

		return model;
	}

	@RequestMapping(value = { "add" }, method = {RequestMethod.POST,RequestMethod.GET })
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response) {
		model = new ModelAndView();
		model.addObject("tipoPublicacion",this.tipoPublicacionManager.findAll());
		model.addObject("categorias", categoriaManager.findAll());
		PublicTemporal temp = new PublicTemporal();
		temp.setEstado("E");
		temp = (PublicTemporal) temporalManager.save(temp);
		model.addObject("temporal", temp);
		
		if(request.getParameter("option") == null) {
			model.addObject("url", "publica/show");
		}else {
			model.addObject("url", "publica/mantenedor");
		}
		
		model.setViewName("publica/add");

		return model;
	}
  
	@RequestMapping(value ="separate", method = {RequestMethod.POST,RequestMethod.GET } )
	public ModelAndView separate(HttpServletRequest request, HttpServletResponse response) {
		this.model = new ModelAndView();
		int tipo = request.getParameter("opcion") == null ? 0 : Integer.parseInt(request.getParameter("opcion"));
		int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.pageSize"));
		int start = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.pageIndex"));
		this.model.addObject("publicacion", this.publicaManager.findPublicacionTipo(tipo, start, limit));
		this.model.addObject("categorias", this.catTipoPublicManager.findByTipoPublicacion(2));
		this.model.setViewName("publica/separate");
		return this.model;
	}

	@RequestMapping(value ="buscar", method = {RequestMethod.POST,RequestMethod.GET })
	public ModelAndView buscar(HttpServletRequest request,HttpServletResponse response) {
		this.model = new ModelAndView();
		int idTipoPublicacion = request.getParameter("opcion") == null ? 0: Integer.parseInt(request.getParameter("opcion"));
		String fecha = request.getParameter("fecha");
		int idCategoria = 0;
		this.model.addObject("publicacion", this.publicaManager.findPublicacionByParams(idTipoPublicacion,DateUtils.stringToDate(fecha), idCategoria));
		this.model.setViewName("publica/anuncios/busqueda");

		return this.model;
	}
	
	@RequestMapping(value ="load", method = {RequestMethod.GET, RequestMethod.POST })
	public ModelAndView load(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		try {
			int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.size"));
			int pageSelected = (request.getParameter("page") == null)?0: Integer.parseInt(request.getParameter("page"));
			String fecha = request.getParameter("fecha");
			int tipoPublicacion = (request.getParameter("tipoPublicacion")==null)?-1:Integer.parseInt(request.getParameter("tipoPublicacion") );
			model.addObject("pagina", pageSelected);
			model.addObject("paginacion", getPaginacion(limit) );
			if(pageSelected==0) {
				model.addObject("publicacion", publicaManager.findPaginacion(tipoPublicacion, fecha, pageSelected, limit));
			} else {
				if(pageSelected == 2) {
					model.addObject("publicacion", publicaManager.findPaginacion(tipoPublicacion, fecha,  pageSelected * limit- limit, limit));
				} else {
					model.addObject("publicacion", publicaManager.findPaginacion(tipoPublicacion, fecha,  pageSelected * limit- limit, pageSelected * limit));
				}
			}
			
			model.setViewName("publica/mantenedor/tabla");
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
			model.setViewName("publica/mantenedor/paginacion");
		} catch (Exception e) {
			model.addObject("message", e);
			model.setViewName("global/message");
		}

		return this.model;
	}

	@RequestMapping(value = "mostrar" , method = {RequestMethod.POST,RequestMethod.GET })
	public ModelAndView mostrar(HttpServletRequest request,HttpServletResponse response) {
		this.model = new ModelAndView();
		int idNoticia = request.getParameter("idNoticia") == null ? 0 : Integer.parseInt(request.getParameter("idNoticia"));
		this.model.addObject("publicacion",this.publicaManager.findById(Integer.valueOf(idNoticia)));
		this.model.setViewName("publica/noticias/one");
		return this.model;
	}

	@RequestMapping(value = "delete" , method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		this.model = new ModelAndView();
		String options = request.getParameter("options");
		options = options.substring(0, options.length() - 1);
		String[] array = options.split(",");
		try {
			for (int x = 0; x < array.length; x++) {
				this.publicaManager.deleteById(array[x]);
			}
			this.model.addObject("message", Integer.valueOf(1));
			this.model.setViewName("global/message");
		} catch (Exception e) {
			this.model.addObject("message", e);
			this.model.setViewName("global/message");
			logger.error("[Intranet]", e);
		}
		return this.model;
	}

	@RequestMapping(value = "propias" , method = {RequestMethod.POST,RequestMethod.GET })
	public ModelAndView propias(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		try {
			Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
			String fecha = request.getParameter("fecha");
			int tipoPublicacion = request.getParameter("tipoPublicacion") == null ? 0: Integer.parseInt(request.getParameter("tipoPublicacion"));
			model.addObject("publicacion", publicaManager.findByUser(usuario.getIdusuario(), tipoPublicacion, fecha));
			int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.size"));
			int pageSelected = (request.getParameter("page") == null)?1: Integer.parseInt(request.getParameter("page"));
			model.addObject("pagina", pageSelected);
			model.addObject("paginacion", getPaginacion(limit) );
			
			if (tipoPublicacion == 0) {
				model.addObject("tipoPublicacion", tipoPublicacionManager.findAll());
				model.setViewName("publica/mantenedor/include");
			} else {
				model.setViewName("publica/mantenedor/tabla");
			}
		} catch (Exception e) {
			logger.error("[Intranet]", e);
			this.model.addObject("message", e);
			this.model.setViewName("global/message");
		}

		return this.model;
	}
	
	@RequestMapping(value = "mantenedor" , method = {RequestMethod.POST,RequestMethod.GET })
	public ModelAndView mantenedorPublicacion(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		try {
			String fecha = request.getParameter("fecha");
			int tipoPublicacion = request.getParameter("tipoPublicacion") == null ? 0: Integer.parseInt(request.getParameter("tipoPublicacion"));
			
			int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.size"));
			int pageSelected = (request.getParameter("page") == null)?1: Integer.parseInt(request.getParameter("page"));
			if(pageSelected==0) {
				model.addObject("publicacion", publicaManager.findPaginacion(tipoPublicacion, fecha, pageSelected, limit));
			} else {
				if(pageSelected == 2) {
					model.addObject("publicacion", publicaManager.findPaginacion(tipoPublicacion, fecha,  pageSelected * limit- limit, limit));
				} else {
					model.addObject("publicacion", publicaManager.findPaginacion(tipoPublicacion, fecha,  pageSelected * limit- limit, pageSelected * limit));
				}
				
			}
			
			model.addObject("pagina", pageSelected);
			model.addObject("paginacion", getPaginacion(limit) );
			
			if (tipoPublicacion == 0) {
				model.addObject("tipoPublicacion", tipoPublicacionManager.findAll());
				model.setViewName("publica/mantenedor/include");
			} else {
				model.setViewName("publica/mantenedor/tabla");
			}
		} catch (Exception e) {
			logger.error("[Intranet]", e);
			this.model.addObject("message", e);
			this.model.setViewName("global/message");
		}

		return this.model;
	}

	@RequestMapping(value = { "todas" }, method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView todas(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		try {
			String fecha = request.getParameter("fecha");
			int tipoPublicacion = request.getParameter("tipoPublicacion") == null ? 0: Integer.parseInt(request.getParameter("tipoPublicacion"));
			model.addObject("publicacion",publicaManager.findTodas(tipoPublicacion, fecha));
			if (tipoPublicacion == 0) {
				this.model.addObject("tipoPublicacion",this.tipoPublicacionManager.findAll());
				this.model.setViewName("publica/mantenedor/todas");
			} else {
				this.model.setViewName("publica/mantenedor/tabla");
			}
		} catch (Exception e) {
			logger.error("[Intranet]", e);
			this.model.addObject("message", e);
			this.model.setViewName("global/message");
		}

		return model;
	}

	@RequestMapping(value = { "update" }, method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
		int id = request.getParameter("idNoticia") == null ? 0 : Integer.parseInt(request.getParameter("idNoticia"));
		Publicacion publica = (Publicacion) publicaManager.findById(Integer.valueOf(id));
		List<?> list = catTipoPublicManager.findByTipoPublicacion(publica.getTipoPublicacion().getIdtipoPublicacion());
		model.addObject("publica", publica);
		model.addObject("fechaModificacion", DateUtils.DateToString(new Date(), "dd/MM/yyyy"));
		model.addObject("perfil", Integer.valueOf(usuario.getPerfil().getIdperfil()));
		model.addObject("tipoPublicacion", tipoPublicacionManager.findAll());
		model.addObject("categorias", list);
		model.setViewName("publica/mantenedor/editar");

		return model;
	}

	private int getPaginacion(int limit) {
		int total = this.publicaManager.countAll();
		int d = total / limit;
		int remain = total % limit;
		if (remain > 0) {
			d++;
		}

		return d;
	}
}
