package cl.intranet.web;

import cl.acgp.commons.helper.ImageUtils;
import cl.acgp.commons.mvc.service.jpa.ServiceManager;
import cl.intranet.domain.Categoria;
import cl.intranet.domain.Imagene;
import cl.intranet.domain.PublicTemporal;
import cl.intranet.domain.Publicacion;
import cl.intranet.domain.TipoPublicacion;
import cl.intranet.domain.UploadItem;
import cl.intranet.domain.Usuario;
import cl.intranet.service.CategoriaManager;
import cl.intranet.service.ImageneManager;
import cl.intranet.service.PublicTemporalManager;
import cl.intranet.service.PublicacionManager;
import cl.intranet.service.TipoPublicacionManager;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "upload" })
public class upload {
	private static final String basePath = System.getProperty("catalina.base");
	private static final Logger logger = Logger.getLogger(upload.class);
	private TipoPublicacionManager tipoPublicacionManager = (TipoPublicacionManager) ServiceManager.factory("TipoPublicacionManager");
	private PublicacionManager publicaManager = (PublicacionManager) ServiceManager.factory("PublicacionManager");
	private ImageneManager imageManager = (ImageneManager) ServiceManager.factory("ImageneManager");
	private PublicTemporalManager temporalManager = (PublicTemporalManager) ServiceManager.factory("PublicTemporalManager");
	private CategoriaManager categoriaManager = (CategoriaManager) ServiceManager.factory("CategoriaManager");

	@RequestMapping(value = { "upload" }, method = { RequestMethod.POST })
	@ResponseBody
	public String create(UploadItem uploadItem, BindingResult result,HttpServletRequest request, HttpServletResponse response) {
		String fileName = uploadItem.getFiles().getOriginalFilename();
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuario");
		if ((!result.hasErrors()) && (uploadItem != null)) {
			try {
				String filePath = basePath+ File.separator+ ResourceBundle.getBundle("module").getString("module.fileUpload") + fileName;
				String thumbnailPath = basePath+ File.separator + ResourceBundle.getBundle("module").getString("module.thumbnailUpload") + fileName;
				uploadItem.getFiles().transferTo(new File(filePath));
				ImageUtils.createThumbnail(filePath, thumbnailPath, 80, 80);
				Publicacion publicacion = this.publicaManager.findByTemporal(uploadItem.getIdpublicTemporal());
				Imagene img = new Imagene();
				if (publicacion == null) {
					publicacion = new Publicacion();
					publicacion.setContenidoPublicacion(uploadItem.getContenidoPublicacion());
					publicacion.setTituloPublicacion(uploadItem.getTituloPublicacion());
					publicacion.setTipoPublicacion((TipoPublicacion) this.tipoPublicacionManager.findById(uploadItem.getIdtipoPublicacion()));
					publicacion.setUsuario(usuario);

					PublicTemporal temp = (PublicTemporal) this.temporalManager.findById(Integer.valueOf(uploadItem.getIdpublicTemporal()));
					publicacion.setPublicTemporal(temp);
					publicacion.setCategoria((Categoria) this.categoriaManager.findById(uploadItem.getIdCategoria()));
					this.publicaManager.save(publicacion);
					img.setNombreImagen(fileName);
					img.setPublicacion(publicacion);
					this.imageManager.save(img);
					temp.setEstado("U");
					this.temporalManager.update(temp);
				} else {
					img.setNombreImagen(fileName);
					img.setPublicacion(publicacion);
					this.imageManager.save(img);
				}
			} catch (IllegalStateException e) {
				logger.error("[Intranet]", e);
			} catch (IOException e) {
				logger.error("[Intranet]", e);
			} catch (Exception e) {
				logger.error("[Intranet]", e);
			}

		}

		String success = "\"name\":\"" + fileName+ "\",\"delete_type\": \"BORRAR\",";
		success = success + "\"thumbnail_url\":\"public/upload/thumbnail/"+ fileName + "\",";
		success = success + "\"url\":\"public/upload/normal/" + fileName+ "\",";
		success = success + "\"delete_url\":\"public/upload/upload/normal/"+ fileName + "\",";
		success = success + "\"size\":\"" + uploadItem.getFiles().getSize()+ "\",";
		success = success + "\"type\":\""+ uploadItem.getFiles().getContentType() + "\"";
		return "[{" + success + "}]";
	}
}