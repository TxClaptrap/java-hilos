package psp.tema2.ejemplos._2_Sumador;

public class SumadorSinHilos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n1=1;
		int n2=100000;
		long suma=0;
		for (int i=n1;i<=n2;i++) {
			suma+=i;
		}
		System.out.println(suma);
	}
 
}
