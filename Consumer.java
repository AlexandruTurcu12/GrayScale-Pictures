package ProiectGreyScale;

public class Consumer extends Divide{
	Consumer() {
		super();
	}
	public Consumer(Buffer b) {
		super(false, b);	//se seteaza type = false
		System.out.println("Apelare constructor pentru Consumer");
	}
}
