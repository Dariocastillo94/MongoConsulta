package consulta;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import consulta.entidades.Provincia;

public class TablaProvincias extends JPanel {

	private static final long serialVersionUID = 1L;

	VentanaProvincia panelProvincia = null;
	/**
	 * Create the panel.
	 */
	public TablaProvincias(VentanaProvincia panelProvincia ) {
		this.panelProvincia = panelProvincia;
		
		//Creo un objeto JTable con el constructor m�s sencillo del que dispone
		JTable table = new JTable(DatosProvincias.getDatosProvincias(), 
				DatosProvincias.getTituloColumnas());
		//Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		
		// Accedo a los clics realizados sobre la tabla
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int indiceFilaSel = table.getSelectedRow();
				Provincia provinciaSel = DatosProvincias.getAlProvincias()
						.get(indiceFilaSel);
				System.out.println("provincia seleccionada: " + provinciaSel.getLabel());
				panelProvincia.mostrarCcaa(provinciaSel);
			}
		});
		
		//Agregamos el JScrollPane al contenedor
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
	}

	

}
