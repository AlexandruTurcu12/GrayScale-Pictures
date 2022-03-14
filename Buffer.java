package ProiectGreyScale;

public class Buffer {
	private int[][] pixels;
	private boolean available = false;
	
	public synchronized int[][] get() {
		while (!available) {   	//resursa devine disponibila pentru citire
			try {
				wait();		//asteapta producatorul sa puna o valoare
			} catch (InterruptedException e) {
				System.out.println(e); 			//eroare
			}
		}
		
		available = false;		//se seteaza disponibilitatea
		notifyAll();			//se anunta incheierea procedurii
		return this.pixels;		//se returneaza matricea
	}
	
	public synchronized void put(int[][] pixels) {
		while (available) {		//resursa nu mai este disponibila pentru citire
			try {
				wait();		//asteapta consumatorul sa ia o valoare		
			} catch (InterruptedException e) {
				System.out.println(e); 	//eroare
			}
		}
		
		this.pixels = pixels;	//se returneaza matricea
		available = true;		//se seteaza disponibilitatea
		notifyAll();			//se anunta incheierea procedurii
	}
}
