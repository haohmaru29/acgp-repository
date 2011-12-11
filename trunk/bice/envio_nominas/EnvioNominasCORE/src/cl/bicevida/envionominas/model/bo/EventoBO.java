package cl.bicevida.envionominas.model.bo;

import cl.bicevida.envionominas.model.enums.TipoEventoEnum;

import java.io.Serializable;

public class EventoBO implements Serializable {
    private TipoEventoEnum tarea;
    private Long id;

    public EventoBO() {
    }

    public EventoBO( TipoEventoEnum _tarea ) {
        this.tarea = _tarea;
    }

    public void setTarea( TipoEventoEnum tarea ) {
        this.tarea = tarea;
    }

    public TipoEventoEnum getTarea() {
        return tarea;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String toString() {
        return "EventoBO{" + "tarea=" + tarea + ", id=" + id + "}";
    }
}
