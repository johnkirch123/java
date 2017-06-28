package guessing.number.mod;

/**
 *Student: John Kirch
 *ID: S01581562
 *Date: 02-09-2017
 *CSC-240 Assignment 4
 */
import java.util.Random;

public class Guess {
    
    static final Random randomNum = new Random();
    private static int randomValue;
    
    public static void play () {
        
        randomValue = 1 + randomNum.nextInt(1000);
        
    }
    
    public int getRandom() {
        return randomValue;
    }
}
