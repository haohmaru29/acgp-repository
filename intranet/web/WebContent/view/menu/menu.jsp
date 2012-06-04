<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!--menu -->
<script type="text/javascript">
	ddsmoothmenu.init({
		mainmenuid: "smoothmenu1", //menu DIV id
		orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
		classname: 'ddsmoothmenu', //class added to menu's outer DIV
		contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
	});
	
	ddsmoothmenu.init({
		mainmenuid: "smoothmenu2", 
		orientation: 'v', 
		classname: 'ddsmoothmenu-v', 
		contentsource: "markup" 
	});
</script>
<div id="smoothmenu1" class="ddsmoothmenu">
	<c:choose>
		<c:when test="${menu != null }">
			<ul>
				<c:forEach items="${menu}" var="menu">
					<li>
						<c:choose>
							<c:when test="${menu.urlMenu!='' && menu.urlMenu!=null}">
								<a href="${menu.urlMenu }">${menu.nombreMenu }</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);">${menu.nombreMenu }</a>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${child != null }">
								<ul>
									<c:forEach items="${child }" var="child">
										<c:if test="${child.idpadre == menu.idmenu }">
											<li><a href="javascript:void(0);" onclick="System.loadContent('${child.urlMenu }');return false;">${child.nombreMenu }</a></li>
											
										</c:if>
									</c:forEach>
								</ul>
							</c:when>
						</c:choose>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			
		</c:otherwise>
	</c:choose>
	<br style="clear: left" />
	<!-- Buscador 
				<div id="buscador">
				<form action="#" >				
				<div id="search-icon"></div>
				<input name="search" type="text" id="input-marco">
				</form>
				</div>
			<br style="clear: left" />
	<!-- END LOGO -->			
</div>
