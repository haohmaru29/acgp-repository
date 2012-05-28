/*     */ package cl.intranet.web;
/*     */ 
/*     */ import cl.acgp.commons.mvc.service.jpa.ServiceManager;
/*     */ import cl.intranet.domain.Perfil;
/*     */ import cl.intranet.domain.Usuario;
/*     */ import cl.intranet.service.PerfilManager;
/*     */ import cl.intranet.service.PublicacionManager;
/*     */ import cl.intranet.service.UsuarioManager;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"usuario"})
/*     */ public class usuario
/*     */ {
/*     */   private ModelAndView model;
/*  25 */   private static final Logger logger = Logger.getLogger(usuario.class);
/*  26 */   private UsuarioManager usuarioManager = (UsuarioManager)ServiceManager.factory("UsuarioManager");
/*  27 */   private PerfilManager perfilManager = (PerfilManager)ServiceManager.factory("PerfilManager");
/*  28 */   private PublicacionManager publicaManager = (PublicacionManager)ServiceManager.factory("PublicacionManager");
/*     */ 
/*  32 */   @RequestMapping(value={"form"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public ModelAndView form(HttpServletRequest request, HttpServletResponse response) { this.model = new ModelAndView();
/*  33 */     if (request.getSession(false).getAttribute("usuario") != null) {
/*  34 */       this.model.setViewName("usuarios/add");
/*     */     } else {
/*  36 */       this.model.addObject("message", "1");
/*  37 */       this.model.setViewName("global/message");
/*     */     }
/*     */ 
/*  40 */     return this.model; }
/*     */ 
/*     */   @RequestMapping(value={"mantenedor"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public ModelAndView mantenedor(HttpServletRequest request, HttpServletResponse response) {
/*  45 */     this.model = new ModelAndView();
/*     */     try {
/*  47 */       Usuario usuario = (Usuario)request.getSession(false).getAttribute("usuario");
/*  48 */       if (usuario.getPerfil().getIdperfil() == 1) {
/*  49 */         String fecha = request.getParameter("fecha");
/*  50 */         int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.size"));
/*  51 */         int start = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.index"));
/*  52 */         this.model.addObject("paginacion", Integer.valueOf(getPaginacion(limit)));
/*  53 */         this.model.addObject("usuarios", this.usuarioManager.findByParams(fecha, usuario.getIdusuario(), start, limit));
/*  54 */         this.model.setViewName("usuarios/mantenedor/include");
/*     */       } else {
/*  56 */         this.model.addObject("message", "no tiene permiso para ingresar a esta sección");
/*  57 */         this.model.setViewName("global/message");
/*     */       }
/*     */     } catch (Exception e) {
/*  60 */       this.model.addObject("message", e);
/*  61 */       this.model.setViewName("global/message");
/*     */     }
/*     */ 
/*  64 */     return this.model;
/*     */   }
/*     */   @RequestMapping(value={"load"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public ModelAndView load(HttpServletRequest request, HttpServletResponse response) {
/*  69 */     this.model = new ModelAndView();
/*     */     try {
/*  71 */       Usuario usuario = (Usuario)request.getSession(false).getAttribute("usuario");
/*  72 */       String fecha = request.getParameter("fecha");
/*  73 */       int limit = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.size"));
/*  74 */       int start = Integer.parseInt(ResourceBundle.getBundle("module").getString("module.mantenedor.index"));
/*  75 */       this.model.addObject("paginacion", Integer.valueOf(getPaginacion(limit)));
/*  76 */       this.model.addObject("usuarios", this.usuarioManager.findByParams(fecha, usuario.getIdusuario(), start, limit));
/*  77 */       this.model.setViewName("usuarios/mantenedor/tabla");
/*     */     } catch (Exception e) {
/*  79 */       this.model.addObject("message", e);
/*  80 */       this.model.setViewName("global/message");
/*     */     }
/*     */ 
/*  83 */     return this.model;
/*     */   }
/*     */   @RequestMapping(value={"edit"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
/*  88 */     this.model = new ModelAndView();
/*  89 */     int id = request.getParameter("idUsuario") == null ? 0 : Integer.parseInt(request.getParameter("idUsuario"));
/*  90 */     if (id != -1) {
/*  91 */       this.model.addObject("titulo", "Modificar Datos Usuario");
/*  92 */       this.model.addObject("usuarios", this.usuarioManager.findById(Integer.valueOf(id)));
/*     */     } else {
/*  94 */       this.model.addObject("titulo", "Agregar nuevo Usuario");
/*     */     }
/*  96 */     this.model.addObject("perfiles", this.perfilManager.findAll());
/*  97 */     this.model.setViewName("usuarios/mantenedor/modificar");
/*     */ 
/*  99 */     return this.model;
/*     */   }
/*     */   @RequestMapping(value={"delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
/* 104 */     this.model = new ModelAndView();
/* 105 */     String options = request.getParameter("options");
/* 106 */     options = options.substring(0, options.length() - 1);
/* 107 */     String[] array = options.split(",");
/*     */     try {
/* 109 */       for (int x = 0; x < array.length; x++) {
/* 110 */         this.usuarioManager.deleteById(array[x]);
/*     */       }
/* 112 */       this.model.addObject("message", Integer.valueOf(1));
/* 113 */       this.model.setViewName("global/message");
/*     */     } catch (Exception e) {
/* 115 */       this.model.addObject("message", e);
/* 116 */       this.model.setViewName("global/message");
/* 117 */       logger.error("[Intranet]", e);
/*     */     }
/* 119 */     return this.model;
/*     */   }
/*     */   @RequestMapping(value={"mostrar"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public ModelAndView mostrar(HttpServletRequest request, HttpServletResponse response) {
/* 124 */     this.model = new ModelAndView();
/* 125 */     int idUsuario = request.getParameter("idUsuario") == null ? 0 : Integer.parseInt(request.getParameter("idUsuario"));
/*     */     try {
/* 127 */       this.model.addObject("usuario", this.usuarioManager.findById(Integer.valueOf(idUsuario)));
/* 128 */       this.model.addObject("noticias", Integer.valueOf(this.publicaManager.findByUser(idUsuario, 1, null).size()));
/* 129 */       this.model.addObject("anuncios", Integer.valueOf(this.publicaManager.findByUser(idUsuario, 2, null).size()));
/* 130 */       this.model.setViewName("usuarios/mantenedor/datos_usuario");
/*     */     } catch (Exception e) {
/* 132 */       this.model.addObject("message", e);
/* 133 */       this.model.setViewName("global/message");
/* 134 */       logger.error("[Intranet]", e);
/*     */     }
/* 136 */     return this.model;
/*     */   }
/*     */ 
/*     */   private int getPaginacion(int limit)
/*     */   {
/* 141 */     int total = this.usuarioManager.countAll();
/* 142 */     int d = total / limit;
/* 143 */     int remain = total % limit;
/* 144 */     if (remain > 0) {
/* 145 */       d++;
/*     */     }
/*     */ 
/* 148 */     return d;
/*     */   }
/*     */ }

/* Location:           E:\Proyectos\ACGP\intranet\WEB-INF\classes\
 * Qualified Name:     cl.intranet.web.usuario
 * JD-Core Version:    0.6.0
 */