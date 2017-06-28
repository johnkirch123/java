/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coin.toss;

/**
 *
 * @author John
 */
import java.util.Scanner;

public class CoinTossing {
    
    
    public static void main(String[] args) {
        
        
        int numberOfTosses = 0;
        do {
        
        Scanner input = new Scanner(System.in);
        System.out.println ("How many times would you like the coin flipped? ");
        numberOfTosses = input.nextInt();
        
        
        
        Coin.flip(numberOfTosses);
        System.out.println(Coin.getStatement());
                
        } while (numberOfTosses != 0);
     }
    
}
