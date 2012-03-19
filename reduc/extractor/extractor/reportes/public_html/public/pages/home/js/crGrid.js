function reLocation(fileName) {
     System.download(fileName);
}

function delFile(url, id, idHistorial) {
     Ext.Ajax.request({ 
          url : 'excel/delExcel?file=' + url+'&user=' + usuario+'&idHistorial=' + idHistorial, 
          success: function( response, request ) { 
              System.MessageBox.info('Reporte eliminado correctamente');
              Ext.destroy(Ext.get(id));
          },
          failure: function ( result, request ) { 
              System.MessageBox.error('Se ha producido un error al eliminar reporte, favor contacte con la mesa de ayuda');          
          } 
     });
}

Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
    'Ext.toolbar.Paging'
]);

Ext.onReady(function() {
    Ext.apply(Ext.form.field.VTypes, {
        daterange: function(val, field) {
            var date = field.parseDate(val);
            if(!date) return false;
            if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
                var start = Ext.getCmp(field.startDateField);
                start.setMaxValue(date);
                start.validate();
                this.dateRangeMax = date;
            }
            else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
                var end = Ext.getCmp(field.endDateField);
                end.setMinValue(date);
                end.validate();
                this.dateRangeMin = date;
            }
            return true;
        },
        daterangeText: 'Fecha inicio debe ser menor a Fecha termino'
    });
    
    Home.dateIni= Ext.create('Ext.form.DateField', {
         fieldLabel: 'Fecha Inicial',
         name: 'startdt',
         id: 'startdt',
         editable: false,
         format: 'd/m/Y',
         width: 200,
         margins:'0 3 0 0',
         vtype: 'daterange',
         maxValue: new Date(),
         endDateField: 'enddt' // id of the end date field
    });
    
    Home.dateEnd = Ext.create('Ext.form.DateField', {
         fieldLabel: 'Fecha Termino',
         name: 'enddt',
         id: 'enddt',
         vtype: 'daterange',
         width: 200,
         margins:'0 3 0 20',
         editable: false,
         format: 'd/m/Y',
         maxValue: new Date(),
         startDateField: 'startdt' // id of the start date field
    });
    
    CentroResultado.panel = Ext.create('Ext.Panel', {
          frame: false,
          border: false,
          height: '100%',
          margins: '0 0 0 0',
          defaults     : { flex : 1, autoScroll: true },
          layout: { 
              type: 'accordion',
              align: 'stretch'
          },
          items : [ CentroResultado.grid, Seccion.grid,Especialidad.grid, 
              Paciente.grid, Atencion.grid,Profesional.grid, Prestacion.gridCodigo, 
              Prestacion.gridTipo, Institucion.grid
          ]
    });
    
    CentroResultado.finalPanel = Ext.create('Ext.Panel', {
        frame: false,
        border: false,
        title: 'Filtros',
        layout       : {
            type: 'hbox'
            ,align: 'stretch'
        },
        defaults     : { flex : 1 }, //auto stretch
        items        : [ CentroResultado.panel],
        tbar:[Home.dateIni ,Home.dateEnd]
    });
    
    CentroResultado.finalPanel2 = Ext.create('Ext.Panel', {
        frame: false,
        border: false,
        title: 'Encabezados',
        layout       : {
            type: 'vbox'
            ,align: 'stretch'
        },
        defaults     : { flex : 1 }, 
        items        : [ Campos.grid,Home.dnd ]
    });
    
    CentroResultado.panelInproccess = Ext.create('Ext.Panel', {
          frame: false,
          border: false,
          layout: 'fit',
          defaults: {autoScroll: true, anchor: 1}, 
          title: 'En progreso',
          contentEl: 'fileHistory'
      });
      
      CentroResultado.panelFinalizado = Ext.create('Ext.Panel', {
          frame: false,
          title: 'Finalizados',
          border: false,
          defaults: {autoScroll: true,anchor: 1}, 
          layout: 'fit',
          autoscroll: true,
          contentEl: 'finalizados'
      });
      
      CentroResultado.historial = Ext.create('Ext.Panel', {
          frame: false,
          title: 'Historial',
          border: false,
          layout: 'fit',
          split: true,
          collapsible: true,
          items: [Historial.grid]
      });
      
      CentroResultado.panelWest = Ext.create('Ext.Panel', {
          frame: false,
          defaults     : { flex : 1, anchor: 1, autoScroll: true },
          layout: {
              type: 'vbox',
              align: 'stretch'
          },
          items: [ CentroResultado.panelInproccess, CentroResultado.historial ]
    });
    
    CentroResultado.viewport = Ext.create('Ext.Viewport', {
          layout:'border',
          renderTo: Ext.getBody(),
          viewConfig: {
            forceFit:  true                     
          },
          items:[{
            region:'north',
            margins:'2 2 2 2',
            layout: 'fit',
            border: false,
            contentEl: 'north'
          },{
              region:'west',
              width: 280,
              margins:'2 2 2 2',
              layout: 'fit',
              collapsible: true,
              items: [CentroResultado.finalPanel2]
          }, {
              region:'center',
              margins:'2 2 2 2',
              layout: 'fit',
              items: [CentroResultado.finalPanel]
               ,dockedItems: {
                  xtype: 'toolbar',
                  dock: 'bottom',
                  border: false,
                  id: 'mb9',
                  items: ['->',{
                      iconCls: 'page-excel-icon',
                      text: 'Generar Reporte',
                      handler: function(){
                          var order ='';
                          DnD.secondGrid.store.each(function(record) {
                              order=order + record.data['nombrebd'] + ',';
                          });
                          if(Ext.getCmp('startdt').getValue() == null) {
                              System.MessageBox.info('Debe Seleccionar <b>Fecha Inicial</b>');return false;
                          }
                          if(Ext.getCmp('enddt').getValue() == null) {
                              System.MessageBox.info('Debe seleccionar <b>Fecha Termino</b>');return false;
                          }
                          if(CamposCount=='') {
                              System.MessageBox.info('Debe seleccionar algun <b>Encabezado</b>');return false;
                          }
                          var fechaInicial = Ext.Date.format( Ext.getCmp('startdt').getValue(), 'd/m/Y');
                          var fechaFinal = Ext.Date.format( Ext.getCmp('enddt').getValue(), 'd/m/Y');
                          
                          var centro_resultado = '';
                          
                          var opcion=GlobalCount;
                          /*if(opcion=='' && CentroResultado.all == false) {
                              System.MessageBox.error('Debe seleccionar un centro de resultados');
                          } else {*/
                          if(CentroResultado.all!=false || Especialidad.all!= false || Seccion.all!= false || Especialidad.Seleccionados!='' || Seccion.Seleccionados!=''
                              || PrestacionTipo.Seleccionados!= '' || Atencion.Seleccionados!='' || Paciente.Seleccionados!='' || Profesional.Seleccionados!='' 
                              || Institucion.Seleccionados != '' || Prestacion.tipoAll!=false || Atencion.All!= false || Paciente.All!= false || Profesional.All!=false
                              || Institucion.All!= false || PrestacionCodigos.Seleccionados!='' || Prestacion.codigoAll!='' || opcion!='') {
                                Ext.MessageBox.prompt(System.name,'Ingrese nombre del archivo', function(btn, text) {
                                    if(btn == 'ok') {
                                        if(text == '') System.MessageBox.info('Debe ingresar nombre del archivo');
                                        else {
                                              Ext.Ajax.request({ 
                                                  url : 'excel/validatePath',
                                                  timeout: 5085000,
                                                  success: function( response, request ) { 
                                                      var jsonData = Ext.decode(response.responseText); 
                                                      if(jsonData.success == false) {
                                                          System.MessageBox.info(jsonData.value);
                                                          return false;
                                                      } else {
                                                          var url = 'excel/export?file=' + text + '&opcion=' + opcion + '&fechaInicio=' + fechaInicial + '&fechaFin=' + fechaFinal+'&campos=' + CamposCount+'&orderBy=' +order+'&user=' + usuario + '&crAll=' ;
                                                          url=  url + CentroResultado.all + '&espAll='+Especialidad.all + '&seccAll=' + Seccion.all;
                                                          url=  url + '&espSelected='+Especialidad.Seleccionados + '&seccSelected='+Seccion.Seleccionados;
                                                          url = url + '&prestacionTipo=' + PrestacionTipo.Seleccionados + '&atencionTipo='+Atencion.Seleccionados;
                                                          url = url + '&pacienteTipo='+Paciente.Seleccionados +'&profesional='+Profesional.Seleccionados;
                                                          url = url + '&institucion='+Institucion.Seleccionados + '&prestacionTipoAll='+ Prestacion.tipoAll;
                                                          url = url + '&atencionAll=' +Atencion.All + '&pacienteTipoAll=' + Paciente.All;
                                                          url = url + '&profesionalAll=' + Profesional.All + '&institucionAll='+ Institucion.All;
                                                          url = url + '&prestacionCodigo='+ PrestacionCodigos.Seleccionados + '&prestacionCodigoAll=' + Prestacion.codigoAll;
                                                          Ext.Ajax.request({ 
                                                              url : url,
                                                              timeout: 9085000,
                                                              success: function( response, request ) { 
                                                                  var jsonData = Ext.decode(response.responseText); 
                                                                  if(jsonData.success == true) {
                                                                      Ext.destroy(Ext.get(text));
                                                                      /*
                                                                      document.getElementById('verArchivo').innerHTML = '<div  style="float:left;height:10px;"><a href="javascript:void(0);"  onclick="javascript:reLocation(\'' + jsonData.data.patFile + '\');">Ver</a></div>';
                                                                      document.getElementById('delArchivo').innerHTML = '<div style="float:right;"><a href="javascript:void(0);"  onclick="javascript:delFile(\'' + jsonData.data.patFile + '\', \'' + text + '\', \'' + jsonData.data.idHistorial + '\' );">Del</a></div>';
                                                                      document.getElementById('fileText').innerHTML = '<b>'  + jsonData.data.fileName + ".xls</b>";
                                                                      document.getElementById('statusText').innerHTML = '';
                                                                      Ext.fly('statusText').createChild({
                                                                        tag: 'div',
                                                                        id: text + '1'
                                                                      });*/
                                                                      Historial.store.load({
                                                                          params: {idUsuario: usuario}
                                                                      });
                                                                      /*
                                                                      Ext.get(jsonData.data.fileName + '1').dom.innerHTML = '<b>Finalizado....</b>';  
                                                                      var image = Ext.fly('finalizados').createChild({
                                                                          tag: 'div',
                                                                          id: text,
                                                                          animateTarget: 'finalizados',
                                                                          html: Ext.get('fileHistoryBar').dom.innerHTML
                                                                      });
                                                                      
                                                                      image.hide().show({
                                                                        duration: 500
                                                                      }).animate({
                                                                        callback: function() {
                                                                        
                                                                        }
                                                                      }).frame();    
                                                                      */
                                                                  } else {
                                                                      Ext.get(text + '1').dom.innerHTML = '<b>No generado....</b>';  
                                                                      System.MessageBox.info('Problemas al generar archivo: ' + jsonData.data);
                                                                  }
                                                              }, 
                                                              failure: function ( result, request ) { 
                                                                  var jsonData = Ext.decode(response.responseText); 
                                                                  Ext.get(text + '1').dom.innerHTML = '<b>No generado....</b>';  
                                                                  System.MessageBox.error('<b>Problemas al generar archivo </b>');          
                                                              } 
                                                        }); 
                                                        Ext.get('fileText').dom.innerHTML = '<b>'  + text + ".xls</b>";
                                                        Ext.get('statusText').dom.innerHTML = '';
                                                        Ext.get('verArchivo').dom.innerHTML = '';
                                                        Ext.get('delArchivo').dom.innerHTML = '';
                                                        Ext.fly('statusText').createChild({
                                                            tag: 'div',
                                                            id: text + '1'
                                                        });
                                                        Ext.get(text + '1').dom.innerHTML = '<b>Generando....</b>';  
                                                        var image = Ext.fly('fileHistory').createChild({
                                                            tag: 'div',
                                                            id: text,
                                                            animateTarget: 'fileHistory',
                                                            html: Ext.get('fileHistoryBar').dom.innerHTML
                                                        });
                                                        image.show();
                                                          
                                                      }
                                                  }
                                              });
                                        }
                                   }
                                });
                          } else {
                               System.MessageBox.info('Debe seleccionar algun otro filtro aparte de la fecha.');
                          }
                      }
                  }]
             }
          },{
              region: 'east',
              title: 'Estados Reportes',
              margins:'2 2 2 2',
              width: 260,
              layout: 'fit',
              collapsible: true,
              autoScroll: true,
              items: [CentroResultado.panelWest]
          },{
              region: 'south',
              contentEl: 'footer',
              border:false,
              frame: false
          }]
      });
      var datosUsuario =new Ext.ux.Element('home/usuario', function( response, request ) { 
        var jsonData = Ext.decode(response.responseText); 
        if(jsonData.success == true) {
            usuario=jsonData.value;
            GlobalMask.hide();
        }
      }, null );
      var testConnection =new Ext.ux.Element('security/connection', function( response, request ) { 
        var jsonData = Ext.decode(response.responseText); 
        if(!jsonData.success) {
            System.MessageBox.error(jsonData.value);
        }
      }, null );
      
      /*Pool de conexiones....*/
      var pool= new Ext.ux.ajax.pool();
      pool.appendObject( datosUsuario );
      pool.appendObject(testConnection );
      pool.appendObject( CentroResultado.grid );
      pool.appendObject( Seccion.grid );
      pool.appendObject( Especialidad.grid );
      pool.appendObject( Historial.grid );
      pool.appendObject( Paciente.grid );
      pool.appendObject( Atencion.grid );
      pool.appendObject( Profesional.grid );
      pool.appendObject( Prestacion.gridCodigo );
      pool.appendObject( Prestacion.gridTipo );
      pool.appendObject( Institucion.grid );
      pool.init();
});
