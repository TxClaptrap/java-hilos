package psp.tema2.taller;

public class Simulacion {

    public static void main(String[] args) {
        String[] nombresHerramientas = {
                "martillo", "destornillador", "alicates", "llave inglesa", "sierra",
                "taladro", "cincel", "lima", "metro", "tenazas"
        };

        Herramienta[] bancoHerramientas = new Herramienta[10];

        for (int i = 0; i < bancoHerramientas.length; i++) {
            bancoHerramientas[i] = new Herramienta(nombresHerramientas[i]);
        }

        Thread[] alumnos = new Thread[5];
        for (int i = 0; i < alumnos.length; i++) {
            alumnos[i] = new Thread(new Alumno("Alumno-" + (i + 1), bancoHerramientas));
            alumnos[i].start();
        }

        // join para que no termine instantáneamente
        for (Thread alumno : alumnos) {
            try {
                alumno.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
