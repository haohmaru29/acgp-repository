Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*'
]);

Ext.onReady(function() {
    
    CentroResultado.store = Ext.create('Ext.data.JsonStore', {
          autoLoad: false,
          remoteSort: false,
          pageSize: Page.size,
          proxy: {
              type: 'ajax',
              url: 'home/centroResultados',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'COD_CENTRO_RESULTADO',
	        fields: ['COD_CENTRO_RESULTADO', 'DES_CENTRO_RESULTADO']
	  });
        
    CentroResultado.store.on({ 
        'beforeload': {
            fn: function(store) {
              var value = Ext.getCmp('searchCR').getValue();
              if( value != '') {
                store.proxy.extraParams.filter = value;
              }
            },
            scope:this
         },
         'exception': {
            fn: function(store, records, options ){
                System.MessageBox.error("<b>Problema de conexion a la base de datos.</b>" );
            }
         }
    });
    
    var sm2 = Ext.create('Ext.selection.CheckboxModel',{
        listeners: {
            selectionchange: function(model, records) {
                var s = CentroResultado.grid.getSelectionModel().getSelection();
                var seleccionados = CentroResultado.grid.getSelectionModel().getCount();
                var engrilla = CentroResultado.store.data.length;
                GlobalCount = '';
                if(seleccionados!=0) {
                    if(engrilla >= seleccionados ) {
                        Ext.each(s, function(item){
                              GlobalCount += '\'' +  item.data.DES_CENTRO_RESULTADO + '\',';
                        });
                    } 
                }
            }
        }
    });
    
    CentroResultado.all = false; 
    CentroResultado.grid = Ext.create('Ext.grid.Panel', {
        store: CentroResultado.store,
        stateful: true,
        width: '50%',
        stateId: 'stateGrid0',
        selModel: sm2,
        title: 'Centro Resultados',
        columns: [{
            text     : 'Codigo',
            width     : 100,
            sortable : false,
            dataIndex: 'COD_CENTRO_RESULTADO'
        }, {
            text     : 'Descripcion',
            flex     : 1,
            sortable : true,
            dataIndex: 'DES_CENTRO_RESULTADO'
        }],
        viewConfig: {
            stripeRows: true
        },
         bbar: Ext.create('Ext.PagingToolbar', {
            store: CentroResultado.store,
            displayInfo: true,
            displayMsg: 'Registro {0} a {1} de {2}',
            emptyMsg: "No hay CR a visualizar",
            items:[
                '-', {
                text: 'Todos',
                iconCls: 'icon-exclamation',
                enableToggle: true,
                toggleHandler: function(btn, pressed) {
                    CentroResultado.all=pressed;
                }
            }]
        }),
        tbar:[{
             xtype: 'textfield',
             name: 'searchCR',
             id: 'searchCR',
             text: 'Buscar',
             hideLabel: true,
             width: 200,
             listeners: {
                 change: {
                     fn: function(){
                        var value = Ext.getCmp('searchCR');
                        CentroResultado.store.load({
                            params: {filter: value.getValue() }
                        }); 
                     },
                     scope: this,
                     buffer: 100
                 }
             }
        }]
    });
});
