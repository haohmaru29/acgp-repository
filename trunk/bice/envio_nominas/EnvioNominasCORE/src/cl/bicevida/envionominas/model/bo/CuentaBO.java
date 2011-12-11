package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

/**
 * Representa una cuenta.
 * 
 * Registro de versiones:
 *      - 1.0 21/10/2010 Giorgio Gortaire (ACGP) : Version inicial.  
 */
public class CuentaBO implements Serializable {
    private Long id;
    private Long nombre;
    private String numeroCuenta;
    private EmpresaBO empresa;
    private BancoBO banco;
    private MonedaBO moneda;
    private TipoCuentaBO tipoCuenta;

    public void setNumeroCuenta( String numeroCuenta ) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setEmpresa( EmpresaBO empresa ) {
        this.empresa = empresa;
    }

    public EmpresaBO getEmpresa() {
        return empresa;
    }

    public void setBanco( BancoBO banco ) {
        this.banco = banco;
    }

    public BancoBO getBanco() {
        return banco;
    }

    public void setMoneda( MonedaBO moneda ) {
        this.moneda = moneda;
    }

    public MonedaBO getMoneda() {
        return moneda;
    }

    public void setTipoCuenta( TipoCuentaBO tipoCuenta ) {
        this.tipoCuenta = tipoCuenta;
    }

    public TipoCuentaBO getTipoCuenta() {
        return tipoCuenta;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre( Long nombre ) {
        this.nombre = nombre;
    }

    public Long getNombre() {
        return nombre;
    }

    public String toString() {
        return "CuentaBO{" + "id=" + id + ", nombre=" + nombre + ", numeroCuenta=" + numeroCuenta + ", empresa=" + empresa + ", banco=" + banco + ", moneda=" + moneda + ", tipoCuenta=" + tipoCuenta + "}";
    }
}
