<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="EnvioNominas.xsd"/>
      <rootElement name="CargaNominaInput" namespace="http://bicevida.ws/services/envionominas"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="nominasbel.wsdl"/>
      <rootElement name="cargaPeticion" namespace="http://bci.ws/infraestructuradenegocios/nominas/nominasbel"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 10.1.3.5.0(build 090730.0200.1754) AT [THU FEB 17 18:15:10 CLST 2011]. -->
?>
<xsl:stylesheet version="1.0" xmlns:tns="http://bci.ws/infraestructuradenegocios/nominas/nominasbel"
                xmlns:env="http://bicevida.ws/services/envionominas"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ehdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.esb.server.headers.ESBHeaderFunctions"
                xmlns:ns1="http://bci.ws/infraestructuradenegocios/nominas/nominasbel/types/rut"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:serv="http://bci.ws/infraestructuradenegocios/nominas/nominasbel/types"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:orcl="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                exclude-result-prefixes="xsl xsd ns0 tns wsdl ns1 soap serv bpws ehdr hwf xp20 xref ora ids orcl">
    <xsl:variable name="eol"><xsl:text>&#13;</xsl:text></xsl:variable>
    <xsl:variable name="padSpace"><xsl:text>&#32;</xsl:text></xsl:variable>
    <xsl:variable name="padZero"><xsl:text>0</xsl:text></xsl:variable>
    <xsl:template match="/">
        <tns:cargaPeticion>
            <serv:rutEmpresa>
                <xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
                    <xsl:if test="env:nombre='rutEmpresa'">
                        <ns1:rut>
                            <xsl:value-of select="env:valor"/>
        		</ns1:rut>
                    </xsl:if>
                </xsl:for-each>
		<xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
                    <xsl:if test="env:nombre='dvRutEmpresa'">
                        <ns1:digitoVerificador>
                            <xsl:value-of select="env:valor"/>
			</ns1:digitoVerificador>
                    </xsl:if>
		</xsl:for-each>
            </serv:rutEmpresa>
            <serv:rutUsuario>
                <xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
                    <xsl:if test="env:nombre='rutUsuario'">
                        <ns1:rut>
                            <xsl:value-of select="env:valor"/>
          		</ns1:rut>
                    </xsl:if>
                </xsl:for-each>
		<xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
                    <xsl:if test="env:nombre='dvRutUsuario'">
                        <ns1:digitoVerificador>
                            <xsl:value-of select="env:valor"/>
        		</ns1:digitoVerificador>
                    </xsl:if>
		</xsl:for-each>
            </serv:rutUsuario>
                <xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
                    <xsl:if test="env:nombre='convenio'">
                        <serv:convenio>
                            <xsl:value-of select="env:valor"/>
      			</serv:convenio>
                    </xsl:if>
		</xsl:for-each>
		<xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
                    <xsl:if test="env:nombre='canal'">
	      		<serv:canal>
                            <xsl:value-of select="env:valor"/>
	        	</serv:canal>
                    </xsl:if>
		</xsl:for-each>
		<xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
                    <xsl:if test="env:nombre='pin'">
		      	<serv:pin>
                            <xsl:value-of select="env:valor"/>
			</serv:pin>
                    </xsl:if>
		</xsl:for-each>
		<serv:archivo>
                    <xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
                    <xsl:if test="env:nombre='nombreArchivo'">
                            <serv:nombre>
                                <xsl:value-of select="concat(env:valor,'_',/env:CargaNominaInput/env:nomina/env:idNomina,'-',/env:CargaNominaInput/env:nomina/env:idProceso)"/>
                            </serv:nombre>
                        </xsl:if>
                    </xsl:for-each>
                    <xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
                        <xsl:if test="env:nombre='tipoArchivo'">
                            <serv:tipo>
                                <xsl:value-of select="env:valor"/>
                            </serv:tipo>
			</xsl:if>
                    </xsl:for-each>
                    <xsl:for-each select="env:CargaNominaInput/env:datosCabecera/env:camposVariables/env:campoVariable">
      			<xsl:if test="env:nombre='plantilla'">
                            <serv:plantilla>
                                <xsl:value-of select="env:valor"/>
                            </serv:plantilla>
			</xsl:if>
                    </xsl:for-each>
                    <serv:cuerpo>
						<!--alinear todo a la derecha, y rellenar con 0-->
                        <xsl:for-each select="env:CargaNominaInput/env:nomina/env:registrosNomina/env:registro">
                            <!-- Traduccion codigos y tipos especificos BCI -->
							<xsl:variable name="formaPago"><xsl:choose>
								<xsl:when test="env:formaPago = '164251'">
									<xsl:text>VVC</xsl:text> 
								</xsl:when>
								<xsl:otherwise>
									<xsl:choose>
										<xsl:when test="env:banco = '016'">
											<xsl:choose>
												<xsl:when test="env:tipoCuenta != '163247'">
													<xsl:text>CCT</xsl:text>
												</xsl:when>
												<xsl:otherwise>
													<xsl:text>AHO</xsl:text>
												</xsl:otherwise>
											</xsl:choose>
										</xsl:when>
										<xsl:otherwise>
											<xsl:choose>
												<xsl:when test="env:tipoCuenta != '163247'">
													<xsl:text>OTC</xsl:text>
												</xsl:when>
												<xsl:otherwise>
													<xsl:text>OTA</xsl:text>
												</xsl:otherwise>
											</xsl:choose>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:otherwise>
							</xsl:choose></xsl:variable>
							<xsl:variable name="oficina">
								<xsl:choose>
									<xsl:when test="env:formaPago = '164251'">
										<xsl:text>245</xsl:text>
									</xsl:when>
								</xsl:choose>
							</xsl:variable>
							<xsl:variable name="tipoDocumento">
								<xsl:choose>
									<xsl:when test="env:tipoDocumentoPago = 'FACTURA'">
										<xsl:text>FAC</xsl:text>
									</xsl:when>
								</xsl:choose>
							</xsl:variable>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="env:rut" />
                                <xsl:with-param name="colWidth" select="8" />
                                <xsl:with-param name="colPad" select="$padSpace" />
                            </xsl:call-template>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="env:digitoVerificador" />
                                <xsl:with-param name="colWidth" select="1" />
                                <xsl:with-param name="colPad" select="$padSpace" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="env:nombre" />
                                <xsl:with-param name="colWidth" select="45" />
                                <xsl:with-param name="colPad" select="$padSpace" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="$formaPago" />
								<!-- si es banco 016 entonces debe ser CCT (corriente), AHO (ahorro), OTC,OTA(Otro banco) , servipag EFE, valevista VVC-->
                                <xsl:with-param name="colWidth" select="3" />
                                <xsl:with-param name="colPad" select="$padSpace" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="env:banco" />
                                <xsl:with-param name="colWidth" select="3" />
                                <xsl:with-param name="colPad" select="$padSpace" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="env:cuenta" />
                                <xsl:with-param name="colWidth" select="20" />
                                <xsl:with-param name="colPad" select="$padZero" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="env:numeroDocumentoPago"/>
								<!--numero de factura-->
                                <xsl:with-param name="colWidth" select="10" />
                                <xsl:with-param name="colPad" select="$padZero" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="env:monto" />
                                <xsl:with-param name="colWidth" select="10" />
                                <xsl:with-param name="colPad" select="$padZero" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="$oficina" />
                                <!-- servipag 999 y vale vista 245-->
								<xsl:with-param name="colWidth" select="3" />
                                <xsl:with-param name="colPad" select="$padSpace" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="concat(substring(/env:CargaNominaInput/env:nomina/env:fechaPago,9,2),substring(/env:CargaNominaInput/env:nomina/env:fechaPago,6,2),substring(/env:CargaNominaInput/env:nomina/env:fechaPago,1,4))" />
                                <xsl:with-param name="colWidth" select="8" />
                                <xsl:with-param name="colPad" select="$padSpace" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="$tipoDocumento"/>
								<!--FAC (factura), NCR Nota de credito, DES descuento, ABO abono, Nota debito (pendiente).-->
                                <xsl:with-param name="colWidth" select="3" />
                                <xsl:with-param name="colPad" select="$padSpace" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="env:glosa"/>
                                <xsl:with-param name="colWidth" select="30" />
                                <xsl:with-param name="colPad" select="$padSpace" />
                            </xsl:call-template>
							<xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="$padZero"/>
                                <xsl:with-param name="colWidth" select="14" />
                                <xsl:with-param name="colPad" select="$padZero" />
                            </xsl:call-template>
                            <xsl:value-of select="$padSpace"/>
                            <xsl:call-template name="makeCol">
                                <xsl:with-param name="data" select="env:idRegistro"/>
                                <xsl:with-param name="colWidth" select="10" />
                                <xsl:with-param name="colPad" select="$padZero" />
                            </xsl:call-template>
                            <xsl:value-of select="$eol"/>					
						</xsl:for-each>
                    </serv:cuerpo>
                </serv:archivo>
                <serv:fecha>
                    <xsl:value-of select="concat(substring(/env:CargaNominaInput/env:datosCabecera/env:fechaActual,9,2),substring(/env:CargaNominaInput/env:datosCabecera/env:fechaActual,6,2),substring(//env:CargaNominaInput/env:datosCabecera/env:fechaActual,1,4))"/>
                </serv:fecha>
        </tns:cargaPeticion>
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
</xsl:stylesheet><!-- Stylus Studio meta-information - (c) 2004-2009. Progress Software Corporation. All rights reserved.

<metaInformation>
	<scenarios>
		<scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="..\..\..\..\..\..\..\stylus\envionominas\CargaNominaInput.xml" htmlbaseurl="" outputurl="" processortype="saxon8" useresolver="yes" profilemode="0"
		          profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no"
		          validator="internal" customvalidator="">
			<advancedProp name="sInitialMode" value=""/>
			<advancedProp name="bXsltOneIsOkay" value="true"/>
			<advancedProp name="bSchemaAware" value="true"/>
			<advancedProp name="bXml11" value="false"/>
			<advancedProp name="iValidation" value="0"/>
			<advancedProp name="bExtensions" value="true"/>
			<advancedProp name="iWhitespace" value="0"/>
			<advancedProp name="sInitialTemplate" value=""/>
			<advancedProp name="bTinyTree" value="true"/>
			<advancedProp name="bWarnings" value="true"/>
			<advancedProp name="bUseDTD" value="false"/>
			<advancedProp name="iErrorHandling" value="fatal"/>
		</scenario>
	</scenarios>
	<MapperMetaTag>
		<MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
		<MapperBlockPosition></MapperBlockPosition>
		<TemplateContext></TemplateContext>
		<MapperFilter side="source"></MapperFilter>
	</MapperMetaTag>
</metaInformation>
-->