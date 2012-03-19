Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
]);
Ext.onReady(function() {
    
    Historial.store = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        baseParams: {idUsuario: usuario, estado: 1},
        proxy: {
              type: 'ajax',
              url: 'historial/usuario',
              baseParams: {idUsuario: usuario, estado: 1},
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'ID_HISTORIAL_REPORTE',
	        fields: [ 'NOMBRE_ARCHIVO', 'ID_USUARIO', 'ID_HISTORIAL_REPORTE', 'FECHA_CREACION_E' ]
    });
    
    Historial.grid = Ext.create('Ext.grid.Panel', {
        store: Historial.store,
        stateful: true,
        stateId: 'stateGridHistorial',
        border: false,
        columns: [{
            text     : 'ARCHIVO',
            width     : 80,
            sortable : false,
            dataIndex: 'NOMBRE_ARCHIVO'
        }, {
            text     : 'FECHA CREACION',
            flex     : 150,
            sortable : false,
            dataIndex: 'FECHA_CREACION_E'
        }, {
            xtype: 'actioncolumn',
            width: 50,
            items: [{
                icon   : 'library/system/icons/magnifier.png', 
                tooltip: 'Visualizar reporte',
                handler: function(grid, rowIndex, colIndex) {
                    var rec = Historial.store.getAt(rowIndex);
                    System.download(rec.get('NOMBRE_ARCHIVO'));
                }
            }, {
                icon   : 'library/system/icons/delete.png',  // Use a URL in the icon config
                tooltip: 'Eliminar reporte',
                handler: function(grid, rowIndex, colIndex) {
                    var rec = Historial.store.getAt(rowIndex);
                    System.deleteFile(rec.get('NOMBRE_ARCHIVO'), '',rec.get('ID_HISTORIAL_REPORTE'));
                }
            }]
        }],
        viewConfig: {
            stripeRows: true
        }
    });
    
    Historial.store.on('beforeload', function(store){
        store.proxy.extraParams.idUsuario=usuario;
        store.proxy.extraParams.estado=1;
    });
});