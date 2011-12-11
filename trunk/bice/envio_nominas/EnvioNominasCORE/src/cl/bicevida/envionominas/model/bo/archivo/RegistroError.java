package cl.bicevida.envionominas.model.bo.archivo;

import java.io.Serializable;

public class RegistroError implements Serializable {
    
    private String registro;
    private String servicio;
    private String numNomina;
    private String numCContable;
    private String monto;
    private String ctaTitular;
    private String rutTitular;
    private String campoErroneo;
    private String valorCampo;
    private String glosaError;

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setNumNomina(String numNomina) {
        this.numNomina = numNomina;
    }

    public String getNumNomina() {
        return numNomina;
    }

    public void setNumCContable(String numCContable) {
        this.numCContable = numCContable;
    }

    public String getNumCContable() {
        return numCContable;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMonto() {
        return monto;
    }

    public void setCtaTitular(String ctaTitular) {
        this.ctaTitular = ctaTitular;
    }

    public String getCtaTitular() {
        return ctaTitular;
    }

    public void setRutTitular(String rutTitular) {
        this.rutTitular = rutTitular;
    }

    public String getRutTitular() {
        return rutTitular;
    }

    public void setCampoErroneo(String campoErroneo) {
        this.campoErroneo = campoErroneo;
    }

    public String getCampoErroneo() {
        return campoErroneo;
    }

    public void setValorCampo(String valorCampo) {
        this.valorCampo = valorCampo;
    }

    public String getValorCampo() {
        return valorCampo;
    }

    public void setGlosaError(String glosaError) {
        this.glosaError = glosaError;
    }

    public String getGlosaError() {
        return glosaError;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getRegistro() {
        return registro;
    }
}
