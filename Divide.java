package ProiectGreyScale;

public abstract class Divide extends Thread{
	private Boolean type;
	private Buffer buffer;
	
	Divide() {
		type = true;
	}
	Divide(Boolean x, Buffer b) {	//se retin buffer-ul si natura thread-ului necesar: Producer sau Consumer
		type = x;
		buffer = b;
	}
	public void run() {
		if(type) {
			
			int width = GreyScale.initialImage.getWidth();
			int height = GreyScale.initialImage.getHeight();
			
			for (int i = 0; i < 4; i++) {	//impart imaginea in sferturi
				int[][] pixels = new int[width][height];	//matrice prin intermediul careia care se trimite un sfert din imagine
				for (int j = height/4 * i; j < height/4 * (i + 1); j++) { 	//se alege sfertul actual al pozei
					for (int k = 0; k < width; k++) {
						pixels[k][j] = GreyScale.initialImage.getRGB(k, j);		//se retine pixelul curent din imaginea originala
					}
				}
				
				buffer.put(pixels);		//se pune sfertul de poza curent in Buffer
				
				System.out.println("Producatorul a pus sfertul cu numarul " + (i + 1) + " al imaginii.");
				
				try {
						sleep(1000);
					} catch (InterruptedException e) {
						System.out.println(e);		//eroare
					}
			}
		}
		else {
			
			int width = GreyScale.initialImage.getWidth();
			int height = GreyScale.initialImage.getHeight();
			
			for (int i = 0; i < 4; i++) {	//impart imaginea in sferturi
				int[][] pixels = new int[width][height/4+3]; 	//matrice in care se va retine sfertul de imagine primit
				pixels = buffer.get();		//se ia sfertul de poza curent din Buffer
				for (int j = height/4 * i; j < height/4 * (i + 1); j++) {		//se alege sfertul actual al pozei
					for (int k = 0; k < width; k++) {
						GreyScale.preProcessingImage.setRGB(k, j, pixels[k][j]);		//se seteaza pixelul curent in imaginea ce va fi trimisa spre procesare
					}
				}
				
				System.out.println("Consumatorul a preluat sfertul cu numarul " + (i + 1) + " al imaginii.");
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					System.out.println(e);		//eroare
				}
			}
		}
	}
}
