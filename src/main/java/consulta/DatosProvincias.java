package consulta;

import java.util.List;

import consulta.controladores.ControladorProvincia;
import consulta.entidades.Provincia;

public class DatosProvincias {
 
	public static  List<Provincia> provincias=null;
	/**
	 * 
	 * @return
	 */
	public static String[] getTituloColumnas() {
		return new String[] {"Code","Label","Parent_code"};
	}
	
	public static List<Provincia> getAlProvincias() {
		if (provincias == null) {
			provincias = (List<Provincia>)ControladorProvincia.getInstance().allProvincias();;
		}
		return provincias;

	}
	
	public static Object[][] getDatosProvincias(){
		//primero obtenemos todas las provincias
		List<Provincia> provincia = getAlProvincias();
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[provincia.size()][3];
		// Cargo los datos de la lista de personas en la matriz de los datos
		for (int i = 0; i < provincia.size(); i++) {
			Provincia p = provincia.get(i);
			datos[i][0] = p.getCode();
			datos[i][1] = p.getLabel();
			datos[i][2] = p.getParentCode();
		}
		return datos;

	}

}
