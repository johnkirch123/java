package guessing.number.mod;

/**
 *Student: John Kirch
 *ID: S01581562
 *Date: 02-09-2017
 *CSC-240 Assignment 4
 */
import java.util.Random;

public enum Guess1 {
    HIGH, LOW, JACKPOT;
    
    static final Random randomNum = new Random();
    private static final int randomValue = 1 + randomNum.nextInt(1000);
    
    public static String play (int guess) {
         
        switch(compare(guess)) {
                case HIGH:
                    return "Too high. Try again!";
                case LOW:
                    return "Too low. Try again!";
                case JACKPOT:
                    return "Congratulations!";
                default:
                    return "You broke the game!!!";
        }
        
    }
    
    private static Guess1 compare(int guess) {
        
        if (randomValue > guess)
            return LOW;
        else if (randomValue < guess)
            return HIGH;
        else 
            return JACKPOT;
    }
    
}
