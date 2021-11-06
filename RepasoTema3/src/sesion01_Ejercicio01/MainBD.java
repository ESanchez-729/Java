package sesion01_Ejercicio01;

public class MainBD {

	public static void main(String[] args) {
		
		AccesoBD db = new AccesoBD();
		
		db.conectar();
		
		db.consultaPorLocalidad("Malaga");

	}

}
