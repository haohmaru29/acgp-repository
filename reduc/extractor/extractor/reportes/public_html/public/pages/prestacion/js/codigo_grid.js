Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
]);
Ext.onReady(function() {
    
    Prestacion.storeCodigo = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        pageSize: Page.size,
        proxy: {
              type: 'ajax',
              url: 'home/codigoPestacion',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'COD_PRESTACION',
	        fields: [ 'COD_PRESTACION', 'DES_PRESTACION' ]
    });
    Prestacion.codigoAll = false;
    var selColumnCodigo = Ext.create('Ext.selection.CheckboxModel',{
        listeners: {
            selectionchange: function(model, records) {
                var s = Prestacion.gridCodigo.getSelectionModel().getSelection();
                var seleccionados = Prestacion.gridCodigo.getSelectionModel().getCount();
                var engrilla = Prestacion.storeCodigo.data.length;
                PrestacionCodigos.Seleccionados = '';
                if(seleccionados!=0) {
                    if(engrilla >= seleccionados ) {
                        Ext.each(s, function(item){
                              PrestacionCodigos.Seleccionados += '\'' +  item.data.COD_PRESTACION + '\',';
                        });
                    } 
                }
            }
        }
    });
    
    Prestacion.gridCodigo = Ext.create('Ext.grid.Panel', {
        store: Prestacion.storeCodigo,
        stateful: true,
        title: 'Prestacion',
        stateId: 'stateGridCodigoPrestacion',
        border: false,
        selModel: selColumnCodigo,
        columns: [{
            text     : 'Codigo',
            width     : 80,
            sortable : false,
            dataIndex: 'COD_PRESTACION'
        }, {
            text     : 'Descripcion',
            flex     : 150,
            sortable : false,
            dataIndex: 'DES_PRESTACION'
        }],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: Prestacion.storeCodigo,
            displayInfo: true,
            displayMsg: 'Registro {0} a {1} de {2}',
            emptyMsg: "No hay codigos",
            items:[
                '-', {
                text: 'Todos',
                iconCls: 'icon-exclamation',
                enableToggle: true,
                toggleHandler: function(btn, pressed) {
                    Prestacion.codigoAll=pressed;
                }
            }]
        }),
        tbar:[{
             xtype: 'textfield',
             name: 'searchCodPrest',
             id: 'searchCodPrest',
             text: 'Buscar',
             hideLabel: true,
             width: 200,
             listeners: {
                 change: {
                     fn: function(){
                        var value = Ext.getCmp('searchCodPrest');
                        Prestacion.storeCodigo.load({
                            params: {filter: value.getValue() }
                        }); 
                     },
                     scope: this,
                     buffer: 100
                 }
             }
        }],
        viewConfig: {
            stripeRows: true
        }
    });
    
   /*
    Historial.store.on('beforeload', function(store){
        store.proxy.extraParams.idUsuario=usuario;
        store.proxy.extraParams.estado=1;
    });
    */
});