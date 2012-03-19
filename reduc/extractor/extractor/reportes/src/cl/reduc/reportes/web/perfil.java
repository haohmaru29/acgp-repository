package cl.reduc.reportes.web;
import cl.reduc.commons.mvc.service.ServiceManager;
import cl.reduc.commons.utils.json.JsonView;
import cl.reduc.reportes.service.UsuarioManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class perfil {
    
    UsuarioManager usuarioManager = 
              (UsuarioManager) ServiceManager.factory("UsuarioManager");
    
    public void usuarios(HttpServletRequest request, HttpServletResponse response) {
        JsonView jsonView = new JsonView();
        jsonView.prepareResponse(usuarioManager.findAll());
        jsonView.render( response );
    }
}