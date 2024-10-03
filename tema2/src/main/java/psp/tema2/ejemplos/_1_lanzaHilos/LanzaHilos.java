package psp.tema2.ejemplos._1_lanzaHilos;

class Hilo implements Runnable{

	private final String nombre;
	
	Hilo(String nombre){
		this.nombre=nombre;
	}
	
	@Override
	public void run() {
		System.out.println("Hola, soy "+nombre);
		System.out.printf("Hilo %s terminado.\n",nombre);
	}
	
}

public class LanzaHilos {

	public static void main(String[] args) {
		Thread t1=new Thread(new Hilo("Hilo1"));
		Thread t2=new Thread(new Hilo("Hilo2"));
		Thread t3=new Thread(new Hilo("Hilo3"));
		Thread t4=new Thread(new Hilo("Hilo4"));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		System.out.println("Proceso principal terminado");
	}
}
