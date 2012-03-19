package cl.reduc.reportes.web;

import cl.reduc.commons.Application;
import cl.reduc.commons.mvc.service.ServiceManager;
import cl.reduc.commons.odbc.Oracle;
import cl.reduc.commons.utils.json.JsonView;
import cl.reduc.reportes.service.HistorialReporteManager;

import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class historial {

    HistorialReporteManager historialManager = 
            (HistorialReporteManager)ServiceManager.factory("HistorialReporteManager");
    
    public void usuario(HttpServletRequest request, HttpServletResponse response ){
        String idUsuario=request.getParameter("idUsuario");
        int idEstado = Integer.parseInt(request.getParameter("estado"));
        Connection conn = null;
        JsonView jsonView = new JsonView();
        try {
            conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
            jsonView.prepareResponse(historialManager.findByUser(conn, idUsuario, idEstado) );
        }catch(SQLException e) {
          jsonView.prepareResponse(false, e +"");
        } finally {
          Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
        }
        jsonView.render(response);
    }
  
}