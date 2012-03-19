Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
]);
Ext.onReady(function() {
    
    Prestacion.storeTipo = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        pageSize: Page.size,
        proxy: {
              type: 'ajax',
              url: 'home/tipoPrestacion',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'TIPO_PRESTACION_ORIGEN',
	        fields: [ 'TIPO_PRESTACION_ORIGEN' ]
    });
    Prestacion.tipoAll = false;
    var selTipo = Ext.create('Ext.selection.CheckboxModel',{
        listeners: {
            selectionchange: function(model, records) {
                var s = Prestacion.gridTipo.getSelectionModel().getSelection();
                var seleccionados = Prestacion.gridTipo.getSelectionModel().getCount();
                var engrilla = Prestacion.storeTipo.data.length;
                PrestacionTipo.Seleccionados = '';
                if(seleccionados!=0) {
                    if(engrilla >= seleccionados ) {
                        Ext.each(s, function(item){
                              PrestacionTipo.Seleccionados += '\'' +  item.data.TIPO_PRESTACION_ORIGEN + '\',';
                        });
                    } 
                }
            }
        }
    });
    
    Prestacion.gridTipo = Ext.create('Ext.grid.Panel', {
        store: Prestacion.storeTipo,
        stateful: true,
        title: 'Tipo Prestacion',
        stateId: 'stateGridTipoPrestacion',
        border: false,
        selModel: selTipo,
        columns: [{
            text     : 'Tipo Prestacion',
            flex     : 1,
            sortable : false,
            dataIndex: 'TIPO_PRESTACION_ORIGEN'
        }],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: Prestacion.storeTipo,
            displayInfo: true,
            displayMsg: 'Registro {0} a {1} de {2}',
            emptyMsg: "No hay datos",
            items:[
                '-', {
                text: 'Todos',
                iconCls: 'icon-exclamation',
                enableToggle: true,
                toggleHandler: function(btn, pressed) {
                    Prestacion.tipoAll=pressed;
                }
            }]
        }),
        tbar:[{
             xtype: 'textfield',
             name: 'searchTipoPrest',
             id: 'searchTipoPrest',
             text: 'Buscar',
             hideLabel: true,
             width: 200,
             listeners: {
                 change: {
                     fn: function(){
                        var value = Ext.getCmp('searchTipoPrest');
                        Prestacion.storeTipo.load({
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
});