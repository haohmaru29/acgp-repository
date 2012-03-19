package cl.reduc.reportes.service;
import cl.reduc.commons.mvc.service.AbstractServiceManager;
import cl.reduc.reportes.repository.EspecialidadReclasifController;

import java.sql.Connection;
import java.sql.ResultSet;

public class EspecialidadReclasifManager extends AbstractServiceManager {

    public ResultSet findAllEspecialidad(Connection conn) {
        return ((EspecialidadReclasifController ) jpaController).findAllEspecialidad(conn);
    }
    
    public ResultSet findByCod(Connection conn, String cod) {
        return ((EspecialidadReclasifController ) jpaController).findByCod(conn,cod);
    }
}