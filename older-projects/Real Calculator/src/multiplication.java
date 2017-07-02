import java.util.Scanner;

public class multiplication {
	public void multiplication(String name){
	
	Scanner calcNum = new Scanner(System.in);
	
	double fnum, snum, answer;
	
	System.out.println("Your first number is?");
	fnum = calcNum.nextDouble();
	System.out.println("Your second number is?");
	snum = calcNum.nextDouble();
	
	answer = fnum * snum;

}
}
