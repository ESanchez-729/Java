package Ejercicio03;

import java.io.File;

public class ejercicio03_eliminar {

	public static void main(String[] args) {
		
		boolean resultado;
		
		File fichero = new File("C:\\DAM2\\Edgar.txt");
		
		resultado = fichero.delete();
		
		if (resultado)
			
			System.out.println("Fichero borrado correctamente.");
		
		else 
			
			System.out.println("No se ha podido borrar el fichero.");
		
		File directorio = new File("C:\\DAM2");
		
		resultado = directorio.delete();

		if (resultado)
			
			System.out.println("Directorio borrado correctamente.");
		
		else 
			
			System.out.println("No se ha podido borrar el directorio.");
	}

}
