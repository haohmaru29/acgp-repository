package cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.oracle;

import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.DetalleGastoBO;
import cl.bicevida.envionominas.model.bo.GastoNominaBO;
import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.bo.TipoNominaBO;
import cl.bicevida.envionominas.model.config.EnvioNominasConfig;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.interfaces.GastosNominaDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementa el acceso a datos para el calculo de los gastos asociados a una nomina.
 * 
 * Registro de versiones:
 *      - 1.0 17/11/2010 Giorgio Gortaire (ACGP) : Version inicial. 
 */
public class OracleGastosNominaDAO implements GastosNominaDAO {
    private EnvioNominasConfig config;

    public OracleGastosNominaDAO() {
        config = new EnvioNominasConfig();
    }

    public List<GastoNominaBO> queryGastosNominas( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _bancoProceso, Long _tipoNomina ) throws DAOException {
        List<GastoNominaBO> gastos = new ArrayList<GastoNominaBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select     " + 
            "    n.ID as ID_NOMINA,     " + 
            "    n.FECHA_ESTADO as FECHA_CONCILIACION," + 
            "    qa.FECHA_INICIO as FECHA_INICIO_PROCESO, " +
            "    qb.NOMBRE_BANCO as NOMBRE_BANCO_PROCESO, " + 
            "    tn.NOMBRE||' - '||tn.DESCRIPCION as TIPO_NOMINA, " + 
            "    qb.NOMBRE_BANCO as NOMBRE_BANCO," + 
            "    case when qb.MONTO_MISMO_BANCO is null then 0 else qb.MONTO_MISMO_BANCO end as COMISION_MISMOBANCO," + 
            "    case when qb.MONTO_OTROS_BANCOS is null then 0 else qb.MONTO_OTROS_BANCOS end as COMISION_OTROSBANCOS," + 
            "    case when qc.CANTIDAD_PAGADO is null then 0 else qc.CANTIDAD_PAGADO end as CANTIDAD_TOTAL_PAGADO," + 
            "    case when qc.MONTO_TOTAL_PAGADO is null then 0 else qc.MONTO_TOTAL_PAGADO end as MONTO_TOTAL_PAGADO," + 
            "    case when qf.CANTIDAD_RECHAZADO is null then 0 else qf.CANTIDAD_RECHAZADO end as CANTIDAD_TOTAL_RECHAZADO," + 
            "    case when qf.MONTO_RECHAZADO is null then 0 else qf.MONTO_RECHAZADO end as MONTO_TOTAL_RECHAZADO," + 
            "    case when qd.CANTIDAD_MISMOBANCO is null then 0 else qd.CANTIDAD_MISMOBANCO end as CANTIDAD_MISMOBANCO," + 
            "    case when qd.MONTO_MISMOBANCO is null then 0 else qd.MONTO_MISMOBANCO end as MONTO_MISMOBANCO," + 
            "    case when qe.CANTIDAD_OTROSBANCOS is null then 0 else qe.CANTIDAD_OTROSBANCOS end as CANTIDAD_OTROSBANCOS," + 
            "    case when qe.MONTO_OTROSBANCOS is null then 0 else qe.MONTO_OTROSBANCOS end as MONTO_OTROSBANCOS " + 
            " from " + 
            "    NOMINAS n left outer join     " + 
            "    ( select REFERENCIA_NOMINA as ID_NOMINA, min(FECHA_CREACION) as FECHA_INICIO from EVNM_PROCESOS group by REFERENCIA_NOMINA ) qa on qa.ID_NOMINA = n.ID join    " + 
            "    TIPOS_NOMINA tn on tn.ID = n.REFERENCIA_TIPO_NOMINA left outer join     " + 
            "    ( select qb_b.ID_BANCO_PROCESO as ID_BANCO_PROCESO, qb_d.ID as ID_NOMINA, qb_b.NOMBRE as NOMBRE_BANCO, qb_c.MONTO_MISMO_BANCO as MONTO_MISMO_BANCO, qb_c.MONTO_OTROS_BANCOS as MONTO_OTROS_BANCOS      from EVNM_PROCESOS qb_a left outer join EVNM_BANCOS_PROCESO qb_b on qb_a.REFERENCIA_BANCO_PROCESO = qb_b.ID_BANCO_PROCESO left outer join EVNM_COMISIONES qb_c on qb_b.ID_BANCO_PROCESO = qb_c.REFERENCIA_BANCO_PROCESO join NOMINAS qb_d on qb_a.REFERENCIA_NOMINA = qb_d.ID join BANCOS qb_f on qb_b.REFERENCIA_BANCO = qb_f.ID      where qb_d.FECHA_CURSE between qb_c.FECHA_INICIO_VIGENCIA and qb_c.FECHA_TERMINO_VIGENCIA     ) qb on qb.ID_NOMINA = n.ID left outer join     " + 
            "    ( select qc_a.REFERENCIA_NOMINA as ID_NOMINA, count(1) as CANTIDAD_PAGADO, sum(qc_a.MONTO) as MONTO_TOTAL_PAGADO      from TRANSACCIONES_PAGO qc_a where qc_a.REFERENCIA_ESTADO_TRANSACCION = "+config.get(EnvioNominasConfig.ID_REGISTRO_PAGADO)+"      group by qc_a.REFERENCIA_NOMINA     ) qc on qc.ID_NOMINA = n.ID left outer join     " + 
            "    ( select qd_b.ID as ID_NOMINA, count(1) as CANTIDAD_MISMOBANCO, sum(qd_d.MONTO) as MONTO_MISMOBANCO      from EVNM_PROCESOS qd_a join NOMINAS qd_b on qd_a.REFERENCIA_NOMINA = qd_b.ID left outer join EVNM_BANCOS_PROCESO qd_c on qd_a.REFERENCIA_BANCO_PROCESO = qd_c.ID_BANCO_PROCESO left outer join TRANSACCIONES_PAGO qd_d on qd_b.ID = qd_d.REFERENCIA_NOMINA      where qd_d.REFERENCIA_BANCO = qd_c.REFERENCIA_BANCO and qd_d.REFERENCIA_ESTADO_TRANSACCION = "+config.get(EnvioNominasConfig.ID_REGISTRO_PAGADO)+"      group by qd_b.ID    ) qd on qd.ID_NOMINA = n.ID left outer join     " + 
            "    ( select qe_b.ID as ID_NOMINA, count(1) as CANTIDAD_OTROSBANCOS, sum(qe_d.MONTO) as MONTO_OTROSBANCOS from EVNM_PROCESOS qe_a join NOMINAS qe_b on qe_a.REFERENCIA_NOMINA = qe_b.ID left outer join EVNM_BANCOS_PROCESO qe_c on qe_a.REFERENCIA_BANCO_PROCESO = qe_c.ID_BANCO_PROCESO left outer join TRANSACCIONES_PAGO qe_d on qe_b.ID = qe_d.REFERENCIA_NOMINA      where qe_d.REFERENCIA_BANCO <> qe_c.REFERENCIA_BANCO and qe_d.REFERENCIA_ESTADO_TRANSACCION = "+config.get(EnvioNominasConfig.ID_REGISTRO_PAGADO)+"      group by qe_b.ID    ) qe on qe.ID_NOMINA = n.ID left outer join   " + 
            "    ( select qf_b.ID as ID_NOMINA, count(1) as CANTIDAD_RECHAZADO, sum(qf_d.MONTO) as MONTO_RECHAZADO from EVNM_PROCESOS qf_a join NOMINAS qf_b on qf_a.REFERENCIA_NOMINA = qf_b.ID left outer join TRANSACCIONES_PAGO qf_d on qf_b.ID = qf_d.REFERENCIA_NOMINA where qf_d.REFERENCIA_ESTADO_TRANSACCION = "+config.get(EnvioNominasConfig.ID_REGISTRO_RECHAZADO)+" group by qf_b.ID )  qf on qf.ID_NOMINA = n.ID " +         
            "    where n.REFERENCIA_ESTADO_NOMINA in (" + config.get(EnvioNominasConfig.ID_NOMINA_CONCILIADA) + ", " + config.get(EnvioNominasConfig.ID_NOMINA_CONCILIADA_CON_ERROR) + ")";
        if ( _loteNomina != null && !"".equals(_loteNomina.trim())) {
            sql = sql + " and upper(n.LOTE) like '%" + _loteNomina.trim().toUpperCase() + "%'";
        }
        if ( _tipoNomina != null ) {
            sql = sql + " and tn.ID = " + _tipoNomina;
        }
        if ( _bancoPago != null ) {
            sql = sql + " and n.ID in (select sstp.REFERENCIA_NOMINA from TRANSACCIONES_PAGO sstp where sstp.REFERENCIA_BANCO = " + _bancoPago + ")";
        }
        if ( _bancoProceso != null ) {
            sql = sql + " and qb.ID_BANCO_PROCESO = " + _bancoProceso;
        }
        if ( _fechaDesde != null && _fechaHasta != null ) {
            sql = sql + " and n.FECHA_CURSE between " + _fechaDesde + " and " + _fechaHasta;
        }
        sql = sql + " order by ID_NOMINA DESC";
        
        System.out.println(sql);
        
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                GastoNominaBO gasto = new GastoNominaBO();
                gasto.setNomina( NominaBO.crear( rs.getLong( "ID_NOMINA" ), TipoNominaBO.crear( rs.getString( "TIPO_NOMINA" ) ), rs.getString( "NOMBRE_BANCO" ), rs.getTimestamp( "FECHA_INICIO_PROCESO" ), rs.getTimestamp( "FECHA_CONCILIACION" ) ) );
                gasto.setCantidadMismoBanco( rs.getLong( "CANTIDAD_MISMOBANCO" ) );
                gasto.setCantidadOtrosBancos( rs.getLong( "CANTIDAD_OTROSBANCOS" ) );
                gasto.setCantidadProcesados( rs.getLong( "CANTIDAD_TOTAL_PAGADO" ) );
                gasto.setComisionMismoBanco( rs.getBigDecimal( "COMISION_MISMOBANCO" ) );
                gasto.setComisionOtrosBancos( rs.getBigDecimal( "COMISION_OTROSBANCOS" ) );
                gasto.setMontoTotalMismoBanco( rs.getBigDecimal( "MONTO_MISMOBANCO" ) );
                gasto.setMontoTotalOtrosBancos( rs.getBigDecimal( "MONTO_OTROSBANCOS" ) );
                gasto.setMontoTotalProcesados( rs.getBigDecimal( "MONTO_TOTAL_PAGADO" ) );
                gasto.setFechaInicioProceso( rs.getDate( "FECHA_INICIO_PROCESO" ) );
                gasto.setFechaConciliacionProceso( rs.getDate( "FECHA_CONCILIACION" ) );
                gasto.setCantidadRechazados(rs.getLong("CANTIDAD_TOTAL_RECHAZADO"));
                gasto.setMontoTotalRechazados(rs.getBigDecimal("MONTO_TOTAL_RECHAZADO"));
                gasto.getNomina().setBancoProceso(rs.getString("NOMBRE_BANCO_PROCESO"));
                gastos.add( gasto );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return gastos;
    }

    public List<DetalleGastoBO> queryDetalleGastosbyNomina( Long _idNomina ) throws DAOException {
        List<DetalleGastoBO> detalleGastos = new ArrayList<DetalleGastoBO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select " + 
            "    b.NOMBRE, " + 
            "    case when x.REGISTROS_PROCESADOS is null then 0 else x.REGISTROS_PROCESADOS end as REGISTROS_PROCESADOS, " + 
            "    case when x.TOTAL_PROCESADOS is null then 0 else x.TOTAL_PROCESADOS end as TOTAL_PROCESADOS, " + 
            "    case when y.REGISTROS_NO_PROCESADOS is null then 0 else y.REGISTROS_NO_PROCESADOS end as REGISTROS_NO_PROCESADOS, " + 
            "    case when y.TOTAL_NO_PROCESADOS is null then 0 else y.TOTAL_NO_PROCESADOS end as TOTAL_NO_PROCESADOS " + 
            "from " + 
            "    (" + 
            "        select distinct(wtp.REFERENCIA_BANCO) as ID_BANCO " + 
            "        from TRANSACCIONES_PAGO wtp " + 
            "        where wtp.REFERENCIA_NOMINA = "+_idNomina+" " + 
            "    ) w left outer join " + 
            "    (" + 
            "        select distinct(xtp.REFERENCIA_BANCO) as ID_BANCO, count(1) as REGISTROS_PROCESADOS, sum(xtp.MONTO) as TOTAL_PROCESADOS " + 
            "        from TRANSACCIONES_PAGO xtp " + 
            "        where xtp.REFERENCIA_NOMINA = "+_idNomina+" and xtp.REFERENCIA_ESTADO_TRANSACCION = " + config.get( EnvioNominasConfig.ESTADO_TRANSACCION_PROCESADO ) + " " + 
            "        group by xtp.REFERENCIA_BANCO order by xtp.REFERENCIA_BANCO desc" + 
            "    ) x on w.ID_BANCO = x.ID_BANCO left outer join " + 
            "    (" + 
            "        select distinct(ytp.REFERENCIA_BANCO) as ID_BANCO, count(1) as REGISTROS_NO_PROCESADOS, sum(ytp.MONTO) as TOTAL_NO_PROCESADOS " + 
            "        from TRANSACCIONES_PAGO ytp " + 
            "        where ytp.REFERENCIA_NOMINA = "+_idNomina+" and ytp.REFERENCIA_ESTADO_TRANSACCION <> " + config.get( EnvioNominasConfig.ESTADO_TRANSACCION_PROCESADO ) + " " + 
            "        group by ytp.REFERENCIA_BANCO order by ytp.REFERENCIA_BANCO desc" + 
            "    ) y on w.ID_BANCO = y.ID_BANCO join BANCOS b on b.ID = w.ID_BANCO";
        try {
            conn = OracleDAOFactory.createConnection( config.get( EnvioNominasConfig.JNDI_DATASOURCE ) );
            stmt = conn.createStatement();
            rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                DetalleGastoBO detalle = new DetalleGastoBO();
                detalle.setBanco( BancoBO.crear( rs.getString( "NOMBRE" ) ) );
                detalle.setCantidadProcesados( rs.getLong( "REGISTROS_PROCESADOS" ) );
                detalle.setMontoTotalProcesados( rs.getBigDecimal( "TOTAL_PROCESADOS" ) );
                detalle.setCantidadNoProcesados( rs.getLong( "REGISTROS_NO_PROCESADOS" ) );
                detalle.setMontoTotalNoProcesados( rs.getBigDecimal( "TOTAL_NO_PROCESADOS" ) );
                detalleGastos.add( detalle );
            }
        } catch ( RuntimeException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } catch ( DAOException e ) {
            throw e;
        } catch ( SQLException e ) {
            throw new DAOException( "Error en la consulta : [" + e.getMessage() + "][" + sql + "]", e );
        } finally {
            OracleDAOFactory.closeAll( conn, stmt, rs );
        }
        return detalleGastos;
    }
}
