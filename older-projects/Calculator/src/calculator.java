import java.util.Scanner;

public class calculator {
	public static void main(String args[]){
		Scanner mathHolder = new Scanner(System.in);
		double fnum, snum, answer;
		
		System.out.println("What is the first number? ");
		fnum = mathHolder.nextDouble();
		
		System.out.println("What is the second number? ");
		snum = mathHolder.nextDouble();
		
		answer = fnum + snum;
		
		System.out.println("Your answer is approximately! ");
		System.out.print(answer);

	}
		
	}


