package cl.reduc.reportes.service;
import cl.reduc.commons.mvc.service.AbstractServiceManager;
import cl.reduc.reportes.repository.OrdenAtencionController;
import java.sql.Connection;
import java.sql.ResultSet;

public class OrdenAtencionManager extends AbstractServiceManager {
    
    public ResultSet findAllTipoPaciente(Connection conn, String tipoPaciente) {
        return ((OrdenAtencionController) jpaController ).findAllTipoPaciente(conn,tipoPaciente);
    }
    
    public ResultSet findTipoAtencion(Connection conn, String tipoAtencion) {
        return ((OrdenAtencionController) jpaController ).findTipoAtencion(conn, tipoAtencion);
    }
    
    public ResultSet findAllInstitucion(Connection conn) {
        return ( (OrdenAtencionController) jpaController).finAllInstitucion(conn);
    }
}