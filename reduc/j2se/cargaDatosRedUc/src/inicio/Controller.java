package inicio;

import cl.reduc.commons.database.oracle.OracleController;
import cl.reduc.commons.utils.DateUtils;

public class Controller extends OracleController {

	public void loadData() {
		String anio = "2011";
		for (int x = 1; x <= 11; x++) {
			System.out.println(DateUtils.validaMont(x + "") + anio);
			String query = "INSERT INTO DET_ORDEN_ATENCION_AMB ( "
					+ " ID_PRESTACION,  " + " CORRELATIVO_PRESTACION, "
					+ " COD_PRESTACION, " + " DES_PRESTACION, "
					+ " TIPO_PRESTACION_INT, TIPO_PRESTACION_ORIGEN ," 
					+ " COD_CENTRO_RESULTADO,     "
					+ " DES_CENTRO_RESULTADO, " + " COD_AGRUPADOR, "
					+ " VALOR_CONVENIO, " + " VALOR_LISTA, "
					+ " VALOR_PAGO,      " + " NUM_ORDEN_ATENCION, "
					+ " CANTIDAD_PRESTACION, " + " FOLIO_RESERVA, "
					+ " RUT_PROF_RESERVA, " + " NOM_PROF_RESERVA, "
					+ " APA_PROF_RESERVA, " + " AMA_PROF_RESERVA, "
					+ " ESPECIALIDAD, " + " TIPO_CONSULTA " + " ) "
					+ " SELECT DISTINCT "
					+ " lec.PREST_SINONIMO  AS ID_PRESTACION, "
					+ " lec.CORRELATIVO     AS CORRELATIVO_PRESTACION, "
					+ " pr.COD_FLEX 		AS COD_PRESTACION, "
					+ " pr.DES_FLEX 		AS DES_PRESTACION, "
					+ " cre.TIPO_PRESTACION	AS TIPO_PRESTACION_INT, "
					+ " ''	AS TIPO_PRESTACION_ORIGEN, "
					+ " cr.COD_FLEX 		AS COD_CENTRO_RESULTADO, "
					+ " cr.DES_FLEX 		AS DES_CENTRO_RESULTADO, "
					+ " lec.GRUPO_TYC 		AS COD_AGRUPADOR, "
					+ " lec.PRECIO_CONVENIO AS VALOR_CONVENIO, "
					+ " lec.PRECIO_LISTA 	AS VALOR_LISTA, "
					+ " lec.PRECIO_A_PAGAR  AS VALOR_PAGO, "
					+ " lec.OACREA_SINONIMO AS NUM_ORDEN_ATENCION, "
					+ " lec.CANTIDAD 		AS CANTIDAD_PRESTACION, "
					+ " lec.FOLIO_ATARESE   AS FOLIO_RESERVA, "
					+ " ps.RUT 				AS RUT_PROF_RESERVA, "
					+ " ps.NOMBRES 			AS NOM_PROF_RESERVA, "
					+ " ps.A_PATERNO 		AS APA_PROF_RESERVA, "
					+ " ps.A_MATERNO 		AS AMA_PROF_RESERVA, "
					+ " ''    AS ESPECIALIDAD, "
					+ " NVL(cre.TIPO_CONSULTA,'') AS TIPO_CONSULTA "
					+ " FROM OPS$NUCLEO.LAB_EXAMENES_CREADOS lec, "
					+ " PREST pr, " + " CENRES cr, "
					+ " ops$nucleo.lab_oas_creadas oa, "
					+ " OPS$ADMATA.ATA_RESERVA rs, " + " PERS ps, "
					+ " TARCAGRU ag, " + "  CONSULTA_RECLASIF cre "
					+ " WHERE lec.PREST_SINONIMO = pr.SINONIMO(+) "
					+ " AND lec.CR_SINONIMO = cr.SINONIMO(+) "
					+ " AND lec.OACREA_SINONIMO = oa.sinonimo(+) ";
			if (x == 1) {
				query += " AND oa.FECHA_PAGO BETWEEN to_date('01"
						+ DateUtils.validaMont(x + "") + anio + "','ddmmyyyy')";
			} else {
				query += " AND oa.FECHA_PAGO BETWEEN to_date('02"
						+ DateUtils.validaMont(x + "") + anio + "','ddmmyyyy')";
				
			}
			if (x == 1) {
				query += " and to_date('01" + DateUtils.validaMont(x + 1 + "")
						+ anio + "','ddmmyyyy')";
			} else if (x == 12) {
				query += " AND to_date('31" + DateUtils.validaMont(x + "")
						+ anio + "','ddmmyyyy')";
				;
			} else {
				query += " AND to_date('01" + DateUtils.validaMont(x + 1 + "")
						+ anio + "','ddmmyyyy')";
				
			}
			query += " + 0.99999 " + " and lec.FOLIO_ATARESE = rs.FOLIO(+) "
					+ " AND rs.MEDICO_SINONIMO = ps.SINONIMO(+) "
					+ " AND lec.GRUPO_TYC = ag.AGRUCODIG(+) "
					+ " AND pr.COD_FLEX = cre.COD_PRESTACION(+)";

			System.out.println("Resultado de consulta: " + update(query));

		}

	}
}
