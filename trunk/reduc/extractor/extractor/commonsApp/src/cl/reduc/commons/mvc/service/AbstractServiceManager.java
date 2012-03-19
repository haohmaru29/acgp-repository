package cl.reduc.commons.mvc.service;
import cl.reduc.commons.Application;
import cl.reduc.commons.mvc.controller.AbstractJpaController;
import cl.reduc.commons.mvc.controller.JpaController;

import java.util.List;

public abstract class AbstractServiceManager {

    protected AbstractJpaController jpaController;
    protected List objectResponse;
    protected List mapResponse;
    public static String entityClass;

    public AbstractServiceManager() {
        String controllerName = Application.getInstance().repository().concat(entityClass);
        jpaController = (AbstractJpaController) JpaController.factory( controllerName );
    }
}