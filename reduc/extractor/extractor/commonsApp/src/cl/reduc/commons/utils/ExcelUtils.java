package cl.reduc.commons.utils;
import cl.reduc.commons.ofimatica.excel.WriteExcel;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
  private WriteExcel excel;
	private int paginas;
	private String fecha;
	private int cont;
	private int contWriter;
  private Runtime runtime;
	
	public ExcelUtils(String path, String fecha) {
		paginas=0;
		this.fecha = fecha;
		cont=1;
		contWriter=0;
		excel = new WriteExcel();
		excel.createWorkBook(path);
		excel.setFormatArialBold();
    runtime = Runtime.getRuntime ();
	}
	
	public static void resultSetToExcel(ResultSet rs, String path) {
		try {
			WriteExcel excel = new WriteExcel();
			excel.createWorkBook(path);
			excel.createSheet();
			excel.setFormatArialBold();
			ResultSetMetaData rsMetaData = rs.getMetaData();
		    int numberOfColumns = rsMetaData.getColumnCount();
		    String value = "";
		    for (int i=0; i < numberOfColumns; i++) {
		    	value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ');
		    	if(!value.equals("CENTRO"))
		    		excel.addCell(i, 0, value);
		    }
			
			excel.setFormatArialNoBold();
			int cont = 1;
			int paginas=0;
			while(rs.next()) {
				for(int x=1;x<=numberOfColumns;x++) {
					excel.addCell(x-1, cont, rs.getString(x));
                }
				if(cont>45000) {
					paginas++;
					excel.createSheet(paginas, "Pagina " + paginas);
					cont=1;
				}
				cont++;
			}
			excel.writeExcel();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultset(rs);
		}
	}
	
	public static void resultSetToExcelSinFiltro(ResultSet rs, String path) {
		WriteExcel excel = new WriteExcel();
		try {
			excel.createWorkBook(path);
			excel.createSheet();
			excel.setFormatArialBold();
			ResultSetMetaData rsMetaData = rs.getMetaData();
		    int numberOfColumns = rsMetaData.getColumnCount();
		    String value = "";
		    for (int i=0; i < numberOfColumns; i++) {
		    	value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ');
		    	excel.addCell(i, 0, value);
		    }
			excel.setFormatArialNoBold();
			int cont = 1;
			int cont2= 1;
			int paginas=0;
			while(rs.next()) {
				System.out.println("Cantidad de Registros :" + cont2);
				for(int x=1;x<=numberOfColumns;x++) {
					excel.addCell(x-1, cont, rs.getString(x));
                }
				if(cont>10000) {
					paginas++;
					excel.createSheet(paginas, "Pagina " + paginas);
					cont=1;
					System.gc();
					
				}
				if(cont2>240000) {
					break;
				}
				cont++;
				cont2++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultset(rs);
			excel.writeExcel();
		}
	}
	
	public static void resultSetToExcelSinFiltro(ResultSet rs, String path, String fecha) {
		WriteExcel excel = new WriteExcel();
		try {
			excel.createWorkBook(path);
			excel.createSheet(0,fecha);
			excel.setFormatArialBold();
			ResultSetMetaData rsMetaData = rs.getMetaData();
		    int numberOfColumns = rsMetaData.getColumnCount();
		    String value = "";
		    for (int i=0; i < numberOfColumns; i++) {
		    	value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ');
		    	excel.addCell(i, 0, value);
		    }
			excel.setFormatArialNoBold();
			int cont = 1;
			int cont2= 1;
			int paginas=0;
			while(rs.next()) {
				System.out.println("Cantidad de Registros :" + cont2);
				for(int x=1;x<=numberOfColumns;x++) {
					excel.addCell(x-1, cont, rs.getString(x));
                }
				if(cont>10000) {
					paginas++;
					excel.createSheet(paginas, "Pagina " + paginas);
					cont=1;
					System.gc();
					
				}
				if(cont2>240000) {
					break;
				}
				cont++;
				cont2++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultset(rs);
			excel.writeExcel();
		}
	}
	
	public static void resultSetToExcelPorFecha(ResultSet rs, String path) {
		WriteExcel excel = new WriteExcel();
		try {
			excel.createWorkBook(path);
			excel.createSheet();
			excel.setFormatArialBold();
			ResultSetMetaData rsMetaData = rs.getMetaData();
		    int numberOfColumns = rsMetaData.getColumnCount();
		    String value = "";
		    for (int i=0; i < numberOfColumns; i++) {
		    	value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ');
		    	excel.addCell(i, 0, value);
		    }
			excel.setFormatArialNoBold();
			int cont = 1;
			int cont2= 1;
			int paginas=0;
			while(rs.next()) {
				System.out.println("Cantidad de Registros :" + cont2);
				for(int x=1;x<=numberOfColumns;x++) {
					excel.addCell(x-1, cont, rs.getString(x));
                }
				if(cont>10000) {
					paginas++;
					excel.createSheet(paginas, "Pagina " + paginas);
					cont=1;
					System.gc();
				}
				if(cont2>240000) {
					break;
				}
				cont++;
				cont2++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultset(rs);
			excel.writeExcel();
		}
	}
  
  public void resultSetToExcelUnique(ResultSet rs, String fecha,String hasta, String fechaDesde, String fechaHasta, String mesHasta, String anioHasta) 
    throws OutOfMemoryError {
		try {
        ResultSetMetaData rsMetaData = rs.getMetaData();
		    int numberOfColumns = rsMetaData.getColumnCount();
		    String oldNumPrestacion = "";
		    String oldCodPrestacion = "";
		    String oldCodFonasa = "";
		    String oldOldCondFonasa="";
		    String value = "";
		    List codigosFonasaArray = new ArrayList();
		    if(this.fecha!=null) {
				if(!this.fecha.equals(fecha)) {
					this.fecha=fecha;
					excel.createSheet(paginas, fecha + " a " + hasta);
					paginas++;
				}
			} 
		    int filter = -1;
		    
		    for (int i=0; i < numberOfColumns; i++) {
		    	value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ').trim();
		    	if("FECHA PAGO ORDEN2".equals(value)) {
		    		filter= i+1;
		    	} else if(filter==-1){ 
		    		excel.addCell(i, 0, value);
		    	} else {
		    		excel.addCell(i-1, 0, value);
		    	}
		    }
			excel.setFormatArialNoBold();
			
			int cont2= 1;
			String cod ="";
			int contCampos = numberOfColumns;
			String numPrestacion = "";
			String prestCod = "";
			String prestFonasa = "";
			String puntos ="";
			String title ="Red UC - Generacion Reportes [Generando .";
			int contPuntos = 0;
			int contPuntos2 = 0;
			int sw = 0;
      List codFonasaClean = null;
			int columnas=0;
      //long freeMemory = 0;
      int mb = 1024*1024;
      long maxMemory = runtime.maxMemory() / mb;
      maxMemory = maxMemory;
      //System.out.println("maxMemory -------------> " + maxMemory);
      long usedMemory =0;
			while(rs.next()) {
        usedMemory = (runtime.totalMemory() - runtime.freeMemory())/mb;
        //freeMemory = runtime.freeMemory()/mb;
        //System.out.println("---------> " + freeMemory + " <---------- used -----> " + usedMemory + " max-->"+maxMemory );
        if(usedMemory>=maxMemory) {
          break;
        }
        try {
          numPrestacion = rs.getString("NUMERO_ORDEN_ATENCION");
        }catch(SQLException e ) {
          numPrestacion = "";
        }
        try {
          prestCod =  rs.getString("CODIGO_PRESTACION");
        }catch(SQLException e) {
          prestCod  = "";
        }
        try {
          prestFonasa = rs.getString("CODIGO_FONASA");
        } catch(SQLException e ) {
          prestFonasa = "";
        }
				int v = 1;
				if(sw==0){
					cont++;
					for(int x=1;x<=numberOfColumns;x++) {
						if(v != 1) {
							excel.addCell(x-2, cont-1, rs.getString(x));
						} else if(x != filter) {
							excel.addCell(x-1, cont-1, rs.getString(x));
						} else if(x == filter) {
							v=x;
						}
	                }
					oldNumPrestacion = numPrestacion;
					oldCodPrestacion = prestCod;
					sw=1;
				} else {
					if(oldNumPrestacion.equals(numPrestacion) && oldCodPrestacion.equals(prestCod)) {
            codigosFonasaArray.add(prestFonasa);
					} else {
            codFonasaClean = new ArrayList();
            Iterator it = codigosFonasaArray.iterator();
						columnas=numberOfColumns;
            String prestFonasaValue = "";
            int sw1 = 0;
						while(it.hasNext()) {
                prestFonasaValue=it.next() + "";
                if(codFonasaClean.size() != 0) {
                  sw = 0;
                  for(int p = 0; p<codFonasaClean.size(); p++ ) {
                    if(codFonasaClean.get(p).toString().equals(prestFonasaValue) ) {
                        sw1 = 1;
                    }
                  }
                  if(sw1==0)
                    codFonasaClean.add(prestFonasaValue );
                } else {
                    codFonasaClean.add(prestFonasaValue );
                }
		        }
            Iterator it2 = codFonasaClean.iterator();
            while(it2.hasNext()) {
                excel.addCell(columnas++ -1, cont-1, it2.next());
            }
            
						cont++;
						for(int x=1;x<=numberOfColumns;x++) {
							if(v != 1) {
								excel.addCell(x-2, cont-1, rs.getString(x));
							} else if(x != filter) {
								excel.addCell(x-1, cont-1, rs.getString(x));
							} else if(x == filter) {
								v=x;
							}
		        }
						oldNumPrestacion = numPrestacion;
						oldCodPrestacion = prestCod;
						codigosFonasaArray=new ArrayList();
					}
				}
				
				if(cont>=2) {
					oldOldCondFonasa = oldCodFonasa;
				}
        System.out.println("------> " + cont);
				if(cont>65530) {
					filter=-1;
					excel.createSheet(paginas, fecha + " a " + hasta + " " + paginas);
					for (int i=0; i < numberOfColumns; i++) {
				    	value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ');
				    	if("FECHA PAGO ORDEN2".equals(value)) {
				    		filter= i+1;
				    	} else if(filter==-1){ 
				    		excel.addCell(i, 0, value);
				    	} else {
				    		excel.addCell(i-1, 0, value);
				    	}
				    }
					paginas++;
					cont=1;
					//cont++;
					contCampos = 0;	
				} 
				if(contWriter>15000) {
					excel.flushFOS();
					contWriter = 0;
					System.gc();
				}
				contWriter++;
				cont2++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			excel.flushFOS();
			excel.writeExcel();
			closeResultset(rs);
		}
	}
  
  /*
	public void resultSetToExcelUnique(ResultSet rs, String fecha,String hasta, String fechaDesde, String fechaHasta, String mesHasta, String anioHasta) {
		try {
        ResultSetMetaData rsMetaData = rs.getMetaData();
		    int numberOfColumns = rsMetaData.getColumnCount();
		    String oldNumPrestacion = "";
		    String oldCodPrestacion = "";
		    String oldCodFonasa = "";
		    String oldOldCondFonasa="";
		    String value = "";
		    List codigosFonasaArray = new ArrayList();
		    if(this.fecha!=null) {
				if(!this.fecha.equals(fecha)) {
					this.fecha=fecha;
					excel.createSheet(paginas, fecha + " a " + hasta);
					paginas++;
				}
			} 
		    int filter = -1;
		    
		    for (int i=0; i < numberOfColumns; i++) {
		    	value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ').trim();
		    	if("FECHA PAGO ORDEN2".equals(value)) {
		    		filter= i+1;
		    	} else if(filter==-1){ 
		    		excel.addCell(i, 0, value);
		    	} else {
		    		excel.addCell(i-1, 0, value);
		    	}
		    }
			excel.setFormatArialNoBold();
			
			int cont2= 1;
			String cod ="";
			int contCampos = numberOfColumns;
			String numPrestacion = "";
			String prestCod = "";
			String prestFonasa = "";
			String puntos ="";
			String title ="Red UC - Generacion Reportes [Generando .";
			int contPuntos = 0;
			int contPuntos2 = 0;
			int sw = 0;
			int columnas=0;
			while(rs.next()) {
        try {
          numPrestacion = rs.getString("NUMERO_ORDEN_ATENCION");
        }catch(SQLException e ) {
          numPrestacion = "";
        }
        try {
          prestCod =  rs.getString("CODIGO_PRESTACION");
        }catch(SQLException e) {
          prestCod  = "";
        }
        try {
          prestFonasa = rs.getString("CODIGO_FONASA");
        } catch(SQLException e ) {
          prestFonasa = "";
        }
				int v = 1;
				if(sw==0){
					cont++;
					for(int x=1;x<=numberOfColumns;x++) {
						if(v != 1) {
							excel.addCell(x-2, cont-1, rs.getString(x));
						} else if(x != filter) {
							excel.addCell(x-1, cont-1, rs.getString(x));
						} else if(x == filter) {
							v=x;
						}
	                }
					oldNumPrestacion = numPrestacion;
					oldCodPrestacion = prestCod;
					sw=1;
				} else {
					if(oldNumPrestacion.equals(numPrestacion) && oldCodPrestacion.equals(prestCod)) {
						codigosFonasaArray.add(prestFonasa);
					} else {
						Iterator it = codigosFonasaArray.iterator();
						columnas=numberOfColumns;
						while(it.hasNext()) {
							excel.addCell(columnas++ -1, cont-1, it.next());
		                }
						cont++;
						for(int x=1;x<=numberOfColumns;x++) {
							if(v != 1) {
								excel.addCell(x-2, cont-1, rs.getString(x));
							} else if(x != filter) {
								excel.addCell(x-1, cont-1, rs.getString(x));
							} else if(x == filter) {
								v=x;
							}
		                }
						oldNumPrestacion = numPrestacion;
						oldCodPrestacion = prestCod;
						codigosFonasaArray=new ArrayList();
					}
				}
				
				if(cont>=2) {
					oldOldCondFonasa = oldCodFonasa;
				}
				if(cont>65530) {
					filter=-1;
					excel.createSheet(paginas, fecha + " a " + hasta + " " + paginas);
					for (int i=0; i < numberOfColumns; i++) {
				    	value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ');
				    	if("FECHA PAGO ORDEN2".equals(value)) {
				    		filter= i+1;
				    	} else if(filter==-1){ 
				    		excel.addCell(i, 0, value);
				    	} else {
				    		excel.addCell(i-1, 0, value);
				    	}
				    }
					paginas++;
					cont=1;
					cont++;
					contCampos = 0;	
				} 
				if(contWriter>15000) {
					excel.flushFOS();
					contWriter = 0;
					System.gc();
				}
				contWriter++;
				cont2++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			excel.flushFOS();
			excel.writeExcel();
			closeResultset(rs);
		}
	}
  */
  
	public boolean validaCodFonasa(List fonasa, String oldCodFonasa) {
      Iterator it = fonasa.iterator();
      boolean response = false;
      while(it.hasNext()) {
        if(oldCodFonasa.equals(it.next())) {
          response = true;
          break;
        }
      }
      return response;
	}
	
	public void closeWorkBook() {
		excel.writeExcel();
	}
	
	public boolean validaPrestFonasa(List array, String cod ) {
		boolean response =true;
		Iterator it= array.iterator();
		while(it.hasNext()) {
			if(it.next().toString().equals(cod)) {
				response=false;
			}
		}
		return response;
	}
	
	public static void closeResultset(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
  
}