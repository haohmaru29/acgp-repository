package cl.intranet.web;

import cl.acgp.commons.mvc.service.jpa.ServiceManager;
import cl.intranet.domain.Menu;
import cl.intranet.domain.PerfilMenu;
import cl.intranet.domain.Usuario;
import cl.intranet.service.MenuManager;
import cl.intranet.service.UsuarioManager;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "security" })
public class security {
	private ModelAndView model;
	private static final Logger logger = Logger.getLogger(security.class);
	private MenuManager menuManager = (MenuManager) ServiceManager.factory("MenuManager");
	private UsuarioManager usuarioManager = (UsuarioManager) ServiceManager.factory("UsuarioManager");

	@RequestMapping(value = { "login" }, method = {RequestMethod.POST,RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		this.model = new ModelAndView();
		String nickname = request.getParameter("nickname");
		String clave = request.getParameter("clave");
		try {
			Usuario usuario = this.usuarioManager.login(nickname, clave);
			if (usuario != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("usuario", usuario);
				model.addObject("message", "1");
				model.setViewName("global/message");
			} else {
				model.addObject("message", "Datos ingresados invalidos, favor vuelva a intentar");
				model.setViewName("global/message");
			}
		} catch (Exception e) {
			model.addObject("message", e);
			model.setViewName("global/message");
		}

		return model;
	}

	@RequestMapping(value = { "closeSession" }, method = { RequestMethod.POST })
	public ModelAndView closeSession(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		try {
			request.getSession(false).invalidate();
		} catch(Exception e ) {
			logger.error("Session inactiva" , e );
		}
		model.addObject("message", 1 );
		model.setViewName("global/message");

		return model;
	}

	@RequestMapping(value = { "menu" }, method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView menu(HttpServletRequest request, HttpServletResponse response) {
		model = new ModelAndView();
		HttpSession session = request.getSession(false);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		List<Menu> menus = new ArrayList<Menu>();
		if (usuario != null) {
			for (PerfilMenu perfil : usuario.getPerfil().getPerfilMenus()) {
				menus.add(perfil.getMenu());
			}
		} else {
			for (Menu men : menuManager.findMenu()) {
				int x = 0;
				for (PerfilMenu perfil : men.getPerfilMenus()) {
					if (perfil.getPerfil().getIdperfil() != 1) {
						x = 1;
						break;
					}
				}
				if (x == 1)
					menus.add(men);
			}
		}
		model.addObject("menu", menus);
		model.addObject("child", this.menuManager.findChilds());
		model.setViewName("menu/menu");

		return model;
	}
}