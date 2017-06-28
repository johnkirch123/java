
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
    private static int headsCounter;
    private static int tailsCounter;
    private static String coinReturnStatement;
    private final static int HEADS_STATE = 0;
    private final static int TAILS_STATE = 1;
    
    public static void flip (int numberOfTosses){
        
        Coin coinState = Coin.HEADS;
        
        for (int i = 0; i < numberOfTosses; i++) {
            
            int flip = randomFlips.nextInt(2);
            
            if (flip == HEADS_STATE) {
                
                coinState = Coin.HEADS;
                
            }
            if (flip == TAILS_STATE) {
                
                coinState = Coin.TAILS;
                
            }
            
            switch (coinState) {
                
                case HEADS: 
                    
                    headsCounter++;
                    break;
                
                case TAILS:
                    tailsCounter++;
                    break;
                
            }
            
        }
        
        coinReturnStatement = "The total number of heads flipped is: " + headsCounter
                    + "\nThe total number of tails flipped is: " + tailsCounter;
    
    
    }
    
    static String getStatement () {
        
        return coinReturnStatement;
        
    }
    
}
