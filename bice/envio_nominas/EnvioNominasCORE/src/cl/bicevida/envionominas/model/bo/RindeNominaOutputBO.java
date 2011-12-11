package cl.bicevida.envionominas.model.bo;

import java.util.ArrayList;

public class RindeNominaOutputBO implements java.io.Serializable {
    
    protected ArrayList<cl.bicevida.envionominas.model.bo.TransaccionPagoBO> transaccionPagos;

    public void setTransaccionPagos(ArrayList<cl.bicevida.envionominas.model.bo.TransaccionPagoBO> transaccionPagos) {
        this.transaccionPagos = transaccionPagos;
    }

    public ArrayList<cl.bicevida.envionominas.model.bo.TransaccionPagoBO> getTransaccionPagos() {
        return transaccionPagos;
    }
}
