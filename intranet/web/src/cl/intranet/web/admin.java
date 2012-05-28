/*    */ package cl.intranet.web;
/*    */ 
/*    */ import cl.acgp.commons.mvc.service.jpa.AbstractServiceManager;
/*    */ import cl.acgp.commons.mvc.service.jpa.ServiceManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"admin"})
/*    */ public class admin
/*    */ {
/* 23 */   private static final Logger logger = Logger.getLogger(admin.class);
/*    */   private ModelAndView model;
/*    */ 
/*    */   @RequestMapping(value={"save"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 29 */     this.model = new ModelAndView();
/*    */     try {
/* 31 */       String controller = request.getParameter("mngr");
/* 32 */       Map params = new HashMap(request.getParameterMap());
/* 33 */       params.remove("mngr");
/* 34 */       AbstractServiceManager admin = ServiceManager.factory(controller + "Manager");
/* 35 */       admin.save(params);
/* 36 */       this.model.addObject("message", "1");
/* 37 */       this.model.setViewName("global/message");
/*    */     } catch (Exception e) {
/* 39 */       this.model.addObject("message", "Problemas: " + e);
/* 40 */       this.model.setViewName("global/message");
/* 41 */       logger.error("[Intranet]", e);
/*    */     }
/*    */ 
/* 44 */     return this.model;
/*    */   }
/*    */ 
/*    */   public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 49 */     this.model = new ModelAndView();
/*    */     try {
/* 51 */       String id = request.getParameter("id");
/* 52 */       String controller = request.getParameter("mngr");
/* 53 */       Map params = new HashMap(request.getParameterMap());
/* 54 */       params.remove("mngr");
/* 55 */       AbstractServiceManager admin = ServiceManager.factory(controller + "Manager");
/* 56 */       admin.deleteById(id);
/* 57 */       this.model.addObject("message", "1");
/* 58 */       this.model.setViewName("global/message");
/*    */     } catch (Exception e) {
/* 60 */       this.model.addObject("message", e);
/* 61 */       this.model.setViewName("global/message");
/* 62 */       logger.error("[Intranet]", e);
/*    */     }
/* 64 */     return this.model;
/*    */   }
/*    */ }

/* Location:           E:\Proyectos\ACGP\intranet\WEB-INF\classes\
 * Qualified Name:     cl.intranet.web.admin
 * JD-Core Version:    0.6.0
 */