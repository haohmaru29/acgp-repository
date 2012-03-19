Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*'
]);

Ext.onReady(function() {
    Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
    
    var myData2 = [
        ['NUMERO ORDEN ATENCION', 'oa.NUM_ORDEN_ATENCION'],
        ['FECHA ORDEN ATENCION', 'oa.FECHA_CREACION_OA'],
        ['FECHA PAGO OA', 'oa.FECHA_PAGO_OA'],
        ['OBSERVACION', 'oa.OBSERVACION'],
        ['COD SECCION ORIGEN', 'oa.COD_SECCION_ORIGEN'],
        ['TIPO PRESTACION RED', 'dt.SECCION'],
        ['DES LUGAR PAGO', 'oa.DES_LUGAR_PAGO'],
        ['LUGAR PAGO', 'oa.TIPO_PACIENTE'],
        ['TIPO PACIENTE', 'oa.TIPO_PACIENT'],
        ['TIPO ATENCION', 'oa.TIPO_ATENCION'],
        ['COD CONVENIO', 'oa.COD_CONVENIO'],
        ['DES CONVENIO', 'oa.DES_CONVENIO'],
        ['INSTITUCION CONVENIO', 'oa.INSTITUCION_CONVENIO'],
        ['RUT PACIENTE', 'oa.RUT_PACIENTE'],
        ['DIRECCION PACIENTE', 'oa.DIRECCION_PAC'],
        ['COMUNA PACIENTE', 'oa.COMUNA_PAC'],
        ['NOMBRE PACIENTE', 'oa.TELEFONO_PAC'],
        ['RUT MEDICO', '(oa.NOM_PACIENTE || \' \' || oa.APA_PACIENTE || \' \'|| oa.AMA_PACIENTE)'],
        ['NOMBRE MEDICO', 'oa.RUT_MED_DERIVADOR'],
        ['COD PREVISION', '(oa.NOM_MED_DERIVADOR || \' \' || oa.APA_MED_DERIVADOR || \' \' || oa.AMA_MED_DERIVADOR)'],
        ['DES PREVISION', 'oa.COD_PREVISION'],
        ['FECHA NACIMIENTO', 'oa.DES_PREVISION'],
        ['EDAD PACIENTE', 'TO_CHAR(oa.FECHA_NACIMIENTO, \'dd/mm/yyyy\')'],
        ['SEXO PACIENTE', 'oa.SEXO_PACIENTE'],
        ['CODIGO PRESTACION', 'dt.COD_PRESTACION'],
        ['DESCRIPCION PRESTACION', 'dt.DES_PRESTACION'],
        ['TIPO PRESTACION', 'dt.TIPO_PRESTACION_INT'],
        ['CODIGO CENTRO RESULTADO', 'dt.COD_CENTRO_RESULTADO'],
        ['DESCRIPCION CENTRO RESULTADO', 'dt.DES_CENTRO_RESULTADO '],
        ['CODIGO AGRUPADOR', 'dt.COD_AGRUPADOR'],
        ['VALOR CONVENIO ', 'dt.VALOR_CONVENIO'],
        ['VALOR LISTA', 'dt.VALOR_LISTA'],
        ['MONTO PAGADO', 'dt.VALOR_PAGO'],
        ['CANTIDAD PRESTACION', 'dt.CANTIDAD_PRESTACION'],
        ['FOLIO RESERVA', 'dt.FOLIO_RESERVA'],
        ['RUT PROF RESERVA', 'dt.RUT_PROF_RESERVA'],
        ['NOMBRE MEDICO RESERVA', '(dt.NOM_PROF_RESERVA || \' \' || dt.APA_PROF_RESERVA || \' \' || dt.AMA_PROF_RESERVA)'],
        ['ESPECIALIDAD', 'dt.ESPECIALIDAD'],
        ['TIPO CONSULTA', 'dt.TIPO_CONSULTA']
        , ['CODIGO FONASA', 'pf.COD_FONASA']
    ];

    Campos.store = Ext.create('Ext.data.ArrayStore', {
        fields: [
           {name: 'nombre'} , 
           {name: 'nombrebd'}
        ],
        data: myData2
    });

    var smCampos = Ext.create('Ext.selection.CheckboxModel', {
        listeners: {
            selectionchange: function(model, records) {
                var s = Campos.grid.getSelectionModel().getSelection();
                var seleccionados = Campos.grid.getSelectionModel().getCount();
                var engrilla = Campos.store.data.length;
                CamposCount = '';
                if(seleccionados!=0) {
                    if(engrilla > seleccionados ) {
                        Ext.each(s, function(item){
                              CamposCount +=   item.data.nombrebd + ' as ' + item.data.nombre.replace(/ /gi,'_') + ',';
                        });
                    } else {
                        CamposCount = 'TODOS';
                    }
                }
            }
        }
    });
    Campos.grid = Ext.create('Ext.grid.Panel', {
        store: Campos.store,
        stateful: true,
        stateId: 'stateGrid',
        selModel: smCampos,
        columns: [{
            text     : 'Nombre',
            flex     : 1,
            sortable : true,
            dataIndex: 'nombre'
        }],
        viewConfig: {
            stripeRows: true
        }
    });
});

