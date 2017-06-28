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
public class E {
    
    private double eAnswer = 1;
    private double eValue = 1;
    private double rValue = 1;
    
    public void eCalculation () {
        
        Scanner input = new Scanner(System.in);
        
        do {
            // user prompt for number of e terms to calculate
            System.out.println("Please enter the number of terms to calculate e (or 0 and enter to exit): ");
            eValue = input.nextDouble();
            // sentinel value check, end program if true
            if (eValue == 0) {
                
                System.out.println("The e^0 is 1");
                System.exit(0);
            // if number entered is negative, notify user and prompt again    
            } else if (eValue < 0) {
                
                System.out.printf("%d is not a valid term value for e.%n%n", eValue);
                
            } else {
                /** first for loop is for all the iterations of the terms
                * second for loop is for the iterations of each reciprocal*/
                for (double i = eValue; i > 0; i--) {
                    for (double j = eValue; j > 0; j--) {
                        rValue = rValue * j; 
                }
                    /** find answer to e and reset rValue with each iteration, 
                     * eValue-- sets up the next rValue iteration*/
                    eAnswer = eAnswer + (1 / rValue);
                    rValue = 1;
                    eValue--;
                }
                
                System.out.printf("The e Value with the terms provided is: %.15f%n%n", eAnswer);
                // re-initialize eAnswer and eValue to 1 for the next user query
                eAnswer = 1;
                eValue = 1;
            }
        } while ((int) eValue != 0); // continue this loop while sentinel value is not input
    }
}
