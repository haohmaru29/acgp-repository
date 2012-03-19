package cl.reduc.reportes.service;
import cl.reduc.commons.mvc.service.AbstractServiceManager;
import cl.reduc.reportes.repository.CentroReclasifController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CentroReclasifManager extends AbstractServiceManager {

    public ResultSet findAll(Connection conn) throws SQLException {
        return ( (CentroReclasifController ) jpaController).finAllCr(conn);
    }
    
    public ResultSet findByCod(Connection conn, String cod) throws SQLException {
        return ( (CentroReclasifController ) jpaController).findCrByCod(conn, cod);
    }
    
    public ResultSet findSeccionByCod(Connection conn, String cod) {
        return ((CentroReclasifController) jpaController).findSeccionByCod(conn,cod);
    }
    
    public ResultSet findAllSeccion(Connection conn) {
        return ((CentroReclasifController) jpaController).findAllSeccion(conn);
    }
}