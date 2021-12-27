package sesion01_Ejercicio02;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ControlAcceso {

	private JFrame frmControlDeAcceso;
	private JTextField user;
	private JLabel cerrado, abierto;
	private JPasswordField password;
	
	private static AccesoDB db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ControlAcceso window = new ControlAcceso();
					window.frmControlDeAcceso.setVisible(true);
					
					db = new AccesoDB();
					db.conectar();
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
	public ControlAcceso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmControlDeAcceso = new JFrame();
		frmControlDeAcceso.setTitle("Control de acceso DAM Curso 2020-2021");
		frmControlDeAcceso.getContentPane().setFocusable(false);
		frmControlDeAcceso.setBounds(100, 100, 680, 450);
		frmControlDeAcceso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmControlDeAcceso.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Log-in");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String usuario = user.getText();
				char [] pass = password.getPassword();
				String contra = String.valueOf(pass);
				
				if (usuario.isEmpty() || contra.isEmpty()) {
					
					JOptionPane .showMessageDialog(frmControlDeAcceso, "Debe completar ambos campos.");
				}
				
				else {

					if (db.validar(usuario, contra)) {
						
						abierto.setVisible(true);
						cerrado.setVisible(false);
						JOptionPane.showMessageDialog(frmControlDeAcceso, "Bienvenido " + usuario);
					}
					
					else {
						
						abierto.setVisible(false);
						cerrado.setVisible(true);
						JOptionPane.showMessageDialog(frmControlDeAcceso, "Nombre de usuario o contraseña incorrectos. Inténtelo de nuevo.");
					}
				}
				
			}		
		}
		);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(164, 212, 100, 24);
		frmControlDeAcceso.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton_1.setBounds(164, 247, 100, 24);
		frmControlDeAcceso.getContentPane().add(btnNewButton_1);
		
		user = new JTextField();
		user.setFont(new Font("Arial", Font.PLAIN, 14));
		user.setBounds(147, 128, 134, 24);
		frmControlDeAcceso.getContentPane().add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(75, 128, 52, 23);
		frmControlDeAcceso.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(50, 159, 83, 23);
		frmControlDeAcceso.getContentPane().add(lblNewLabel_1);
		
		cerrado = new JLabel("");
		cerrado.setIcon(new ImageIcon(ControlAcceso.class.getResource("/Imagenes/candado_cerrado.png")));
		cerrado.setBounds(437, 108, 100, 170);
		frmControlDeAcceso.getContentPane().add(cerrado);
		cerrado.setVisible(false);
		
		abierto = new JLabel("");
		abierto.setIcon(new ImageIcon(ControlAcceso.class.getResource("/Imagenes/candado_abierto.png")));
		abierto.setBounds(437, 108, 100, 170);
		frmControlDeAcceso.getContentPane().add(abierto);
		
		password = new JPasswordField();
		password.setBounds(147, 161, 134, 24);
		frmControlDeAcceso.getContentPane().add(password);
		abierto.setVisible(false);
	}

}
