package psp.tema2.sincronizado;

/**
 * Clase para el control de los requisitos
 * 
 * @author jmartinezs
 *
 */
public class Puente {

	private static final int PESO_MAXIMO=300;
	private static final int MAX_PERSONAS=4;
	private static final int MAX_PERSONAS_IZQUIERDA=3;
	private static final int MAX_PERSONAS_DERECHA=3;
	
	private int peso=0;
	private int numPersonas=0;
	private int numPersonasIzquierda=0;
	private int numPersonasDerecha=0;
	
	synchronized public int getPeso() {
		return peso;
	}

	public int getNumPersonasIzquierda() {
		return numPersonasIzquierda;
	}

	public int getNumPersonasDerecha() {
		return numPersonasDerecha;
	}

	

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public void setNumPersonasIzquierda(int numPersonasIzquierda) {
		this.numPersonasIzquierda = numPersonasIzquierda;
	}

	public void setNumPersonasDerecha(int numPersonasDerecha) {
		this.numPersonasDerecha = numPersonasDerecha;
	}

	synchronized public int getNumPersonas() {
		return numPersonas;
	}
	
	synchronized public boolean autorizacionPaso(Persona persona) {
		boolean resultado;
		if (this.peso+persona.getPeso()<=Puente.PESO_MAXIMO && this.numPersonas<Puente.MAX_PERSONAS && this.numPersonasDerecha<Puente.MAX_PERSONAS_DERECHA && this.numPersonasIzquierda<Puente.MAX_PERSONAS_IZQUIERDA) {
			if (persona.getDireccion()==1) {
				this.numPersonasIzquierda++;
			}
			else {
				this.numPersonasDerecha++;
			}
			this.numPersonas = numPersonasDerecha + numPersonasIzquierda;
			this.peso+=persona.getPeso();
			resultado=true;
		}
		else
			resultado=false;
		return resultado;
	}
	
	synchronized public void terminaPaso(Persona persona) {
		this.peso-=persona.getPeso();
		this.numPersonas--;
	}
}
