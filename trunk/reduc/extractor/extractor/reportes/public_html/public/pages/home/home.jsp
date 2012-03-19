  <script type="text/javascript" src="public/pages/historial/js/historial.js"></script>
  <script type="text/javascript" src="public/pages/home/js/home_dnd.js" ></script>
  <script type="text/javascript" src="public/pages/campos/js/campos.js" ></script>
  <script type="text/javascript" src="public/pages/prestacion/js/prestacion_grid.js" ></script>
  <script type="text/javascript" src="public/pages/seccion/js/seccion.js" ></script>
  <script type="text/javascript" src="public/pages/especialidad/js/especialidad.js"></script>
  <script type="text/javascript" src="public/pages/home/js/centro_resultado.js" ></script>
  
  <jsp:include page="../prestacion/include.jsp"></jsp:include>
  <jsp:include page="../institucion/include.jsp"></jsp:include>
  <jsp:include page="../profesional/include.jsp"></jsp:include>
  <jsp:include page="../paciente/include.jsp"></jsp:include>
  <jsp:include page="../atencion/include.jsp"></jsp:include>
  
  <script type="text/javascript" src="public/pages/home/js/crGrid.js" ></script>
  
  <jsp:include page="header.jsp"></jsp:include>
  <div id="div_centro_resultados" style="display:none;"></div>     
  <div id="fileHistory" style="margin:15px;width:220px;"></div>
  
  <div id="finalizados" style="margin:15px;width:220px;display:none;"></div>     
  
  <div id="fileHistoryBar" style="height:180px; display:none; margin-top: 2px;">
      <table width="100%" border="1" cellspacing="0" cellpadding="2" style="text-align:center;font-size:12px;">
          <tr>
              <td colspan="2"><div id="fileText"></div></td>
          </tr>
          
          <tr>
              <td>Estado:</td>
              <td><div id="statusText"></div></div></td>
          </tr>
          <tr>
            <td colspan="2">
                <div style="float:left;"><div id="verArchivo"><a href="javascript:void(0);" ></a></div></div>
                <div style="float:right;"><div id="delArchivo"><a href="javascript:void(0);"></a></div></div>
            </td>
          </tr>
      </table>
  </div>
    
  <jsp:include page="footer.jsp"></jsp:include>
  <div style="display:none;">
      <iframe  id="iframeDownload"></iframe>
  </div>