package psp.tema2.ejemplos._2_Sumador;

class HiloSumador2 implements Runnable{
	
	int n1,n2;
	
	public HiloSumador2(int n1,int n2) {
		this.n1=n1;
		this.n2=n2;
	}

	@Override
	public void run() {
		long suma=0;
		for (int i=n1;i<=n2;i++) {
			suma+=i;
		}
		System.out.println(n1+" al "+n2+":"+suma);
	}	
}

public class SumadorConHilos2 {

	public static void main(String[] args) {
		Thread t1=new Thread(new HiloSumador2(1,33333));
		Thread t2=new Thread(new HiloSumador2(33334,66666));
		Thread t3=new Thread(new HiloSumador2(66667,100000));
		t1.start();
		t2.start();
		t3.start();
	}
}
