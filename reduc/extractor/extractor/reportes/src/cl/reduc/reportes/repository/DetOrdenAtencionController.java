package cl.reduc.reportes.repository;
import cl.reduc.commons.Application;
import cl.reduc.commons.mvc.controller.AbstractJpaController;
import cl.reduc.commons.utils.PropertiesUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;

public class DetOrdenAtencionController extends AbstractJpaController {

    private static final Logger logger = Logger.getLogger(DetOrdenAtencionController.class);
    private ResultSet rs;

    public ResultSet findAllProfesional(Connection conn) {
        String packageName = "{ call " + Application.getInstance().packageDB() + ".PROFESIONAL_RESERVA(?) }";
        try {
            CallableStatement call = conn.prepareCall(packageName);
            call.registerOutParameter(1, OracleTypes.CURSOR);
              
            call.execute();
            rs = (ResultSet) call.getObject(1);
       } catch(SQLException e ) {
            logger.error(e);
       }
          
       return rs;
    }
    
    public ResultSet findAllTipoPrestacion(Connection conn, String tipoPrestacion) {
        String packageName = "{ call " + Application.getInstance().packageDB() + ".TIPO_PRESTACION(?,?) }";
        try {
            CallableStatement call = conn.prepareCall(packageName);
            call.setString(1, tipoPrestacion);
            call.registerOutParameter(2, OracleTypes.CURSOR);
              
            call.execute();
            rs = (ResultSet) call.getObject(2);
       } catch(SQLException e ) {
            logger.error(e);
       }
          
       return rs;
    }
}