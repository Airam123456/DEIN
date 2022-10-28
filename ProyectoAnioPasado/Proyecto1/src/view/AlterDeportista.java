package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.DeporteDAO;
import dao.DeportistaDAO;
import model.Deporte;
import model.Deportista;
import model.Equipo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

public class AlterDeportista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtSexo;
	private JTextField txtPeso;
	private JTextField txtAltura;
	private boolean editar;
	private DeportistaDAO cDeportista;
	private JPanel panel_btn;
	private JButton btnActualizar;
	private JButton btnBorrar;
	private JScrollPane scrollPane;
	private JList<Deportista> list_Deportistas;
	private JButton btnCerrar;
	private DefaultListModel<Deportista> mdlDeportista;
	private boolean modificandoDatos = false;
	private int idDeportista;
	private JButton btnAceptar;

	

	/**
	 * Create the dialog.
	 */
	public AlterDeportista(boolean editar) {
		this.editar = editar;
		cDeportista= new DeportistaDAO();
		
		if (this.editar) {
			
			dibujarEditar();
			cargarDeportistas();
			cargarTxtField();
			gestionarEventos();
		}else {
			
			dibujarNuevo();
			gestionarEventos();
		}
		
	
		
		
	}
	


	private void dibujarEditar() {
		setTitle("Editar Deportistas");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 679, 261);
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
		gbl_contentPanel.columnWidths = new int[]{328, 84, 250};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		contentPanel.setBackground(new Color(204, 255, 255));
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
			txtNombre = new JTextField();
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 2;
			gbc_txtNombre.gridy = 0;
			contentPanel.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblSexo = new JLabel("Sexo: ");
			GridBagConstraints gbc_lblSexo = new GridBagConstraints();
			gbc_lblSexo.anchor = GridBagConstraints.EAST;
			gbc_lblSexo.insets = new Insets(0, 0, 5, 5);
			gbc_lblSexo.gridx = 1;
			gbc_lblSexo.gridy = 1;
			contentPanel.add(lblSexo, gbc_lblSexo);
		}
		{
			txtSexo = new JTextField();
			GridBagConstraints gbc_txtSexo = new GridBagConstraints();
			gbc_txtSexo.insets = new Insets(0, 0, 5, 0);
			gbc_txtSexo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSexo.gridx = 2;
			gbc_txtSexo.gridy = 1;
			contentPanel.add(txtSexo, gbc_txtSexo);
			txtSexo.setColumns(10);
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
			JLabel lblPeso = new JLabel("Peso: ");
			GridBagConstraints gbc_lblPeso = new GridBagConstraints();
			gbc_lblPeso.anchor = GridBagConstraints.EAST;
			gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
			gbc_lblPeso.gridx = 1;
			gbc_lblPeso.gridy = 2;
			contentPanel.add(lblPeso, gbc_lblPeso);
		}
		{
			txtPeso = new JTextField();
			GridBagConstraints gbc_txtPeso = new GridBagConstraints();
			gbc_txtPeso.insets = new Insets(0, 0, 5, 0);
			gbc_txtPeso.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPeso.gridx = 2;
			gbc_txtPeso.gridy = 2;
			contentPanel.add(txtPeso, gbc_txtPeso);
			txtPeso.setColumns(10);
		}
		{
			JLabel lblAltura = new JLabel("Altura: ");
			GridBagConstraints gbc_lblAltura = new GridBagConstraints();
			gbc_lblAltura.anchor = GridBagConstraints.EAST;
			gbc_lblAltura.insets = new Insets(0, 0, 5, 5);
			gbc_lblAltura.gridx = 1;
			gbc_lblAltura.gridy = 3;
			contentPanel.add(lblAltura, gbc_lblAltura);
		}
		{
			txtAltura = new JTextField();
			GridBagConstraints gbc_txtAltura = new GridBagConstraints();
			gbc_txtAltura.insets = new Insets(0, 0, 5, 0);
			gbc_txtAltura.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAltura.gridx = 2;
			gbc_txtAltura.gridy = 3;
			contentPanel.add(txtAltura, gbc_txtAltura);
			txtAltura.setColumns(10);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridheight = 5;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				list_Deportistas = new JList();
				
				scrollPane.setViewportView(list_Deportistas);
			}
		}
		
		{
			panel_btn = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_btn.getLayout();
			flowLayout.setHgap(25);
			GridBagConstraints gbc_panel_btn = new GridBagConstraints();
			gbc_panel_btn.gridwidth = 2;
			gbc_panel_btn.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel_btn.gridx = 1;
			gbc_panel_btn.gridy = 4;
			contentPanel.add(panel_btn, gbc_panel_btn);
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
		
	}

	public void dibujarNuevo() {
		setTitle("Nuevo Deportista");
		setBounds(100, 100, 450, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
			txtNombre = new JTextField();
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 1;
			gbc_txtNombre.gridy = 0;
			contentPanel.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblSexo = new JLabel("Sexo: ");
			GridBagConstraints gbc_lblSexo = new GridBagConstraints();
			gbc_lblSexo.anchor = GridBagConstraints.EAST;
			gbc_lblSexo.insets = new Insets(0, 0, 5, 5);
			gbc_lblSexo.gridx = 0;
			gbc_lblSexo.gridy = 1;
			contentPanel.add(lblSexo, gbc_lblSexo);
		}
		{
			txtSexo = new JTextField();
			GridBagConstraints gbc_txtSexo = new GridBagConstraints();
			gbc_txtSexo.insets = new Insets(0, 0, 5, 0);
			gbc_txtSexo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSexo.gridx = 1;
			gbc_txtSexo.gridy = 1;
			contentPanel.add(txtSexo, gbc_txtSexo);
			txtSexo.setColumns(10);
		}
		{
			JLabel lblPeso = new JLabel("Peso: ");
			GridBagConstraints gbc_lblPeso = new GridBagConstraints();
			gbc_lblPeso.anchor = GridBagConstraints.EAST;
			gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
			gbc_lblPeso.gridx = 0;
			gbc_lblPeso.gridy = 2;
			contentPanel.add(lblPeso, gbc_lblPeso);
		}
		{
			txtPeso = new JTextField();
			GridBagConstraints gbc_txtPeso = new GridBagConstraints();
			gbc_txtPeso.insets = new Insets(0, 0, 5, 0);
			gbc_txtPeso.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPeso.gridx = 1;
			gbc_txtPeso.gridy = 2;
			contentPanel.add(txtPeso, gbc_txtPeso);
			txtPeso.setColumns(10);
		}
		{
			JLabel lblAltura = new JLabel("Altura: ");
			GridBagConstraints gbc_lblAltura = new GridBagConstraints();
			gbc_lblAltura.anchor = GridBagConstraints.EAST;
			gbc_lblAltura.insets = new Insets(0, 0, 0, 5);
			gbc_lblAltura.gridx = 0;
			gbc_lblAltura.gridy = 3;
			contentPanel.add(lblAltura, gbc_lblAltura);
		}
		{
			txtAltura = new JTextField();
			GridBagConstraints gbc_txtAltura = new GridBagConstraints();
			gbc_txtAltura.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAltura.gridx = 1;
			gbc_txtAltura.gridy = 3;
			contentPanel.add(txtAltura, gbc_txtAltura);
			txtAltura.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
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
	
	

	private void cargarDeportistas() {
		ArrayList<Deportista> arrDeporte = cDeportista.selectDeportista();
		
		mdlDeportista = new DefaultListModel<Deportista>();
		mdlDeportista.addAll(arrDeporte);
		list_Deportistas.setModel(mdlDeportista);
		if (mdlDeportista.getSize()>0) {
			list_Deportistas.setSelectedIndex(0);
			btnActualizar.setEnabled(true);
			btnBorrar.setEnabled(true);
			txtNombre.setEnabled(true);
			txtAltura.setEnabled(true);
			txtPeso.setEnabled(true);
			txtSexo.setEnabled(true);
		}else {
			btnActualizar.setEnabled(false);
			btnBorrar.setEnabled(false);
			txtNombre.setEnabled(false);
			txtNombre.setText("");
			txtAltura.setEnabled(false);
			txtAltura.setText("");
			txtPeso.setEnabled(false);
			txtPeso.setText("");
			txtSexo.setEnabled(false);
			txtSexo.setText("");
		}
		
	}

	private void cargarTxtField() {
		txtNombre.setText(list_Deportistas.getSelectedValue().getNombre());
		txtAltura.setText(list_Deportistas.getSelectedValue().getAltura()+"");
		txtPeso.setText(list_Deportistas.getSelectedValue().getPeso()+"");
		txtSexo.setText(list_Deportistas.getSelectedValue().getSexo());
		
	}
	
	private void gestionarEventos() {

		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cerrar();
			}
		});
		
		
		
		if (editar) {
			list_Deportistas.addListSelectionListener(new ListSelectionListener() {
				
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
					
					int reply = JOptionPane.showConfirmDialog(getContentPane(), "Seguro que quieres borrar el deportista?", "Borrar deportista", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
					    if(cDeportista.deleteDeportista(list_Deportistas.getSelectedValue())) {
					    	JOptionPane.showMessageDialog(getContentPane(), "Deportista borrado correctamente");
					    	cargarDeportistas();
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
					idDeportista= list_Deportistas.getSelectedValue().getId();
					Deportista deportista = new Deportista(idDeportista, txtNombre.getText(), txtSexo.getText(), Integer.parseInt(txtPeso.getText()), Integer.parseInt(txtAltura.getText()));
					
					
					if (!cDeportista.existeDeportista(deportista)) {
						cDeportista.updateDeportista(deportista);
						cargarDeportistas();
					}
					
				}
			});
		
		}
		else {
			btnAceptar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Deportista deportista = new Deportista(0, txtNombre.getText(), txtSexo.getText(), Integer.parseInt(txtPeso.getText()), Integer.parseInt(txtAltura.getText()));
					
					if (!cDeportista.existeDeportista(deportista)) {
						cDeportista.insertDeportista(deportista);
						
						
					}
					
					cerrar();
				}
				
			});
		}
		
		
	}
	
	private void cerrar() {
		cDeportista.cerrarConexion();
		setVisible(false);
	}

}
