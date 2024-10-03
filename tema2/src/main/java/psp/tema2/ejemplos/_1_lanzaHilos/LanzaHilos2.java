package psp.tema2.ejemplos._1_lanzaHilos;

import java.util.Random;

class Hilo2 implements Runnable{

	private final String nombre;
	
	Hilo2(String nombre){
		this.nombre=nombre;
	}
	
	@Override
	public void run() {
		int pausa=new Random().nextInt(500);
		System.out.println("Hola, soy "+nombre+" con pausa de "+pausa);
		try {
			Thread.sleep(pausa);
		} catch (InterruptedException e) {
			System.err.println("ERROR InterruptedException: "+e.getMessage());

		}
		System.out.printf("Hilo %s terminado.\n",nombre);
	}
	
}

public class LanzaHilos2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1=new Thread(new Hilo2("Hilo1"));
		Thread t2=new Thread(new Hilo2("Hilo2"));
		Thread t3=new Thread(new Hilo2("Hilo3"));
		Thread t4=new Thread(new Hilo2("Hilo4"));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		}
		catch (InterruptedException e) {
			System.out.println("Proceso interrumpido");
		}
		System.out.println("Proceso principal terminado");
	}

}
