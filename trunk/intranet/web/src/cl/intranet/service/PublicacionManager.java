/*    */ package cl.intranet.service;
/*    */ 
/*    */ import cl.acgp.commons.mvc.service.jpa.AbstractServiceManager;
/*    */ import cl.intranet.domain.Publicacion;
/*    */ import cl.intranet.repository.PublicacionController;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PublicacionManager extends AbstractServiceManager<Publicacion>
/*    */ {
/*    */   public List<?> findPublicacionTipo(int idTipoPublicacion, int index, int limit)
/*    */   {
/* 13 */     return ((PublicacionController)this.jpaController).findPublicacionTipo(idTipoPublicacion, index, limit);
/*    */   }
/*    */ 
/*    */   public List<?> findPublicacionByParams(int idTipoPublicacion, Date fecha, int idCategoria) {
/* 17 */     return ((PublicacionController)this.jpaController).findPublicacionByParams(idTipoPublicacion, fecha, idCategoria);
/*    */   }
/*    */ 
/*    */   public Publicacion findByTemporal(int idTemporal) {
/* 21 */     return ((PublicacionController)this.jpaController).findByTemporal(idTemporal);
/*    */   }
/*    */ 
/*    */   public int countAll() {
/* 25 */     return ((PublicacionController)this.jpaController).countAll();
/*    */   }
/*    */ 
/*    */   public List<?> findByUser(int idUsuario, int idTipoPublicacion, String fecha) {
/* 29 */     return ((PublicacionController)this.jpaController).findByUser(idUsuario, idTipoPublicacion, fecha);
/*    */   }
/*    */ 
/*    */   public List<?> findTodas(int idTipoPublicacion, String fecha) {
/* 33 */     return ((PublicacionController)this.jpaController).findTodas(idTipoPublicacion, fecha);
/*    */   }
/*    */ }

/* Location:           E:\Proyectos\ACGP\intranet\WEB-INF\classes\
 * Qualified Name:     cl.intranet.service.PublicacionManager
 * JD-Core Version:    0.6.0
 */