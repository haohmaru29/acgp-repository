Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
]);
Ext.onReady(function() {
    
    Atencion.store = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        pageSize: Page.size,
        proxy: {
              type: 'ajax',
              url: 'home/atencion',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'TIPO_ATENCION',
	        fields: [ 'TIPO_ATENCION' ]
    });
    Atencion.All = false;
    var smAtencion = Ext.create('Ext.selection.CheckboxModel',{
        listeners: {
            selectionchange: function(model, records) {
                var s = Atencion.grid.getSelectionModel().getSelection();
                var seleccionados = Atencion.grid.getSelectionModel().getCount();
                var engrilla = Atencion.store.data.length;
                Atencion.Seleccionados = '';
                if(seleccionados!=0) {
                    if(engrilla >= seleccionados ) {
                        Ext.each(s, function(item){
                              Atencion.Seleccionados += '\'' +  item.data.TIPO_ATENCION + '\',';
                        });
                    } 
                }
            }
        }
    });
    
    Atencion.grid = Ext.create('Ext.grid.Panel', {
        store: Atencion.store,
        stateful: true,
        title: 'Tipo Atencion',
        stateId: 'stateGridPaciente',
        border: false,
        selModel: smAtencion,
        columns: [{
            text     : 'Tipo Atencion',
            flex     : 1,
            sortable : false,
            dataIndex: 'TIPO_ATENCION'
        }],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: Atencion.store,
            displayInfo: true,
            displayMsg: 'Registro {0} a {1} de {2}',
            emptyMsg: "No hay datos",
            items:[
                '-', {
                text: 'Todos',
                iconCls: 'icon-exclamation',
                enableToggle: true,
                toggleHandler: function(btn, pressed) {
                    Atencion.All=pressed;
                }
            }]
        }),
        tbar:[{
             xtype: 'textfield',
             name: 'searchAtencion',
             id: 'searchAtencion',
             text: 'Buscar',
             hideLabel: true,
             width: 200,
             listeners: {
                 change: {
                     fn: function(){
                        var value = Ext.getCmp('searchAtencion');
                        Atencion.store.load({
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