// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.0.0, build 060119.1546.05277)

package cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types;


public class RetListaRegion implements java.io.Serializable {
    protected cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RegionModel[] listaRegion;
    protected java.lang.String mensajeRetorno;
    protected java.lang.String codigoRetorno;
    
    public RetListaRegion() {
    }
    
    public cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RegionModel[] getListaRegion() {
        return listaRegion;
    }
    
    public void setListaRegion(cl.bicevida.core.model.services.ws.types.actualizadatos.fasetres.ws.param.types.RegionModel[] listaRegion) {
        this.listaRegion = listaRegion;
    }
    
    public java.lang.String getMensajeRetorno() {
        return mensajeRetorno;
    }
    
    public void setMensajeRetorno(java.lang.String mensajeRetorno) {
        this.mensajeRetorno = mensajeRetorno;
    }
    
    public java.lang.String getCodigoRetorno() {
        return codigoRetorno;
    }
    
    public void setCodigoRetorno(java.lang.String codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }
}
