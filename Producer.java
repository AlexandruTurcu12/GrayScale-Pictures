package ProiectGreyScale;

public class Producer extends Divide{
	Producer() {
		super();
	}
	public Producer(Buffer b) {
		super(true, b);		//se seteaza type = true
		System.out.println("Apelare constructor pentru Producer");
	}
}
