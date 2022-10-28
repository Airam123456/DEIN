package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DeporteDAO;
import dao.DeportistaDAO;
import dao.EquipoDAO;
import dao.EventoDAO;
import dao.OlimpiadaDAO;
import dao.ParticipacionDAO;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

public class Indice extends JFrame {

	private JPanel contentPane;
	private JComboBox<Olimpiada> comboBox_Olimpiadas;
	private JList<Evento> list_Evento;
	private OlimpiadaDAO cOlimpiada;
	private EventoDAO cEvento;
	private DeporteDAO cDeporte;
	private EquipoDAO cEquipo;
	private DeportistaDAO cDeportista;
	private ParticipacionDAO cParticipacion;
	private JButton btnAniadirOlimpiada, btnEditarOlimpiada, btnBorrarOlimpiada;
	private JButton btnAniadirEvento, btnEditarEvento, btnBorrarEvento;
	private JRadioButton rdbtnSummer, rdbtnWinter;
	private DefaultComboBoxModel<Olimpiada> mdlOlimpiada;
	private DefaultListModel<Evento> mdlEvento;
	private DefaultListModel <Participacion> mdlPart;
	private JMenuItem mntmAniadir_equipo;
	private JMenuItem mntmGestionar_equipo;
	private JMenuItem mntmAniadir_deporte;
	private JMenuItem mntmGestionar_Deporte;
	private JMenuItem mntmAniadir_deportista;
	private JMenuItem mntmGestionar_deportista;
	private JList<Participacion> list_Participaciones;
	private JButton btnAniadir_Participacion;
	private JButton btnEditar_Participacion;
	private JButton btnBorrar_Participacion;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Indice frame = new Indice();
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
	public Indice() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Indice.class.getResource("/images/sports.jpg")));
		
		
		iniciarDAO();

		dibujar();
		cargarComboOlimpiadasVerano();
		cargarEventos();
		cargarParticipaciones();
		gestionarEventos();
	}

	private void iniciarDAO() {
		cOlimpiada= new OlimpiadaDAO();
		cEvento = new EventoDAO();
		cDeportista= new DeportistaDAO();
		cEquipo = new EquipoDAO ();
		cDeporte = new DeporteDAO();
		cParticipacion = new ParticipacionDAO();
	}
	
	private void dibujar () {
		setTitle("Gestor de Olimpiadas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 492);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDeportista = new JMenu("Deportistas");
		menuBar.add(mnDeportista);
		
		mntmAniadir_deportista = new JMenuItem("Añadir");
		
		mnDeportista.add(mntmAniadir_deportista);
		
		mntmGestionar_deportista = new JMenuItem("Gestionar");
		
		mnDeportista.add(mntmGestionar_deportista);
		
		if(cDeportista.selectDeportista().isEmpty())
			mntmGestionar_deportista.setEnabled(false);
		else
			mntmGestionar_deportista.setEnabled(true);
		
		JMenu mnDeporte = new JMenu("Deportes");
		menuBar.add(mnDeporte);
		
		mntmAniadir_deporte = new JMenuItem("Añadir");
		
		mnDeporte.add(mntmAniadir_deporte);
		
		mntmGestionar_Deporte = new JMenuItem("Gestionar");
		
		if (cDeporte.selectDeportes().isEmpty())
			mntmGestionar_Deporte.setEnabled(false);
		else 
			mntmGestionar_Deporte.setEnabled(true);
		
			
		
		
		mnDeporte.add(mntmGestionar_Deporte);
		
		JMenu mnEquipo = new JMenu("Equipos");
		menuBar.add(mnEquipo);
		
		mntmAniadir_equipo = new JMenuItem("Añadir");
		
		mnEquipo.add(mntmAniadir_equipo);
		
		mntmGestionar_equipo = new JMenuItem("Gestionar");
		
		if(cEquipo.selectEquipos().isEmpty())
			mntmGestionar_equipo.setEnabled(false);
		else
			mntmGestionar_equipo.setEnabled(true);
		
		mnEquipo.add(mntmGestionar_equipo);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{94, 102, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_olimpiadas = new JPanel();
		panel_olimpiadas.setBackground(new Color(204, 255, 255));
		panel_olimpiadas.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_panel_olimpiadas = new GridBagConstraints();
		gbc_panel_olimpiadas.insets = new Insets(0, 0, 5, 0);
		gbc_panel_olimpiadas.fill = GridBagConstraints.BOTH;
		gbc_panel_olimpiadas.gridx = 0;
		gbc_panel_olimpiadas.gridy = 0;
		contentPane.add(panel_olimpiadas, gbc_panel_olimpiadas);
		GridBagLayout gbl_panel_olimpiadas = new GridBagLayout();
		gbl_panel_olimpiadas.columnWidths = new int[]{0, 337, 0, 0, 0, 0, 0};
		gbl_panel_olimpiadas.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_olimpiadas.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_olimpiadas.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_olimpiadas.setLayout(gbl_panel_olimpiadas);
		
		JLabel lblOlimpiadas = new JLabel("OLIMPIADAS");
		lblOlimpiadas.setForeground(SystemColor.desktop);
		GridBagConstraints gbc_lblOlimpiadas = new GridBagConstraints();
		gbc_lblOlimpiadas.insets = new Insets(0, 0, 5, 5);
		gbc_lblOlimpiadas.anchor = GridBagConstraints.EAST;
		gbc_lblOlimpiadas.gridx = 0;
		gbc_lblOlimpiadas.gridy = 1;
		panel_olimpiadas.add(lblOlimpiadas, gbc_lblOlimpiadas);
		
	
		btnAniadirOlimpiada = new JButton("");
		
		btnAniadirOlimpiada.setIcon(new ImageIcon(Indice.class.getResource("/images/new.png")));
		GridBagConstraints gbc_btnAniadirOlimpiada = new GridBagConstraints();
		gbc_btnAniadirOlimpiada.insets = new Insets(0, 0, 5, 5);
		gbc_btnAniadirOlimpiada.gridx = 2;
		gbc_btnAniadirOlimpiada.gridy = 1;
		panel_olimpiadas.add(btnAniadirOlimpiada, gbc_btnAniadirOlimpiada);
		
		btnEditarOlimpiada = new JButton("");
		
		btnEditarOlimpiada.setIcon(new ImageIcon(Indice.class.getResource("/images/edit.png")));
		GridBagConstraints gbc_btnEditarOlimpiada = new GridBagConstraints();
		gbc_btnEditarOlimpiada.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditarOlimpiada.gridx = 3;
		gbc_btnEditarOlimpiada.gridy = 1;
		panel_olimpiadas.add(btnEditarOlimpiada, gbc_btnEditarOlimpiada);
		
		btnBorrarOlimpiada = new JButton("");
		
		btnBorrarOlimpiada.setIcon(new ImageIcon(Indice.class.getResource("/images/trash.png")));
		GridBagConstraints gbc_btnBorrarOlimpiada = new GridBagConstraints();
		gbc_btnBorrarOlimpiada.insets = new Insets(0, 0, 5, 5);
		gbc_btnBorrarOlimpiada.gridx = 4;
		gbc_btnBorrarOlimpiada.gridy = 1;
		panel_olimpiadas.add(btnBorrarOlimpiada, gbc_btnBorrarOlimpiada);
		
		comboBox_Olimpiadas = new JComboBox();
		
		
		
		GridBagConstraints gbc_comboBox_Olimpiadas = new GridBagConstraints();
		gbc_comboBox_Olimpiadas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Olimpiadas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Olimpiadas.gridx = 1;
		gbc_comboBox_Olimpiadas.gridy = 1;
		panel_olimpiadas.add(comboBox_Olimpiadas, gbc_comboBox_Olimpiadas);
		
		
		
		GridBagConstraints gbc_btnVerOlimpiada = new GridBagConstraints();
		gbc_btnVerOlimpiada.insets = new Insets(0, 0, 5, 0);
		gbc_btnVerOlimpiada.gridx = 5;
		gbc_btnVerOlimpiada.gridy = 1;
		
		JPanel panel_rdbtn_olimpiadas = new JPanel();
		GridBagConstraints gbc_panel_rdbtn_olimpiadas = new GridBagConstraints();
		gbc_panel_rdbtn_olimpiadas.insets = new Insets(0, 0, 0, 5);
		gbc_panel_rdbtn_olimpiadas.fill = GridBagConstraints.BOTH;
		gbc_panel_rdbtn_olimpiadas.gridx = 1;
		gbc_panel_rdbtn_olimpiadas.gridy = 2;
		panel_olimpiadas.add(panel_rdbtn_olimpiadas, gbc_panel_rdbtn_olimpiadas);
		
		ButtonGroup bg = new ButtonGroup();
		
		rdbtnSummer = new JRadioButton("Verano");
		rdbtnSummer.setSelected(true);
		
		panel_rdbtn_olimpiadas.add(rdbtnSummer);
		bg.add(rdbtnSummer);
		
		rdbtnWinter = new JRadioButton("Invierno");
		
		panel_rdbtn_olimpiadas.add(rdbtnWinter);
		bg.add(rdbtnWinter);
		
		JPanel panel_Eventos = new JPanel();
		panel_Eventos.setBackground(new Color(204, 255, 255));
		panel_Eventos.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_panel_Eventos = new GridBagConstraints();
		gbc_panel_Eventos.insets = new Insets(0, 0, 5, 0);
		gbc_panel_Eventos.fill = GridBagConstraints.BOTH;
		gbc_panel_Eventos.gridx = 0;
		gbc_panel_Eventos.gridy = 1;
		contentPane.add(panel_Eventos, gbc_panel_Eventos);
		GridBagLayout gbl_panel_Eventos = new GridBagLayout();
		gbl_panel_Eventos.columnWidths = new int[]{588, 130, 0};
		gbl_panel_Eventos.rowHeights = new int[]{101, 0};
		gbl_panel_Eventos.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_Eventos.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_Eventos.setLayout(gbl_panel_Eventos);
		
		JScrollPane scrollPane_Evento = new JScrollPane();
		GridBagConstraints gbc_scrollPane_Evento = new GridBagConstraints();
		gbc_scrollPane_Evento.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Evento.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_Evento.gridx = 0;
		gbc_scrollPane_Evento.gridy = 0;
		panel_Eventos.add(scrollPane_Evento, gbc_scrollPane_Evento);
		
		list_Evento = new JList();
		scrollPane_Evento.setViewportView(list_Evento);
		list_Evento.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "EVENTOS", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 92, 92)));
		
		
		JPanel panel_btn_Evento = new JPanel();
		panel_btn_Evento.setBackground(new Color(204, 255, 255));
		GridBagConstraints gbc_panel_btn_Evento = new GridBagConstraints();
		gbc_panel_btn_Evento.fill = GridBagConstraints.BOTH;
		gbc_panel_btn_Evento.gridx = 1;
		gbc_panel_btn_Evento.gridy = 0;
		panel_Eventos.add(panel_btn_Evento, gbc_panel_btn_Evento);
		GridBagLayout gbl_panel_btn_Evento = new GridBagLayout();
		gbl_panel_btn_Evento.columnWidths = new int[]{34, 0};
		gbl_panel_btn_Evento.rowHeights = new int[]{10, 0, 0, 0};
		gbl_panel_btn_Evento.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_btn_Evento.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_btn_Evento.setLayout(gbl_panel_btn_Evento);
		
		btnAniadirEvento = new JButton("");
		
		btnAniadirEvento.setIcon(new ImageIcon(Indice.class.getResource("/images/new.png")));
		GridBagConstraints gbc_btnAniadirEvento = new GridBagConstraints();
		gbc_btnAniadirEvento.insets = new Insets(0, 0, 5, 0);
		gbc_btnAniadirEvento.gridx = 0;
		gbc_btnAniadirEvento.gridy = 0;
		panel_btn_Evento.add(btnAniadirEvento, gbc_btnAniadirEvento);
		
		btnEditarEvento = new JButton("");
		
		btnEditarEvento.setIcon(new ImageIcon(Indice.class.getResource("/images/edit.png")));
		GridBagConstraints gbc_btnEditarEvento = new GridBagConstraints();
		gbc_btnEditarEvento.insets = new Insets(0, 0, 5, 0);
		gbc_btnEditarEvento.gridx = 0;
		gbc_btnEditarEvento.gridy = 1;
		panel_btn_Evento.add(btnEditarEvento, gbc_btnEditarEvento);
		
		btnBorrarEvento = new JButton("");
		
		btnBorrarEvento.setIcon(new ImageIcon(Indice.class.getResource("/images/trash.png")));
		GridBagConstraints gbc_btnBorrarEvento = new GridBagConstraints();
		gbc_btnBorrarEvento.gridx = 0;
		gbc_btnBorrarEvento.gridy = 2;
		panel_btn_Evento.add(btnBorrarEvento, gbc_btnBorrarEvento);
		
		JPanel panel_Participaciones = new JPanel();
		panel_Participaciones.setBackground(new Color(204, 255, 255));
		panel_Participaciones.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_panel_Participaciones = new GridBagConstraints();
		gbc_panel_Participaciones.fill = GridBagConstraints.BOTH;
		gbc_panel_Participaciones.gridx = 0;
		gbc_panel_Participaciones.gridy = 2;
		contentPane.add(panel_Participaciones, gbc_panel_Participaciones);
		GridBagLayout gbl_panel_Participaciones = new GridBagLayout();
		gbl_panel_Participaciones.columnWidths = new int[]{375, 0, 0};
		gbl_panel_Participaciones.rowHeights = new int[]{16, 0};
		gbl_panel_Participaciones.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_Participaciones.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_Participaciones.setLayout(gbl_panel_Participaciones);
		
		JScrollPane scrollPane_Participaciones = new JScrollPane();
		GridBagConstraints gbc_scrollPane_Participaciones = new GridBagConstraints();
		gbc_scrollPane_Participaciones.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Participaciones.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_Participaciones.gridx = 0;
		gbc_scrollPane_Participaciones.gridy = 0;
		panel_Participaciones.add(scrollPane_Participaciones, gbc_scrollPane_Participaciones);
		
		list_Participaciones = new JList();
		list_Participaciones.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "PARTICIPACIONES", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 92, 92)));
		scrollPane_Participaciones.setViewportView(list_Participaciones);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(15);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		panel_Participaciones.add(panel, gbc_panel);
		
		btnAniadir_Participacion = new JButton("");
		
		btnAniadir_Participacion.setIcon(new ImageIcon(Indice.class.getResource("/images/new.png")));
		panel.add(btnAniadir_Participacion);
		
		btnEditar_Participacion = new JButton("");
		
		btnEditar_Participacion.setIcon(new ImageIcon(Indice.class.getResource("/images/edit.png")));
		panel.add(btnEditar_Participacion);
		
		btnBorrar_Participacion = new JButton("");
		
		btnBorrar_Participacion.setIcon(new ImageIcon(Indice.class.getResource("/images/trash.png")));
		panel.add(btnBorrar_Participacion);
		
		this.pack();
	}
	
	private void gestionarEventos () {
		btnAniadirEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterEvento frame = new AlterEvento ((Olimpiada) comboBox_Olimpiadas.getSelectedItem());
				frame.setVisible(true);
				cargarEventos();
				cargarParticipaciones();
			}
		});
		
		rdbtnWinter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cargarComboOlimpiadasInvierno();
				if (mdlOlimpiada.getSize()>0) {
					cargarEventos();
					cargarParticipaciones();
				}
				else {
					mdlEvento.clear();
					mdlPart.clear();
				}
					
			}
		});
		
		rdbtnSummer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cargarComboOlimpiadasVerano();
				if (mdlOlimpiada.getSize()>0) {
					cargarEventos();
					cargarParticipaciones();
				}
				else {
					mdlEvento.clear();
					mdlPart.clear();
				}
					
				
			}
		});
		
		comboBox_Olimpiadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarEventos();
				if (mdlEvento.getSize()>0) {
					cargarParticipaciones();
				}else {
					mdlPart.clear();
				}
				
			}
		});
		
		btnBorrarOlimpiada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int reply = JOptionPane.showConfirmDialog(getContentPane(), "Seguro que quieres borrar la olimpiada?", "Borrar olimpiada", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					if(cOlimpiada.deleteOlimpiada((Olimpiada) comboBox_Olimpiadas.getSelectedItem())) {
						JOptionPane.showMessageDialog(getContentPane(), "Olimpiada borrada correctamente");
						if (rdbtnSummer.isSelected())
							cargarComboOlimpiadasVerano();
						else
							cargarComboOlimpiadasInvierno();
						
						if (mdlOlimpiada.getSize()>0) {
							cargarEventos();
							cargarParticipaciones();
						}
						else {
							mdlEvento.clear();
							mdlPart.clear();
						}
					}
					else {
						JOptionPane.showMessageDialog(getContentPane(), "No se puede borrar, existen dependencias");
					}	
				
				
				
			}
			}
		});
		
		btnAniadirOlimpiada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AlterOlimpiada frame = new AlterOlimpiada();
				frame.setVisible(true);
				
				if (rdbtnSummer.isSelected())
					cargarComboOlimpiadasVerano();
				else
					cargarComboOlimpiadasInvierno();
				
				if (mdlOlimpiada.getSize()>0) {
					cargarEventos();
					cargarParticipaciones();
				}
				else {
					mdlEvento.clear();
					mdlPart.clear();
				}
				
			}
		});
		
		btnEditarOlimpiada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterOlimpiada frame = new AlterOlimpiada((Olimpiada) comboBox_Olimpiadas.getSelectedItem());
				frame.setVisible(true);
				
				if (rdbtnSummer.isSelected())
					cargarComboOlimpiadasVerano();
				else
					cargarComboOlimpiadasInvierno();
				
				if (mdlOlimpiada.getSize()>0) {
					cargarEventos();
					cargarParticipaciones();
				}
				else {
					mdlEvento.clear();
					mdlPart.clear();
				}
			}
		});
		
		btnBorrarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(getContentPane(), "Seguro que quieres borrar el Evento?", "Borrar evento", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					if(cEvento.deleteEvento(list_Evento.getSelectedValue())) {
						JOptionPane.showMessageDialog(getContentPane(), "Evento borrado correctamente");
						cargarEventos();
						if (mdlEvento.getSize()>0) {
							cargarParticipaciones();
						}else {
							mdlPart.clear();
						}
					}
					else {
						JOptionPane.showMessageDialog(getContentPane(), "No se puede borrar el evento, existen dependencias");
					}
				} 	
				
				
				
			}
		});
		
		btnEditarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterEvento frame =new AlterEvento((Olimpiada) comboBox_Olimpiadas.getSelectedItem(), list_Evento.getSelectedValue());
				frame.setVisible(true);
				cargarEventos();
				if (mdlEvento.getSize()>0) {
					cargarParticipaciones();
				}else {
					mdlPart.clear();
				}
			}
		});
		
		mntmAniadir_equipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterEquipo frame = new AlterEquipo (false);
				frame.setVisible(true);
				
				if(!cEquipo.selectEquipos().isEmpty())
					mntmGestionar_equipo.setEnabled(true);
				
				cargarParticipaciones();
			}
		});
		
		mntmGestionar_equipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterEquipo frame = new AlterEquipo(true);
				frame.setVisible(true);
				
				if(cEquipo.selectEquipos().isEmpty())
					mntmGestionar_equipo.setEnabled(false);
				
				cargarParticipaciones();
			}
		});
		
		mntmAniadir_deporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterDeporte frame = new AlterDeporte(false);
				frame.setVisible(true);
				
				
				if(!cDeporte.selectDeportes().isEmpty())
					mntmGestionar_Deporte.setEnabled(true);
				
				cargarParticipaciones();
			}
		});
		
		mntmGestionar_Deporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterDeporte frame = new AlterDeporte(true);
				frame.setVisible(true);
				if(cDeporte.selectDeportes().isEmpty())
					mntmGestionar_Deporte.setEnabled(false);
				
				cargarEventos();
			}
		});
		
		mntmAniadir_deportista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterDeportista frame= new AlterDeportista(false);
				frame.setVisible(true);
				
				if(!cDeportista.selectDeportista().isEmpty())
					mntmGestionar_deportista.setEnabled(true);
			}
		});
		
		mntmGestionar_deportista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterDeportista frame = new AlterDeportista(true);
				frame.setVisible(true);
				
				if(cDeportista.selectDeportista().isEmpty())
					mntmGestionar_deportista.setEnabled(false);
				
				cargarParticipaciones();
			}
		});
		
		btnAniadir_Participacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterParticipacion frame = new AlterParticipacion(list_Evento.getSelectedValue());
				frame.setVisible(true);
				
				cargarParticipaciones();
			}
		});
		
		btnEditar_Participacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterParticipacion frame = new AlterParticipacion(list_Evento.getSelectedValue(), list_Participaciones.getSelectedValue());
				frame.setVisible(true);
				
				cargarParticipaciones();
			}
		});
		btnBorrar_Participacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int reply = JOptionPane.showConfirmDialog(getContentPane(), "Seguro que quieres borrar la participacion?", "Borrar participacion", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					if(cParticipacion.borrarParticipacion((Participacion) list_Participaciones.getSelectedValue())) {
						JOptionPane.showMessageDialog(getContentPane(), "Participacion borrada correctamente");
						cargarParticipaciones();
					}
				} 
				cParticipacion.borrarParticipacion(list_Participaciones.getSelectedValue());
				
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cEvento.cerrarConexion();
				cOlimpiada.cerrarConexion();
				cEquipo.cerrarConexion();
				cDeporte.cerrarConexion();
				cDeportista.cerrarConexion();
				cParticipacion.cerrarConexion();
			}
		});
		
		
	}
	
	
	
	
	private void cargarComboOlimpiadasVerano() {
		ArrayList <Olimpiada> lstOlimp= cOlimpiada.selectOlimpiadasVerano();
		mdlOlimpiada= new DefaultComboBoxModel<Olimpiada>();
		mdlOlimpiada.addAll(lstOlimp);
		comboBox_Olimpiadas.setModel(mdlOlimpiada);
		if (mdlOlimpiada.getSize() > 0) {
			comboBox_Olimpiadas.setSelectedIndex(0);
			btnEditarOlimpiada.setEnabled(true);
			btnBorrarOlimpiada.setEnabled(true);
			comboBox_Olimpiadas.setEnabled(true);
		}else {
			btnEditarOlimpiada.setEnabled(false);
			btnBorrarOlimpiada.setEnabled(false);
			comboBox_Olimpiadas.setEnabled(false);
		}
	}
	private void cargarComboOlimpiadasInvierno() {
		ArrayList <Olimpiada> lstOlimp= cOlimpiada.selectOlimpiadasInvierno();
		mdlOlimpiada= new DefaultComboBoxModel<Olimpiada>();
		mdlOlimpiada.addAll(lstOlimp);
		comboBox_Olimpiadas.setModel(mdlOlimpiada);
		if (mdlOlimpiada.getSize() > 0) {
			comboBox_Olimpiadas.setSelectedIndex(0);
			btnEditarOlimpiada.setEnabled(true);
			btnBorrarOlimpiada.setEnabled(true);
			comboBox_Olimpiadas.setEnabled(true);
		}else {
			btnEditarOlimpiada.setEnabled(false);
			btnBorrarOlimpiada.setEnabled(false);
			comboBox_Olimpiadas.setEnabled(false);
		}
	}
	
	private void cargarEventos () {
		ArrayList <Evento> arrEventos = cEvento.selectEventosPorOlimpiada((Olimpiada) comboBox_Olimpiadas.getSelectedItem());
		mdlEvento = new DefaultListModel<Evento>();
		mdlEvento.addAll(arrEventos);
		list_Evento.setModel(mdlEvento);
		if (mdlEvento.getSize() > 0) {
			list_Evento.setSelectedIndex(0);
			btnEditarEvento.setEnabled(true);
			btnBorrarEvento.setEnabled(true);
		}else {
			btnEditarEvento.setEnabled(false);
			btnBorrarEvento.setEnabled(false);
		}
	}
	
	private void cargarParticipaciones () {
		ArrayList <Participacion> arrPart= cParticipacion.selectParticipacionesPorEvento(list_Evento.getSelectedValue());
		mdlPart =new DefaultListModel<Participacion>();
		mdlPart.addAll(arrPart);
		list_Participaciones.setModel(mdlPart);
		if (mdlPart.getSize()>0) {
			list_Participaciones.setSelectedIndex(0);
			btnEditar_Participacion.setEnabled(true);
			btnBorrar_Participacion.setEnabled(true);
		}else {
			btnEditar_Participacion.setEnabled(false);
			btnBorrar_Participacion.setEnabled(false);
		}
		
	}

}
