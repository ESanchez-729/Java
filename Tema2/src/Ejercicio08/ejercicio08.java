package Ejercicio08;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ejercicio08 {

	public static void main(String[] args) throws ClassNotFoundException  {
		
		File f = new File ("profesores.dat");
		
		
		try {
			
			ObjectInputStream filtro = new ObjectInputStream(new FileInputStream(f));
			
			if(f.exists()) {
				
				MiObjectOutputStream escritura = new MiObjectOutputStream(new FileOutputStream(f, true));
				String comprobar = "n";
				Scanner teclado = new Scanner(System.in);
				ArrayList <profesor> lista = new ArrayList<profesor>();
				String nombre;
				double antiguedad = 0;
				
				while (!comprobar.equals("s")) {
					
					System.out.println("Introduce un nombre: ");
					nombre = teclado.next();
					
					System.out.println("Introduce la antiguedad: ");
					antiguedad = teclado.nextDouble();
					
					System.out.println("Quieres salir? s/n");
					comprobar = teclado.next();
					
					lista.add(new profesor(nombre, antiguedad));
					
				}
				
				for (profesor p : lista) {
					
					escritura.writeObject(p);
					
				}
				
				escritura.close();
				
			}
			
			else {
				
				ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(f));
				
				
				String comprobar = "n";
				Scanner teclado = new Scanner(System.in);
				ArrayList <profesor> lista = new ArrayList<profesor>();
				String nombre;
				double antiguedad = 0;
				
				while (!comprobar.equals("s")) {
					
					System.out.println("Introduce un nombre: ");
					nombre = teclado.next();
					
					System.out.println("Introduce la antiguedad: ");
					antiguedad = teclado.nextDouble();
					
					System.out.println("Quieres salir? s/n");
					comprobar = teclado.next();
					
					lista.add(new profesor(nombre, antiguedad));
					
				}
				
				for (profesor p : lista) {
					
					escritura.writeObject(p);
					
				}
				
				escritura.close();
				
				
			}
			
			while (true) {
				
				profesor g = (profesor) filtro.readObject();
				System.out.println(g);
				
			}

		}
		
		catch (EOFException e) {
			
			System.out.println("Has llegado al final del fichero");
			
		}
		
		catch (IOException e) {
			
			System.out.println("No se ha podido crear el archivo");
			e.printStackTrace();
		}
	}
}
