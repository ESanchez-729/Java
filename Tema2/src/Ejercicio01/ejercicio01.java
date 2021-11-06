package Ejercicio01;

import java.io.File;

public class ejercicio01 {

	public static void main(String[] args) {
		
		File a = new File("C:\\Windows");
		
		File [] listado = a.listFiles();
		
		for (File archivo : listado) {
			
			if (archivo.isFile() && archivo.isHidden()) {
				
				System.out.println("Nombre: " + archivo.getName());
				System.out.println("Tamaño: " + archivo.length());
				System.out.println("Lectura: " + archivo.canRead());
				System.out.println("Escritura:" + archivo.canWrite());
			}
		}
	}

}
