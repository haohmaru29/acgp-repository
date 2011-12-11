package cl.reduc.reportes.ui;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cl.reduc.commons.database.oracle.ODBCOracle;
import cl.reduc.reportes.service.CBManager;
import cl.reduc.reportes.service.DetalleManager;
import cl.reduc.reportes.service.ResumenManager;
import cl.reduc.reportes.utils.ArrayUtils;
import cl.reduc.reportes.utils.DateUtils;
import javax.swing.JScrollPane;

public class Inicio extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txt_anio;
	private JTextField txt_file;
	private JComboBox cb_mes;
	private JComboBox cb_tipoatencion;
	private JComboBox cb_especialidad;
	private JComboBox tipoReporte;
	private JComboBox cb_sucursal;
	private JList cb_cr;
	private ResumenManager resumenManager = new ResumenManager();
	private DetalleManager detalleManager = new DetalleManager();
	private CBManager cbManager = new CBManager();
	private JTextField txt_desde;
	private JTextField txt_hasta;
	private JTextField txt_busq;
	private DefaultListModel model;
	private DefaultListModel modelInicial;
	private List crList;
	private List newList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inicio() {
		setTitle("Red UC - Generacion Reportes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 461);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 279, 713, 1);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(48, 31, 25, 14);
		panel.add(lblMes);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(275, 31, 25, 14);
		panel.add(lblAo);
		
		txt_anio = new JTextField();
		txt_anio.setBounds(313, 28, 54, 20);
		panel.add(txt_anio);
		txt_anio.setColumns(10);
		
		JLabel lblSucursal = new JLabel("Seccion");
		lblSucursal.setBounds(19, 66, 54, 14);
		panel.add(lblSucursal);
		
		JLabel lblNewLabel = new JLabel("Especialidad");
		lblNewLabel.setBounds(396, 31, 81, 14);
		panel.add(lblNewLabel);
		
		cb_especialidad = new JComboBox();
		ArrayUtils.recorreArray(cbManager.getEspecialidades(),cb_especialidad);
		cb_especialidad.setBounds(481, 28, 220, 20);
		panel.add(cb_especialidad);
		cb_mes = new JComboBox();
		DateUtils.loadMeses(cb_mes);	
		cb_mes.setBounds(85, 28, 178, 20);
		panel.add(cb_mes);
		
		JLabel lblEspecialidad = new JLabel("Tipo Atenci\u00F3n");
		lblEspecialidad.setBounds(396, 65, 90, 16);
		panel.add(lblEspecialidad);
		
		cb_tipoatencion = new JComboBox();
		cb_tipoatencion.setBounds(481, 63, 220, 20);
		panel.add(cb_tipoatencion);
		ArrayUtils.loadTipoAtencion(cb_tipoatencion);
		
		cb_sucursal = new JComboBox();
		cb_sucursal.setBounds(85, 65, 178, 17);
		ArrayUtils.recorreArray(cbManager.getSucursales(),cb_sucursal);
		panel.add(cb_sucursal);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Tipo Reporte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 10, 713, 68);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tipoReporte.getSelectedIndex()!=4 ) { 
					if(validateCamp() ) {
						accion();
					}
				} else {
					opcionCr();
				}
				
			}
		});
		btnGenerarReporte.setBounds(288, 376, 176, 41);
		contentPane.add(btnGenerarReporte);
		String date = new Date().toLocaleString().trim();
		System.out.println(date.substring(0, date.indexOf(" ")));
		JLabel lblFecha_1 = new JLabel(date.substring(0, date.trim().indexOf(" ")));
		lblFecha_1.setBounds(647, 12, 76, 14);
		contentPane.add(lblFecha_1);
		lblFecha_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Destino Archivo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 302, 713, 62);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblDirectorio = new JLabel("Directorio");
		lblDirectorio.setBounds(12, 23, 59, 16);
		panel_3.add(lblDirectorio);
		
		txt_file = new JTextField();
		txt_file.setBounds(89, 21, 502, 20);
		panel_3.add(txt_file);
		txt_file.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchFolder();
			}
		});
		btnBuscar.setBounds(603, 18, 98, 26);
		panel_3.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 90, 715, 207);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha Pago desde");
		lblNewLabel_2.setBounds(378, 14, 114, 16);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha Pago hasta");
		lblNewLabel_3.setBounds(377, 43, 115, 16);
		panel_1.add(lblNewLabel_3);
		
		txt_desde = new JTextField();
		txt_desde.setBounds(489, 12, 130, 20);
		panel_1.add(txt_desde);
		txt_desde.setColumns(10);
		
		txt_hasta = new JTextField();
		txt_hasta.setColumns(10);
		txt_hasta.setBounds(489, 42, 130, 20);
		panel_1.add(txt_hasta);
		txt_desde.setEnabled(false);
		txt_hasta.setEnabled(false);
		
		JLabel lblNewLabel_1 = new JLabel("CR");
		lblNewLabel_1.setBounds(12, 14, 55, 16);
		panel_1.add(lblNewLabel_1);
		modelInicial = new DefaultListModel(); 
		crList = cbManager.getCr();
		newList =crList; 
		ArrayUtils.loadList(crList, modelInicial);
		
		model = new DefaultListModel(); 
		tipoReporte = new JComboBox();
		tipoReporte.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String tipo = tipoReporte.getSelectedItem().toString().trim();
				if(tipo.indexOf("DETALLE X CR Y PERIODO") != -1) {
					txt_desde.setEnabled(true);
					txt_hasta.setEnabled(true);
					cb_cr.enable(true);
					
					cb_mes.enable(false);
					cb_tipoatencion.enable(false);
					cb_sucursal.enable(false);
					cb_especialidad.enable(false);
				} else { 
					txt_desde.setEnabled(false);
					txt_hasta.setEnabled(false);
					//cb_cr.setEnabled(false);
					
					cb_mes.setEnabled(true);
					cb_tipoatencion.setEnabled(true);
					cb_sucursal.setEnabled(true);
					cb_especialidad.setEnabled(true);
				}
			}
		});
		tipoReporte.setBounds(12, 28, 334, 18);
		
		tipoReporte.addItem("RESUMEN ORDENES ATENCION POR ESPECIALIDAD");
		tipoReporte.addItem("DETALLE ORDENES ATENCION POR ESPECIALIDAD");
		tipoReporte.addItem("RESUMEN ORDENES ATENCION POR SECCION");
		tipoReporte.addItem("DETALLE ORDENES ATENCION POR SECCION");
		tipoReporte.addItem("DETALLE POR CR Y PERIODO");
		panel_2.add(tipoReporte);
		tipoReporte.setSelectedIndex(4);
		tipoReporte.setEnabled(false);
		txt_desde.setEnabled(true);
		txt_hasta.setEnabled(true);
		JLabel lblddmmyyyy = new JLabel("(dd/mm/yyyy)");
		lblddmmyyyy.setBounds(625, 14, 78, 16);
		panel_1.add(lblddmmyyyy);
		
		JLabel label = new JLabel("(dd/mm/yyyy)");
		label.setBounds(625, 43, 78, 16);
		panel_1.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 42, 320, 153);
		panel_1.add(scrollPane);
		cb_cr = new JList(modelInicial);
		scrollPane.setViewportView(cb_cr);
		cb_cr.setEnabled(true);
		
		txt_busq = new JTextField();
		txt_busq.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String contenido = txt_busq.getText().trim();
				if(!contenido.equals("")) {
					searchModel(model, contenido);
				} else {
					cleanModel();
					
				}
			}
		});
		txt_busq.setBounds(39, 12, 321, 20);
		panel_1.add(txt_busq);
		txt_busq.setColumns(10);
		addWindowListener(new WindowListener () {
			
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando ventana");
				ODBCOracle.getInstance().closeConnection();
				
			}
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public void searchModel(DefaultListModel model, String filter) {
		String content = "";
		model.clear();
		int tam = crList.size();
		List list = new ArrayList();
		filter = filter.trim().toUpperCase();
		for(int x=0; x<tam;x++) {
			content = crList.get(x).toString().toUpperCase().trim();
			if(content.indexOf(filter) != -1) {
				list.add(content);
			} 
		}
		newList = list;
		ArrayUtils.loadList(newList, model);
		cb_cr.setModel(model);
	}
	
	public void cleanModel() {
		cb_cr.setModel(modelInicial);
	}
	
	public void accion() {
		String especialidad = cb_especialidad.getSelectedItem().toString();
        String mes = DateUtils.getMesPorNombre(cb_mes.getSelectedItem().toString().trim());
        String anio = txt_anio.getText().trim();
		if(tipoReporte.getSelectedIndex() == 0 || tipoReporte.getSelectedIndex() == 2) {
			opcionResumen(especialidad, mes, anio);
		} else if(tipoReporte.getSelectedIndex() == 1 || tipoReporte.getSelectedIndex() == 3) {
			opcionDetalles(especialidad, mes, anio);
		} else if(tipoReporte.getSelectedIndex() == 5) {
			opcionCr();
		}
		JOptionPane.showMessageDialog(this, "Reporte Generado", "ALERTA", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void opcionCr() {
		if(txt_desde.getText().trim().equals("") || txt_hasta.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Debe ingresar la fecha desde y hasta", "Atencion", JOptionPane.ERROR_MESSAGE);
		} else if(txt_file.getText().trim().equals("") ) {
			JOptionPane.showMessageDialog(this, "Debe Seleccionar un directorio", "ALERTA", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				String fechaDesde = txt_desde.getText();
				String fechaHasta = txt_hasta.getText();
				String selected = buildSFiltro();
				int diaDesde = Integer.parseInt(fechaDesde.substring(0, fechaDesde.indexOf("/")) );
				int mesDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.indexOf("/")+1, fechaDesde.lastIndexOf("/")) );
				int anioDesde = Integer.parseInt(fechaDesde.substring(fechaDesde.lastIndexOf("/")+1, fechaDesde.length()) );
			
				int diaHasta = Integer.parseInt(fechaHasta.substring(0, fechaHasta.indexOf("/")) );
				int mesHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.indexOf("/")+1, fechaHasta.lastIndexOf("/")) );
				int anioHasta = Integer.parseInt(fechaHasta.substring(fechaHasta.lastIndexOf("/")+1, fechaHasta.length()) );
				
				
				if(selected == "") {
					JOptionPane.showMessageDialog(this, "Debe seleccionar algun CR, verifique por favor", "ALERTA", JOptionPane.WARNING_MESSAGE);
				} else {
					if(fechaDesde.substring(0, fechaDesde.indexOf("/")).length() ==2 && fechaHasta.substring(0, fechaHasta.indexOf("/")).length()==2 ) {
						if(fechaDesde.substring(fechaDesde.indexOf("/")+1, fechaDesde.lastIndexOf("/")).length()==2 && fechaHasta.substring(fechaHasta.indexOf("/")+1, fechaHasta.lastIndexOf("/")).length()==2) {
							if(fechaDesde.substring(fechaDesde.lastIndexOf("/")+1, fechaDesde.length()).length()==4 && fechaHasta.substring(fechaHasta.lastIndexOf("/")+1, fechaHasta.length()).length()==4) {
								if(anioHasta> anioDesde) {
									detalleManager.detallePorCr(txt_desde.getText(), txt_hasta.getText(), selected, txt_file.getText(), this);
								} else if(anioDesde< anioHasta) {
									JOptionPane.showMessageDialog(this, "Fecha ingresada con formato no reconocido, verifique por favor", "ALERTA", JOptionPane.WARNING_MESSAGE);
								} else if(anioDesde==anioHasta) {
									if(mesHasta < mesDesde) {
										JOptionPane.showMessageDialog(this, "Fecha ingresada con formato no reconocido, verifique por favor", "ALERTA", JOptionPane.WARNING_MESSAGE);
									} else if(mesHasta==mesDesde ) {
										if(diaDesde>diaHasta) {
											JOptionPane.showMessageDialog(this, "Fecha ingresada con formato no reconocido, verifique por favor", "ALERTA", JOptionPane.WARNING_MESSAGE);
										} else {
											detalleManager.detallePorCr(txt_desde.getText(), txt_hasta.getText(), selected, txt_file.getText(), this);
										}
									} else {
										detalleManager.detallePorCr(txt_desde.getText(), txt_hasta.getText(), selected, txt_file.getText(), this);
									}
								}
							} else {
								JOptionPane.showMessageDialog(this, "Fecha ingresada con formato no reconocido, verifique por favor", "ALERTA", JOptionPane.WARNING_MESSAGE);
								
							}
						} else {
							JOptionPane.showMessageDialog(this, "Fecha ingresada con formato no reconocido, verifique por favor", "ALERTA", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(this, "Fecha ingresada con formato no reconocido, verifique por favor", "ALERTA", JOptionPane.WARNING_MESSAGE);
					}
				}
			} catch(Exception e ) {
				JOptionPane.showMessageDialog(this, "Se ha producido el siguiente error :\n" + e.getMessage(), "ALERTA", JOptionPane.WARNING_MESSAGE);
			} finally {
				System.out.println("Ejecutando Garbage Colector");
				System.gc();
			}
			
		}
	}
	
	public String buildSFiltro() {
		int cant = cb_cr.getSelectedValues().length;
		Object[] values =cb_cr.getSelectedValues(); 
		String response = "";
		String comp = "";
		String array[];
		for(int x=0;x<cant;x++) {
			comp = values[x].toString();
			array = comp.split("-");
			
			if(array.length>1)
				response +="'" + array[1].toString().trim() + "'" ;
			else {
				response +="'" + array[0].toString().trim() + "'";
			}
			if(x<cant-1) response+= ",";
		}
		
		return response;
	}
	
	public boolean validateDate(String fecha) {
		boolean response = false;
		int value = 0;
		for(int x=0;x<fecha.length();x++) {
			if(fecha.substring(x,x+1).equals("/")) {
				value++;
			}
		}
		if(value>=2) {
			response=true;
		}
		return response;
	}
	
	public void opcionDetalles(String especialidad, String mes, String anio ) {
		if(tipoReporte.getSelectedItem().toString().indexOf("ESPECIALIDAD") != -1) {
			//detalleManager.detallePorEspecialidad(mes, anio, especialidad, cb_sucursal.getSelectedItem().toString(), txt_file.getText());
		} else if(tipoReporte.getSelectedItem().toString().indexOf("SECCION") != -1) { 
			//detalleManager.detallePorSeccion(mes, anio, especialidad, cb_sucursal.getSelectedItem().toString(), txt_file.getText());
		}
	}
	
	public void opcionResumen(String especialidad, String mes, String anio ) {
		if(tipoReporte.getSelectedItem().toString().indexOf("ESPECIALIDAD") != -1) {
			resumenManager.resumenPorEspecialidad(mes, anio, especialidad, cb_sucursal.getSelectedItem().toString(), txt_file.getText());
		} else if(tipoReporte.getSelectedItem().toString().indexOf("SECCION") != -1) { 
			resumenManager.resumenPorSeccion(mes, anio, especialidad, cb_sucursal.getSelectedItem().toString(), txt_file.getText());
		}
	}
	
	public boolean validateCamp() {
		boolean response = false;
		if(!cb_mes.getSelectedItem().toString().equals("") && !txt_file.getText().equals("")  && !txt_anio.getText().equals("") && !cb_sucursal.getSelectedItem().toString().trim().equals("") ) {
			response = true;
		} else if(cb_mes.getSelectedItem().toString().equals("") ) {
			JOptionPane.showMessageDialog(this, "Debe Seleccionar un mes", "ALERTA", JOptionPane.WARNING_MESSAGE);
		} else if(txt_file.getText().equals("")  ) {
			JOptionPane.showMessageDialog(this, "Debe Seleccionar un directorio", "ALERTA", JOptionPane.WARNING_MESSAGE);
		} else if(txt_anio.getText().equals("") ) {
			JOptionPane.showMessageDialog(this, "Debe ingresar un año", "ALERTA", JOptionPane.WARNING_MESSAGE);
		} if(cb_sucursal.getSelectedItem().toString().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una sucursal para continuar", "ALERTA", JOptionPane.WARNING_MESSAGE);
		} 
		
		return response;
	}
	
	public void searchFolder() {
		Frame ventana = new Frame();
        ventana.setResizable(true);
        FileDialog abrir = new FileDialog(ventana,"Seleccione directorio",FileDialog.SAVE);
        abrir.setDirectory(".");
        abrir.setFile("informe");
        abrir.setVisible(true);
        String ruta = abrir.getDirectory()+((abrir.getFile()==null)?"": abrir.getFile() );
        if(ruta.indexOf(".xls") != -1) {
        	txt_file.setText(ruta);
        } else {
        	txt_file.setText(ruta + ".xls");
        }
        
        txt_file.setEditable(false);
	}
}
