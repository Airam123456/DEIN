package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevaOlimpiada extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaOlimpiada frame = new NuevaOlimpiada();
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
	public NuevaOlimpiada() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblModificarOlimpiadas = new JLabel("NUEVA OLIMPIADA");
		GridBagConstraints gbc_lblModificarOlimpiadas = new GridBagConstraints();
		gbc_lblModificarOlimpiadas.insets = new Insets(0, 0, 5, 0);
		gbc_lblModificarOlimpiadas.gridx = 0;
		gbc_lblModificarOlimpiadas.gridy = 0;
		contentPane.add(lblModificarOlimpiadas, gbc_lblModificarOlimpiadas);
		
		JPanel panel_lbl_txtfield = new JPanel();
		GridBagConstraints gbc_panel_lbl_txtfield = new GridBagConstraints();
		gbc_panel_lbl_txtfield.insets = new Insets(0, 0, 5, 0);
		gbc_panel_lbl_txtfield.fill = GridBagConstraints.BOTH;
		gbc_panel_lbl_txtfield.gridx = 0;
		gbc_panel_lbl_txtfield.gridy = 1;
		contentPane.add(panel_lbl_txtfield, gbc_panel_lbl_txtfield);
		GridBagLayout gbl_panel_lbl_txtfield = new GridBagLayout();
		gbl_panel_lbl_txtfield.columnWidths = new int[]{0, 0, 0};
		gbl_panel_lbl_txtfield.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_lbl_txtfield.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_lbl_txtfield.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_lbl_txtfield.setLayout(gbl_panel_lbl_txtfield);
		
		JLabel lblAnio = new JLabel("Año:");
		GridBagConstraints gbc_lblAnio = new GridBagConstraints();
		gbc_lblAnio.anchor = GridBagConstraints.EAST;
		gbc_lblAnio.fill = GridBagConstraints.VERTICAL;
		gbc_lblAnio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnio.gridx = 0;
		gbc_lblAnio.gridy = 0;
		panel_lbl_txtfield.add(lblAnio, gbc_lblAnio);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_lbl_txtfield.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblTemporada = new JLabel("Temporada:");
		GridBagConstraints gbc_lblTemporada = new GridBagConstraints();
		gbc_lblTemporada.anchor = GridBagConstraints.EAST;
		gbc_lblTemporada.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemporada.gridx = 0;
		gbc_lblTemporada.gridy = 1;
		panel_lbl_txtfield.add(lblTemporada, gbc_lblTemporada);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Verano", "Invierno"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel_lbl_txtfield.add(comboBox, gbc_comboBox);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 0, 5);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 2;
		panel_lbl_txtfield.add(lblCiudad, gbc_lblCiudad);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel_lbl_txtfield.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_btn = new JPanel();
		GridBagConstraints gbc_panel_btn = new GridBagConstraints();
		gbc_panel_btn.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_btn.gridx = 0;
		gbc_panel_btn.gridy = 2;
		contentPane.add(panel_btn, gbc_panel_btn);
		
		JButton btnAceptar = new JButton("Aceptar");
		panel_btn.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
			}
		});
		panel_btn.add(btnCancelar);
	}

}
