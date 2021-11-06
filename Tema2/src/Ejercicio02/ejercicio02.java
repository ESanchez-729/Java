package Ejercicio02;

import java.io.File;

public class ejercicio02 {

	public static void main(String[] args) {
		
		
		
		if (args.length > 0) {
		
			File a = new File(args[0]);
			
			if (a.exists()) {
			
			System.out.println("El fichero o directorio existe.");
			}
		
			else {
			
			System.out.println("El fichero o directorio no existe.");
			}

		}
		
		else {
			
			System.out.println("No se han encontrado los argumentos.");
		}
}

}
