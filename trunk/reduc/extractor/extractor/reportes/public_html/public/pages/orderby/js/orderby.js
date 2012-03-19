Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.dd.*',
	'Ext.window.MessageBox'
]);

Ext.define('DataObject', {
    extend: 'Ext.data.Model',
    fields: ['name']
});

Ext.onReady(function(){

    var firstGridStore2 = [
        ['NUMERO ORDEN ATENCION', 'oa.NUM_ORDEN_ATENCION'],
        ['FECHA PAGO ORDEN', 'FECHA_PAGO_ORDEN2']
    ];

    var firstGridStore = Ext.create('Ext.data.ArrayStore', {
        fields: [
           {name: 'nombre'} , 
           {name: 'nombrebd'}
        ],
        data: firstGridStore2
    });
    
    var columns = [
        {text: "Descripcion", flex: 1, sortable: false, dataIndex: 'nombre'}
    ];

    var firstGrid = Ext.create('Ext.grid.Panel', {
        multiSelect: true,
        viewConfig: {
            plugins: {
                ptype: 'gridviewdragdrop',
                dragGroup: 'firstGridDDGroup',
                dropGroup: 'secondGridDDGroup'
            },
            listeners: {
                drop: function(node, data, dropRec, dropPosition) {
                    var dropOn = dropRec ? ' ' + dropPosition + ' ' + dropRec.get('nombre') : ' on empty view';
                }
            },
            forceFit:  true
        },
        store            : firstGridStore,
        columns          : columns,
        stripeRows       : true,
        title            : 'A seleccionar',
        margins          : '0 2 0 0'
    });

    var secondGridStore = Ext.create('Ext.data.Store', {
        model: 'DataObject'
    });

    DnD.secondGrid = Ext.create('Ext.grid.Panel', {
        viewConfig: {
            plugins: {
                ptype: 'gridviewdragdrop',
                dragGroup: 'secondGridDDGroup',
                dropGroup: 'firstGridDDGroup'
            },
            listeners: {
                drop: function(node, data, dropRec, dropPosition) {
                    var dropOn = dropRec ? ' ' + dropPosition + ' ' + dropRec.get('nombre') : ' on empty view';
                }
            },
            forceFit: true
        },
        store            : secondGridStore,
        columns          : columns,
        stripeRows       : true,
        title            : 'Seleccionados',
        margins          : '0 0 0 3'
    });

    Home.dnd = Ext.create('Ext.Panel', {
        layout       : {
            type: 'hbox'
            ,align: 'stretch'
            //,padding: 5
        },
        collapsible: true,
        title: 'Ordenar por',
        border: false,
        defaults     : { flex : 1 },
        items        : [ firstGrid,DnD.secondGrid ]
    });      
});
