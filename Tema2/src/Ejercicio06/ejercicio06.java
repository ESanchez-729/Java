package Ejercicio06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ejercicio06 {

	public static void main(String[] args) {
		
		int contador = 0;
		
		try {
		
			String elegida = args[1];
		
			BufferedReader lectura = new BufferedReader(new FileReader(args[0]));
			
			String linea;
			
			while ((linea = lectura.readLine()) != null) {
				
				String [] listaTemp = linea.split(" ");
			
		
			for (String palRepe : listaTemp) {
				
				if (palRepe.toLowerCase().contains(elegida.toLowerCase())) {
					
					contador ++;
					
				}
				
			}
		} // del while
			
			lectura.close();
			
			System.out.println("La palabra " + elegida + " se repite " + contador + " veces.");
		}
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
