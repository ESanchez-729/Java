package Ejercicio05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ejercicio05 {

	public static void main(String[] args) {
		
		int l = args[0].length();
		String nombre = args[0].substring(0, l - 4);
		
		try {
		
			BufferedReader filtroLectura = new BufferedReader(new FileReader(args[0]));
				
			BufferedWriter filtroEscritura = new BufferedWriter(new FileWriter(nombre + "_sort.txt"));
			
			ArrayList<String> lista = new ArrayList<String>();
			
			String linea;
			
			while ((linea = filtroLectura.readLine()) != null) {
				
				lista.add(linea);
				
				
			}
			
			Collections.sort(lista);
			
			for (String letra : lista) {
				
				filtroEscritura.write(letra);
				filtroEscritura.newLine();
			}
			
			filtroEscritura.close();
			
		
		}
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
