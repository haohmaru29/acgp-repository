package cl.bicevida.envionominas.model.utils;

import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.NominaBO;
import cl.bicevida.envionominas.model.bo.ParametroBO;
import cl.bicevida.envionominas.model.bo.ProcesoEnvioBO;
import cl.bicevida.envionominas.model.bo.RegistroNominaBO;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CampoVariableType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CamposVariables;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.CargaNominaInputType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.DatosCabeceraType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.NominaType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RegistroNominaType;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RegistrosNomina;
import cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas.RindeNominaInputType;

public class TraductorWSTypes {
    public TraductorWSTypes() {
    }

    /**
     * 
     * @param bancoProceso
     * @return
     */
    public DatosCabeceraType generarCabeceraMensaje( BancoProcesoBO bancoProceso) {
        DatosCabeceraType datosCabecera = new DatosCabeceraType();
        CamposVariables camposVariables = new CamposVariables();
        CampoVariableType[] listCamposVariables = new CampoVariableType[ bancoProceso.getParametrosServicio().size() ];
        int count_parametros = 0;
        for ( ParametroBO parametro: bancoProceso.getParametrosServicio() ) {
            CampoVariableType campoVariable = new CampoVariableType();
            campoVariable.setNombre( parametro.getClaveParametro() );
            campoVariable.setValor( parametro.getValorParametro() );
            listCamposVariables[ count_parametros ] = campoVariable;
            count_parametros++;
        }
        camposVariables.setCampoVariable(listCamposVariables);
        datosCabecera.setBancoProceso( bancoProceso.getId().toString() );
        datosCabecera.setFechaActual( new SimpleDate().toCalendar() );
        datosCabecera.setCamposVariables( camposVariables );
        return datosCabecera;
    }

    /**
     * 
     * @param proceso
     * @return
     */
    public RegistrosNomina generarRegistrosMensaje( ProcesoEnvioBO proceso ) {
        RegistrosNomina registros = new RegistrosNomina();
        RegistroNominaType[] listRegistros = new RegistroNominaType[ proceso.getRegistros().size() ];
        int count_registros = 0;
        for ( RegistroNominaBO registroProceso: proceso.getRegistros() ) {
            RegistroNominaType registro = new RegistroNominaType();
            registro.setIdRegistro( registroProceso.getId().toString() );
            registro.setBanco( registroProceso.getBanco().getCodigoMatriz() );
            registro.setCuenta( registroProceso.getCuenta() );
            registro.setDigitoVerificador( registroProceso.getDv() );
            registro.setEstadoPago( null );
            registro.setFechaMovimiento( ( new SimpleDate( proceso.getNomina().getFechaPago() ) ).toCalendar() );
            registro.setFormaPago( proceso.getNomina().getTipoPago().getId().toString() );
            registro.setGlosa( null );
            registro.setMonto( registroProceso.getMonto().toString() );
            registro.setNombre( registroProceso.getNombre() );
            registro.setNumeroDocumentoPago( null );
            registro.setOficinaDestino( registroProceso.getOficinaDestino() );
            registro.setRut( registroProceso.getRut().toString() );
            registro.setTipoDocumentoPago( "FACTURA" );
            registro.setTipoCuenta( registroProceso.getTipoCuenta().getId().toString() );
            listRegistros[ count_registros ] = registro;
            count_registros++;
        }
        registros.setRegistro( listRegistros );
        return registros;
    }

    /**
     * Genera un objeto del tipo RindeNominaInputType a partir de un proceso para 
     * realizar la rendicion de carga.
     * @param _proceso
     * @return
     */
    public RindeNominaInputType generarEstructuraRendicion( ProcesoEnvioBO _proceso, BancoProcesoBO _bancoProceso) {
        RindeNominaInputType type = new RindeNominaInputType();
        DatosCabeceraType datosCabecera;
        datosCabecera = generarCabeceraMensaje( _bancoProceso);
        type.setDatosCabecera( datosCabecera );
        type.setFecha( new SimpleDate( _proceso.getFechaEnvio() ).toCalendar() );
        type.setIdNomina( _proceso.getNomina().getId().toString() );
        type.setIdProceso( _proceso.getId().toString() );
        type.setTipoRendicion("CARGA");
        return type;
    }

    /**
     * Genera un objeto del tipo RindeNominaInputType a partir de una nomina para
     * la consultar su rendicion de pago.
     * @param _nomina
     * @return
     */
    public RindeNominaInputType generarEstructuraRendicion( NominaBO _nomina, BancoProcesoBO _bancoProceso) {
        RindeNominaInputType type = new RindeNominaInputType();
        DatosCabeceraType datosCabecera;
        datosCabecera = generarCabeceraMensaje( _bancoProceso);
        type.setDatosCabecera( datosCabecera );
        type.setFecha( new SimpleDate( _nomina.getFechaPago() ).toCalendar() );
        type.setIdNomina( _nomina.getId().toString() );
        type.setIdProceso( null );
        type.setTipoRendicion("PAGO");
        return type;
    }
    
    /**
     * Genera un objeto del tipo CargaNominaInputType a partir de un proceso.
     * @param _proceso Proceso de envio, debe contener una nomina cargada.
     * @return La estructura de datos definida para interoperabilidad con el 
     * servicio web de envio de nominas.
     */
    public CargaNominaInputType generarEstructuraCarga( ProcesoEnvioBO _proceso, BancoProcesoBO _bancoProceso) {
        CargaNominaInputType type = new CargaNominaInputType();
        DatosCabeceraType datosCabecera;
        datosCabecera = generarCabeceraMensaje( _bancoProceso);
        RegistrosNomina registros = generarRegistrosMensaje( _proceso );
        NominaType nomina = new NominaType();
        nomina.setIdNomina( _proceso.getNomina().getId().toString() );
        nomina.setIdProceso( _proceso.getId().toString() );
        nomina.setFolioProceso( null );
        nomina.setFechaPago( ( new SimpleDate( _proceso.getNomina().getFechaPago() ) ).toCalendar() );
        nomina.setRegistrosNomina( registros );
        type.setDatosCabecera( datosCabecera );
        type.setNomina( nomina );
        return type;
    }
}
