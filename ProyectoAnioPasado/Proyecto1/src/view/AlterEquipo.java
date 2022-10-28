package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.EquipoDAO;
import model.Equipo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlterEquipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_Nombre;
	private JTextField textField_Iniciales;
	private boolean editar;
	private EquipoDAO cEquipo;
	private JButton btnActualizar;
	private int idEquipo;
	private JScrollPane scrollPane;
	private JList<Equipo> list_Equipos;
	private JPanel panel_btn;
	private JButton btnCerrar;
	private JButton btnBorrar;
	private boolean modificandoDatos = false;
	private JButton btnAceptar;


	/**
	 * Create the dialog.
	 */
	
	public AlterEquipo (boolean editar) {
		this.editar = editar;
		cEquipo =  new EquipoDAO();
		
		if (this.editar) {
			
			dibujarEditar();
			cargarEquipos();
			cargarTxtField();
			gestionarEventos();
		}else {
			
			dibujarNueva();
			gestionarEventos();
		}
		
			
		
		
	}

	private void dibujarNueva() {
		setTitle("Nuevo Equipo");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 182);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{182, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		contentPanel.setBackground(new Color(204, 255, 255));
		{
			JLabel lblNombre = new JLabel("Nombre: ");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.EAST;
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 0;
			gbc_lblNombre.gridy = 0;
			contentPanel.add(lblNombre, gbc_lblNombre);
		}
		{
			textField_Nombre = new JTextField();
			GridBagConstraints gbc_textField_Nombre = new GridBagConstraints();
			gbc_textField_Nombre.insets = new Insets(0, 0, 5, 0);
			gbc_textField_Nombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_Nombre.gridx = 1;
			gbc_textField_Nombre.gridy = 0;
			contentPanel.add(textField_Nombre, gbc_textField_Nombre);
			textField_Nombre.setColumns(10);
		}
		{
			JLabel lblIniciales = new JLabel("Iniciales: ");
			GridBagConstraints gbc_lblIniciales = new GridBagConstraints();
			gbc_lblIniciales.anchor = GridBagConstraints.EAST;
			gbc_lblIniciales.insets = new Insets(0, 0, 0, 5);
			gbc_lblIniciales.gridx = 0;
			gbc_lblIniciales.gridy = 1;
			contentPanel.add(lblIniciales, gbc_lblIniciales);
		}
		{
			textField_Iniciales = new JTextField();
			GridBagConstraints gbc_textField_Iniciales = new GridBagConstraints();
			gbc_textField_Iniciales.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_Iniciales.gridx = 1;
			gbc_textField_Iniciales.gridy = 1;
			contentPanel.add(textField_Iniciales, gbc_textField_Iniciales);
			textField_Iniciales.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				btnCerrar = new JButton("Cancelar");
				
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		
	}

	private void dibujarEditar() {
		setTitle("Editar Equipos");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 689, 243);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{689, 0};
		gridBagLayout.rowHeights = new int[]{184, 35, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{328, 84, 164, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		
		{
			panel_btn = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_btn.getLayout();
			flowLayout.setHgap(25);
			GridBagConstraints gbc_panel_btn = new GridBagConstraints();
			gbc_panel_btn.gridwidth = 2;
			gbc_panel_btn.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel_btn.gridx = 1;
			gbc_panel_btn.gridy = 2;
			contentPanel.add(panel_btn, gbc_panel_btn);
			contentPanel.setBackground(new Color(204, 255, 255));
			{
				btnActualizar = new JButton("");
				panel_btn.add(btnActualizar);
				btnActualizar.setIcon(new ImageIcon(AlterEquipo.class.getResource("/images/update.png")));
				
				btnActualizar.setActionCommand("OK");
				getRootPane().setDefaultButton(btnActualizar);
			}
			{
				btnBorrar = new JButton("");
				btnBorrar.setIcon(new ImageIcon(AlterEquipo.class.getResource("/images/trash.png")));
				panel_btn.add(btnBorrar);
			}
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridheight = 3;
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			contentPanel.add(scrollPane, gbc_scrollPane);
			contentPanel.setBackground(new Color(204, 255, 255));
			{
				list_Equipos = new JList();
				
				scrollPane.setViewportView(list_Equipos);
			}
		}
		{
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setActionCommand("Cancel");
			GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
			gbc_btnCerrar.gridx = 0;
			gbc_btnCerrar.gridy = 1;
			getContentPane().add(btnCerrar, gbc_btnCerrar);
		}
		{
			JLabel lblNombre = new JLabel("Nombre: ");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.EAST;
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 1;
			gbc_lblNombre.gridy = 0;
			contentPanel.add(lblNombre, gbc_lblNombre);
		}
		{
			textField_Nombre = new JTextField();
			GridBagConstraints gbc_textField_Nombre = new GridBagConstraints();
			gbc_textField_Nombre.insets = new Insets(0, 0, 5, 0);
			gbc_textField_Nombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_Nombre.gridx = 2;
			gbc_textField_Nombre.gridy = 0;
			contentPanel.add(textField_Nombre, gbc_textField_Nombre);
			textField_Nombre.setColumns(10);

		}
		{
			JLabel lblIniciales = new JLabel("Iniciales: ");
			GridBagConstraints gbc_lblIniciales = new GridBagConstraints();
			gbc_lblIniciales.anchor = GridBagConstraints.EAST;
			gbc_lblIniciales.insets = new Insets(0, 0, 5, 5);
			gbc_lblIniciales.gridx = 1;
			gbc_lblIniciales.gridy = 1;
			contentPanel.add(lblIniciales, gbc_lblIniciales);
			
		}
		{
			textField_Iniciales = new JTextField();
			GridBagConstraints gbc_textField_Iniciales = new GridBagConstraints();
			gbc_textField_Iniciales.insets = new Insets(0, 0, 5, 0);
			gbc_textField_Iniciales.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_Iniciales.gridx = 2;
			gbc_textField_Iniciales.gridy = 1;
			contentPanel.add(textField_Iniciales, gbc_textField_Iniciales);
			textField_Iniciales.setColumns(10);

		}
		
		
	}

	private void gestionarEventos () {
		
		
		
		
		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cerrar();
			}
		});
		
		
		
		if (editar) {
			list_Equipos.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (!modificandoDatos)
						cargarTxtField();
					modificandoDatos = false;
				}
			});
			
			btnBorrar.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					modificandoDatos = true;
					int reply = JOptionPane.showConfirmDialog(getContentPane(), "Seguro que quieres borrar el equipo?", "Borrar equipo", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
					    if(cEquipo.deleteEquipo(list_Equipos.getSelectedValue())) {
					    	JOptionPane.showMessageDialog(getContentPane(), "Equipo borrado correctamente");
					    	cargarEquipos();
					    }
					    else {
					    	JOptionPane.showMessageDialog(getContentPane(), "No se puede borrar, existen dependencias");
					    }
					}
					
					
				}
				
			});
			
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modificandoDatos=true;
					idEquipo= list_Equipos.getSelectedValue().getId();
					Equipo equipoNuevo = new Equipo(idEquipo, textField_Nombre.getText(), textField_Iniciales.getText());
					
					
					if (!cEquipo.existeEquipo(equipoNuevo)) {
						cEquipo.updateEquipo(equipoNuevo);
						cargarEquipos();
					}
					
				}
			});
		
		}
		else {
			btnAceptar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Equipo equipoNuevo = new Equipo(0,textField_Nombre.getText() , textField_Iniciales.getText());
					
					if (!cEquipo.existeEquipo(equipoNuevo)) {
						cEquipo.insertEquipo(equipoNuevo);
						
					}
					
					cerrar();
				}
				
			});
		}
		
	}
	
	private void cargarEquipos() {
		ArrayList<Equipo> arrEquipo = cEquipo.selectEquipos();
		DefaultListModel<Equipo> mdlEquipo = new DefaultListModel<Equipo>();
		mdlEquipo.addAll(arrEquipo);
		list_Equipos.setModel(mdlEquipo);
		if (mdlEquipo.getSize()>0) {
			list_Equipos.setSelectedIndex(0);
			btnActualizar.setEnabled(true);
			btnBorrar.setEnabled(true);
			textField_Iniciales.setEnabled(true);
			textField_Nombre.setEnabled(true);
			
			
		}else {
			btnActualizar.setEnabled(false);
			btnBorrar.setEnabled(false);
			textField_Iniciales.setText("");
			textField_Iniciales.setEnabled(false);
			textField_Nombre.setText("");
			textField_Nombre.setEnabled(false);
		}
			
	}
	
	private void cargarTxtField() {
		textField_Iniciales.setText(list_Equipos.getSelectedValue().getIniciales());
		textField_Nombre.setText(list_Equipos.getSelectedValue().getNombre());
	}
	
	
	private void cerrar() {
		cEquipo.cerrarConexion();
		setVisible(false);
	}
	
}
