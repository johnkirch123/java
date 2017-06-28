/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author John Kirch
 */
import java.util.Scanner; //program uses class Scanner

public class Arithmetic {
    
    public static void main (String[] args) {
        
        Scanner input = new Scanner(System.in); // Scanner for input
        
        int num1, num2, sum, mult, sub, div, mod;
        
        System.out.println("Please enter the first number: ");
        num1 = input.nextInt();
        
        System.out.println("Please enter the second number: ");
        num2 = input.nextInt();
        
        sum = num1 + num2;
        mult = num1 * num2;
        sub = num1 - num2;
        div = num1 / num2;
        mod = num1 % num2;
        
        System.out.println("The sum of these numbers is " + sum);
        System.out.println("The product of these numbers is " + mult);
        System.out.println("The difference of these numbers is " + sub);
        System.out.println("The second number divides into the first number " + div + " times, with a remainder of " + mod);
    }
    
}
