package cl.reduc.reportes.service;
import cl.reduc.commons.mvc.service.AbstractServiceManager;
import cl.reduc.reportes.repository.ConsultaReclasifController;

import java.sql.Connection;
import java.sql.ResultSet;

public class ConsultaReclasifManager extends AbstractServiceManager {

    public ResultSet findAllCodigos(Connection conn) {
        return ( (ConsultaReclasifController) jpaController).findAllCodigos(conn);
    }
}