<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="ArchivoRendicionBICE.wsdl"/>
      <rootElement name="Nomina" namespace="http://TargetNamespace.com/ArchivoRendicionBICE"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="EnvioNominas_ArchivoRendicionOutBICE_RS.wsdl"/>
      <rootElement name="RindeNominaOutput" namespace="http://bicevida.ws/services/envionominas"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.5.0(build 090730.0200.1754) AT [MON DEC 05 10:03:57 GMT-04:00 2011]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:imp1="http://TargetNamespace.com/ArchivoRendicionBICE"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns1="http://oracle.com/esb/namespaces/EnvioNominas"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/file/ArchivoRendicionBICE/"
                xmlns:inp1="http://bicevida.ws/services/envionominas"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/file/"
                exclude-result-prefixes="xsl imp1 plt pc jca ns0 tns hdr ns1 inp1 bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:template match="/">
    <inp1:RindeNominaOutput>
      <inp1:respuestaServicio>
        <xsl:value-of select="/imp1:Nomina/imp1:TransaccionPago/imp1:NOM_ARCHIVO"/>
      </inp1:respuestaServicio>
      <inp1:nominas>
        <inp1:nomina>
          <inp1:registrosNomina>
            <xsl:for-each select="/imp1:Nomina/imp1:TransaccionPago">
              <inp1:registro>
                <inp1:rut>
                  <xsl:value-of select="imp1:RUT_TITULAR"/>
                </inp1:rut>
                <inp1:nombre>
                  <xsl:value-of select="imp1:NOM_TITULAR"/>
                </inp1:nombre>
                <inp1:banco>
                  <xsl:value-of select="imp1:COD_BANCO"/>
                </inp1:banco>
                <inp1:cuenta>
                  <xsl:value-of select="imp1:NUM_CTATIT"/>
                </inp1:cuenta>
                <inp1:monto>
                  <xsl:value-of select="imp1:MTO_MONTO"/>
                </inp1:monto>
                <inp1:oficinaDestino>
                  <xsl:value-of select="imp1:NOM_OFICINA_DESTINO"/>
                </inp1:oficinaDestino>
                <inp1:fechaMovimiento>
                  <xsl:value-of select="imp1:FEC_FECHA"/>
                </inp1:fechaMovimiento>
                <inp1:glosa>
                  <xsl:value-of select="imp1:GLS_GLOSA"/>
                </inp1:glosa>
                <inp1:estadoPago>
                  <xsl:value-of select="imp1:COD_ESTADO_REN"/>
                </inp1:estadoPago>
                <inp1:tipoCuenta>
                  <xsl:value-of select="imp1:COD_TIPCTA"/>
                </inp1:tipoCuenta>
              </inp1:registro>
            </xsl:for-each>
          </inp1:registrosNomina>
        </inp1:nomina>
      </inp1:nominas>
    </inp1:RindeNominaOutput>
  </xsl:template>
</xsl:stylesheet>
