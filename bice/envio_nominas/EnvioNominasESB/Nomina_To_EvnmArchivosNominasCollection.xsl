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
      <schema location="ArchivosNomina_BD.wsdl"/>
      <rootElement name="EvnmArchivosNominasCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/ArchivosNominaBD"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.5.0(build 090730.0200.1754) AT [TUE DEC 06 11:32:11 GMT-04:00 2011]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:imp1="http://TargetNamespace.com/ArchivoRendicionBICE"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:pc="http://xmlns.oracle.com/pcbpel/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns0="http://www.w3.org/2001/XMLSchema"
                xmlns:jca="http://xmlns.oracle.com/pcbpel/wsdl/jca/"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:top="http://xmlns.oracle.com/pcbpel/adapter/db/top/ArchivosNominaBD"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/file/ArchivoRendicionBICE/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ArchivosNomina_BD/"
                xmlns:ns2="http://xmlns.oracle.com/pcbpel/adapter/db/"
                xmlns:hdr="http://xmlns.oracle.com/pcbpel/adapter/file/"
                exclude-result-prefixes="xsl imp1 plt pc ns0 jca tns hdr top ns1 ns2 bpws ehdr hwf xp20 xref ora ids orcl">
  <xsl:variable name="slash"><xsl:text>&#47;</xsl:text></xsl:variable>   
  <xsl:variable name="puntoComa"><xsl:text>&#59;</xsl:text></xsl:variable>
  <xsl:template match="/">
    <top:EvnmArchivosNominasCollection>
      <top:EvnmArchivosNominas>
        <top:nombreArchivo>
          <xsl:value-of select="/imp1:Nomina/imp1:TransaccionPago/imp1:NOM_ARCHIVO"/>
        </top:nombreArchivo>
        <top:referenciaBancoProceso>
          <xsl:text disable-output-escaping="no">32</xsl:text>
        </top:referenciaBancoProceso>
        <top:fechaCreacion>
          <xsl:value-of select="xp20:current-dateTime()"/>
        </top:fechaCreacion>
         <top:cuerpo>
                 <xsl:for-each select="/imp1:Nomina/imp1:TransaccionPago">
                      <xsl:value-of select="/imp1:Nomina/imp1:TransaccionPago"/>
                 </xsl:for-each>
       </top:cuerpo> 
      </top:EvnmArchivosNominas>
      
    </top:EvnmArchivosNominasCollection>
  </xsl:template>
</xsl:stylesheet>
