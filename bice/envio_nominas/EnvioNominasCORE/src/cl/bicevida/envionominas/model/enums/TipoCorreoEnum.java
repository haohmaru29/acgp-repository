package cl.bicevida.envionominas.model.enums;

public enum TipoCorreoEnum {
    CAMBIO_ESTADO( 1 ),
    NOTIFICACION( 2 ),
    ;
    private int id;

    TipoCorreoEnum( int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
