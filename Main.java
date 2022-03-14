package ProiectGreyScale;

import java.io.IOException;
import java.util.Scanner;

public class Main{

	static void display(String... values){  //varargs
		 for(String s:values){  
			   System.out.println(s);
		 }  
	}
	
	public static void main(String[] args) throws IOException{
		display("Aceasta aplicatie are urmatoarele componente:","1. Citirea unei imagini","2. Transformarea acesteia din color in alb-negru",
				"3. Scrierea noii imagini");
		display("\nAlegeti metoda de introducere a argumentelor:","0 pentru argumente de la tastatura sau",
				"1 pentru argumente din linia de comanda");
		
		Scanner scanner = new Scanner(System.in);
		int scan = scanner.nextInt();
		
		if(scan == 1){
			try{
				String in = args[0]; //locatia imaginii initiale
				String out = args[1]; //locatia imaginii finale
				
				GreyScale gs = new GreyScale(in, out); //declar un obiect de tip GreyScale
				gs.startAplicatie(); //pornesc aplicatia propriu-zisa
			} catch (ArrayIndexOutOfBoundsException exp) {
				System.out.println("Numar insuficient de argumente.");
			}
		 }
		else
		{
			System.out.println("Locatia fisierului din care se citeste poza:");
			Scanner scanner1 = new Scanner(System.in);
			String in = scanner1.nextLine(); //locatia imaginii initiale
			
			System.out.println("Locatia fisierului unde se va scrie poza:");
			Scanner scanner2 = new Scanner(System.in);
			String out = scanner2.nextLine(); //locatia imaginii finale
			
			GreyScale gs = new GreyScale(in, out); //declar un obiect de tip GreyScale
			gs.startAplicatie(); //pornesc aplicatia propriu-zisa
			
			scanner.close(); //opresc scanner-ul pentru metoda de preluare a argumentelor
			scanner1.close(); //opresc scanner-ul pentru citirea locatiei imaginii originale
			scanner2.close(); //opresc scanner-ul pemtru citirea locatiei imaginii finale
		}
	}

}
