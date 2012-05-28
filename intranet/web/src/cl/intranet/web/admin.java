package cl.intranet.web;

import cl.acgp.commons.mvc.service.jpa.AbstractServiceManager;
import cl.acgp.commons.mvc.service.jpa.ServiceManager;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "admin" })
public class admin {
	private static final Logger logger = Logger.getLogger(admin.class);
	private ModelAndView model;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "save" }, method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		this.model = new ModelAndView();
		try {
			String controller = request.getParameter("mngr");
			Map<Object, Object> params = new HashMap<Object, Object>(request.getParameterMap());
			params.remove("mngr");
			AbstractServiceManager<?> admin = ServiceManager.factory(controller+ "Manager");
			admin.save(params);
			this.model.addObject("message", "1");
			this.model.setViewName("global/message");
		} catch (Exception e) {
			this.model.addObject("message", "Problemas: " + e);
			this.model.setViewName("global/message");
			logger.error("[Intranet]", e);
		}

		return this.model;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		this.model = new ModelAndView();
		try {
			String id = request.getParameter("id");
			String controller = request.getParameter("mngr");
			Map<Object, Object> params = new HashMap<Object, Object>(request.getParameterMap());
			params.remove("mngr");
			AbstractServiceManager<?> admin = ServiceManager.factory(controller+ "Manager");
			admin.deleteById(id);
			this.model.addObject("message", "1");
			this.model.setViewName("global/message");
		} catch (Exception e) {
			this.model.addObject("message", e);
			this.model.setViewName("global/message");
			logger.error("[Intranet]", e);
		}
		return this.model;
	}
}