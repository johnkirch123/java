import java.util.Scanner;

public class methodsWithParameters {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		anotherClass anotherClassObject = new anotherClass();
		
		System.out.println("Enter your name here: ");
		String name = input.nextLine();
		
		anotherClassObject.simpleMessage(name);

	}

}
