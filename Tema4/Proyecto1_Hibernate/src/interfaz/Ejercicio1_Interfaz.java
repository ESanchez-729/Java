package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import primero.Empleados;
import primero.Departamentos;
import primero.SessionFactoryUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Calendar;

import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ejercicio1_Interfaz {

	private JFrame frame;
	private JTextField TF_empno;
	private JTextField TF_apellido;
	private JTextField TF_oficio;
	private JTextField TF_salario;
	private JTextField TF_comision;
	private JFormattedTextField FTF_date;
	private JComboBox CB_departamento;
	private JComboBox CB_director;

	SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	Session session;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Ejercicio1_Interfaz window = new Ejercicio1_Interfaz();
					window.frame.setVisible(true);
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ejercicio1_Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 570, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTI\u00D3N DE EMPLEADOS");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(109, 22, 180, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00BA DE EMPLEADO:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(54, 63, 110, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("APELLIDO:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(54, 100, 68, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("SALARIO:");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(54, 174, 60, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("OFICIO:");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_3.setBounds(54, 137, 48, 14);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("COMISI\u00D3N:");
		lblNewLabel_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_4.setBounds(54, 211, 68, 14);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		TF_empno = new JTextField();
		TF_empno.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyTyped(java.awt.event.KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(! Character.isDigit(c) || TF_empno.getText().length()>=4)
					
					e.consume();
			}
		});
		TF_empno.setBounds(174, 59, 86, 20);
		frame.getContentPane().add(TF_empno);
		TF_empno.setColumns(10);
		
		TF_apellido = new JTextField();
		TF_apellido.setBounds(130, 96, 130, 20);
		frame.getContentPane().add(TF_apellido);
		TF_apellido.setColumns(10);
		
		TF_oficio = new JTextField();
		TF_oficio.setBounds(130, 133, 130, 20);
		frame.getContentPane().add(TF_oficio);
		TF_oficio.setColumns(10);
		
		TF_salario = new JTextField();
		TF_salario.setBounds(130, 170, 130, 20);
		frame.getContentPane().add(TF_salario);
		TF_salario.setColumns(10);
		
		TF_comision = new JTextField();
		TF_comision.setBounds(130, 207, 130, 20);
		frame.getContentPane().add(TF_comision);
		TF_comision.setColumns(10);
		
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		FTF_date = new JFormattedTextField(formato);
		FTF_date.setBounds(370, 208, 110, 20);
		frame.getContentPane().add(FTF_date);
		fechaPorDefecto(FTF_date);
		
		JButton BTN_consulta = new JButton("CONSULTAR EMPLEADO");
		BTN_consulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				session = sesion.openSession();
								
				limpiarCampos(false);
				
				Short id = Short.valueOf(TF_empno.getText());
				Empleados emp = (Empleados) session.get(Empleados.class, id);
				//Empleados emp2 = (Empleados) session.createQuery("from Empleados where emp_no = :emp").setInteger("emp", id);
				
				
				if (emp == null) {
					
					JOptionPane.showMessageDialog(frame, "El empleado introducido no existe", "Sin resultados", JOptionPane.ERROR_MESSAGE, null);
				}
				
				else {
					
					TF_apellido.setText(emp.getApellido());
					TF_oficio.setText(emp.getOficio());
					TF_salario.setText(String.valueOf(emp.getSalario()));
					
					if (emp.getComision() == null) {
						
						TF_comision.setText("0");
					}
					
					else {
						
						TF_comision.setText(String.valueOf(emp.getComision()));
					}
					
					FTF_date.setText(String.valueOf(emp.getFechaAlt()));
					seleccionarDepartamento(emp.getDepartamentos());
					seleccionarDirector(emp.getDir());
				}

				session.close();
				
			}
		});
		BTN_consulta.setFont(new Font("Arial", Font.PLAIN, 12));
		BTN_consulta.setBounds(307, 57, 200, 23);
		frame.getContentPane().add(BTN_consulta);
		
		CB_departamento = new JComboBox();
		cargarDepartamentos();
		CB_departamento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CB_departamento.setEditable(true);
		CB_departamento.setSelectedItem("Elige un departamento");
		CB_departamento.setToolTipText("");
		CB_departamento.setBounds(357, 122, 150, 22);
		frame.getContentPane().add(CB_departamento);
		
		CB_director = new JComboBox();
		cargarDirectores();
		CB_director.setFont(new Font("Tahoma", Font.PLAIN, 11));
		CB_director.setEditable(true);
		CB_director.setBounds(357, 159, 150, 22);
		frame.getContentPane().add(CB_director);
		
		JLabel lblNewLabel_2 = new JLabel("FECHA ALTA:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(285, 211, 86, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("(?)");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent arg0) {
				
				lblNewLabel_3.setToolTipText("Formato: yyyy-MM-dd");
			}
		});
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_3.setBounds(490, 209, 17, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton BTN_insertar = new JButton("INSERTAR");
		BTN_insertar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				session = sesion.openSession();
				
				Short num = Short.valueOf(TF_empno.getText());
				
				Empleados comprobar =  (Empleados) session.get(Empleados.class, num);
				
				if (comprobar == null) {
					
					if (TF_empno.getText().equals("") || TF_apellido.getText().equals("") || TF_oficio.getText().equals("") || TF_salario.getText().equals("") || FTF_date.getText().equals("") || CB_departamento.getSelectedIndex() == 0 || CB_director.getSelectedIndex() == 0) {
						
						JOptionPane.showMessageDialog(frame, "Todos los campos deben estar rellenados", "Error de inserción", JOptionPane.ERROR_MESSAGE, null);
					}
					
					else {
						
						String dep = String.valueOf(CB_departamento.getSelectedItem());
						String[] valores = dep.split(" | ");
						Byte numDep = Byte.valueOf(valores[0]); //era bait, pepoclown
						
						String direc = String.valueOf(CB_director.getSelectedItem());
						String[] direcs = direc.split(" | ");
						Short numDir = Short.valueOf(direcs[0]);
								
						Departamentos depart = (Departamentos) session.get(Departamentos.class, numDep);
						
						Empleados emp = new Empleados();
						emp.setEmpNo(Short.valueOf(TF_empno.getText()));
						emp.setApellido(TF_apellido.getText());
						emp.setOficio(TF_oficio.getText());
						emp.setSalario(Float.valueOf(TF_salario.getText()));
						
						if (TF_comision.getText().equals("")) {
							
							emp.setComision(Float.valueOf(0));
						}
						
						else {
							
							emp.setComision(Float.valueOf(TF_comision.getText()));
						}
						
						emp.setFechaAlt(Date.valueOf(FTF_date.getText()));
						emp.setDepartamentos(depart);
						emp.setDir(numDir);
						

						Transaction transac = session.beginTransaction();
						
						session.save(emp);
						transac.commit();	
					}
					
					limpiarCampos(true);
					cargarDirectores();	
				}
				
				else {
					
					JOptionPane.showMessageDialog(frame, "Ya hay un empleado con este identificador.", "Error de consistencia", JOptionPane.ERROR_MESSAGE, null);
				}	
			}
		});
		BTN_insertar.setBounds(109, 286, 97, 23);
		frame.getContentPane().add(BTN_insertar);
		
		JButton BTN_eliminar = new JButton("ELIMINAR");
		BTN_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					Short num = Short.valueOf(TF_empno.getText());
					
					session = sesion.openSession();
					Empleados comprobar = (Empleados) session.createQuery("FROM Empleados WHERE dir = :dir").setInteger("dir", num).uniqueResult();
					
					if (comprobar == null) {
						
						Transaction transac = session.beginTransaction();
						
						Empleados emp = (Empleados) session.get(Empleados.class, num);
						session.delete(emp);
						
						transac.commit();
					}
					
					else {
						
						JOptionPane.showMessageDialog(frame, "El empleado introducido es director de al menos un empleado", "Entidad con dependencias", JOptionPane.ERROR_MESSAGE, null );
						
					}		
				}
				
				catch(org.hibernate.NonUniqueResultException result) {
					
					JOptionPane.showMessageDialog(frame, "El empleado introducido es un director", "Entidad con dependencias", JOptionPane.ERROR_MESSAGE, null );
				} 
				
				catch (java.lang.IllegalArgumentException argument) {
					
					JOptionPane.showMessageDialog(frame, "El empleado introducido no existe", "Sin resultados", JOptionPane.ERROR_MESSAGE, null);
				}
				
				finally {
						
					limpiarCampos(true);
					cargarDirectores();
				}
			}
		});
		BTN_eliminar.setBounds(226, 286, 97, 23);
		frame.getContentPane().add(BTN_eliminar);
		
		JButton BTN_modificar = new JButton("MODIFICAR");
		BTN_modificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				session = sesion.openSession();
				
				Short num = Short.valueOf(TF_empno.getText());
				
				Empleados comprobar = (Empleados) session.get(Empleados.class, num);
				
				if (comprobar == null) {
					
					JOptionPane.showMessageDialog(frame, "El empleado introducido no existe", "Sin resultados", JOptionPane.ERROR_MESSAGE, null);
				}
				
				else {
					
					if (TF_empno.getText().equals("") || TF_apellido.getText().equals("") || TF_oficio.getText().equals("") || TF_salario.getText().equals("") || FTF_date.getText().equals("") || CB_departamento.getSelectedIndex() == 0 || CB_director.getSelectedIndex() == 0) {
						
						JOptionPane.showMessageDialog(frame, "Todos los campos deben estar rellenados", "Error de inserción", JOptionPane.ERROR_MESSAGE, null);
					}
					
					else {
						
						String dep = String.valueOf(CB_departamento.getSelectedItem());
						String[] valores = dep.split(" | ");
						Byte numDep = Byte.valueOf(valores[0]); //era bait, pepoclown
						
						String direc = String.valueOf(CB_director.getSelectedItem());
						String[] direcs = direc.split(" | ");
						Short numDir = Short.valueOf(direcs[0]);
						
						Departamentos depa = (Departamentos) session.get(Departamentos.class, numDep);
						
						comprobar.setApellido(TF_apellido.getText());
						comprobar.setOficio(TF_oficio.getText());
						comprobar.setSalario(Float.valueOf(TF_salario.getText()));
						
						if (TF_comision.getText().equals("")) {
							
							comprobar.setComision(Float.valueOf(0));
						}
						
						else {
							
							comprobar.setComision(Float.valueOf(TF_comision.getText()));
						}
						
						comprobar.setFechaAlt(Date.valueOf(FTF_date.getText()));
						comprobar.setDepartamentos(depa);
						comprobar.setDir(numDir);
						
						Transaction transac = session.beginTransaction();
						
						session.update(comprobar);
						transac.commit();
					}
					
					limpiarCampos(true);
					cargarDirectores();
				}	
			}	
		});
		BTN_modificar.setBounds(345, 286, 97, 23);
		frame.getContentPane().add(BTN_modificar);
		
		JButton BTN_salir = new JButton("SALIR");
		BTN_salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				sesion.close();
				System.exit(-1);
			}
		});
		BTN_salir.setBounds(167, 332, 97, 23);
		frame.getContentPane().add(BTN_salir);
		
		JButton BTN_limpiar = new JButton("LIMPIAR");
		BTN_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				limpiarCampos(true);
			}
		});
		BTN_limpiar.setBounds(282, 332, 97, 23);
		frame.getContentPane().add(BTN_limpiar);
		
		
	}

	public void limpiarCampos(boolean comprobar) {

		if (comprobar) {

			TF_empno.setText("");
			TF_apellido.setText("");
			TF_oficio.setText("");
			TF_salario.setText("");
			TF_comision.setText("");
			FTF_date.setText("");
			CB_departamento.setSelectedItem("Elige un departamento");
			CB_director.setSelectedItem("Elige un director");
		}

		else {

			TF_apellido.setText("");
			TF_oficio.setText("");
			TF_salario.setText("");
			TF_comision.setText("");
			FTF_date.setText("");
			CB_departamento.setSelectedItem("Elige un departamento");
			CB_director.setSelectedItem("Elige un director");
		}
	}

	public void cargarDepartamentos() {

		session = sesion.openSession();

		Query consulta = session.createQuery("from Departamentos");
		List<Departamentos> lista = consulta.list();
		Iterator cursor = lista.iterator();

		/*while (cursor.hasNext()) {

			Departamentos dep = (Departamentos) (cursor.next());
			CB_departamento.addItem(dep.getDeptNo() + " | " + dep.getDnombre());
		}*/
		
		for(Departamentos dept : lista) {
			
			CB_departamento.addItem(dept.getDeptNo() + " | " + dept.getDnombre());
		}

		session.close();
	}

	public void cargarDirectores() {

		session = sesion.openSession();
		
		CB_director.removeAllItems();
		CB_director.addItem("Elige un director");
		CB_director.setSelectedItem("Elige un director");

		Query consulta = session.createQuery("from Empleados");
		List<Empleados> lista = consulta.list();
		Iterator cursor = lista.iterator();

		while (cursor.hasNext()) {

			Empleados dir = (Empleados) (cursor.next());
			CB_director.addItem(dir.getEmpNo() + " | " + dir.getApellido());
		}

		session.close();
	}

	public boolean seleccionarDepartamento(Departamentos dep) {

		for (int i = 0; i < CB_departamento.getItemCount(); i++) {

			String num = String.valueOf(CB_departamento.getItemAt(i));
			String[] valores = num.split(" | ");

			Byte id = dep.getDeptNo();

			try {

				if (Byte.valueOf(valores[0]) == id) {

					CB_departamento.setSelectedIndex(i);
					return true;
				}
			}

			catch (Exception ex) {
			}
		}

		return false;
	}

	public boolean seleccionarDirector(short id) {

		for (int i = 0; i < CB_director.getItemCount(); i++) {

			String num = String.valueOf(CB_director.getItemAt(i));
			String[] valores = num.split(" | ");

			try {

				if (Short.valueOf(valores[0]) == id) {

					CB_director.setSelectedIndex(i);
					return true;
				}
			}

			catch (Exception ex) {
			}
		}

		return false;
	}
	
	public void fechaPorDefecto(JTextField campo) {
		
		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		campo.setText(fecha);
	}
}
