package psp.tema2.sincronizado;

/*
 * RModifica el programa para que también exista un valor máximo par ael contador. Comprueba que funciona correctamente 
 */

public class IncrementaDecrementa2 {

	private static final int NUM_HILOS_INC = 10;
	private static final int NUM_HILOS_DEC = 10;

	class Contador {
		private int cuenta;

		Contador(int valorInicial) {
			cuenta = valorInicial;
		}

		synchronized public int getCuenta() {
			return cuenta;
		}

		synchronized int incrementa() {
			cuenta++;
			return cuenta;
		}

		synchronized int decrementa() {
			cuenta--;
			return cuenta;
		}
	}

	class HiloInc implements Runnable {

		private final String id;
		private final Contador cont;

		HiloInc(String id, Contador c) {
			this.id = id;
			cont = c;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!Thread.interrupted()) {
				// Hace falta el bloque, porque son dos operaciones, incrementar y mostrar la
				// cuenta
				// Podría ser que se incremente, pero el valor no sea correcto.
				synchronized (this.cont) {
					while (this.cont.getCuenta() > 9000) {
						System.out.println(
								"!!! Hilo " + id + " no puede incrementar, valor contador:" + cont.getCuenta());
						try {
							this.cont.wait();
						} catch (InterruptedException e) {
							System.err.println("ERROR InterruptedException: " + e.getMessage());
						}
					}
					this.cont.incrementa();
					this.cont.notifyAll();
					// System.out.println("Hilo "+id+" se incrementa, valor
					// contador:"+cont.getCuenta());
				}
			}
		}
	}

	class HiloDec implements Runnable {

		private final String id;
		private final Contador cont;

		HiloDec(String id, Contador c) {
			this.id = id;
			cont = c;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!Thread.interrupted()) {
				synchronized (this.cont) {
					while (this.cont.getCuenta() < 1) {
						System.out.println(
								"!!! Hilo " + id + " no puede decrementar, valor contador:" + cont.getCuenta());
						try {
							this.cont.wait();
						} catch (InterruptedException e) {
							System.err.println("ERROR InterruptedException: " + e.getMessage());
						}
					}
					this.cont.decrementa();
					this.cont.notifyAll();
					// System.out.println("Hilo "+id+" se decrementa, valor
					// contador:"+cont.getCuenta());
				}
			}
		}
	}

	public static void main(String[] args) {
		IncrementaDecrementa2 programa = new IncrementaDecrementa2();
		IncrementaDecrementa2.Contador c = programa.new Contador(0);
		Thread[] hilosInc = new Thread[NUM_HILOS_INC];
		for (int i = 0; i < NUM_HILOS_INC; i++) {
			Thread h = new Thread(programa.new HiloInc("INC" + i, c));
			hilosInc[i] = h;
		}
		Thread[] hilosDec = new Thread[NUM_HILOS_DEC];
		for (int i = 0; i < NUM_HILOS_DEC; i++) {
			Thread h = new Thread(programa.new HiloDec("DEC" + i, c));
			hilosDec[i] = h;
		}

		for (int i = 0; i < NUM_HILOS_INC; i++) {
			hilosInc[i].start();
		}
		for (int i = 0; i < NUM_HILOS_DEC; i++) {
			hilosDec[i].start();
		}
	}
}
