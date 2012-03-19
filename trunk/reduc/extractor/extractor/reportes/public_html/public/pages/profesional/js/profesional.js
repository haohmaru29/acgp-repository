Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
]);
Ext.onReady(function() {
    
    Profesional.store = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        pageSize: Page.size,
        proxy: {
              type: 'ajax',
              url: 'home/profesional',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'RUT_PROF_RESERVA',
	        fields: [ 'RUT_PROF_RESERVA', 'NOMBRE_PROF' ]
    });
    Profesional.All = false;
    var smProfesional = Ext.create('Ext.selection.CheckboxModel',{
        listeners: {
            selectionchange: function(model, records) {
                var s = Profesional.grid.getSelectionModel().getSelection();
                var seleccionados = Profesional.grid.getSelectionModel().getCount();
                var engrilla = Profesional.store.data.length;
                Profesional.Seleccionados = '';
                if(seleccionados!=0) {
                    if(engrilla >= seleccionados ) {
                        Ext.each(s, function(item){
                              Profesional.Seleccionados += '\'' +  item.data.RUT_PROF_RESERVA + '\',';
                        });
                    } 
                }
            }
        }
    });
    
    Profesional.grid = Ext.create('Ext.grid.Panel', {
        store: Profesional.store,
        stateful: true,
        title: 'Profesional',
        stateId: 'stateGridProfesional',
        border: false,
        selModel: smProfesional,
        columns: [{
            text     : 'Rut',
            width     : 100,
            sortable : false,
            dataIndex: 'RUT_PROF_RESERVA'
        }, {
            text     : 'Nombre profesional',
            flex     : 150,
            sortable : false,
            dataIndex: 'NOMBRE_PROF'
        }],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: Profesional.store,
            displayInfo: true,
            displayMsg: 'Registro {0} a {1} de {2}',
            emptyMsg: "No hay codigos",
            items:[
                '-', {
                text: 'Todos',
                iconCls: 'icon-exclamation',
                enableToggle: true,
                toggleHandler: function(btn, pressed) {
                    Profesional.All=pressed;
                }
            }]
        }),
        tbar:[{
             xtype: 'textfield',
             name: 'searchProfesional',
             id: 'searchProfesional',
             text: 'Buscar',
             hideLabel: true,
             width: 200,
             listeners: {
                 change: {
                     fn: function(){
                        var value = Ext.getCmp('searchProfesional');
                        Profesional.store.load({
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