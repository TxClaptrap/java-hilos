package psp.tema2.ejemplos._2_Sumador;

import java.util.ArrayList;
import java.util.List;


class Acumulador {
    long acumulador = 0;

    public synchronized void acumular(long l) {
        acumulador += l;
    }

    public long getAcumulador() {
        return acumulador;
    }
}

class SumadorDeHilos implements Runnable {

    int n1, n2;
    Acumulador acu;

    public SumadorDeHilos(int n1, int n2, Acumulador a) {
        this.n1 = n1;
        this.n2 = n2;
        this.acu = a;
    }

    @Override
    public void run() {
        for (int i = n1; i <= n2; i++) {
            acu.acumular(i);
        }
    }
}

public class SumadorMultiHilo {

    static Acumulador suma;
    private static int N_HILOS = 4;

    public static void main(String[] args) {
        suma = new Acumulador();

        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int salto = (n2 - n1) / N_HILOS;
        System.out.println("Salto: " + salto);

        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < N_HILOS; i++) {
            Thread t = new Thread(new SumadorDeHilos(n1, n1 + salto, suma));
            n1 = n1 + salto + 1;
            hilos.add(t);
            t.start(); 
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("ERROR InterruptedException: " + e.getMessage());
            }
        }

        System.out.println("Suma: " + suma.getAcumulador());
    }

}
