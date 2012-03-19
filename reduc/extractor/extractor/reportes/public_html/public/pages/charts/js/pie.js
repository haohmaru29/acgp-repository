
Ext.require('Ext.chart.*');
Ext.require('Ext.layout.container.Fit');

Ext.onReady(function () {
    
     Ext.define('dataChartPie', {
        extend: 'Ext.data.Model',
        fields: ['CANTIDAD', 'IU_USUARIO']
    });
    
    Charts.storePie = Ext.create('Ext.data.JsonStore', {
          autoLoad: false,
          model: 'dataChartPie',
          remoteSort: true,
          proxy: {
              type: 'ajax',
              url: 'home/chart',
              method: 'post',
              reader: {
                  type: 'json',
                  root: 'data'
              }
          },
	        idProperty: 'CANTIDAD',
	        fields: ['CANTIDAD', 'IU_USUARIO']
	  });

     var  panel1 = Ext.create('widget.panel', {
        width: 800,
        height: 600,
        title: 'Semester Trends',
        //renderTo: Ext.getBody(),
        layout: 'fit',
        tbar: [{
            text: 'Reload Data',
            handler: function() {
                Charts.storePie.load();
            }
        }, {
            enableToggle: true,
            pressed: false,
            text: 'Donut',
            toggleHandler: function(btn, pressed) {
                var chart = Ext.getCmp('chartCmp');
                chart.series.first().donut = pressed ? 35 : false;
                chart.refresh();
            }
        }],
        items: {
            xtype: 'chart',
            id: 'chartCmp',
            animate: true,
            store: Charts.storePie,
            shadow: true,
            legend: {
                position: 'right'
            },
            insetPadding: 60,
            theme: 'Base:gradients',
            series: [{
                type: 'pie',
                field: 'CANTIDAD',
                showInLegend: true,
                donut: false,
                tips: {
                  trackMouse: true,
                  width: 140,
                  height: 28,
                  renderer: function(storeItem, item) {
                    //calculate percentage.
                    var total = 0;
                    Charts.storePie.each(function(rec) {
                        total += rec.get('CANTIDAD');
                    });
                    this.setTitle(storeItem.get('IU_USUARIO') + ': ' + Math.round(storeItem.get('CANTIDAD') / total * 100) + '%');
                  }
                },
                highlight: {
                  segment: {
                    margin: 20
                  }
                },
                label: {
                    field: 'IU_USUARIO',
                    display: 'rotate',
                    contrast: true,
                    font: '18px Arial'
                }
            }]
        }
    });
    
    Charts.Window = Ext.create('Ext.Window', {
        width: 600,
        height: 500,
        hidden: false,
        maximizable: true,
        title: 'Ingresos Usuarios',
        modal:true,
        closable: true,
        closeAction:'hide',
        layout: 'fit',
        tbar: [{
            iconCls: 'icon-refresh',
            text: 'Actualizar',
            tooltip: 'Actualizar grafico',
            handler: function() {
                Charts.storePie.load();
            }
        }],
        items: [ panel1 ]
    });
    
    Charts.Window.on('close', function() {
        Charts.Window.hide();
    });
});
