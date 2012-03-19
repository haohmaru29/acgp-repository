Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
]);
Ext.onReady(function() {
    
    Paciente.store = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        pageSize: Page.size,
        proxy: {
              type: 'ajax',
              url: 'home/paciente',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'TIPO_PACIENTE',
	        fields: [ 'TIPO_PACIENTE' ]
    });
    Paciente.All = false;
    var smPaciente = Ext.create('Ext.selection.CheckboxModel',{
        listeners: {
            selectionchange: function(model, records) {
                var s = Paciente.grid.getSelectionModel().getSelection();
                var seleccionados = Paciente.grid.getSelectionModel().getCount();
                var engrilla = Paciente.store.data.length;
                Paciente.Seleccionados = '';
                if(seleccionados!=0) {
                    if(engrilla >= seleccionados ) {
                        Ext.each(s, function(item){
                              Paciente.Seleccionados += '\'' +  item.data.TIPO_PACIENTE + '\',';
                        });
                    } 
                }
            }
        }
    });
    
    Paciente.grid = Ext.create('Ext.grid.Panel', {
        store: Paciente.store,
        stateful: true,
        title: 'Tipo Paciente',
        stateId: 'stateGridPaciente',
        border: false,
        selModel: smPaciente,
        columns: [{
            text     : 'Tipo paciente',
            flex     : 1,
            sortable : false,
            dataIndex: 'TIPO_PACIENTE'
        }],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: Paciente.store,
            displayInfo: true,
            displayMsg: 'Registro {0} a {1} de {2}',
            emptyMsg: "No hay datos",
            items:[
                '-', {
                text: 'Todos',
                iconCls: 'icon-exclamation',
                enableToggle: true,
                toggleHandler: function(btn, pressed) {
                    Paciente.All=pressed;
                }
            }]
        }),
        tbar:[{
             xtype: 'textfield',
             name: 'searchPaciente',
             id: 'searchPaciente',
             text: 'Buscar',
             hideLabel: true,
             width: 200,
             listeners: {
                 change: {
                     fn: function(){
                        var value = Ext.getCmp('searchPaciente');
                        Paciente.store.load({
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