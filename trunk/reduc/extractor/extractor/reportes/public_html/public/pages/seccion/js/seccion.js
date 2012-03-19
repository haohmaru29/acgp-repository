Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
]);
Ext.onReady(function() {

    Seccion.storeSeccion = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        pageSize: Page.size,
        proxy: {
              type: 'ajax',
              url: 'home/secciones',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'COD_SECCION',
	        fields: ['COD_FLEX', 'DES_FLEX' ]
    });
    
    var smSeccion =  Ext.create('Ext.selection.CheckboxModel',{
          listeners: {
              selectionchange: function(model, records) {
                  var s = Seccion.grid.getSelectionModel().getSelection();
                  var seleccionados = Seccion.grid.getSelectionModel().getCount();
                  var engrilla = Seccion.storeSeccion.data.length;
                  Seccion.Seleccionados = '';
                  if(seleccionados!=0) {
                      if(engrilla >= seleccionados ) {
                          Ext.each(s, function(item){
                                Seccion.Seleccionados += '\'' +  item.data.COD_FLEX + '\',';
                          });
                      } 
                  }
              }
          }
    });
    
    Seccion.all=false;
    Seccion.grid = Ext.create('Ext.grid.Panel', {
        store: Seccion.storeSeccion,
        stateful: true,
        width: '50%',
        stateId: 'stateGrid3',
        selModel: smSeccion,
        title: 'Seccion',
        columns: [{
            text     : 'Codigo',
            flex     : 1,
            sortable : false,
            dataIndex: 'COD_FLEX'
        }/*, {
            text     : 'Descripcion',
            flex     : 1,
            sortable : true,
            dataIndex: 'DES_FLEX'
        }*/],
        viewConfig: {
            stripeRows: true
        },
        bbar: Ext.create('Ext.PagingToolbar', {
            store: Seccion.storeSeccion,
            displayInfo: true,
            displayMsg: 'Registro {0} a {1} de {2}',
            emptyMsg: "No hay Secciones a visualizar",
            items:[
                '-', {
                text: 'Todos',
                iconCls: 'icon-exclamation',
                enableToggle: true,
                toggleHandler: function(btn, pressed) {
                    Seccion.all=pressed;
                }
            }]
        }),
        tbar:[{
             xtype: 'textfield',
             name: 'searchSeccion',
             id: 'searchSeccion',
             text: 'Buscar',
             hideLabel: true,
             width: 200,
             listeners: {
                 change: {
                     fn: function(){
                        var value = Ext.getCmp('searchSeccion');
                        Seccion.storeSeccion.load({
                            params: {filter: value.getValue() }
                        }); 
                     },
                     scope: this,
                     buffer: 100
                 }
             }
        }]
    });
    
    Seccion.storeSeccion.on({ 
        'beforeload': {
            fn: function(store) {
              var value = Ext.getCmp('searchSeccion');
              if( value != '') {
                store.proxy.extraParams.filter = value.getValue();
              }
            },
            scope:this
         }
    });
    
});