package cl.bicevida.envionominas.model.bo.archivo;

import java.io.Serializable;

import java.util.List;

public class NominaErrores implements Serializable {

    private List<RegistroError> registroErrores;

    public void setRegistroErrores(List<RegistroError> registroErrores) {
        this.registroErrores = registroErrores;
    }

    public List<RegistroError> getRegistroErrores() {
        return registroErrores;
    }
}
