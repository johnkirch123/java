import java.util.Scanner;

public class addition {
	public void addition(String name){
	Scanner calcNum = new Scanner(System.in);
	
	double fnum, snum, answer;
	
	System.out.println("Your first number is?");
	fnum = calcNum.nextDouble();
	System.out.println("Your second number is?");
	snum = calcNum.nextDouble();
	
	answer = fnum / snum;
	}

}
