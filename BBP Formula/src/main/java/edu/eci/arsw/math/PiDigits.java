package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.Scanner;

import edu.eci.arsw.threads.BBPThread;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

	private static int DigitsPerSum = 8;
	private static double Epsilon = 1e-17;

	/**
	 * 
	 * Returns a range of hexadecimal digits of pi.
	 * 
	 * @param start   The starting location of the range.
	 * @param count   The number of digits to return
	 * @param threads The number of threads to use
	 * @return An array containing the hexadecimal digits.
	 * @throws InterruptedException
	 */
	public static byte[] getDigits(int start, int count, int threads) throws InterruptedException {

		int num = count / threads;
		int modulo = count % threads;
		byte[] digits = new byte[count];

		ArrayList<BBPThread> threadList = new ArrayList<>();
		int acum = 0;
		for (int i = 0; i < threads; i++) {
			BBPThread hilo = null;
			if (modulo > 0) {
				hilo = new BBPThread(start + i * num + acum, num + 1);
				modulo -= 1;
				acum += 1;
			} else {
				hilo = new BBPThread(start + i * num + acum, num);
			}
			threadList.add(hilo);
			hilo.start();

		}

		int var = 0;
		for (BBPThread T : threadList) {
			T.join();
			for (byte b : T.getRes()) {
				digits[var] = b;
				var++;
			}
		}

		return digits;
	}

	/**
	 * Returns a range of hexadecimal digits of pi.
	 * 
	 * @param start The starting location of the range.
	 * @param count The number of digits to return
	 * @return An array containing the hexadecimal digits.
	 */
	public static byte[] getDigits(int start, int count) {
		if (start < 0) {
			throw new RuntimeException("Invalid Interval");
		}

		if (count < 0) {
			throw new RuntimeException("Invalid Interval");
		}

		byte[] digits = new byte[count];
		double sum = 0;

		for (int i = 0; i < count; i++) {
			if (i % DigitsPerSum == 0) {
				sum = 4 * sum(1, start) - 2 * sum(4, start) - sum(5, start) - sum(6, start);

				start += DigitsPerSum;
			}

			sum = 16 * (sum - Math.floor(sum));
			digits[i] = (byte) sum;
		}

		return digits;
	}

	/// <summary>
	/// Returns the sum of 16^(n - k)/(8 * k + m) from 0 to k.
	/// </summary>
	/// <param name="m"></param>
	/// <param name="n"></param>
	/// <returns></returns>
	private static double sum(int m, int n) {
		double sum = 0;
		int d = m;
		int power = n;

		while (true) {
			double term;

			if (power > 0) {
				term = (double) hexExponentModulo(power, d) / d;
			} else {
				term = Math.pow(16, power) / d;
				if (term < Epsilon) {
					break;
				}
			}

			sum += term;
			power--;
			d += 8;
		}

		return sum;
	}

	/// <summary>
	/// Return 16^p mod m.
	/// </summary>
	/// <param name="p"></param>
	/// <param name="m"></param>
	/// <returns></returns>
	private static int hexExponentModulo(int p, int m) {
		int power = 1;
		while (power * 2 <= p) {
			power *= 2;
		}

		int result = 1;

		while (power > 0) {
			if (p >= power) {
				result *= 16;
				result %= m;
				p -= power;
			}

			power /= 2;

			if (power > 0) {
				result *= result;
				result %= m;
			}
		}

		return result;
	}

	public static void main(String[] args) throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < 6; i++) {
			System.out.println("Threads: ");
			int threads = scan.nextInt();
			// int nucleosProcesadoresLogicos = Runtime.getRuntime().availableProcessors();
			// System.out.println("Nucleos " + nucleosProcesadoresLogicos);
			System.out.println("start: ");
			int start = scan.nextInt();
			System.out.println("Longitud: ");
			int longi = scan.nextInt();
			// Mira el momento en el que se ejecuta el proceso
			long inicio = System.currentTimeMillis();
			PiDigits.getDigits(start, longi, threads);
			// Mira el momento en el que se termina el proceso
			long fin = System.currentTimeMillis();
			// Calcula el tiempo de ejecución de ese proceso
			double tiempo = (double) ((fin - inicio)/1000);
			System.out.println("El tiempo de ejecución de los " + threads + " hilos fue de " + tiempo + " segundos.");
			
			
		}
	}

}
