<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="EnvioNominas_ArchivoRendicionOutBICE_RS.wsdl"/>
      <rootElement name="RindeNominaOutput" namespace="http://bicevida.ws/services/envionominas"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="ArchivosNomina_BD.wsdl"/>
      <rootElement name="EvnmArchivosNominasCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/ArchivosNominaBD"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.5.0(build 090730.0200.1754) AT [MON DEC 05 17:22:42 GMT-04:00 2011]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:tns="http://oracle.com/esb/namespaces/EnvioNominas"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:top="http://xmlns.oracle.com/pcbpel/adapter/db/top/ArchivosNominaBD"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:inp1="http://bicevida.ws/services/envionominas"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ArchivosNomina_BD/"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/db/"
                exclude-result-prefixes="xsl tns ns0 inp1 plt pc jca top ns1 hdr bpws ehdr hwf xp20 xref ora ids orcl">
                
  <xsl:variable name="eol"><xsl:text>&#13;</xsl:text></xsl:variable>
  <xsl:variable name="padSpace"><xsl:text>&#59;</xsl:text></xsl:variable>
  <xsl:variable name="slash"><xsl:text>&#47;</xsl:text></xsl:variable>
  <xsl:variable name="padZero"><xsl:text>0</xsl:text></xsl:variable>
  <xsl:template match="/">
  
    <top:EvnmArchivosNominasCollection>
      <top:EvnmArchivosNominas>
        <top:nombreArchivo>
          <xsl:value-of select="/inp1:RindeNominaOutput/inp1:respuestaServicio"/>
        </top:nombreArchivo>
        <top:referenciaBancoProceso>
          <xsl:text disable-output-escaping="no">32</xsl:text>
        </top:referenciaBancoProceso>
        <top:fechaCreacion>
          <xsl:value-of select="xp20:current-dateTime()"/>
        </top:fechaCreacion>
        <top:cuerpo>
                 <xsl:for-each select="/inp1:RindeNominaOutput/inp1:nominas/inp1:nomina/inp1:registrosNomina/inp1:registro">
           
                <xsl:value-of select="inp1:rut" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:digitoVerificador" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:nombre" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:formaPago" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:banco" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:cuenta" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:numeroDocumentoPago" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:monto" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:oficinaDestino" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:fechaMovimiento" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:tipoDocumentoPago" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:glosa" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:estadoPago" />
                <xsl:value-of select="$slash"/>
                <xsl:value-of select="inp1:tipoCuenta" />
                <xsl:value-of select="$padSpace"/> 
         </xsl:for-each>
       </top:cuerpo>
      </top:EvnmArchivosNominas>
    </top:EvnmArchivosNominasCollection>
  </xsl:template>
   <xsl:template name="makeCol">
        <xsl:param name="colWidth" select="0"/>
        <xsl:param name="colPad" select="$padSpace"/>
        <xsl:param name="data" select="dato"/>
        <xsl:choose>
                <xsl:when test="string-length($data) &lt; $colWidth">
                    <xsl:choose>
                        <xsl:when test="$colPad = $padSpace">
                                <xsl:call-template name="textPad">
                                <xsl:with-param name="padCount" select="$colWidth - string-length($data)" />
                                <xsl:with-param name="colPad" select="$colPad" />
                            </xsl:call-template>
                                                <xsl:value-of select="$data" />
                        </xsl:when>
                        <xsl:otherwise>
                                <xsl:call-template name="textPad">
                                <xsl:with-param name="padCount" select="$colWidth - string-length($data)" />
                                <xsl:with-param name="colPad" select="$colPad" />
                            </xsl:call-template>
                                <xsl:value-of select="$data" />
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="substring($data,1,$colWidth)" />
                </xsl:otherwise>
        </xsl:choose>
  </xsl:template>
    <xsl:template name="textPad">
	<xsl:param name="padCount" select="0" />
    <xsl:param name="colPad" select="$padSpace" />
    <xsl:if test="$padCount > 0">
    	<xsl:value-of select="$colPad" />
        <xsl:call-template name="textPad">
	        <xsl:with-param name="padCount" select="$padCount - 1" />
	        <xsl:with-param name="colPad" select="$colPad" />
       	</xsl:call-template>
  	</xsl:if>
    </xsl:template>
</xsl:stylesheet>