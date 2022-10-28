package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class NuevoDeportista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtSexo;
	private JTextField txtPeso;
	private JTextField txtAltura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoDeportista dialog = new NuevoDeportista();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoDeportista() {
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
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
