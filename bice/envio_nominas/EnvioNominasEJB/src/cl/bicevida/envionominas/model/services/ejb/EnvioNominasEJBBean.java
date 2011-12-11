package cl.bicevida.envionominas.model.services.ejb;

import cl.bicevida.envionominas.model.bo.ArchivoBO;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.ComisionBO;
import cl.bicevida.envionominas.model.bo.DetalleGastoBO;
import cl.bicevida.envionominas.model.bo.GastoNominaBO;
import cl.bicevida.envionominas.model.bo.OrigenBO;
import cl.bicevida.envionominas.model.logic.SchedulerManager;
import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.EstadoNominaBO;
import cl.bicevida.envionominas.model.bo.EstadoTransaccionBO;
import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.bo.TipoCuentaBO;
import cl.bicevida.envionominas.model.bo.TipoFeriadoBO;
import cl.bicevida.envionominas.model.bo.FeriadoBO;
import cl.bicevida.envionominas.model.bo.ParametroBO;
import cl.bicevida.envionominas.model.bo.ListaDistribucionBO;
import cl.bicevida.envionominas.model.bo.CorreoBO;

import cl.bicevida.envionominas.model.bo.TipoNominaBO;

import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.classes.DAOFactory;
import cl.bicevida.envionominas.model.dataaccess.dataservices.dao.exceptions.DAOException;
import cl.bicevida.envionominas.model.exceptions.EnvioNominasException;

import cl.bicevida.envionominas.model.logic.EnvioNominasManager;

import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType;

import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.Timeout;
import javax.ejb.Timer;

import javax.ejb.TransactionAttribute;

import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

@Stateless( name = "EnvioNominasEJB" )
public class EnvioNominasEJBBean implements EnvioNominasEJB, EnvioNominasEJBLocal {
    @Resource
    private SessionContext ctx;
    private static final Logger log = Logger.getLogger( EnvioNominasEJBBean.class );
    private DAOFactory factory = DAOFactory.getDAOFactory( DAOFactory.ORACLE );

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<TipoNominaBO> obtenerTiposNomina() {
        List<TipoNominaBO> lista = null;
        try {
            lista = factory.getTiposNominaDAO().queryTiposNomina();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<BancoBO> obtenerBancosPago() {
        List<BancoBO> lista = null;
        try {
            lista = factory.getBancosDAO().queryBancos();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<EstadoTransaccionBO> obtenerEstadosTransaccion() {
        List<EstadoTransaccionBO> lista = null;
        try {
            lista = factory.getEstadosTransaccionDAO().queryEstadosTransaccion();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<EstadoNominaBO> obtenerEstadosNomina() {
        List<EstadoNominaBO> lista = null;
        try {
            lista = factory.getEstadosNominaDAO().queryEstadosNomina();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<TipoFeriadoBO> obtenerTiposFeriado() {
        List<TipoFeriadoBO> lista = null;
        try {
            lista = factory.getTiposFeriadoDAO().queryTiposFeriado();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<TipoCuentaBO> obtenerTiposCuenta() {
        List<TipoCuentaBO> lista = null;
        try {
            lista = factory.getTiposCuentaDAO().queryTiposCuenta();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<NominaBO> obtenerNominasCancelables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) {
        List<NominaBO> nominas = new ArrayList<NominaBO>();
        try {
            nominas = factory.getNominaDAO().queryNominasCancelables( _bancoPago, _fechaDesde, _fechaHasta, _numeroNomina, _tipoNomina, _tipoCuenta );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return nominas;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<RegistroNominaBO> consultaEstadoTransaccionesNomina( Long _bancoPago, Long _estadoNomina, Long _estadoTransaccion, Date _fechaDesde, Date _fechaHasta, String _loteNomina, String _rutTitular, Long _bancoProceso, Long _tipoNomina ) {
        List<RegistroNominaBO> lista = null;
        try {
            lista = factory.getNominaDAO().consultaEstadoTransaccionesNomina( _bancoPago, _estadoNomina, _estadoTransaccion, _fechaDesde, _fechaHasta, _loteNomina, _rutTitular, _bancoProceso, _tipoNomina );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<NominaBO> obtenerNominasExtraibles( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso ) {
        List<NominaBO> nominas = new ArrayList<NominaBO>();
        try {
            nominas = factory.getNominaDAO().queryNominasExtraibles( _fechaDesde, _fechaHasta, _loteNomina, _tipoNomina, _bancoProceso );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return nominas;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<NominaBO> obtenerNominasAutorizables( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, Long _numeroNomina, Long _tipoNomina, Long _tipoCuenta ) {
        List<NominaBO> nominas = new ArrayList<NominaBO>();
        try {
            nominas = factory.getNominaDAO().queryNominasAutorizables( _bancoPago, _fechaDesde, _fechaHasta, _numeroNomina, _tipoNomina, _tipoCuenta );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return nominas;
    }

    @Timeout
    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void timeout( Timer timer ) {
        SchedulerManager helper = new SchedulerManager();
        helper.handleTimeout( ctx, timer );
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void inicializarScheduler() {
        SchedulerManager helper = new SchedulerManager();
        helper.inicializar( ctx );
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<RegistroNominaBO> obtenerRegistrosPorNomina( Long _idNomina ) {
        List<RegistroNominaBO> registros = new ArrayList<RegistroNominaBO>();
        try {
            registros = factory.getRegistroNominaDAO().queryRegistrosByNomina( _idNomina );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return registros;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<GastoNominaBO> obtenerGastosNominas( Long _bancoPago, Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _bancoProceso, Long _tipoNomina ) {
        List<GastoNominaBO> gastos = new ArrayList<GastoNominaBO>();
        try {
            gastos = factory.getGastosNominaDAO().queryGastosNominas( _bancoPago, _fechaDesde, _fechaHasta, _loteNomina, _bancoProceso, _tipoNomina );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return gastos;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<FeriadoBO> obtenerFeriado() {
        List<FeriadoBO> lista = null;
        try {
            lista = factory.getFeriadoDAO().queryFeriado();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void agregarFeriado( String _tipoFeriado, String _descripcionFeriado, Date _fechaFeriado ) {
        try {
            factory.getFeriadoDAO().agregarFeriado( _tipoFeriado, _descripcionFeriado, _fechaFeriado );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void eliminarFeriado( Date _fechaFeriado ) {
        try {
            factory.getFeriadoDAO().eliminarFeriado( _fechaFeriado );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void modificarFeriado( Long idFeriado, String tipoFeriado, String descripcionFeriado, Date fechaFeriado ) {
        try {
            factory.getFeriadoDAO().modificarFeriado( idFeriado, tipoFeriado, descripcionFeriado, fechaFeriado );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public Long buscarFeriado( Date _fechaFeriado ) {
        Long idFeriado = null;
        try {
            idFeriado = factory.getFeriadoDAO().buscarFeriado( _fechaFeriado );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return idFeriado;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<CorreoBO> obtenerCorreos() {
        List<CorreoBO> lista = null;
        try {
            lista = factory.getCorreosDAO().queryCorreos();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void agregarCorreo( String _estadoCorreo, String _nombreCorreo, String _usuarioCorreo ) {
        try {
            factory.getCorreosDAO().queryCrearNuevoCorreo( _estadoCorreo, _nombreCorreo, _usuarioCorreo, null, null );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void eliminarCorreo( Long _idCorreo ) {
        try {
            factory.getCorreosDAO().queryEliminarCorreo( _idCorreo );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void modificarCorreo( Long idCorreo, String _estadoCorreo, String _nombreCorreo, String _usuarioCorreo ) {
        try {
            factory.getCorreosDAO().queryModificarCorreo( idCorreo, _estadoCorreo, _nombreCorreo, _usuarioCorreo, null, null );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public Long buscarCorreo( String _nombre ) {
        Long idFeriado = null;
        try {
            idFeriado = factory.getCorreosDAO().queryBuscarCorreoByNombre( _nombre );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return idFeriado;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<ParametroBO> obtenerParametros() {
        List<ParametroBO> lista = null;
        try {
            lista = factory.getParametrosDAO().queryParametro();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void agregarParametro( String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) {
        try {
            factory.getParametrosDAO().agregarParametro( _claveParametro, _valorParametro, _descripcionParametro, _tipoParametro );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void eliminarParametro( Long _idParametro ) {
        try {
            factory.getParametrosDAO().eliminarParametro( _idParametro );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void modificarParametro( Long idParametro, String _claveParametro, String _valorParametro, String _descripcionParametro, String _tipoParametro ) {
        try {
            factory.getParametrosDAO().modificarParametro( idParametro, _claveParametro, _valorParametro, _descripcionParametro, _tipoParametro );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public Long buscarParametro( String _claveParametro, Long _idBancoProceso ) {
        Long idFeriado = null;
        try {
            idFeriado = factory.getParametrosDAO().buscarParametro( _claveParametro, _idBancoProceso );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return idFeriado;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<ListaDistribucionBO> obtenerListasDistribucion() {
        List<ListaDistribucionBO> lista = null;
        try {
            lista = factory.getListasDistribucionDAO().queryListaDistribucion();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void agregarListaDistribucion( String _origenLista, String _nombreLista ) {
        try {
            factory.getListasDistribucionDAO().agregarListaDistribucion( _origenLista, _nombreLista );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void eliminarListaDistribucion( Long _idLista ) {
        try {
            factory.getListasDistribucionDAO().eliminarListaDistribucion( _idLista );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void modificarListaDistribucion( Long idLista, String _origenLista, String _nombreLista ) {
        try {
            factory.getListasDistribucionDAO().modificarListaDistribucion( idLista, _origenLista, _nombreLista );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public Long buscarListaDistribucion( String _origenLista ) {
        Long idLista = null;
        try {
            idLista = factory.getListasDistribucionDAO().buscarListaDistribucion( _origenLista );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return idLista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<OrigenBO> obtenerOrigen() {
        List<OrigenBO> lista = null;
        try {
            lista = factory.getOrigenesDAO().queryOrigenes();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<DetalleGastoBO> obtenerDetalleGastoNomina( Long _idNomina ) {
        List<DetalleGastoBO> detalle = new ArrayList<DetalleGastoBO>();
        try {
            detalle = factory.getGastosNominaDAO().queryDetalleGastosbyNomina( _idNomina );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return detalle;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<ComisionBO> obtenerComisionesByBancoProceso( Long _idBancoProceso ) {
        List<ComisionBO> comisiones = null;
        try {
            comisiones = factory.getComisionesDAO().queryComisionesByBancoProceso( _idBancoProceso );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return comisiones;
    }
    
    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void modificarComision( Long _idComision, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos, Date _fechaInicioVigencia, Date _fechaFinVigencia ) {
        try {
            factory.getComisionesDAO().updateComision( _idComision, _montoMismoBanco, _montoOtrosBancos, _fechaInicioVigencia, _fechaFinVigencia );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void eliminarComisionById( Long _idComision ) {
        try {
            factory.getComisionesDAO().eliminarComision( _idComision );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void agregarComision( Long _idBancoProceso, BigDecimal _montoMismoBanco, BigDecimal _montoOtrosBancos, Date _fechaInicioVigencia, Date _fechaFinVigencia ) {
        try {
            factory.getComisionesDAO().agregarComision( _idBancoProceso, _montoMismoBanco, _montoOtrosBancos, _fechaInicioVigencia, _fechaFinVigencia );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<BancoProcesoBO> obtenerBancosProceso() {
        List<BancoProcesoBO> lista = null;
        try {
            lista = factory.getBancosProcesoDAO().queryBancosProceso();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public ComisionBO obtenerComisionById( Long _idComision ) {
        ComisionBO comision = null;
        try {
            comision = factory.getComisionesDAO().queryComisionById( _idComision );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return comision;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void agregarParametroBancoProceso( Long _idBancoProceso, String clave, String valor, String descripcion, String _tipoParametro ) {
        try {
            factory.getParametrosDAO().queryCrearParametroBancoProceso( _idBancoProceso, clave, valor, descripcion, _tipoParametro );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<ParametroBO> obtenerParametrosByBancoProceso( Long _idBancoProceso ) {
        List<ParametroBO> parametros = null;
        try {
            parametros = factory.getParametrosDAO().queryParametrosByBancoProceso( _idBancoProceso );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return parametros;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void crearBancoProceso( String _nombre, Long _idBanco ) {
        try {
            factory.getBancosProcesoDAO().queryCrearBancoProceso( _nombre, _idBanco );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }
    
    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void eliminarBancoProceso( Long _idBanco ) {
        try {
            factory.getBancosProcesoDAO().queryEliminarBancoProceso( _idBanco );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public Boolean isBancoProcesoEnUso( Long _idBancoProceso ) {
        Boolean isBancoProcesoEnUso = null;
        try {
            isBancoProcesoEnUso = factory.getBancosProcesoDAO().queryIsBancoProcesoEnUso( _idBancoProceso );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return isBancoProcesoEnUso;
    }
    
    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void agregarCorreo( String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) {
        try {
            factory.getCorreosDAO().queryCrearNuevoCorreo( _nombre, _subject, _mensaje, _tipo, _idEstado );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void modificarCorreo( Long _idCorreo, String _nombre, String _subject, String _mensaje, String _tipo, Long _idEstado ) {
        try {
            factory.getCorreosDAO().queryModificarCorreo( _idCorreo, _nombre, _subject, _mensaje, _tipo, _idEstado );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void cancelarNomina( Long _idNomina, String motivoCancelacion ) {
        EnvioNominasManager helper = new EnvioNominasManager( ctx );
        try {
            helper.cancelarNomina( _idNomina, motivoCancelacion );
        } catch ( EnvioNominasException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<NominaBO> obtenerNominasSinFolio( Date _fechaDesde, Date _fechaHasta, String _loteNomina, Long _tipoNomina, Long _bancoProceso ) {
        List<NominaBO> nominas = new ArrayList<NominaBO>();
        try {
            nominas = factory.getNominaDAO().queryNominasSinFolio( _fechaDesde, _fechaHasta, _loteNomina, _tipoNomina, _bancoProceso );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return nominas;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void registrarFolio( Long _idNomina, String _folioExterno ) {
        EnvioNominasManager helper = new EnvioNominasManager( ctx );
        try {
            helper.registrarFolio( _idNomina, _folioExterno );
        } catch ( EnvioNominasException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public int validaBancoFolio( String banco, Long numeroFolioIngresado ) {
        int irc = 0;
        try {
            irc = factory.getNominaDAO().queryValidaBancoFolio( banco, numeroFolioIngresado );
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return irc;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void extraerNomina( Long _idNomina ) {
        EnvioNominasManager manager = new EnvioNominasManager( ctx );
        try {
            manager.extraerNomina( _idNomina );
        } catch ( EnvioNominasException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public List<ArchivoBO> obtenerArchivosNominas() {
        List<ArchivoBO> lista = null;
        try {
            lista = factory.getArchivosNominasDAO().queryArchivosNominas();
        } catch ( DAOException e ) {
            log.error( "EXCEPCION:[" + e.getMessage() + "]", e );
        }
        return lista;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public InputStream obtenerArchivoNomina( String _nombreArchivo ) {
        InputStream stream = null;
        try {
            stream = factory.getArchivosNominasDAO().queryArchivoByNombre( _nombreArchivo );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return stream;
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void ejecutarCargaProcesoEnvioNomina( CargaNominaInputType _type ) throws EnvioNominasException {
        EnvioNominasManager manager = new EnvioNominasManager( ctx );
        manager.ejecutarCargaProcesoEnvioNomina( _type );
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void ejecutarRendicionCargaProcesoEnvioNomina( RindeNominaInputType _type ) throws EnvioNominasException {
        EnvioNominasManager manager = new EnvioNominasManager( ctx );
        manager.ejecutarRendicionCargaProcesoEnvioNomina( _type );
    }

    @TransactionAttribute( TransactionAttributeType.NOT_SUPPORTED )
    public void ejecutarRendicionPagoProcesoEnvioNomina( RindeNominaInputType _type ) throws EnvioNominasException {
        EnvioNominasManager manager = new EnvioNominasManager( ctx );
        manager.ejecutarRendicionPagoProcesoEnvioNomina( _type );
    }

    public SessionContext getContext() {
        return ctx;
    }
}
