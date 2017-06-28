/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author John Kirch, CSC 240, Bruce Haddon, 2/2/17
 */
public class Factorial {
    
    private int factorialAnswer = 1; // initialized to 1 for initial use
    private int factorialValue; // user input value to be factorialized
    
    public void factorialCalculation () {
        
        Scanner input = new Scanner(System.in);
        
        do {
            // prompt user for factorial value
            System.out.println("Please enter a factorial value (or 0 and enter to exit): ");
            factorialValue = input.nextInt();
            // check sentinel value and terminate program if true
            if (factorialValue == 0) {
                
                System.out.println("The factorial of 0 is 0");
                System.exit(0);
            // check factorial value, if negative notify user error and proceed to loop    
            } else if (factorialValue < 0) {
                
                System.out.printf("%d is not a valid factorial value%n%n", factorialValue);
                
            } else {
                // iterate through factorial values multiplying each new value
                for (int i = factorialValue; i > 0; i--) {
                    
                    factorialAnswer = factorialAnswer * i;
                    
                }
                
                System.out.printf("The factorial answer is: %d%n%n", factorialAnswer);
                factorialAnswer = 1; // re-initialize factorial value for next iteration
            }
        } while (factorialValue != 0); // continue to loop while user input value does not equal sentinel value
    }
}
