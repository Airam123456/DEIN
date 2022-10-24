package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UsuarioDAO;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Aviones - Login");
		dibujar();
	}
	
	private void dibujar() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0};
		gbl_contentPane.rowHeights = new int[]{45, 50, 52, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIntroduceTusCredenciales = new JLabel("INTRODUCE TUS CREDENCIALES");
		lblIntroduceTusCredenciales.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblIntroduceTusCredenciales = new GridBagConstraints();
		gbc_lblIntroduceTusCredenciales.fill = GridBagConstraints.BOTH;
		gbc_lblIntroduceTusCredenciales.insets = new Insets(0, 0, 5, 0);
		gbc_lblIntroduceTusCredenciales.gridx = 0;
		gbc_lblIntroduceTusCredenciales.gridy = 0;
		contentPane.add(lblIntroduceTusCredenciales, gbc_lblIntroduceTusCredenciales);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(327675, 50));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		JLabel lblNombre = new JLabel("Usuario:");
		panel.add(lblNombre);
		
		username = new JTextField();
		username.setSize(new Dimension(300, 0));
		panel.add(username);
		username.setColumns(30);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		panel_2.add(lblContrasea);
		
		password = new JTextField();
		panel_2.add(password);
		password.setColumns(30);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Salir");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Entrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = username.getText();
				String pass = password.getText();
				Usuario usuario = new Usuario(nombre,pass);
				UsuarioDAO usuarioDao = new UsuarioDAO();
				if (usuarioDao.validLogin(usuario)) {
					
					try {
						GestionAeropuertos ga = new GestionAeropuertos();
						ga.setVisible(true);
						dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		panel_1.add(btnNewButton_1);
	}

}
