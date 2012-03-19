Ext.require(['Ext.data.*']);
Ext.require('Ext.chart.*');
Ext.require(['Ext.Window', 'Ext.layout.container.Fit', 'Ext.fx.target.Sprite']);

Ext.onReady(function() {
    /*
    Ext.define('dataChart', {
        extend: 'Ext.data.Model',
        fields: ['CANTIDAD', 'IU_USUARIO']
    });
    */
    Charts.store = Ext.create('Ext.data.JsonStore', {
          autoLoad: false,
          //model: 'dataChart',
          remoteSort: true,
          proxy: {
              type: 'ajax',
              url: 'home/chart',
              method: 'post',
              reader: {
                  type: 'json',
                  root: 'data'
              }
          },
	        idProperty: 'CANTIDAD',
	        fields: ['CANTIDAD', 'IU_USUARIO']
	  });
    
    Charts.Window = Ext.create('Ext.Window', {
        width: 600,
        height: 500,
        hidden: false,
        maximizable: true,
        title: 'Ingresos Usuarios',
        modal:true,
        closable: true,
        closeAction:'hide',
        layout: 'fit',
        tbar: [{
            iconCls: 'icon-refresh',
            text: 'Actualizar',
            tooltip: 'Actualizar grafico',
            handler: function() {
                Charts.store.load();
            }
        }],
        items: {
            id: 'chartCmp',
            xtype: 'chart',
            style: 'background:#fff',
            animate: true,
            shadow: true,
            store: Charts.store ,
            axes: [{
                type: 'Numeric',
                position: 'left',
                fields: ['CANTIDAD'],
                label: {
                    renderer: Ext.util.Format.numberRenderer('0,0')
                },
                title: 'Cantidad',
                grid: true,
                minimum: 0
            }, {
                type: 'Category',
                position: 'bottom',
                fields: ['IU_USUARIO'],
                title: 'USUARIO'
            }],
            series: [{
                type: 'column',
                axis: 'left',
                highlight: true,
                tips: {
                  trackMouse: true,
                  width: 140,
                  height: 28,
                  renderer: function(storeItem, item) {
                    this.setTitle(storeItem.get('CANTIDAD') + ': ' + storeItem.get('IU_USUARIO') + '');
                  }
                },
                label: {
                  display: 'insideEnd','text-anchor': 'middle',
                    field: 'CANTIDAD',
                    renderer: Ext.util.Format.numberRenderer('0'),
                    orientation: 'vertical',
                    color: '#333'
                },
                xField: 'IU_USUARIO',
                yField: 'CANTIDAD'
            }]
        }
    });
    
    Charts.Window.on('close', function() {
        Charts.Window.hide();
    });
});