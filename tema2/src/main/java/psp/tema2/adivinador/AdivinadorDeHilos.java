package psp.tema2.adivinador;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AdivinadorDeHilos {
    private static Compartido compartido;
    private static int N_HILOS = 10;

    public static void main(String[] args) throws InterruptedException {
        compartido = new Compartido(Integer.parseInt(args[0]));

        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < N_HILOS; i++) {
            hilos.add(new Thread(new HiloAdivinador("Hilo", compartido)));
            hilos.get(i).start();
        }

        for (Thread thread : hilos) {
            thread.join();
        }
    }
}

class Compartido {
    int numeroElegido;
    boolean adivinado;

    public Compartido(int numeroElegido) {
        this.numeroElegido = numeroElegido;
        this.adivinado = false;
    }

    synchronized String comprueba(int numeroAleatorio) {
        if (adivinado) {
            return "Ya lo han adivinado.";
        }
        if (numeroAleatorio == numeroElegido) {
            adivinado = true;
            return "Lo has adivinado.";
        } else {
            return "Intenta de nuevo.";
        }
    }

    public boolean isAdivinado() {
        return adivinado;
    }

    public int getNumeroElegido() {
        return numeroElegido;
    }

}

class HiloAdivinador implements Runnable {
    private String nombreHilo;
    private Compartido compartido;
    private static int numHilo = 0;
    private int numeroAleatorio;

    public static synchronized void incrementarNumHilo() {
        numHilo++;
    }

    public static synchronized int getNumHilo() {
        return numHilo;
    }

    public HiloAdivinador(String nombreHilo, Compartido compartido) {
        this.nombreHilo = nombreHilo + getNumHilo();
        incrementarNumHilo();
        this.compartido = compartido;
        numeroAleatorio = ThreadLocalRandom.current().nextInt(0, compartido.getNumeroElegido() + 100);
    }

    @Override
    public void run() {
        compartido.comprueba(numeroAleatorio);
        while (true) {
            String respuesta;
    
            synchronized (compartido) {
                if (compartido.isAdivinado()) {
                    System.out.println(nombreHilo + ": Vaya, me han ganado");
                    return;
                }
                respuesta = compartido.comprueba(numeroAleatorio);
                
                if (respuesta.equals("Lo has adivinado.")) {
                    System.out.println(nombreHilo + ": Lo he acertado");
                    return; 
                }
            }
    
            if (respuesta.equals("Ya lo han adivinado.")) {
                System.out.println(nombreHilo + ": Vaya, me han ganado");
                return; 
            }
    
            System.out.println(nombreHilo + ": No lo he acertado.");
            numeroAleatorio = ThreadLocalRandom.current().nextInt(0, compartido.getNumeroElegido() + 100);
        }
    }
    
    
    
    
    
    

}