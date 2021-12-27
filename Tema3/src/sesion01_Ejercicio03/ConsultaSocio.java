package sesion01_Ejercicio03;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ConsultaSocio  implements ActionListener{

	private JFrame frmBusquedaDeSocios;
	private JLabel cuentaResultados;
	private JTextField resultadoSocio;
	private JTextField resultadoNombre;
	private JTextField resultadoEstatura;
	private JTextField resultadoEdad;
	private JTextField resultadoLocalidad;
	private JTextField buscarDato;
	private JButton socioSiguiente;
	private JButton socioAnterior;
	int filas = 0, posicion = 0;
	private JButton botonBuscar;
	private static AccesoDB db;
	private static ResultSet resul;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ConsultaSocio window = new ConsultaSocio();
					window.frmBusquedaDeSocios.setVisible(true);
					
					db = new AccesoDB();
					db.conecta();
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
	public ConsultaSocio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBusquedaDeSocios = new JFrame();
		frmBusquedaDeSocios.setResizable(false);
		frmBusquedaDeSocios.setTitle("Busqueda de socios");
		frmBusquedaDeSocios.setBounds(100, 100, 740, 510);
		frmBusquedaDeSocios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBusquedaDeSocios.getContentPane().setLayout(null);
		
		JLabel numSocio = new JLabel("Socio");
		numSocio.setBounds(109, 107, 50, 25);
		numSocio.setFont(new Font("Arial", Font.PLAIN, 14));
		frmBusquedaDeSocios.getContentPane().add(numSocio);
		
		JLabel nombreSocio = new JLabel("Nombre");
		nombreSocio.setBounds(109, 143, 50, 25);
		nombreSocio.setFont(new Font("Arial", Font.PLAIN, 14));
		frmBusquedaDeSocios.getContentPane().add(nombreSocio);
		
		JLabel estaturaSocio = new JLabel("Estatura");
		estaturaSocio.setBounds(109, 179, 59, 25);
		estaturaSocio.setFont(new Font("Arial", Font.PLAIN, 14));
		frmBusquedaDeSocios.getContentPane().add(estaturaSocio);
		
		JLabel edadSocio = new JLabel("Edad");
		edadSocio.setBounds(109, 215, 50, 25);
		edadSocio.setFont(new Font("Arial", Font.PLAIN, 14));
		frmBusquedaDeSocios.getContentPane().add(edadSocio);
		
		JLabel localidadSocio = new JLabel("Localidad");
		localidadSocio.setBounds(109, 251, 69, 25);
		localidadSocio.setFont(new Font("Arial", Font.PLAIN, 14));
		frmBusquedaDeSocios.getContentPane().add(localidadSocio);
		
		JLabel unidadEstatura = new JLabel("cm");
		unidadEstatura.setBounds(379, 179, 50, 25);
		unidadEstatura.setFont(new Font("Arial", Font.PLAIN, 14));
		frmBusquedaDeSocios.getContentPane().add(unidadEstatura);
		
		JLabel años = new JLabel("a\u00F1os");
		años.setBounds(379, 215, 50, 25);
		años.setFont(new Font("Arial", Font.PLAIN, 14));
		frmBusquedaDeSocios.getContentPane().add(años);
		
		resultadoSocio = new JTextField();
		resultadoSocio.setBounds(324, 107, 45, 25);
		resultadoSocio.setHorizontalAlignment(SwingConstants.CENTER);
		resultadoSocio.setEditable(false);
		resultadoSocio.setFont(new Font("Arial", Font.PLAIN, 12));
		frmBusquedaDeSocios.getContentPane().add(resultadoSocio);
		resultadoSocio.setColumns(10);
		
		resultadoNombre = new JTextField();
		resultadoNombre.setBounds(199, 144, 170, 25);
		resultadoNombre.setHorizontalAlignment(SwingConstants.CENTER);
		resultadoNombre.setEditable(false);
		resultadoNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		frmBusquedaDeSocios.getContentPane().add(resultadoNombre);
		resultadoNombre.setColumns(10);
		
		resultadoEstatura = new JTextField();
		resultadoEstatura.setBounds(324, 180, 45, 25);
		resultadoEstatura.setHorizontalAlignment(SwingConstants.CENTER);
		resultadoEstatura.setEditable(false);
		resultadoEstatura.setFont(new Font("Arial", Font.PLAIN, 12));
		frmBusquedaDeSocios.getContentPane().add(resultadoEstatura);
		resultadoEstatura.setColumns(10);
		
		resultadoEdad = new JTextField();
		resultadoEdad.setBounds(324, 216, 45, 25);
		resultadoEdad.setHorizontalAlignment(SwingConstants.CENTER);
		resultadoEdad.setEditable(false);
		resultadoEdad.setFont(new Font("Arial", Font.PLAIN, 12));
		frmBusquedaDeSocios.getContentPane().add(resultadoEdad);
		resultadoEdad.setColumns(10);
		
		resultadoLocalidad = new JTextField();
		resultadoLocalidad.setBounds(283, 252, 86, 25);
		resultadoLocalidad.setHorizontalAlignment(SwingConstants.CENTER);
		resultadoLocalidad.setEditable(false);
		resultadoLocalidad.setFont(new Font("Arial", Font.PLAIN, 12));
		frmBusquedaDeSocios.getContentPane().add(resultadoLocalidad);
		resultadoLocalidad.setColumns(10);
		
		buscarDato = new JTextField();
		buscarDato.setBounds(531, 124, 110, 25);
		buscarDato.setFont(new Font("Arial", Font.PLAIN, 12));
		frmBusquedaDeSocios.getContentPane().add(buscarDato);
		buscarDato.setColumns(10);
		
		socioAnterior = new JButton("Anterior");
		socioAnterior.addActionListener(this);
		socioAnterior.setEnabled(false);
		socioAnterior.setFont(new Font("Arial", Font.PLAIN, 12));
		socioAnterior.setBounds(254, 364, 89, 25);
		frmBusquedaDeSocios.getContentPane().add(socioAnterior);
		
		socioSiguiente = new JButton("Siguiente");
		socioSiguiente.addActionListener(this);
		socioSiguiente.setEnabled(false);
		socioSiguiente.setFont(new Font("Arial", Font.PLAIN, 12));
		socioSiguiente.setBounds(396, 364, 89, 25);
		frmBusquedaDeSocios.getContentPane().add(socioSiguiente);
			
		botonBuscar = new JButton("Buscar");
		botonBuscar.addActionListener(this);
		botonBuscar.setFont(new Font("Arial", Font.PLAIN, 12));
		botonBuscar.setBounds(541, 161, 90, 25);
		frmBusquedaDeSocios.getContentPane().add(botonBuscar);

		cuentaResultados = new JLabel("");
		cuentaResultados.setBounds(309, 319, 120, 20);
		frmBusquedaDeSocios.getContentPane().add(cuentaResultados);
	
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		String localidad = buscarDato.getText().trim();
		
		try {
		
			if (ae.getSource() == botonBuscar) {
				
				resul = db.buscarPorLocalidad(localidad);
				
				resul.last();
				filas = resul.getRow();
				
				if (filas == 0) {
					
					JOptionPane.showMessageDialog(frmBusquedaDeSocios, "No se han encontrado registros.");
				}
				
				else {
					
					socioAnterior.setEnabled(true);
					socioSiguiente.setEnabled(true);
					
					resul.first();
					posicion = 1;
					
					Socio s = new Socio();
					s.setSocioID(resul.getInt("SocioID"));
					resultadoSocio.setText(Integer.toString(s.getSocioID()));
					s.setNombre(resul.getString("nombre"));
					resultadoNombre.setText(s.getNombre());
					s.setEstatura(resul.getInt("estatura"));
					resultadoEstatura.setText(Integer.toString(s.getEstatura()));
					s.setEdad(resul.getInt("edad"));
					resultadoEdad.setText(Integer.toString(s.getEdad()));
					s.setLocalidad(resul.getString("localidad"));
					resultadoLocalidad.setText(s.getLocalidad());
					cuentaResultados.setText("Socio 1 de " + filas);
					
				}
			}
				
			if (ae.getSource() == socioAnterior) {
				
				if (resul.getRow() == 1) {
					
					JOptionPane.showMessageDialog(frmBusquedaDeSocios, "No hay registros anteriores");
				}
				
				else {
					
					posicion --;
					resul.absolute(posicion);
					
					Socio s = new Socio();
					s.setSocioID(resul.getInt("SocioID"));
					resultadoSocio.setText(Integer.toString(s.getSocioID()));
					s.setNombre(resul.getString("nombre"));
					resultadoNombre.setText(s.getNombre());
					s.setEstatura(resul.getInt("estatura"));
					resultadoEstatura.setText(Integer.toString(s.getEstatura()));
					s.setEdad(resul.getInt("edad"));
					resultadoEdad.setText(Integer.toString(s.getEdad()));
					s.setLocalidad(resul.getString("localidad"));
					resultadoLocalidad.setText(s.getLocalidad());
					cuentaResultados.setText("Socio " + posicion + " de " + filas);
				}
				
			}
				if (ae.getSource() == socioSiguiente) {
					
					if (resul.getRow() == filas) {
						
						JOptionPane.showMessageDialog(frmBusquedaDeSocios, "Has llegado al ultimo registro.");
					}
					
					else {
						
						posicion ++;
						resul.absolute(posicion);
						
						Socio s = new Socio();
						s.setSocioID(resul.getInt("SocioID"));
						resultadoSocio.setText(Integer.toString(s.getSocioID()));
						s.setNombre(resul.getString("nombre"));
						resultadoNombre.setText(s.getNombre());
						s.setEstatura(resul.getInt("estatura"));
						resultadoEstatura.setText(Integer.toString(s.getEstatura()));
						s.setEdad(resul.getInt("edad"));
						resultadoEdad.setText(Integer.toString(s.getEdad()));
						s.setLocalidad(resul.getString("localidad"));
						resultadoLocalidad.setText(s.getLocalidad());
						cuentaResultados.setText("Socio " + posicion + " de " + filas);
					}
				}	
		}
		
		catch (SQLException e) {
		
			e.getMessage();
			e.printStackTrace();
		}
	}
}
