package psp.tema2.ejemplos._2_Sumador;

class Acumulador{
	long acumulador=0;

	public synchronized void acumular(long l) {
		acumulador+=l;
	}
	
	public long getAcumulador() {
		return acumulador;
	}
}

class HiloSumador3 implements Runnable{
	
	int n1,n2;
	Acumulador acu;
	
	public HiloSumador3(int n1,int n2,Acumulador a) {
		this.n1=n1;
		this.n2=n2;
		this.acu=a;
	}

	@Override
	public void run() {
		for (int i=n1;i<=n2;i++) {
			acu.acumular(i);
		}
	}	
}

public class SumadorConHilos3 {

	static Acumulador suma;
	public static void main(String[] args) {
		suma=new Acumulador();
		Thread t1=new Thread(new HiloSumador3(1,33333,suma));
		Thread t2=new Thread(new HiloSumador3(33334,66666,suma));
		Thread t3=new Thread(new HiloSumador3(66667,100000,suma));
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			System.err.println("ERROR InterruptedException: "+e.getMessage());
		}
		System.out.println("Suma:"+suma.getAcumulador());
	}
}
