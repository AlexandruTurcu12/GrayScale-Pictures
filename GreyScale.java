package ProiectGreyScale;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GreyScale extends imageGreyScale{
	private static String inFile; // locatia imaginii initiale
	private static String outFile; // locatia imaginii finale
	public static BufferedImage initialImage = null; // imaginea citita din fisier
	public static BufferedImage preProcessingImage = null; // imaginea inainte de procesare
	private int width;
	private int height;
	static BufferedImage img;
	
	//Setters
	public void setImg(int w, int h){
		this.width=w;
		this.height=h;
	}
	public void setWidth(int w){
		this.width=w;
	}
	public void setHeight(int h){
		this.height=h;
	}
	
	//Getters
	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	
	public GreyScale(){}

	public GreyScale(String in, String out) {
		inFile = in;
		outFile = out;
	}
	
	public GreyScale(int height, int width) {
		this.height = height;
        this.width = width;
	}
		
	public void startAplicatie() {
		File file = null;  // declarare fisier
		
//	citirea imaginii
		
		System.out.println("---------------------------------------------------------------------------------------");
		double startTime = System.nanoTime();
		try {
			file = new File(inFile); //se deschide fisierul de input
			initialImage = ImageIO.read(file); //se citeste imaginea initiala
		} catch(IOException e) {
			System.out.println(e); //eroare 			  
		}
		double stopTime = System.nanoTime();
		double elapsedTime = stopTime - startTime;
        System.out.println("Timpul de executie pentru citirea imaginii a fost de: " + elapsedTime / 10000000000.0 + " secunde");
        
        System.out.println("---------------------------------------------------------------------------------------");
		preProcessingImage = new BufferedImage(initialImage.getWidth(), initialImage.getHeight(), initialImage.getType()); //declarare obiect de tip BufferedImage
		
		Buffer buffer = new Buffer();                    //declarare obiect de tip Buffer
		Producer producer = new Producer(buffer);        //declarare obiect de tip Producer
		Consumer consumer = new Consumer(buffer);        //declarare obiect de tip Consumer
		
		producer.start(); 								//se incepe thread-ul pentru Producer
		consumer.start(); 								//se incepe thread-ul pentru Consumer
		
		try {
			consumer.join(); 					//unesc thread-ul curent cu cel al Consumerului pentru a nu continua executia Main-ului pana nu se termina executia Consumerului
															 
		} catch (InterruptedException e) {
			System.out.println(e); 				//eroare

		}
		
		img = preProcessingImage;
		GreyScale image = null;
		grayImage();
			
		try {
			writeImage(image);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
//transformarea pozei	
	
	public void grayImage(){
	System.out.println("---------------------------------------------------------------------------------------");
	double startTime = System.nanoTime();
		for(int row = 0; row < img.getHeight(); row++){
				for(int col = 0; col < img.getWidth(); col++){
				int p = img.getRGB(col,row); //selectez pixelul dorit
	
				//culorile
	            int a = (p>>24)&0xff;
	            int r = (p>>16)&0xff;
	            int g = (p>>8)&0xff;
	            int b = p&0xff;
	
	            int avg = (r+g+b)/3; //se calculeaza media culorilor

	            p = (a<<24) | (avg<<16) | (avg<<8) | avg; //se inlocuieste valorea RGB cu media
	
	            img.setRGB(col, row, p);
			}
		}
		double stopTime = System.nanoTime();
		double elapsedTime = stopTime - startTime;
	    System.out.println("Timpul de executie pentru aplicarea Gray-Scale a fost de: " + elapsedTime / 10000000000.0 + " secunde");
	}
	
//scrierea imaginii	
	
	public static void writeImage(GreyScale modifiedImage) throws IOException{
		System.out.println("---------------------------------------------------------------------------------------");
		double startTime = System.nanoTime();
		try {
			File file = new File(outFile); //accesez locatia imaginii de output
			ImageIO.write(img, "bmp", file); //scriu imaginea obtinuta la adresa primita
		} catch(IOException e) {
			System.out.println(e); //eroare
		}
		double stopTime = System.nanoTime();
		double elapsedTime = stopTime - startTime;
        System.out.println("Timpul de executie pentru salvarea imaginii a fost de: " + elapsedTime / 10000000000.0 + " secunde");
	}
}
