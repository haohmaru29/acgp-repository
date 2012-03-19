<script type="text/javascript" src="library/system/namespace.js"></script>
<script type="text/javascript" src="library/system/system.js"></script>

<script type="text/javascript" src="library/extjs/4.0.2a/adapter/ext/ext-all-debug.js" ></script>

<!-- ExtJS Lenguaje -->
<script type="text/javascript" src="library/extjs/4.0.2a/locale/ext-lang-es.js" ></script>

<!-- ExtJS User Extensions -->
<script type="text/javascript" src="public/js/pool/Ext.pool.js"></script>

<!-- ExtJS System depends -->
<script type="text/javascript" src="library/system/ext.system.js"></script>

<!-- Namespace Define -->
<script type="text/javascript" src="public/js/environment.js"></script>

<script type="text/javascript">
    Ext.onReady(function(){
        Ext.QuickTips.init();
    });
</script>

<script type="text/javascript">
Ext.onReady(function(){
      GlobalMask = new Ext.LoadMask(Ext.getBody(), {
          msg:"<b>Cargando Aplicaci&oacute;n</b>... Espere por favor."
      });
     GlobalMask.show()
     
});
</script>