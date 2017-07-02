package animals;

public class apples {

	public static void main(String[] args) {
		
		Animal[] thelist = new Animal[3];
		Dog d = new Dog();
		Fish f = new Fish();
		Bird b = new Bird();
		
		thelist[0] = d;
		thelist[1] = f;
		thelist[2] = b;
		
		for(Animal x: thelist){
			x.noise();
		}
		
	}

}
