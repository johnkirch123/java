
import java.util.Scanner;

/**
 *
* @author John Kirch, CSC 240, Bruce Haddon, 2/3/17
 */
public class Ex {
    
    private double eAnswer; // exponential term values, sums all values to answer
    private double n; // user input variable for power of e (i.e. - e^x)
    private int termValue; // user input for number of terms in the sequence
    private int x; // original value of n for the variable in the numerator of the terms
    
    public void exCalculation () {
        
        Scanner input = new Scanner(System.in);
        
        do {
            // enter a value for the number of terms desired for the calculation
            System.out.println("Enter a positive number of terms (or 0 to quit): ");
            termValue = input.nextInt();
            // check the term for Sentinel value, is so terminate program 
            if (termValue == 0) {
                
                System.out.println("Program exited by user.");
                System.exit(0);
                
            }
            // enter value for the power of the exponent
            System.out.println("Please enter an exponential value for e: ");
            n = input.nextDouble();
            // store original n value as x for the numerator calculation
            x = (int)n;
            // check exponent value entered by user for Sentinel value, if so terminate program
            if (n == 0) {
                
                System.out.println("e^0 is 1");
                System.exit(0);
                
            } else {
                // initialize fValue to 1 to begin the factorial
                double fValue = 1;
                for (int i = 1; i <= termValue; i++) {
                    /** for the first iteration of the numerator, multiply by 1 to equate the value of n^1, 
                    then multiply by the original value (x) for each other iteration.*/
                    if (i == 1) {
                        n = n * 1;
                    } else {
                        n = n * x;
                    }
                    /** fValue creates value in denominator, n is now the calculated of the 
                    exponent value raised +1 with each iteration.*/
                    fValue = fValue*i;
                    // each iterration adds to the answer out to termValue number of terms
                    eAnswer += n/fValue;
                    
                }
                if (eAnswer > 0) {
                    
                    System.out.printf("After %d terms the estimate of e to the power of %d is: %.10f%n%n", termValue, x, eAnswer+1);
                  
                } else {
                    
                    System.out.println("The number is too large, please enter a smaller number.");
                    
                }
                // re-initializes eAnswer and termValue to 0 for next user query
                eAnswer = 0;
                termValue = 0;
            }
        } while (n != 0); // while sentinel value is not entered, program will continue to run
    }
}
