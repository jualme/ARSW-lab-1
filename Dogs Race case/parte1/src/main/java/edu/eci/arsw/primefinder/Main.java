package edu.eci.arsw.primefinder;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		PrimeFinderThread pft=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft2=new PrimeFinderThread(10000001, 20000000);
		PrimeFinderThread pft3=new PrimeFinderThread(20000001, 30000000);
		pft.start();
		pft2.start();
		pft3.start();
		
		// Esperar 5 segundos al programa 
		Thread.sleep(5000);
		// Pausar todos los hilos
		pft.detener();
		pft2.detener();
		pft3.detener();
		
		System.out.println("Presione una tecla para continuar: ");
		Scanner scanner = new Scanner(System.in);
		// Leer el salto de linea
		String leer = scanner.nextLine();
		
		// Vuelve a iniciar los hilos
		
		pft.continuar();
		pft2.continuar();
		pft3.continuar();
		
		
	}
	
}
