
package coin.toss;

/**
 *Student: John Kirch
 *ID: S01581562
 *Date: 02-07-2017
 *CSC-240 Assignment 4
 */
import java.security.SecureRandom;

public enum Coin {
    HEADS, TAILS;
    
    private static final SecureRandom randomFlips = new SecureRandom();
    private static int headsCount;
    private static int tailsCount;
    private static String coinReturnStatement;
    private static Coin coinSide;
    
    public static String flipCoin (int numberOfTosses){
        
        
        for (int i = 0; i < numberOfTosses; i++) {
            
            switch (flip()) {
                
                case HEADS:
                    headsCount++;
                    break;
                case TAILS:
                    tailsCount++;
                
            }
                
        }
        
        return coinReturnStatement = "The total number of heads flipped is: " + headsCount + 
                "\nThe total number of tails flipped is: " + tailsCount;
        
    }
    
    private static Coin flip() {
        
        if (randomFlips.nextBoolean()) {
                
                return Coin.HEADS;

            }
            else  {
                
                return Coin.TAILS;
            }
        
    }
    
}
