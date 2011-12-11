package cl.bicevida.envionominas.view.jsf.mb;

import cl.bicevida.core.view.jsf.mb.MbError;
import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;
import cl.bicevida.envionominas.model.bo.BancoBO;
import cl.bicevida.envionominas.model.bo.BancoProcesoBO;
import cl.bicevida.envionominas.model.bo.EstadoNominaBO;
import cl.bicevida.envionominas.model.bo.EstadoTransaccionBO;
import cl.bicevida.envionominas.model.bo.OrigenBO;
import cl.bicevida.envionominas.model.bo.TipoCuentaBO;
import cl.bicevida.envionominas.model.bo.TipoFeriadoBO;
import cl.bicevida.envionominas.model.bo.TipoNominaBO;
import cl.bicevida.envionominas.model.enums.TipoFeriadoEnum;
import cl.bicevida.envionominas.model.enums.TipoParametroEnum;
import cl.bicevida.envionominas.view.facade.EnvioNominasBusinessFacade;
import cl.bicevida.envionominas.view.facade.EnvioNominasBusinessFacadeImpl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public abstract class AbstractBacking {
    protected EnvioNominasBusinessFacade facade;

    /**
     * Instancia el facade de acceso a la logica de la aplicacion.
     * @return Instancia del facade.
     */
    public EnvioNominasBusinessFacade getFacade() {
        if ( facade == null ) {
            facade = new EnvioNominasBusinessFacadeImpl();
        }
        return facade;
    }

    /**
     * 
     * @return
     */
    public List<SelectItem> getItemsTiposNomina() {
        List<TipoNominaBO> tiposNomina = null;
        List<SelectItem> items = null;
        try {
            tiposNomina = getFacade().obtenerTiposNomina();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( AbstractBacking.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        for ( TipoNominaBO bo: tiposNomina ) {
            if ( items == null ) {
                items = new ArrayList<SelectItem>();
            }
            SelectItem item = new SelectItem();
            item.setLabel( bo.getNombre() + " - " + ( bo.getDescripcion() == null ? "" : bo.getDescripcion() ) );
            item.setValue( bo.getId().toString() );
            items.add( item );
        }
        return items;
    }

    /**
     * 
     * @return
     */
    public List<SelectItem> getItemsBancosPago() {
        List<BancoBO> bancosPago = null;
        List<SelectItem> items = null;
        try {
            bancosPago = getFacade().obtenerBancosPago();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( AbstractBacking.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        for ( BancoBO dto: bancosPago ) {
            if ( items == null ) {
                items = new ArrayList<SelectItem>();
            }
            SelectItem item = new SelectItem();
            item.setLabel( dto.getNombre() );
            item.setValue( dto.getId().toString() );
            items.add( item );
        }
        return items;
    }
    
    public List<SelectItem> getItemsBancosProceso(){
        List<BancoProcesoBO> bancosProceso = null;
        List<SelectItem> items = null;
        try {
            bancosProceso = getFacade().obtenerBancosProceso();
        } catch (Exception e) {
             MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
             mbError.showError( AbstractBacking.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        for ( BancoProcesoBO dto: bancosProceso ) {
            if ( items == null ) {
                items = new ArrayList<SelectItem>();
            }
            SelectItem item = new SelectItem();
            item.setLabel( dto.getNombre() );
            item.setValue( dto.getId().toString() );
            items.add( item );
        }
        return items;
    }

    public List<SelectItem> getItemsEstadosTransaccion() {
        List<EstadoTransaccionBO> estadosTransaccion = null;
        List<SelectItem> items = null;
        try {
            estadosTransaccion = getFacade().obtenerEstadosTransaccion();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( AbstractBacking.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        for ( EstadoTransaccionBO dto: estadosTransaccion ) {
            if ( items == null ) {
                items = new ArrayList<SelectItem>();
            }
            SelectItem item = new SelectItem();
            item.setLabel( dto.getNombre() );
            item.setValue( dto.getId().toString() );
            items.add( item );
        }
        return items;
    }

    public List<SelectItem> getItemsTiposCuenta() {
        List<TipoCuentaBO> tiposCuenta = null;
        List<SelectItem> items = null;
        try {
            tiposCuenta = getFacade().obtenerTiposCuenta();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( AbstractBacking.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        for ( TipoCuentaBO dto: tiposCuenta ) {
            if ( items == null ) {
                items = new ArrayList<SelectItem>();
            }
            SelectItem item = new SelectItem();
            item.setLabel( dto.getNombre() );
            item.setValue( dto.getId().toString() );
            items.add( item );
        }
        return items;
    }

    public List<SelectItem> getItemsEstadosNomina() {
        List<EstadoNominaBO> estadosNomina = null;
        List<SelectItem> items = null;
        try {
            estadosNomina = getFacade().obtenerEstadosNomina();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( AbstractBacking.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        for ( EstadoNominaBO dto: estadosNomina ) {
            if ( items == null ) {
                items = new ArrayList<SelectItem>();
            }
            SelectItem item = new SelectItem();
            item.setLabel( dto.getNombre() );
            item.setValue( dto.getId().toString() );
            items.add( item );
        }
        return items;
    }

    public List<SelectItem> getItemsTiposFeriados() {
        List<SelectItem> items = null;
        for ( TipoFeriadoEnum tipo: TipoFeriadoEnum.values() ) {
            if ( items == null ) {
                items = new ArrayList<SelectItem>();
            }
            SelectItem item = new SelectItem();
            item.setLabel( tipo.name() );
            item.setValue( tipo.name() );
            items.add( item );
        }
        return items;
    }

    public List<SelectItem> getItemsOrigen() {
        List<OrigenBO> origenes = null;
        List<SelectItem> items = null;
        try {
            origenes = getFacade().obtenerOrigen();
        } catch ( Exception e ) {
            MbError mbError = ( MbError ) JsfUtils.getValue( "#{MbError}" );
            mbError.showError( AbstractBacking.class.getName(), BvWebLog.LEVEL_ERROR, null, e.getMessage(), null, null, "bienvenida" );
        }
        for ( OrigenBO dto: origenes ) {
            if ( items == null ) {
                items = new ArrayList<SelectItem>();
            }
            SelectItem item = new SelectItem();
            item.setLabel( dto.getNombre() );
            item.setValue( dto.getCodigo().toString() );
            items.add( item );
        }
        return items;
    }

    public List<SelectItem> getItemsTipoParametro() {
        List<SelectItem> items = null;
        for ( TipoParametroEnum tipo: TipoParametroEnum.values() ) {
            if ( items == null ) {
                items = new ArrayList<SelectItem>();
            }
            SelectItem item = new SelectItem();
            item.setLabel( tipo.name() );
            item.setValue( tipo.name() );
            items.add( item );
        }
        return items;
    }
}
