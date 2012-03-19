Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
    'Ext.toolbar.Paging'
]);
Ext.onReady(function() {
    Ext.QuickTips.init();
    Ext.define('prestacion', {
        extend: 'Ext.data.Model',
        fields: ['COD_PRESTACION', 'DES_PRESTACION']
    });
    
    Prestacion.store = Ext.create('Ext.data.JsonStore', {
          autoLoad: false,
          model: 'prestacion',
          remoteSort: false,
          proxy: {
              type: 'ajax',
              url: 'home/prestaciones',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'COD_PRESTACION',
	        fields: ['COD_PRESTACION', 'DES_PRESTACION']
	  });
    
    Prestacion.store.on({ 
        'beforeload': {
            fn: function(s) {
            
            },
            scope:this
         },
         'exception': {
            fn: function(store, records, options ){
                System.MessageBox.error("Se ha producido un error: " + store );
            }
         }
    });
    
    var sm = Ext.create('Ext.selection.CheckboxModel');

    Prestacion.grid = Ext.create('Ext.grid.Panel', {
        store: Prestacion.store,
        title: 'Tipo Prestacion',
        stateful: true,
        stateId: 'stateGrid',
        selModel: sm,
        columns: [{
            text     : 'Cod. Prestacion',
            width     : 150,
            sortable : false,
            dataIndex: 'COD_PRESTACION'
        }, {
            text     : 'Desc. Prestacion',
            flex     : 1,
            sortable : true,
            dataIndex: 'DES_PRESTACION'
        }],
        viewConfig: {
            stripeRows: true
        }
    });
    
});