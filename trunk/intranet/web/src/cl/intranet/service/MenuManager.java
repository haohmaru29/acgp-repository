/*    */ package cl.intranet.service;
/*    */ 
/*    */ import cl.acgp.commons.mvc.service.jpa.AbstractServiceManager;
/*    */ import cl.intranet.domain.Menu;
/*    */ import cl.intranet.repository.MenuController;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MenuManager extends AbstractServiceManager<Menu>
/*    */ {
/*    */   public List<Menu> findMenu()
/*    */   {
/* 12 */     return ((MenuController)this.jpaController).findMenu();
/*    */   }
/*    */ 
/*    */   public List<?> findChilds() {
/* 16 */     return ((MenuController)this.jpaController).findChilds();
/*    */   }
/*    */ 
/*    */   public List<?> findMenuByPerfil(int idPerfil) {
/* 20 */     return ((MenuController)this.jpaController).findMenuByPerfil(idPerfil);
/*    */   }
/*    */ }

/* Location:           E:\Proyectos\ACGP\intranet\WEB-INF\classes\
 * Qualified Name:     cl.intranet.service.MenuManager
 * JD-Core Version:    0.6.0
 */