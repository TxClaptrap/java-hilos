package psp.tema2.ejemplos._2_Sumador;


class HiloSumador implements Runnable{
	
	int n1,n2;
	
	public HiloSumador(int n1,int n2) {
		this.n1=n1;
		this.n2=n2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long suma=0;
		for (int i=n1;i<=n2;i++) {
			suma+=i;
		}
		System.out.println(suma);
	}	
}

public class SumadorConHilos1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t=new Thread(new HiloSumador(1,100000));
		t.start();
	}
}
