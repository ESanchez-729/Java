package Ejercicio03;

import java.io.File;
import java.io.IOException;

public class ejercicio03_crear {

	public static void main(String[] args) {
		
		boolean resultado;
		File directorio = new File("C:\\DAM2");
		resultado = directorio.mkdir();
		
		if (resultado) 
			
			System.out.println("Directorio creado.");
		
		else {
			
			System.out.println("No se pudo crear el directorio.");
			System.exit(-1);
		}
		
		try {
			
			File fichero = new File ("C:\\DAM2\\Edgar.txt");
			resultado = fichero.createNewFile();
			
			if (resultado) 
				
				System.out.println("Archivo creado");
			
			else 
				
				System.out.println("No se puede crear el archivo");
				
		}
		
		catch (IOException e) {
			
			System.out.println("Se produjo el error " + e.getMessage());
		}
	}

}
