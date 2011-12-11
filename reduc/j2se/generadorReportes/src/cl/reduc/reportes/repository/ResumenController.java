package cl.reduc.reportes.repository;

import java.sql.ResultSet;

import cl.reduc.commons.database.oracle.OracleController;
import cl.reduc.reportes.utils.DateUtils;

public class ResumenController extends OracleController {

	/*
	public ResultSet findAllByEspecialidad(String mes, String anio, String especialidad) {
		String sql = "select  "
                + " mes_actual.CENTRO as CENTRO,  "
                + " case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end as CANTIDAD_" + DateUtils.getMes(Integer.parseInt(DateUtils.getMesAnterior(mes))) + "_de_" + anio + ", "
                + " case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end as CANTIDAD_MES_ACTUAL,  "
                + " (case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end - case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end) / case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end * 100 as VARIACION_MES, "
                + " case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end as CANTIDAD_ACUMULADO_ANTERIOR, "
                + " case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end as CANTIDAD_ACUMULADO_ACTUAL,   "
                + " (case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end - case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end) / case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end * 100 as VARIACION_ACUMULADO "
                + " from "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+) and "
                + " dt.ID_PRESTACION = ps.SINONIMO(+) and "
                + " ps.SINONIMO_TYC = tr.PHOSSINON(+) and "
                + " tr.PHOSCPRES = tp.COD_FLEX(+)) and "
                + " to_char(oa.FECHA_PAGO,'MM') = '" + mes + "' and  "
                + " to_char(oa.FECHA_PAGO,'YYYY') = '" + anio + "' and "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + " dt.FOLIO_RESERVA is not null ";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) mes_actual,  "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + "     (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + "     rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') = '" + mes + "' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '" + DateUtils.getMesAnterior(mes) + "' and  "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null ";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) mes_anterior, "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + "     (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + "     rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') <= '" + mes + "' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '" + anio + "' and  "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) acumulado_actual, "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + " dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + " to_char(oa.FECHA_PAGO,'MM') <= '" + mes + "' and  "
                + " to_char(oa.FECHA_PAGO,'YYYY') = '" + DateUtils.getAnioAnterior(anio) + "' and  "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + " dt.FOLIO_RESERVA is not null ";
                //+ "and "
                //+ " (tp.DES_FLEX like '%EXAMEN%') and ";
                sql+=validateEspecialidad(especialidad)
                //+ " es.DES_FLEX like upper('%" + especialidad + "%') "
                + " group by oa.SECCION) acumulado_anterior "
                + " where  "
                + " mes_actual.CENTRO = mes_anterior.CENTRO(+) and "
                + "     mes_actual.CENTRO = acumulado_actual.CENTRO(+) and "
                + "     mes_actual.CENTRO = acumulado_anterior.CENTRO(+)";
		
		return select(sql);
	}
	
	public ResultSet medicas(String especialidad, String anio, String mes, String prestacion) {
        String query = "select "
                + " mes_actual.CENTRO as CENTRO, "
                + " case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end as CANTIDAD_" + DateUtils.getMes(Integer.parseInt(DateUtils.getMesAnterior(mes))) + "_de_" + anio + ", "
                + " case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end as CANTIDAD_" + DateUtils.getMes(Integer.parseInt(mes)) + "_de_" + anio + ", "
                + " (case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end - case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end) / case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end * 100 as VARIACION_MESES, "
                + " case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end as A_" + DateUtils.getMes(Integer.parseInt(mes)) + "_de_" + DateUtils.getAnioAnterior(anio) + ", "
                + " case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end as A_" + DateUtils.getMes(Integer.parseInt(mes)) + "_de_" + anio + ", "
                + " (case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end - case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end) / case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end * 100 as VARIACION_ACUMULADO "
                + "from "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ORDEN_ATENCION oa, DETALLE_ORDEN_ATENCION dt, RESERVA rs, ESPECIALID es "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + " dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + " to_char(oa.FECHA_PAGO,'MM') = '" + mes + "' and "
                + " to_char(oa.FECHA_PAGO,'YYYY') = '" + anio + "' and "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + " dt.FOLIO_RESERVA is not null and "
                + " dt.PRESTACION like '%" + prestacion + "%' and "
                + "(dt.PRESTACION not like '%PSIQUIATRIA%' and dt.PRESTACION not like '%PSICOLOGIA%') ";
                query+=validateEspecialidad(especialidad)
                + "group by oa.SECCION) mes_actual,  "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ORDEN_ATENCION oa, DETALLE_ORDEN_ATENCION dt, RESERVA rs, ESPECIALID es "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + " dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "to_char(oa.FECHA_PAGO,'MM') = '" + DateUtils.getMesAnterior(mes) + "' and  "
                + "to_char(oa.FECHA_PAGO,'YYYY') = '" + anio + "' and "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "dt.FOLIO_RESERVA is not null and "
                + "dt.PRESTACION like '%" + prestacion + "%' and "
                + "(dt.PRESTACION not like '%PSIQUIATRIA%' and dt.PRESTACION not like '%PSICOLOGIA%') ";
                //"and "
                //+ "es.DES_FLEX like upper('%" + especialidad + "%') "
                query+=validateEspecialidad(especialidad)
                + "group by oa.SECCION) mes_anterior, "
                + "(select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + "from ORDEN_ATENCION oa, DETALLE_ORDEN_ATENCION dt, RESERVA rs, ESPECIALID es "
                + " where "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + " dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + " to_char(oa.FECHA_PAGO,'MM') <= '" + mes + "' and  "
                + " to_char(oa.FECHA_PAGO,'YYYY') = '" + anio + "' and  "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + " dt.FOLIO_RESERVA is not null and "
                + " dt.PRESTACION like '%CONSULTA%' and "
                + " (dt.PRESTACION not like '%PSIQUIATRIA%' and dt.PRESTACION not like '%PSICOLOGIA%') ";
                query+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) acumulado_actual, "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ORDEN_ATENCION oa, DETALLE_ORDEN_ATENCION dt, RESERVA rs, ESPECIALID es "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + " dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + " to_char(oa.FECHA_PAGO,'MM') <= '" + mes + "' and  "
                + " to_char(oa.FECHA_PAGO,'YYYY') = '" + DateUtils.getAnioAnterior(anio) + "' and  "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + " dt.FOLIO_RESERVA is not null and "
                + " dt.PRESTACION like '%CONSULTA%' and "
                + " (dt.PRESTACION not like '%PSIQUIATRIA%' and dt.PRESTACION not like '%PSICOLOGIA%') ";
                query+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) acumulado_anterior "
                + " where  "
                + " mes_actual.CENTRO = mes_anterior.CENTRO(+) and "
                + " mes_actual.CENTRO = acumulado_actual.CENTRO(+) and "
                + " mes_actual.CENTRO = acumulado_anterior.CENTRO(+) ";

        return select(query);
    }
	
	public ResultSet procedimientos(String mes, String anio, String especialidad) {
        String sql = "select "
                + "     mes_actual.CENTRO as CENTRO,  "
                + "     case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end as CANTIDAD_" + DateUtils.getMes(Integer.parseInt(DateUtils.getMesAnterior(mes))) + "_de_" + anio + ", "
                + "     case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end as CANTIDAD_MES_ACTUAL,  "
                + "     (case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end - case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end) / case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end * 100 as VARIACION_MES, "
                + "     case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end as CANTIDAD_ACUMULADO_ANTERIOR, "
                + "     case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end as CANTIDAD_ACUMULADO_ACTUAL,   "
                + "     (case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end - case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end) / case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end * 100 as VARIACION_ACUMULADO "
                + " from "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + " dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+) and "
                + " dt.ID_PRESTACION = ps.SINONIMO(+) and "
                + " ps.SINONIMO_TYC = tr.PHOSSINON(+) and "
                + " tr.PHOSCPRES = tp.COD_FLEX(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') = '" + mes + "' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '" + anio + "' and "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null and "
                + "     (tp.DES_FLEX like '%PROCEDIMIENTO%') ";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) mes_actual,  "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + " dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + " to_char(oa.FECHA_PAGO,'MM') = '" + DateUtils.getMesAnterior(mes) + "' and  "
                + " to_char(oa.FECHA_PAGO,'YYYY') = '" +anio + "' and  "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + " dt.FOLIO_RESERVA is not null and "
                + " (tp.DES_FLEX like '%PROCEDIMIENTO%') ";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) mes_anterior, "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + "     (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + "     rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') <= '" + mes + "' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '" + anio + "' and  "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null and "
                + "     (tp.DES_FLEX like '%PROCEDIMIENTO%') and "
                + "     es.DES_FLEX like upper('%" + especialidad + "%') "
                + " group by oa.SECCION) acumulado_actual, "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + " dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + " to_char(oa.FECHA_PAGO,'MM') <= '" + mes + "' and  "
                + " to_char(oa.FECHA_PAGO,'YYYY') = '" + DateUtils.getAnioAnterior(anio) + "' and "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + " dt.FOLIO_RESERVA is not null and "
                + " (tp.DES_FLEX like '%PROCEDIMIENTO%') ";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) acumulado_anterior "
                + " where  "
                + " mes_actual.CENTRO = mes_anterior.CENTRO(+) and "
                + " mes_actual.CENTRO = acumulado_actual.CENTRO(+) and "
                + " mes_actual.CENTRO = acumulado_anterior.CENTRO(+) ";

        return select(sql);
    }
	
	public ResultSet noMedicas(String mes, String anio) {
        String sql = " select  "
                + "     mes_actual.CENTRO as CENTRO,  "
                + "     case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end as CANTIDAD_" + DateUtils.getMes(Integer.parseInt(DateUtils.getMesAnterior(mes))) + "_de_" + anio + ", "
                + "     case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end as CANTIDAD_MES_ACTUAL,  "
                + "     (case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end - case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end) / case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end * 100 as VARIACION_MES, "
                + "     case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end as CANTIDAD_ACUMULADO_ANTERIOR, "
                + "     case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end as CANTIDAD_ACUMULADO_ACTUAL,   "
                + "     (case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end - case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end) / case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end * 100 as VARIACION_ACUMULADO "
                + " from "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es "
                + " where  "
                + "     (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + "     rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') = '"+mes+"' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '"+anio+"' and  "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null and "
                + "     (dt.PRESTACION like '%PSIQUIATRIA%' or dt.PRESTACION like '%PSICOLOGIA%' or dt.PRESTACION like '%KINESIO%' or dt.PRESTACION like '%FONOAUDIO%' or dt.PRESTACION like '% TERAP%' or dt.PRESTACION like '%ENFERMER%' or dt.PRESTACION like '%NUTRICIO%')  "
                + " group by oa.SECCION) mes_actual,  "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es "
                + " where  "
                + "     (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + "     rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') = '"+DateUtils.getMesAnterior(mes)+"' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '" + anio +"' and  "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null and "
                + "     (dt.PRESTACION like '%PSIQUIATRIA%' or dt.PRESTACION like '%PSICOLOGIA%' or dt.PRESTACION like '%KINESIO%' or dt.PRESTACION like '%FONOAUDIO%' or dt.PRESTACION like '% TERAP%' or dt.PRESTACION like '%ENFERMER%' or dt.PRESTACION like '%NUTRICIO%') "
                + " group by oa.SECCION) mes_anterior, "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es "
                + " where "
                + "     (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + "     rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') <= '"+mes+"' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '"+anio+"' and  "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null and "
                + "     (dt.PRESTACION like '%PSIQUIATRIA%' or dt.PRESTACION like '%PSICOLOGIA%' or dt.PRESTACION like '%KINESIO%' or dt.PRESTACION like '%FONOAUDIO%' or dt.PRESTACION like '% TERAP%' or dt.PRESTACION like '%ENFERMER%' or dt.PRESTACION like '%NUTRICIO%') "
                + " group by oa.SECCION) acumulado_actual, "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es "
                + " where  "
                + "     (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + "     rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') <= '"+mes+"' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '"+DateUtils.getAnioAnterior(anio) +"' and  "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null and "
                + "     (dt.PRESTACION like '%PSIQUIATRIA%' or dt.PRESTACION like '%PSICOLOGIA%' or dt.PRESTACION like '%KINESIO%' or dt.PRESTACION like '%FONOAUDIO%' or dt.PRESTACION like '% TERAP%' or dt.PRESTACION like '%ENFERMER%' or dt.PRESTACION like '%NUTRICIO%') "
                + " group by oa.SECCION) acumulado_anterior "
                + " where  "
                + "     mes_actual.CENTRO = mes_anterior.CENTRO(+) and "
                + "     mes_actual.CENTRO = acumulado_actual.CENTRO(+) and "
                + "     mes_actual.CENTRO = acumulado_anterior.CENTRO(+)  ";
        
        return select(sql);
    }
	
	public ResultSet examenes(String mes, String anio, String especialidad) {
        String sql = "select  "
                + " mes_actual.CENTRO as CENTRO,  "
                + " case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end as CANTIDAD_" + DateUtils.getMes(Integer.parseInt(DateUtils.getMesAnterior(mes))) + "_de_" + anio + ", "
                + " case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end as CANTIDAD_MES_ACTUAL,  "
                + " (case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end - case when mes_anterior.CANTIDAD is null then 0 else mes_anterior.CANTIDAD end) / case when mes_actual.CANTIDAD is null then 0 else mes_actual.CANTIDAD end * 100 as VARIACION_MES, "
                + " case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end as CANTIDAD_ACUMULADO_ANTERIOR, "
                + " case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end as CANTIDAD_ACUMULADO_ACTUAL,   "
                + " (case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end - case when acumulado_anterior.CANTIDAD is null then 0 else acumulado_anterior.CANTIDAD end) / case when acumulado_actual.CANTIDAD is null then 0 else acumulado_actual.CANTIDAD end * 100 as VARIACION_ACUMULADO "
                + " from "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+) and "
                + " dt.ID_PRESTACION = ps.SINONIMO(+) and "
                + " ps.SINONIMO_TYC = tr.PHOSSINON(+) and "
                + " tr.PHOSCPRES = tp.COD_FLEX(+)) and "
                + " to_char(oa.FECHA_PAGO,'MM') = '" + mes + "' and  "
                + " to_char(oa.FECHA_PAGO,'YYYY') = '" + anio + "' and "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + " dt.FOLIO_RESERVA is not null and "
                + " (tp.DES_FLEX like '%EXAMEN%') ";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) mes_actual,  "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + "     (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + "     rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') = '" + mes + "' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '" + DateUtils.getMesAnterior(mes) + "' and  "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null and "
                + "     (tp.DES_FLEX like '%EXAMEN%') ";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) mes_anterior, "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + "     (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + "     dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + "     rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + "     to_char(oa.FECHA_PAGO,'MM') <= '" + mes + "' and  "
                + "     to_char(oa.FECHA_PAGO,'YYYY') = '" + anio + "' and  "
                + "     upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + "     dt.FOLIO_RESERVA is not null and "
                + "     (tp.DES_FLEX like '%EXAMEN%') ";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) acumulado_actual, "
                + " (select distinct oa.SECCION as CENTRO, count(distinct oa.CODIGO) as CANTIDAD "
                + " from ADMAMED.ORDEN_ATENCION oa, ADMAMED.DETALLE_ORDEN_ATENCION dt, ADMAMED.RESERVA rs, ESPECIALID es, PREST ps, TARCPHOS tr, TPREST tp "
                + " where  "
                + " (oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) and "
                + " dt.FOLIO_RESERVA = rs.FOLIO(+) and "
                + " rs.ID_ESPECIALIDAD = es.SINONIMO(+)) and "
                + " to_char(oa.FECHA_PAGO,'MM') <= '" + mes + "' and  "
                + " to_char(oa.FECHA_PAGO,'YYYY') = '" + DateUtils.getAnioAnterior(anio) + "' and  "
                + " upper(oa.TIPO_ATENCION) = 'AMBULATORIO' and "
                + " dt.FOLIO_RESERVA is not null and "
                + " (tp.DES_FLEX like '%EXAMEN%') and ";
                sql+=validateEspecialidad(especialidad)
                + " group by oa.SECCION) acumulado_anterior "
                + " where  "
                + " mes_actual.CENTRO = mes_anterior.CENTRO(+) and "
                + "     mes_actual.CENTRO = acumulado_actual.CENTRO(+) and "
                + "     mes_actual.CENTRO = acumulado_anterior.CENTRO(+)";

        return select(sql);
    }
	*/
	
	/**
	 * Nuevas consultas
	 * 
	 */
	public ResultSet porEspecialidad(String mes, String anio, String especialidad, String seccion) {
		System.out.println("Mes Siguiente: " + DateUtils.getMesSiguiente(mes));
		
		String sql   = "select to_char(oa.fecha_pago,'yyyymm') as FECHA_PAGO,dt.DESC_ESPECIALIDAD AS ESPECIALIDAD,"
	             + " count(*) AS CANTIDAD,sum(dt.VALOR_PAGO)AS MONTO"
	             + " from ORDEN_ATENCION oa, DETALLE_ORDEN_ATENCION dt"  
	             + " where oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) ";       
		if(!seccion.toUpperCase().equals("TODOS")) {
		       sql += " AND OA.SECCION='" + seccion + "'";
		} 
		
		if(!especialidad.toUpperCase().equals("TODOS")) {
		       sql += " AND dt.DESC_ESPECIALIDAD='" + especialidad + "'";
		} 
       sql += " AND  (oa.FECHA_PAGO between to_date('01" + mes + DateUtils.getAnioAnterior(anio) + "','ddmmyyyy') AND to_date('01" + DateUtils.getMesSiguiente(mes) + DateUtils.getAnioAnterior(anio)  + "','ddmmyyyy')"
             + " or    oa.FECHA_PAGO between to_date('01" + mes + anio + "','ddmmyyyy') AND to_date('01" + DateUtils.getMesSiguiente(mes) + anio + "','ddmmyyyy'))" 
             + " and   upper(oa.TIPO_ATENCION) = 'AMBULATORIO' "
             + " group by to_char(oa.fecha_pago,'yyyymm'),dt.DESC_ESPECIALIDAD"
             + " order by dt.DESC_ESPECIALIDAD, to_char(oa.fecha_pago,'yyyymm') ";
            
             
		return select(sql);
		
	}
	
	public ResultSet porSeccion(String mes, String anio, String especialidad, String seccion) {
		System.out.println("Mes Siguiente: " + DateUtils.getMesSiguiente(mes));
		String sql   = "select to_char(oa.fecha_pago,'yyyymm') as FECHA_PAGO,oa.SECCION AS SECCION,"
	             + " count(*) AS CANTIDAD,sum(dt.VALOR_PAGO)AS MONTO"
	             + " from ORDEN_ATENCION oa, DETALLE_ORDEN_ATENCION dt"  
	             + " where oa.CODIGO = dt.CODIGO_ORDEN_ATENCION(+) ";
	             
		if(!especialidad.toUpperCase().equals("TODOS")) {
		       sql += " AND dt.DESC_ESPECIALIDAD='" + especialidad + "'";
		}
		
		if(!seccion.toUpperCase().equals("TODOS")) {
		       sql += " AND OA.SECCION='" + seccion + "'";
		} 
		
		sql += " AND  (oa.FECHA_PAGO between to_date('01" + mes + DateUtils.getAnioAnterior(anio) + "','ddmmyyyy') AND to_date('01" + DateUtils.getMesSiguiente(mes) + DateUtils.getAnioAnterior(anio) + "','ddmmyyyy')"
             + " or    oa.FECHA_PAGO between to_date('01" + mes + anio + "','ddmmyyyy') AND to_date('01" + DateUtils.getMesSiguiente(mes) + anio + "','ddmmyyyy'))" 
             + " and   upper(oa.TIPO_ATENCION) = 'AMBULATORIO' "
             + " group by to_char(oa.fecha_pago,'yyyymm'),oa.SECCION"
             + " order by oa.SECCION,to_char(oa.fecha_pago,'yyyymm')";
		
		return select(sql);
	}
	
	
	public static String validateEspecialidad(String especialidad) {
        String response = "";
        if(!especialidad.toUpperCase().equals("TODOS")) {
            response += "  AND es.DES_FLEX like upper('%" + especialidad + "%') ";
        } 
        
        return response;
    }
}
