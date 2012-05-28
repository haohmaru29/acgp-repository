/*    */ package cl.intranet.web;
/*    */ 
/*    */ import cl.acgp.commons.mvc.service.jpa.ServiceManager;
/*    */ import cl.intranet.domain.Menu;
/*    */ import cl.intranet.domain.Perfil;
/*    */ import cl.intranet.domain.PerfilMenu;
/*    */ import cl.intranet.domain.Usuario;
/*    */ import cl.intranet.service.MenuManager;
/*    */ import cl.intranet.service.UsuarioManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"security"})
/*    */ public class security
/*    */ {
/*    */   private ModelAndView model;
/* 27 */   private MenuManager menuManager = (MenuManager)ServiceManager.factory("MenuManager");
/* 28 */   private UsuarioManager usuarioManager = (UsuarioManager)ServiceManager.factory("UsuarioManager");
/*    */ 
/* 32 */   @RequestMapping(value={"login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public ModelAndView login(HttpServletRequest request, HttpServletResponse response) { this.model = new ModelAndView();
/* 33 */     String nickname = request.getParameter("nickname");
/* 34 */     String clave = request.getParameter("clave");
/*    */     try {
/* 36 */       Usuario usuario = this.usuarioManager.login(nickname, clave);
/* 37 */       if (usuario != null) {
/* 38 */         HttpSession session = request.getSession(true);
/* 39 */         session.setAttribute("usuario", usuario);
/* 40 */         this.model.addObject("message", "1");
/* 41 */         this.model.setViewName("global/message");
/*    */       } else {
/* 43 */         this.model.addObject("message", "Datos ingresados invalidos, favor vuelva a intentar");
/* 44 */         this.model.setViewName("global/message");
/*    */       }
/*    */     } catch (Exception e) {
/* 47 */       this.model.addObject("message", e);
/* 48 */       this.model.setViewName("global/infoMessage");
/*    */     }
/*    */ 
/* 51 */     return this.model; }
/*    */ 
/*    */   @RequestMapping(value={"closeSession"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*    */   public ModelAndView closeSession(HttpServletRequest request, HttpServletResponse response) {
/* 56 */     this.model = new ModelAndView();
/* 57 */     request.getSession(false).invalidate();
/* 58 */     this.model.addObject("message", Integer.valueOf(1));
/* 59 */     this.model.setViewName("global/message");
/*    */ 
/* 61 */     return this.model;
/*    */   }
/*    */   @RequestMapping(value={"menu"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public ModelAndView menu(HttpServletRequest request, HttpServletResponse response) {
/* 66 */     this.model = new ModelAndView();
/* 67 */     HttpSession session = request.getSession(false);
/* 68 */     Usuario usuario = (Usuario)session.getAttribute("usuario");
/* 69 */     List menus = new ArrayList();
/* 70 */     if (usuario != null) {
/* 71 */       for (PerfilMenu perfil : usuario.getPerfil().getPerfilMenus()) {
/* 72 */         menus.add(perfil.getMenu());
/*    */       }
/*    */     }
/*    */     else {
/* 76 */       for (Menu men : this.menuManager.findMenu()) {
/* 77 */         int x = 0;
/* 78 */         for (PerfilMenu perfil : men.getPerfilMenus()) {
/* 79 */           if (perfil.getPerfil().getIdperfil() != 1) {
/* 80 */             x = 1;
/* 81 */             break;
/*    */           }
/*    */         }
/* 84 */         if (x == 1)
/* 85 */           menus.add(men);
/*    */       }
/*    */     }
/* 88 */     this.model.addObject("menu", menus);
/* 89 */     this.model.addObject("child", this.menuManager.findChilds());
/*    */ 
/* 91 */     this.model.setViewName("menu/menu");
/*    */ 
/* 93 */     return this.model;
/*    */   }
/*    */ }

/* Location:           E:\Proyectos\ACGP\intranet\WEB-INF\classes\
 * Qualified Name:     cl.intranet.web.security
 * JD-Core Version:    0.6.0
 */