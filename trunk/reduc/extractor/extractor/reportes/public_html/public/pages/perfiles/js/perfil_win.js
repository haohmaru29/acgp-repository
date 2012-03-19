var array = new Array(20);
var contadorArray = 0;
function dropElement(id, idX, nombre) {
    var array2 = new Array(20);
    var cont = 0;
    for(var x=0; x<array.length;x++) {
        if(nombre!=array[x]) {
            array2[cont] = array[x];
            cont++;
        } else {
            contadorArray--;        
        }
    }
    array = new Array(20);
    for(var x=0; x<array2.length;x++) {
        array[x]= array2[x];
    }
    Ext.destroy(Ext.get(id));
    Ext.destroy(Ext.get(idX));
}

function effect(record) {
      array[contadorArray] = record[0].data.NOMBRE;
      contadorArray++;
      var id= record[0].data.ID_USUARIO;
      Ext.get('nombre_dyn').dom.innerHTML = '';
      Ext.get('img_del').dom.innerHTML = '';
      Ext.get('pruebas__').dom.innerHTML='';
      Ext.fly('nombre_dyn').createChild({
          tag: 'div',
          id: id + 'L'
      });
      
      Ext.fly('img_del').createChild({
          tag: 'div',
          id: id + 'M'
      });
      
      Ext.get( id + 'L' ).dom.innerHTML = record[0].data.NOMBRE;    
      Ext.get( id + 'M' ).dom.innerHTML = '<a href="javascript:void(0);" onclick="dropElement(\'' + id + 'L' + '\', \'' + id + 'M' + '\',\''+ record[0].data.NOMBRE +'\');dropElement(\'' + id + 'L' + '\', \'' + id + 'M' + '\',\''+ record[0].data.NOMBRE +'\');">[x]</a>';    
      var image = Ext.fly('pruebas__').createChild({
          tag: 'div',
          id: id,
          animateTarget: 'perfil_win',
          html: Ext.get('tabla_valores').dom.innerHTML
      });
            
      image.hide().show({
        duration: 500
      }).animate({callback: function() {}
      }).frame();     
}

Ext.onReady(function() {    
    Perfil.ususarioStore = Ext.create('Ext.data.JsonStore', {
        autoLoad: false,
        proxy: {
              type: 'ajax',
              url: 'perfil/usuarios',
              method: 'post',
              timeout: 550000,
              reader: {
                  type: 'json',
                  root: 'data',
                  totalProperty : 'count'
              }
          },
          idProperty: 'ID_USUARIO',
          fields: ['ID_USUARIO', 'NOMBRE' ]
    });
    
    Perfil.formPanel = Ext.create('Ext.form.Panel', {
        waitMsgTarget: true,
        layout: 'absolute',
        border: false,
        //waitMsg:'Guardando, espere por favor...',
        items : [ {
            x: 5,
            y: 25,
            xtype: 'combo',
            fieldLabel: 'Usuarios',
            displayField: 'NOMBRE',
            valueField: 'ID_USUARIO',
            name: 'usuarios',
            id: 'usuarios_cb',
            multiSelect: false,
            editable: false,
            allowBlank: false,
            forceSelection: true,
            emptyText:'Seleccione usuarios...',
            store: Perfil.ususarioStore,
            typeAhead: true,
            queryMode: 'remote',
            listeners: {
                'select': function(combo, record, index) {
                    var valor = false;
                    if(contadorArray==0) {
                        effect(record);
                    } else {
                        var size = array.length;
                        for(var y =0; y<size; y++) {
                            if(array[y]==record[0].data.NOMBRE) {
                                valor = false;
                                break;
                            } else {
                                valor = true;
                            }
                        }
                    }
                    if(valor == true) {
                      effect(record);
                    }
                }
            },
            anchor: '-5'
        }, {
            x: 5,
            y:55,
            height: 100,
            contentEl: 'perfil_win'
        }, {
            x:5,
            y: 275,
            xtype : 'hidden',
            name : 'usuario'
        }, {
              x:5,
              y: 305,
              xtype : 'hidden',
              name : 'mngr',
              value: 'Evento'
        }]
    });
    
    Perfil.win=Ext.create('Ext.Window', {
        width: 500,
        height: 500,
        hidden: false,
        maximizable: false,
        title: 'Usuarios',
        modal:true,
        closable: true,
        closeAction:'hide',
        layout: 'fit',
        items: [ Perfil.formPanel ],
        buttons: [{
            text: 'Guardar',
            handler: function() {
                alert(1);
            }
        }, {
            text: 'Limpiar',
            handler: function() {
                Perfil.formPanel.getForm().reset();
            }
        }]
    });
    
    Ext.get('btn_pruebas').on('click', function() {
          Perfil.win.show();
    });
});