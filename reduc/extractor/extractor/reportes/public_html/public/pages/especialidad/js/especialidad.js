Ext.onReady(function() {
    Especialidad.storeEspecialidad = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        pageSize: Page.size,
        proxy: {
              type: 'ajax',
              url: 'home/especialidad',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'COD_CENTRO_RESULTADO',
	        fields: ['COD_PRESTACION' , 'ESPECIALIDAD', 'TIPO_PRESTACION']
    });
    
    var smEspecialida =  Ext.create('Ext.selection.CheckboxModel',{
        listeners: {
            selectionchange: function(model, records) {
                var s = Especialidad.grid.getSelectionModel().getSelection();
                var seleccionados = Especialidad.grid.getSelectionModel().getCount();
                var engrilla = Especialidad.storeEspecialidad.data.length;
                Especialidad.Seleccionados = '';
                if(seleccionados!=0) {
                    if(engrilla >= seleccionados ) {
                        Ext.each(s, function(item){
                              Especialidad.Seleccionados += '\'' +  item.data.TIPO_PRESTACION + '\',';
                        });
                    } 
                }
            }
        }
    });
    Especialidad.all=false;
    Especialidad.grid = Ext.create('Ext.grid.Panel', {
        store: Especialidad.storeEspecialidad,
        stateful: true,
        width: '50%',
        stateId: 'stateGrid2',
        selModel: smEspecialida,
        title: 'Especialidades',
        columns: [{
            text     : 'Codigo',
            width     : 100,
            sortable : false,
            dataIndex: 'COD_PRESTACION'
        }, {
            text     : 'Descripcion',
            flex     : 1,
            sortable : true,
            dataIndex: 'ESPECIALIDAD'
        }],
        viewConfig: {
            stripeRows: true
        },
         bbar: Ext.create('Ext.PagingToolbar', {
            store: Especialidad.storeEspecialidad,
            displayInfo: true,
            displayMsg: 'Registro {0} a {1} de {2}',
            emptyMsg: "No hay Especialidades a visualizar",
            items:[
                '-', {
                text: 'Todos',
                iconCls: 'icon-exclamation',
                enableToggle: true,
                toggleHandler: function(btn, pressed) {
                    Especialidad.all=pressed;
                }
            }]
        }),
        tbar:[{
             xtype: 'textfield',
             name: 'searchEspecialidad',
             id: 'searchEspecialidad',
             text: 'Buscar',
             hideLabel: true,
             width: 200,
             listeners: {
                 change: {
                     fn: function(){
                        var value = Ext.getCmp('searchEspecialidad');
                        Especialidad.storeEspecialidad.load({
                            params: {filter: value.getValue() }
                        }); 
                     },
                     scope: this,
                     buffer: 100
                 }
             }
        }]
    });
    
    Especialidad.storeEspecialidad.on({ 
        'beforeload': {
            fn: function(store) {
              var value = Ext.getCmp('searchEspecialidad');
              if( value != '') {
                store.proxy.extraParams.filter = value.getValue();
              }
            },
            scope:this
         }
    });
});