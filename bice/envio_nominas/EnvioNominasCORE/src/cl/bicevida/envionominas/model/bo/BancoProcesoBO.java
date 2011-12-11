package cl.bicevida.envionominas.model.bo;

import cl.bicevida.envionominas.model.enums.TipoParametroEnum;
import cl.bicevida.envionominas.model.exceptions.ParametroNoEncontradoException;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoProcesoBO implements Serializable {
    private Long id;
    private String nombre;
    private BancoBO banco;
    private List<ComisionBO> comisiones;
    private List<ParametroBO> parametros;
    private Map<String, String> mapParametros;

    public BancoProcesoBO() {
    }

    public BancoProcesoBO( Long id ) {
        this.id = id;
    }

    public BancoProcesoBO( Long _id, BancoBO _banco ) {
        this.id = _id;
        this.banco = _banco;
    }

    public BancoProcesoBO( Long id, String nombre, BancoBO banco ) {
        this.id = id;
        this.nombre = nombre;
        this.banco = banco;
    }

    public static BancoProcesoBO crear( Long id, BancoBO banco ) {
        return new BancoProcesoBO( id, banco );
    }

    public static BancoProcesoBO crear( Long id ) {
        return new BancoProcesoBO( id );
    }

    public static BancoProcesoBO crear( Long id, String nombre, BancoBO banco ) {
        return new BancoProcesoBO( id, nombre, banco );
    }

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public BancoBO getBanco() {
        return banco;
    }

    public void setBanco( BancoBO newBanco ) {
        banco = newBanco;
    }

    public List<ComisionBO> getComisiones() {
        return comisiones;
    }

    public void setComisiones( List<ComisionBO> newComisiones ) {
        comisiones = newComisiones;
    }

    public void setParametros( List<ParametroBO> parametros ) {
        this.parametros = parametros;
    }

    public List<ParametroBO> getParametros() {
        return parametros;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getParametro( String nombre ) throws ParametroNoEncontradoException {
        if ( mapParametros == null ) {
            mapParametros = new HashMap<String, String>();
            if ( parametros != null ) {
                for ( ParametroBO parametro: parametros ) {
                    mapParametros.put( parametro.getClaveParametro(), parametro.getValorParametro() );
                }
            }
        }
        if ( !mapParametros.containsKey( nombre ) ) {
            throw new ParametroNoEncontradoException( "El parametro [" + nombre + "] no existe." );
        }
        return mapParametros.get( nombre );
    }

    public List<ParametroBO> getParametrosServicio() {
        List<ParametroBO> parametrosServicio = null;
        if ( parametros != null ) {
            for ( ParametroBO parametro: parametros ) {
                if ( parametro.getTipoParametro() != null && parametro.getTipoParametro().equals( TipoParametroEnum.SERVICIO.toString() ) ) {
                    if ( parametrosServicio == null ) {
                        parametrosServicio = new ArrayList<ParametroBO>();
                    }
                    parametrosServicio.add( parametro );
                }
            }
        }
        return parametrosServicio;
    }

    public static BancoProcesoBO crear( BancoBO _bancoBO ) {
        return new BancoProcesoBO( _bancoBO );
    }

    public BancoProcesoBO( BancoBO _bancoBO ) {
        this.banco = _bancoBO;
    }

    public static BancoProcesoBO crear( Long _idBancoProceso, String _nombreBancoProceso ) {
        return new BancoProcesoBO( _idBancoProceso, _nombreBancoProceso );
    }

    public BancoProcesoBO( Long _idBancoProceso, String _nombreBancoProceso ) {
        this.id = _idBancoProceso;
        this.nombre = _nombreBancoProceso;
    }

    public String toString() {
        return "BancoProcesoBO{" + "id=" + id + ", nombre=" + nombre + ", banco=" + banco + ", comisiones=" + comisiones + ", parametros=" + parametros + ", mapParametros=" + mapParametros + "}";
    }
}
