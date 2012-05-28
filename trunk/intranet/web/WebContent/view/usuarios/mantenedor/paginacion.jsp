<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:choose>
	<c:when test="${paginacion!= null && !empty paginacion }">
		<c:set var="pagi" value="${paginacion}"></c:set>
		<c:if test="${pagina!=0 && pagina!=null && pagina!=1 }">
			<a href="javascript:void(0);" onclick="loadPagina(${pagina-1 });">
				<< 
			</a>
		</c:if>

		<c:forEach varStatus="paginacionCount" begin="1" end="${pagi }">
			<c:if test="${pagina==null && paginacionCount.count==1}">
				<a href="javascript:void(0);" style="color: #DF6800;" id="link">
					${paginacionCount.count } </a>
			</c:if>
			<c:if test="${pagina!=null && paginacionCount.count==pagina }">
				<a href="javascript:void(0);" id="link" style="color: #DF6800;">
					${paginacionCount.count } </a>
			</c:if>
			<c:if test="${pagina==null && paginacionCount.count!=1 }">
				<a href="javascript:void(0);" onclick="loadPagina(${paginacionCount.count });">
					${paginacionCount.count } </a>
			</c:if>
			<c:if test="${pagina!=null  && paginacionCount.count!=pagina }">
				<a href="javascript:void(0);"
					onclick="loadPagina(${paginacionCount.count });">
					${paginacionCount.count } </a>
			</c:if>
		</c:forEach>

		<c:if test="${pagina!=paginacion && pagina!=null}">
			<a href="javascript:void(0);" onclick="loadPagina(${paginacion });">
				>> </a>
		</c:if>
		<c:if test="${pagina==null}">

		</c:if>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>