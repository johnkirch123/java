package polymorphism;

public class poly {

	public static void main(String[] args) {
		
		fatty john = new fatty();
		food po = new potpie();
		food fo = new food();
		
		john.digest(fo);
		john.digest(po);

	}

}
