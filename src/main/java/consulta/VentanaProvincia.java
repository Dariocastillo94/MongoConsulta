package consulta;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JTextField;

import consulta.controladores.ControladorCcaa;
import consulta.controladores.ControladorProvincia;
import consulta.entidades.Ccaa;
import consulta.entidades.Provincia;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaProvincia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCode;
	private JTextField jtfLabel;
	private JComboBox<Ccaa> jcbCCAA;
	

	/**
	 * Create the panel.
	 */
	public VentanaProvincia() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Gestión de provincias");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Code:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfCode = new JTextField();
		jtfCode.setEnabled(false);
		GridBagConstraints gbc_jtfCode = new GridBagConstraints();
		gbc_jtfCode.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCode.gridx = 1;
		gbc_jtfCode.gridy = 1;
		add(jtfCode, gbc_jtfCode);
		jtfCode.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Label:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jtfLabel = new JTextField();
		GridBagConstraints gbc_jtfLabel = new GridBagConstraints();
		gbc_jtfLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jtfLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfLabel.gridx = 1;
		gbc_jtfLabel.gridy = 2;
		add(jtfLabel, gbc_jtfLabel);
		jtfLabel.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CCAA:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		jcbCCAA = new JComboBox<Ccaa>();
		GridBagConstraints gbc_jcbCCAA = new GridBagConstraints();
		gbc_jcbCCAA.insets = new Insets(0, 0, 5, 5);
		gbc_jcbCCAA.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbCCAA.gridx = 1;
		gbc_jcbCCAA.gridy = 3;
		add(jcbCCAA, gbc_jcbCCAA);
		
		JButton btnCCAA = new JButton("Ver CCAA");
		btnCCAA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPanelGestionCCAAEnJDialog();
			}
		});
		GridBagConstraints gbc_btnCCAA = new GridBagConstraints();
		gbc_btnCCAA.insets = new Insets(0, 0, 5, 5);
		gbc_btnCCAA.gridx = 2;
		gbc_btnCCAA.gridy = 3;
		add(btnCCAA, gbc_btnCCAA);
		
		JButton btnGuardarProvincia = new JButton("Guardar");
		btnGuardarProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			
			}
		});
		GridBagConstraints gbc_btnGuardarProvincia = new GridBagConstraints();
		gbc_btnGuardarProvincia.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardarProvincia.gridx = 2;
		gbc_btnGuardarProvincia.gridy = 4;
		add(btnGuardarProvincia, gbc_btnGuardarProvincia);

		//////////////////////////////////////////////////////
		cargarCcaa();
	}
	/**
	 * Metodo que carga las ccaa en el jcb
	 */
	private void cargarCcaa() {
		List<Ccaa> ccaa = (List<Ccaa>)ControladorCcaa.getInstance().allCcaa();
		for (Ccaa c : ccaa) {
			this.jcbCCAA.addItem(c);
		}
	}
	/**
	 * Metodo que muestra el la ventana de abajo la info de la ccaa seleccionada en la jtable
	 * @param c
	 */
	public void mostrarCcaa(Provincia c) {
		this.jtfCode.setText(c.getCode());
		this.jtfLabel.setText(c.getLabel());
		
		for (int i = 0; i < this.jcbCCAA.getItemCount(); i++) {
			if (c.getParentCode().equals(this.jcbCCAA.getItemAt(i).getCode())) {
				this.jcbCCAA.setSelectedIndex(i);
				
			}
		}
		this.jtfLabel.setText(c.getLabel());
		this.jtfCode.setText(c.getCode());
	}
	
	public void guardar() {
	
		//creamos una provincia
		Provincia p = new Provincia();
		//
		p.setCode(this.jtfCode.getText());
		p.setLabel(this.jtfLabel.getText());
		
		//llamamos al metodo guardar pero le ponemos unas restricciones
		if (this.jtfCode.getText().isEmpty() || this.jtfLabel.getText().isEmpty()) {
		    JOptionPane.showMessageDialog(null, "No has seleccionado nada en la tabla");
		    return;
		}
		ControladorProvincia.getInstance().updateDocument(p);
		JOptionPane.showMessageDialog(null, "Modificación realizada correctamente");
		
	}
	
	private void mostrarPanelGestionCCAAEnJDialog () {
		JDialog dialogo = new JDialog();
		dialogo.setResizable(true);
		dialogo.setTitle("Gestión de CCAA");
		dialogo.setContentPane(new GestionCCAA(
				(Ccaa) this.jcbCCAA.getSelectedItem(), this.jcbCCAA));
		dialogo.pack();
		dialogo.setModal(true);
		dialogo.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
		dialogo.setVisible(true);
	}

}
