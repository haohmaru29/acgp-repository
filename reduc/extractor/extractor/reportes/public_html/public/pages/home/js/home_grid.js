Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
    'Ext.toolbar.Paging'
]);

Ext.onReady(function() {
    var pageSize = 20;    
    Ext.define('data', {
        extend: 'Ext.data.Model',
        fields: ['USUARIO', 'NOMBRE']
    });
    
    Home.store = Ext.create('Ext.data.JsonStore', {
          autoLoad: false,
          pageSize: pageSize,
          model: 'data',
          params: {fechaInicio:Home.oldFechaIni , fechaTermino: Home.oldFechaFin , campos: Home.oldCampos},
          remoteSort: true,
          proxy: {
              type: 'ajax',
              url: 'home/busqueda',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
	        idProperty: 'USUARIO',
	        fields: ['USUARIO', 'NOMBRE']
	  });
    
    Home.store.on({ 
        'beforeload': {
            fn: function(s) {
                Home.store.proxy.extraParams.fechaInicio = Home.oldFechaIni;
                Home.store.proxy.extraParams.fechaTermino = Home.oldFechaFin;
                Home.store.proxy.extraParams.campos = Home.oldCampos;
            },
            scope:this
         },
         'exception': {
            fn: function(store, records, options ){
                System.MessageBox.error("Se ha producido un error: " + store );
            }
         }
    });

    Home.grid = Ext.create('Ext.grid.Panel', {
        store: Home.store,
        stateful: true,
        frame: false,
        border: false,
        stateId: 'stateGrid',
        columns: [{
                text     : 'idPerfil',
                width     : 100,
                sortable : false,
                dataIndex: 'USUARIO'
            }, {
                text     : 'perfilUsuario',
                flex     : 1,
                sortable : true,
                dataIndex: 'NOMBRE'
            }, {
                xtype: 'actioncolumn',
                width: 50,
                items: [{
                    icon   : 'library/icons/fam/delete.gif',  // Use a URL in the icon config
                    tooltip: 'Sell stock',
                    handler: function(grid, rowIndex, colIndex) {
                        var rec = Home.store.getAt(rowIndex);
                        alert("Sell " + rec.get('USUARIO'));
                    }
                }, {
                    getClass: function(v, meta, rec) {          // Or return a class from a function
                        
                    },
                    handler: function(grid, rowIndex, colIndex) {
                        var rec = Home.store.getAt(rowIndex);
                        alert((rec.get('change') < 0 ? "Hold " : "Buy ") + rec.get('USUARIO'));
                    }
                }]
            }
        ],
        title: 'Resultados',
        viewConfig: {
            stripeRows: true
        }
        ,bbar: Ext.create('Ext.PagingToolbar', {
            store: Home.store,
            displayInfo: true,
            displayMsg: 'Mostrando {0} - {1} de {2}',
            emptyMsg: "No hay datos para mostrar",
            pageSize : pageSize,
            items:[
                '-', {
                iconCls   : 'icon-chart-bar',  // Use a URL in the icon config
                tooltip: 'Visualizar grafico',
                handler: function(grid, rowIndex, colIndex) {
                    Charts.Window.show();
                    Charts.store.load();
                }
            }]
        })
    });
    
    Ext.apply(Ext.form.field.VTypes, {
        daterange: function(val, field) {
            var date = field.parseDate(val);

            if (!date) {
                return false;
            }
            if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
                var start = field.up('form').down('#' + field.startDateField);
                start.setMaxValue(date);
                start.validate();
                this.dateRangeMax = date;
            }
            else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
                var end = field.up('form').down('#' + field.endDateField);
                end.setMinValue(date);
                end.validate();
                this.dateRangeMin = date;
            }
            return true;
        },

        daterangeText: 'Fecha inicio debe ser menor a Fecha termino'
    });
    
    Home.dateIni= Ext.create('Ext.form.DateField', {
         fieldLabel: 'Fecha Inicio',
         name: 'startdt',
         id: 'startdt',
         editable: false,
         format: 'd/m/Y',
         vtype: 'daterange',
         endDateField: 'enddt' // id of the end date field
    });
    
    Home.dateEnd = Ext.create('Ext.form.DateField', {
         fieldLabel: 'Fecha Termino',
         name: 'enddt',
         id: 'enddt',
         vtype: 'daterange',
         editable: false,
         format: 'd/m/Y',
         value: new Date(),
         startDateField: 'startdt' // id of the start date field
    });
    
    Ext.create('Ext.FormPanel', {
          height: 40,
          frame: true,
          width: 'auto',
          renderTo: 'componentes',
          layout       : {
              type: 'hbox',
              align: 'stretch',
              padding: 5
          },
          fieldDefaults: {
            labelWidth: 100,
            msgTarget: 'side',
            autoFitErrors: false
          },
          items : [ Home.dateIni , Home.dateEnd ]
    });
    
    var displayPanel = Ext.create('Ext.Panel', {
          height: 452,
          width: 800,
          layout       : {
              type: 'hbox',
              align: 'stretch',
              padding: 5
          },
          renderTo     : 'tabla_result',
          defaults     : { flex : 1 }, 
          items        : [ Home.grid,Home.dnd ]
    });
});
