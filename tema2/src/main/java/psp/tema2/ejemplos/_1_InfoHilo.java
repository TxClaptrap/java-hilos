package psp.tema2.ejemplos;

public class _1_InfoHilo {

	public static void main(String[] args) {
		Thread t=Thread.currentThread();
		System.out.println("ID:"+t.getId()+" Nombre:"+t.getName()+" Prioridad:"+
								t.getPriority()+" Estado:"+t.getState().toString());
		System.out.println("Vivo?:"+t.isAlive()+" Interrumpido?:"+t.isInterrupted()+" esDemonio?:"+t.isDaemon());
	}

}
