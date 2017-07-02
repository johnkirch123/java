import java.util.Scanner;

public class switchStatement {

	public static void main(String[] args) {
		Scanner currentAge = new Scanner(System.in);
		int age;
		age = 0;
		
		System.out.println("How old are you?");
		age = currentAge.nextInt();
		switch (age){
		case 1:
			System.out.println("You can crawl");
			break;
		case 2:
			System.out.println("You can talk");
			break;
		case 3:
			System.out.println("You can get in trouble");
			break;
			default:
				System.out.println("I don't know how old you are");
				break;
		}

	}

}
