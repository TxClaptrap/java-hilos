package psp.tema2.taller;

import java.util.concurrent.ThreadLocalRandom;

public class Alumno implements Runnable {

    private final String nombre;
    private final Herramienta[] bancoHerramientas;

    public Alumno(String nombre, Herramienta[] bancoHerramientas) {
        this.nombre = nombre;
        this.bancoHerramientas = bancoHerramientas;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Herramienta h1 = elegirHerramienta();
                Herramienta h2 = elegirHerramienta();

                while (h2 == h1) { // Para que sean diferentes
                    h2 = elegirHerramienta();
                }

                synchronized (h1) {
                    synchronized (h2) { //Así, mediante brujería, se bloquean las herramientas y los demás no pueden cogerlas
                        System.out.println(nombre + " ha cogido " + h1 + " y " + h2 +  " y está trabajando.");
                        Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
                    }
                }

                System.out.println(nombre + " está descansando.");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
            }
        } catch (InterruptedException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private Herramienta elegirHerramienta() {
        int indice = ThreadLocalRandom.current().nextInt(bancoHerramientas.length);
        return bancoHerramientas[indice];
    }
}
