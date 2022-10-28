package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import dao.DeportistaDAO;
import dao.EquipoDAO;
import dao.EventoDAO;
import dao.ParticipacionDAO;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Participacion;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class AlterParticipacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<Deportista> list_Deportista;
	private JComboBox<Equipo> comboBox_Equipo;
	private JComboBox<String> comboBox_Medalla;
	private JButton okButton;
	private JButton cancelButton;
	private Evento evento;
	private Participacion participacion;
	private EquipoDAO cEquipo;
	private DeportistaDAO cDeportista;
	private EventoDAO cEvento;
	private ParticipacionDAO cParticipacion;
	private JTextField txtEdad;
	
	

	
	public AlterParticipacion(Evento evento) {
		this.evento=evento;
		iniciarDAO();
		setTitle("Nueva Participacion");
		dibujar();
		cargarDatos();
		gestionarEventos();
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public AlterParticipacion(Evento evento, Participacion participacion) {
		this.evento=evento;
		this.participacion=participacion;
		iniciarDAO();
		
		dibujar();
		cargarDatos();
		gestionarEventos();
	}
	
	private void iniciarDAO() {
		cParticipacion = new ParticipacionDAO();
		cEquipo =  new EquipoDAO();
		cDeportista =  new DeportistaDAO();
		cEvento = new EventoDAO();
	}
	
	private void dibujar () {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 809, 276);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{377, 414, 0};
		gbl_contentPanel.rowHeights = new int[]{206, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel_Listas = new JPanel();
			panel_Listas.setBackground(new Color(204, 255, 255));
			panel_Listas.setBorder(new EmptyBorder(10, 10, 10, 10));
			GridBagConstraints gbc_panel_Listas = new GridBagConstraints();
			gbc_panel_Listas.fill = GridBagConstraints.BOTH;
			gbc_panel_Listas.insets = new Insets(0, 0, 0, 5);
			gbc_panel_Listas.gridx = 0;
			gbc_panel_Listas.gridy = 0;
			contentPanel.add(panel_Listas, gbc_panel_Listas);
			GridBagLayout gbl_panel_Listas = new GridBagLayout();
			gbl_panel_Listas.columnWidths = new int[]{157, 0};
			gbl_panel_Listas.rowHeights = new int[]{0, 0};
			gbl_panel_Listas.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel_Listas.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel_Listas.setLayout(gbl_panel_Listas);
			{
				JScrollPane scrollPane_Deportista = new JScrollPane();
				GridBagConstraints gbc_scrollPane_Deportista = new GridBagConstraints();
				gbc_scrollPane_Deportista.fill = GridBagConstraints.BOTH;
				gbc_scrollPane_Deportista.gridx = 0;
				gbc_scrollPane_Deportista.gridy = 0;
				panel_Listas.add(scrollPane_Deportista, gbc_scrollPane_Deportista);
				{
					list_Deportista = new JList<Deportista>();
					list_Deportista.setBorder(new TitledBorder(null, "Deportista", TitledBorder.LEADING, TitledBorder.TOP, null, null));
					scrollPane_Deportista.setViewportView(list_Deportista);
				}
			}
		}
		{
			JPanel panel_Combobox = new JPanel();
			panel_Combobox.setBackground(new Color(204, 255, 255));
			GridBagConstraints gbc_panel_Combobox = new GridBagConstraints();
			gbc_panel_Combobox.fill = GridBagConstraints.BOTH;
			gbc_panel_Combobox.gridx = 1;
			gbc_panel_Combobox.gridy = 0;
			contentPanel.add(panel_Combobox, gbc_panel_Combobox);
			GridBagLayout gbl_panel_Combobox = new GridBagLayout();
			gbl_panel_Combobox.columnWidths = new int[]{116, 0, 0, 0};
			gbl_panel_Combobox.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel_Combobox.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel_Combobox.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
			panel_Combobox.setLayout(gbl_panel_Combobox);
			{
				JLabel lblEquipo = new JLabel("Equipo:");
				GridBagConstraints gbc_lblEquipo = new GridBagConstraints();
				gbc_lblEquipo.anchor = GridBagConstraints.EAST;
				gbc_lblEquipo.insets = new Insets(0, 0, 5, 5);
				gbc_lblEquipo.gridx = 0;
				gbc_lblEquipo.gridy = 0;
				panel_Combobox.add(lblEquipo, gbc_lblEquipo);
			}
			{
				comboBox_Equipo = new JComboBox<Equipo>();
				GridBagConstraints gbc_comboBox_Equipo = new GridBagConstraints();
				gbc_comboBox_Equipo.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox_Equipo.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox_Equipo.gridx = 1;
				gbc_comboBox_Equipo.gridy = 0;
				panel_Combobox.add(comboBox_Equipo, gbc_comboBox_Equipo);
			}
			{
				JLabel lblMedalla = new JLabel("Medalla:");
				GridBagConstraints gbc_lblMedalla = new GridBagConstraints();
				gbc_lblMedalla.anchor = GridBagConstraints.EAST;
				gbc_lblMedalla.insets = new Insets(0, 0, 5, 5);
				gbc_lblMedalla.gridx = 0;
				gbc_lblMedalla.gridy = 1;
				panel_Combobox.add(lblMedalla, gbc_lblMedalla);
			}
			{
				comboBox_Medalla = new JComboBox<String>();
				comboBox_Medalla.setModel(new DefaultComboBoxModel(new String[] {"Gold", "Silver", "Bronze", "Sin medalla"}));
				comboBox_Medalla.setSelectedIndex(0);
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 1;
				panel_Combobox.add(comboBox_Medalla, gbc_comboBox);
			}
			{
				JLabel lblEdad = new JLabel("Edad:");
				GridBagConstraints gbc_lblEdad = new GridBagConstraints();
				gbc_lblEdad.anchor = GridBagConstraints.EAST;
				gbc_lblEdad.insets = new Insets(0, 0, 0, 5);
				gbc_lblEdad.gridx = 0;
				gbc_lblEdad.gridy = 2;
				panel_Combobox.add(lblEdad, gbc_lblEdad);
			}
			{
				txtEdad = new JTextField();
				GridBagConstraints gbc_txtEdad = new GridBagConstraints();
				gbc_txtEdad.anchor = GridBagConstraints.WEST;
				gbc_txtEdad.insets = new Insets(0, 0, 0, 5);
				gbc_txtEdad.gridx = 1;
				gbc_txtEdad.gridy = 2;
				panel_Combobox.add(txtEdad, gbc_txtEdad);
				txtEdad.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Aceptar");
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (participacion !=null) {
			cargarParticipacion();
		}
	
	}
	
	private void cargarParticipacion () {
		comboBox_Equipo.setSelectedItem(participacion.getEquipo());
		list_Deportista.setSelectedValue(participacion.getDeportista(), true);
		txtEdad.setText(participacion.getEdad()+"");
		String medalla = participacion.getMedalla();
		if(medalla == null) {
			comboBox_Medalla.setSelectedIndex(3);
		}
		else {
			if(medalla.equals("Gold")) {
				comboBox_Medalla.setSelectedIndex(0);
			}
			if(medalla.equals("Silver")) {
				comboBox_Medalla.setSelectedIndex(1);
			}
			if(medalla.equals("Bronze")) {
				comboBox_Medalla.setSelectedIndex(2);
			}
		}
		setTitle("Editar Participacion :" + participacion.getDeportista().getId() + "_" + participacion.getEvento().getId_evento() + "_" + participacion.getEquipo().getId());
		
	}
	
	private void gestionarEventos () {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (participacion==null) {
					try {
						int edad=Integer.parseInt(txtEdad.getText());
						Deportista deportista = list_Deportista.getSelectedValue();
						Equipo equipo = (Equipo) comboBox_Equipo.getSelectedItem();
						String medalla = (String) comboBox_Medalla.getSelectedItem();
						
						
						participacion =new Participacion(deportista, evento, equipo, edad, medalla);
						cParticipacion.insertarParticipacion(participacion);
					} catch (NumberFormatException | SQLException exc) {
						JOptionPane.showMessageDialog(getContentPane(), "Error al añadir Participacion");
					}
					
					cerrar();
					
				}else {
					try {
						int edad=Integer.parseInt(txtEdad.getText());
						Deportista deportista = list_Deportista.getSelectedValue();
						Equipo equipo = (Equipo) comboBox_Equipo.getSelectedItem();
						String medalla = (String) comboBox_Medalla.getSelectedItem();
						Deportista anterior= participacion.getDeportista();
						
						participacion =new Participacion(deportista, evento, equipo, edad, medalla);
						cParticipacion.updateParticipacion(participacion, anterior);
					} catch (NumberFormatException | SQLException exc) {
						JOptionPane.showMessageDialog(getContentPane(), "Error al añadir Participacion");
					}
					
					cerrar();
				}
			}
		});
		
		
	}
	
	private void cargarDatos () {
		
		DefaultComboBoxModel<Equipo> modeloEquipos = new DefaultComboBoxModel<Equipo>();
		modeloEquipos.addAll(cEquipo.selectEquipos());
		comboBox_Equipo.setModel(modeloEquipos);
		if(modeloEquipos.getSize() > 0) {
			comboBox_Equipo.setSelectedIndex(0);
		}
		
		
		DefaultListModel<Deportista> modeloDeportistas = new DefaultListModel<Deportista>();
		modeloDeportistas.addAll(cDeportista.selectDeportista());
		list_Deportista.setModel(modeloDeportistas);
		if(modeloDeportistas.size() > 0) {
			list_Deportista.setSelectedIndex(0);
		}	
	}

	private void cerrar() {
		cParticipacion.cerrarConexion();
		cDeportista.cerrarConexion();
		cEquipo.cerrarConexion();
		setVisible(false);
	}

	
}
