package cl.reduc.reportes.web;
import cl.reduc.commons.Application;
import cl.reduc.commons.odbc.Oracle;
import cl.reduc.commons.utils.json.JsonView;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class security {

    private static final Logger logger = Logger.getLogger(security.class);

    public void connection(HttpServletRequest request, HttpServletResponse response ) {
         JsonView jsonView = new JsonView();
        Connection conn = null;
        try {
            conn = Oracle.getInstances(Application.getInstance().jndiDB()).getJNDIConnection();
            jsonView.prepareResponse(true, "Conexion realizada con exito");
        } catch(SQLException e ) {
            logger.error(e);
            jsonView.prepareResponse(false, "Problemas con conexion a base de datos, error: <b>" + e + "</b>");
        } finally {
            Oracle.getInstances(Application.getInstance().jndiDB()).closeConnection(conn);
        }
        jsonView.render(response);
    }
}