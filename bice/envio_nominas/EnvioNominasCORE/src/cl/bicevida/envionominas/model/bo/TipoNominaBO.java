package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

/**
 * Representa un tipo de nomina.
 * 
 * Registro de versiones:
 *      - 1.0 21/10/2010 Giorgio Gortaire (ACGP) : Version inicial.  
 */
public class TipoNominaBO implements Serializable {
    private Long id;
    private String nombre;
    private String descripcion;
    private BancoBO banco;
    private OrigenBO origen;

    public TipoNominaBO() {
    }

    public TipoNominaBO( Long idTipoNomina ) {
        id = idTipoNomina;
    }

    public TipoNominaBO( Long id, String descripcion, String nombre, BancoBO banco, OrigenBO origen ) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.banco = banco;
        this.origen = origen;
    }

    public TipoNominaBO( Long _idTipoNomina, String _nombre, String _descripcion ) {
        this.id = _idTipoNomina;
        this.nombre = _nombre;
        this.descripcion = _descripcion;
    }

    public static TipoNominaBO crear( Long id, String descripcion, String nombre, BancoBO banco, OrigenBO origen ) {
        return new TipoNominaBO( id, descripcion, nombre, banco, origen );
    }

    public static TipoNominaBO crear( Long _idTipoNomina, String _nombre, String _descripcion ) {
        return new TipoNominaBO( _idTipoNomina, _nombre, _descripcion );
    }

    public static TipoNominaBO crear( Long idTipoNomina ) {
        return new TipoNominaBO( idTipoNomina );
    }

    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setBanco( BancoBO banco ) {
        this.banco = banco;
    }

    public BancoBO getBanco() {
        return banco;
    }

    public void setOrigen( OrigenBO origen ) {
        this.origen = origen;
    }

    public OrigenBO getOrigen() {
        return origen;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static TipoNominaBO crear( String nombre ) {
        return new TipoNominaBO( nombre );
    }

    public TipoNominaBO( String nombre ) {
        this.nombre = nombre;
    }

    public String toString() {
        return "TipoNominaBO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", banco=" + banco + ", origen=" + origen + "}";
    }
}
