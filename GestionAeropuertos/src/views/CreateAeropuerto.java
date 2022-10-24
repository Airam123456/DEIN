package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.AeropuertoPublico;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class CreateAeropuerto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JLabel lblNombre;
	private JTextField textFieldPais;
	private JLabel lblPas;
	private JTextField textFieldCiudad;
	private JTextField textFieldCalle;
	private JTextField textFieldNumero;
	private JTextField textFieldAnoInaguracion;
	private JTextField textFieldCapacidad;
	private JTextField textFieldNumTrabajadores;
	private JTextField textFieldFinanciacion;
	private JTextField textFieldNumSocios;
	private JLabel lblCiudad;
	private JLabel lblCalle;
	private JLabel lblNumero;
	private JLabel lblAnoInaguracion;
	private JLabel lblCapacidad;
	private JLabel lblNumTrabajadores;
	private JLabel lblFinanciacion;
	private JLabel lblNumSocios;
	private JPanel panel;
	private JButton btnGuardar;
	private JButton btnCancelar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAeropuerto frame = new CreateAeropuerto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreateAeropuerto() {
		dibujar();
		eventos();
	}

	private void dibujar() {
		setTitle("Aviones - Aeropuerto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel("DATOS DEL AEROPUERTO");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		String[] headers =  AeropuertoPublico.getCampos();	
		
		lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		contentPane.add(lblNombre, gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 1;
		contentPane.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblPas = new JLabel("País:");
		GridBagConstraints gbc_lblPas = new GridBagConstraints();
		gbc_lblPas.insets = new Insets(0, 0, 5, 5);
		gbc_lblPas.anchor = GridBagConstraints.EAST;
		gbc_lblPas.gridx = 0;
		gbc_lblPas.gridy = 2;
		contentPane.add(lblPas, gbc_lblPas);
		
		textFieldPais = new JTextField();
		GridBagConstraints gbc_textFieldPais = new GridBagConstraints();
		gbc_textFieldPais.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPais.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPais.gridx = 1;
		gbc_textFieldPais.gridy = 2;
		contentPane.add(textFieldPais, gbc_textFieldPais);
		textFieldPais.setColumns(10);
		
		lblCiudad = new JLabel("Ciudad:");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 3;
		contentPane.add(lblCiudad, gbc_lblCiudad);
		
		textFieldCiudad = new JTextField();
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 1;
		gbc_textFieldCiudad.gridy = 3;
		contentPane.add(textFieldCiudad, gbc_textFieldCiudad);
		textFieldCiudad.setColumns(10);
		
		lblCalle = new JLabel("Calle");
		GridBagConstraints gbc_lblCalle = new GridBagConstraints();
		gbc_lblCalle.insets = new Insets(0, 0, 5, 5);
		gbc_lblCalle.anchor = GridBagConstraints.EAST;
		gbc_lblCalle.gridx = 0;
		gbc_lblCalle.gridy = 4;
		contentPane.add(lblCalle, gbc_lblCalle);
		
		textFieldCalle = new JTextField();
		GridBagConstraints gbc_textFieldCalle = new GridBagConstraints();
		gbc_textFieldCalle.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCalle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCalle.gridx = 1;
		gbc_textFieldCalle.gridy = 4;
		contentPane.add(textFieldCalle, gbc_textFieldCalle);
		textFieldCalle.setColumns(10);
		
		lblNumero = new JLabel("Número");
		GridBagConstraints gbc_lblNumero = new GridBagConstraints();
		gbc_lblNumero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumero.anchor = GridBagConstraints.EAST;
		gbc_lblNumero.gridx = 0;
		gbc_lblNumero.gridy = 5;
		contentPane.add(lblNumero, gbc_lblNumero);
		
		textFieldNumero = new JTextField();
		GridBagConstraints gbc_textFieldNumero = new GridBagConstraints();
		gbc_textFieldNumero.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNumero.gridx = 1;
		gbc_textFieldNumero.gridy = 5;
		contentPane.add(textFieldNumero, gbc_textFieldNumero);
		textFieldNumero.setColumns(10);
		
		lblAnoInaguracion = new JLabel("Año de inaguración:");
		lblAnoInaguracion.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblAnoInaguracion = new GridBagConstraints();
		gbc_lblAnoInaguracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnoInaguracion.anchor = GridBagConstraints.EAST;
		gbc_lblAnoInaguracion.gridx = 0;
		gbc_lblAnoInaguracion.gridy = 6;
		contentPane.add(lblAnoInaguracion, gbc_lblAnoInaguracion);
		
		textFieldAnoInaguracion = new JTextField();
		GridBagConstraints gbc_textFieldAnoInaguracion = new GridBagConstraints();
		gbc_textFieldAnoInaguracion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAnoInaguracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnoInaguracion.gridx = 1;
		gbc_textFieldAnoInaguracion.gridy = 6;
		contentPane.add(textFieldAnoInaguracion, gbc_textFieldAnoInaguracion);
		textFieldAnoInaguracion.setColumns(10);
		
		lblCapacidad = new JLabel("Capacidad:");
		GridBagConstraints gbc_lblCapacidad = new GridBagConstraints();
		gbc_lblCapacidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapacidad.anchor = GridBagConstraints.EAST;
		gbc_lblCapacidad.gridx = 0;
		gbc_lblCapacidad.gridy = 7;
		contentPane.add(lblCapacidad, gbc_lblCapacidad);
		
		textFieldCapacidad = new JTextField();
		textFieldCapacidad.setText("");
		GridBagConstraints gbc_textFieldCapacidad = new GridBagConstraints();
		gbc_textFieldCapacidad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCapacidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCapacidad.gridx = 1;
		gbc_textFieldCapacidad.gridy = 7;
		contentPane.add(textFieldCapacidad, gbc_textFieldCapacidad);
		textFieldCapacidad.setColumns(10);
		
		lblNumTrabajadores = new JLabel("Número de trabajadores:");
		GridBagConstraints gbc_lblNumTrabajadores = new GridBagConstraints();
		gbc_lblNumTrabajadores.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumTrabajadores.anchor = GridBagConstraints.EAST;
		gbc_lblNumTrabajadores.gridx = 0;
		gbc_lblNumTrabajadores.gridy = 8;
		contentPane.add(lblNumTrabajadores, gbc_lblNumTrabajadores);
		
		textFieldNumTrabajadores = new JTextField();
		GridBagConstraints gbc_textFieldNumTrabajadores = new GridBagConstraints();
		gbc_textFieldNumTrabajadores.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNumTrabajadores.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNumTrabajadores.gridx = 1;
		gbc_textFieldNumTrabajadores.gridy = 8;
		contentPane.add(textFieldNumTrabajadores, gbc_textFieldNumTrabajadores);
		textFieldNumTrabajadores.setColumns(10);
		
		lblFinanciacion = new JLabel("Financiación:");
		GridBagConstraints gbc_lblFinanciacion = new GridBagConstraints();
		gbc_lblFinanciacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblFinanciacion.anchor = GridBagConstraints.EAST;
		gbc_lblFinanciacion.gridx = 0;
		gbc_lblFinanciacion.gridy = 9;
		contentPane.add(lblFinanciacion, gbc_lblFinanciacion);
		
		textFieldFinanciacion = new JTextField();
		GridBagConstraints gbc_textFieldFinanciacion = new GridBagConstraints();
		gbc_textFieldFinanciacion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFinanciacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFinanciacion.gridx = 1;
		gbc_textFieldFinanciacion.gridy = 9;
		contentPane.add(textFieldFinanciacion, gbc_textFieldFinanciacion);
		textFieldFinanciacion.setColumns(10);
		
		lblNumSocios = new JLabel("Número de socios:");
		GridBagConstraints gbc_lblNumSocios = new GridBagConstraints();
		gbc_lblNumSocios.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumSocios.anchor = GridBagConstraints.EAST;
		gbc_lblNumSocios.gridx = 0;
		gbc_lblNumSocios.gridy = 10;
		contentPane.add(lblNumSocios, gbc_lblNumSocios);
		
		textFieldNumSocios = new JTextField();
		GridBagConstraints gbc_textFieldNumSocios = new GridBagConstraints();
		gbc_textFieldNumSocios.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNumSocios.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNumSocios.gridx = 1;
		gbc_textFieldNumSocios.gridy = 10;
		contentPane.add(textFieldNumSocios, gbc_textFieldNumSocios);
		textFieldNumSocios.setColumns(10);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(15);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 11;
		contentPane.add(panel, gbc_panel);
		
		btnGuardar = new JButton("Guardar");
		panel.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
	}
	
	private void eventos() {
		// TODO Auto-generated method stub
		
	}
}
