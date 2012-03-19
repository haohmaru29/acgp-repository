Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
]);
Ext.onReady(function() {
    
    Institucion.store = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        pageSize: Page.size,
        proxy: {
              type: 'ajax',
              url: 'home/institucion',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'INSTITUCION_CONVENIO',
	        fields: [ 'INSTITUCION_CONVENIO' ]
    });
    Institucion.All = false;
    var smInstitucion = Ext.create('Ext.selection.CheckboxModel',{
        listeners: {
            selectionchange: function(model, records) {
                var s = Institucion.grid.getSelectionModel().getSelection();
                var seleccionados = Institucion.grid.getSelectionModel().getCount();
                var engrilla = Institucion.store.data.length;
                Institucion.Seleccionados = '';
                if(seleccionados!=0) {
                    if(engrilla >= seleccionados ) {
                        Ext.each(s, function(item){
                              Institucion.Seleccionados += '\'' +  item.data.INSTITUCION_CONVENIO + '\',';
                        });
                    } 
                }
            }
        }
    });
    
    Institucion.grid = Ext.create('Ext.grid.Panel', {
        store: Institucion.store,
        stateful: true,
        title: 'Institucion',
        stateId: 'stateGridInstitucion',
        border: false,
        selModel: smInstitucion,
        columns: [{
            text     : 'Institucion',
            flex     : 1,
            sortable : false,
            dataIndex: 'INSTITUCION_CONVENIO'
        }],
        bbar: Ext.create('Ext.PagingToolbar', {
            store: Institucion.store,
            displayInfo: true,
            displayMsg: 'Registro {0} a {1} de {2}',
            emptyMsg: "No hay instituciones",
            items:[
                '-', {
                text: 'Todos',
                iconCls: 'icon-exclamation',
                enableToggle: true,
                toggleHandler: function(btn, pressed) {
                    Institucion.All=pressed;
                }
            }]
        }),
        tbar:[{
             xtype: 'textfield',
             name: 'searchInstitucion',
             id: 'searchInstitucion',
             text: 'Buscar',
             hideLabel: true,
             width: 200,
             listeners: {
                 change: {
                     fn: function(){
                        var value = Ext.getCmp('searchInstitucion');
                        Institucion.store.load({
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