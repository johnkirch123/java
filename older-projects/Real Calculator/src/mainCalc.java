import java.util.Scanner;
public class mainCalc {

	public static void main(String args[]) {
		
		Scanner input = new Scanner(System.in);
		addition additionObject = new addition();
		subtraction subtractionObject = new subtraction();
		multiplication multiplicationObject = new multiplication();
		division divisionObject = new division();
		
		System.out.println("This is the calculator!");
		System.out.println("Would you like to add, subtract, multiply or divide?");
		
		String name = input.nextLine();
		
		if(name = input.nextLine(add)){
			additionObject.addition(name);
		}
		if(name = subtract){
			subtractionObject.subtraction(name);
		}
		if(name = multiply){
			multiplicationObject.multiplication(name);
		}
		else{
			divisionObject.division(name);
		}
		
		

	}

	}
