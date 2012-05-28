/*    */ package cl.intranet.service;
/*    */ 
/*    */ import cl.acgp.commons.mvc.service.jpa.AbstractServiceManager;
/*    */ import cl.intranet.domain.Usuario;
/*    */ import cl.intranet.repository.UsuarioController;
/*    */ import java.util.List;
/*    */ 
/*    */ public class UsuarioManager extends AbstractServiceManager<Usuario>
/*    */ {
/*    */   public Usuario login(String nickname, String clave)
/*    */   {
/* 12 */     return ((UsuarioController)this.jpaController).login(nickname, clave);
/*    */   }
/*    */ 
/*    */   public List<?> findByParams(String fecha, int idUsuario, int start, int limit) {
/* 16 */     return ((UsuarioController)this.jpaController).findByParams(fecha, idUsuario, start, limit);
/*    */   }
/*    */ 
/*    */   public int countAll() {
/* 20 */     return ((UsuarioController)this.jpaController).countAll();
/*    */   }
/*    */ }

/* Location:           E:\Proyectos\ACGP\intranet\WEB-INF\classes\
 * Qualified Name:     cl.intranet.service.UsuarioManager
 * JD-Core Version:    0.6.0
 */