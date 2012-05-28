<%
	String url =request.getRequestURL().toString();
	url = url.substring(0, url.indexOf(request.getContextPath()) + request.getContextPath().length());
%>


<link type="text/css" href="<%=url %>/library/jquery/css/acgp/jquery-ui-1.8.19.custom.css" rel="stylesheet" />
<link href="<%=url %>/library/loadmask/jquery.loadmask.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="<%=url %>/library/prettyfoto/css/prettyPhoto.css" type="text/css" media="screen" title="prettyPhoto main stylesheet" charset="utf-8" />
<link rel="stylesheet" href="<%=url %>/library/prettyfoto/css/estilo.css" type="text/css" media="screen" charset="utf-8" />

<link rel="stylesheet" type="text/css" href="<%=url %>/public/css/ddsmoothmenu.css" />
<link rel="stylesheet" type="text/css" href="<%=url %>/public/css/estilo_principal.css" />
<link rel="stylesheet" type="text/css" href="<%=url %>/public/css/contenido.css" />
<link rel="stylesheet" type="text/css" href="<%=url %>/public/css/style-login.css" />
<style>
.mensajeBox{width:400px; height:130px;
		text-align:center;	
		}

#text_BOX{	font-family:Arial, Helvetica, sans-serif; 
			font-size:13px; color:#1F1F1F;
			width:300px; height:80px;
			margin:5px;
			overflow:hidden;}		
</style>