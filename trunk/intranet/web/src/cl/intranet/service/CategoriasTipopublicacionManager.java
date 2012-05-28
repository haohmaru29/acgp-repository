/*    */ package cl.intranet.service;
/*    */ 
/*    */ import cl.acgp.commons.mvc.service.jpa.AbstractServiceManager;
/*    */ import cl.intranet.domain.CategoriasTipopublicacion;
/*    */ import cl.intranet.repository.CategoriasTipopublicacionController;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CategoriasTipopublicacionManager extends AbstractServiceManager<CategoriasTipopublicacion>
/*    */ {
/*    */   public List<?> findByTipoPublicacion(int tipoPublicacion)
/*    */   {
/* 12 */     return ((CategoriasTipopublicacionController)this.jpaController).findByTipoPublicacion(tipoPublicacion);
/*    */   }
/*    */ }

/* Location:           E:\Proyectos\ACGP\intranet\WEB-INF\classes\
 * Qualified Name:     cl.intranet.service.CategoriasTipopublicacionManager
 * JD-Core Version:    0.6.0
 */