
package coin.toss;

/**
 *Student: John Kirch
 *ID: S01581562
 *Date: 02-07-2017
 *CSC-240 Assignment 4
 */
import java.util.Scanner;

public class CoinTossing {
    
    
    static int numberOfTosses = 0;
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        do {
        
        System.out.println ("How many times would you like the coin flipped (0 to escape)? ");
        numberOfTosses = input.nextInt();
        
            if (numberOfTosses != 0) {
                
                System.out.println(Coin.flipCoin(numberOfTosses));
                
            } else if (numberOfTosses < 0) {
                
                System.out.println(numberOfTosses + " is not a valid number of tosses. Please try again.");
                
            }
               
        } while (numberOfTosses != 0);
        
        input.close();
        System.exit(0);
     }
    
}