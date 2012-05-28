<%
	String url =request.getRequestURL().toString();
	url = url.substring(0, url.indexOf(request.getContextPath()) + request.getContextPath().length());
%>

<script type="text/javascript" src="<%=url %>/library/jquery/js/jquery.js"></script>
<script type="text/javascript" src="<%=url %>/library/jquery/js/jquery.custom.js"></script>
<script type='text/javascript' src='<%=url %>/library/loadmask/jquery.loadmask.js'></script>

<script type="text/javascript" src="<%=url %>/library/system/namespace.js"></script>
<script type="text/javascript" src="<%=url %>/library/system/system.js"></script>

<script type="text/javascript" src="<%=url %>/library/jquery/js/ux/jquery.ajax.min.js"></script>

<script type="text/javascript" src="<%=url %>/library/jquery/js/ux/jquery.ajax.form.js"></script>

<script type="text/javascript" src="<%=url %>/library/fileupload/js/tmpl.min.js"></script>
<script type="text/javascript" src="<%=url %>/library/fileupload/js/load-image.min.js"></script>
<script type="text/javascript" src="<%=url %>/library/fileupload/js/canvas-to-blob.min.js"></script>
<script type="text/javascript" src="<%=url %>/library/fileupload/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=url %>/library/fileupload/js/bootstrap-image-gallery.min.js"></script>

<script type="text/javascript" src="<%=url %>/library/fileupload/js/jquery.iframe-transport.js"></script>

<script type="text/javascript" src="<%=url %>/library/fileupload/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="<%=url %>/library/fileupload/js/jquery.fileupload-ip.js"></script>
<script type="text/javascript" src="<%=url %>/library/fileupload/js/jquery.fileupload-ui.js"></script>
<script type="text/javascript" src="<%=url %>/library/fileupload/js/locale.js"></script>

<script type="text/javascript" src="<%=url %>/library/jquery/js/ux/jquery.validate.js"></script>

<script src="<%=url %>/library/prettyfoto/js/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=url %>/library/prettyfoto/js/jquery-foto.js" type="text/javascript" charset="utf-8"></script>


<script type="text/javascript" src="<%=url %>/public/js/environment.js"></script>

