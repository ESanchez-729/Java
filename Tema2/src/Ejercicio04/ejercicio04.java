package Ejercicio04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ejercicio04 {
	

	public static void main(String[] args) {
		
		try {
			
			BufferedWriter filtro = new BufferedWriter (new FileWriter("numeros_pares.txt"));
			BufferedReader filtro2 = new BufferedReader (new FileReader("numeros_pares.txt"));
			
			for (int i = 0; i <= 500; i += 2) {
				
				filtro.write(String.valueOf(i));
				filtro.newLine();
				
			}
			
			filtro.close();
			
			String linea;
			while ((linea = filtro2.readLine()) != null) {
			
				System.out.println(linea);
			
			}
			
			filtro2.close();
		}
		
		catch (IOException e){
			
			e.printStackTrace();
			
		}

	}

}
