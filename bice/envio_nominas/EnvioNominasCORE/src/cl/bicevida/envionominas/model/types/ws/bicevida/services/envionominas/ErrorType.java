// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (10.1.3.5.0, build 090727.2000.36696)

package cl.bicevida.envionominas.model.types.ws.bicevida.services.envionominas;


public class ErrorType extends Exception {
    private java.lang.String codigo;
    private java.lang.String descripcion;
    
    
    public ErrorType() {
    }
    
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }
    
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }
    
    public java.lang.String getCodigo() {
        return codigo;
    }
    
    public java.lang.String getDescripcion() {
        return descripcion;
    }
    
}
