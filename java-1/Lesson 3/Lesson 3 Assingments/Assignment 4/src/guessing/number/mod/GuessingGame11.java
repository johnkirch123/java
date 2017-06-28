package guessing.number.mod;

/**
 *Student: John Kirch
 *ID: S01581562
 *Date: 02-09-2017
 *CSC-240 Assignment 4
 */
import java.util.Scanner;

public class GuessingGame11 {
    
    private static Scanner input = new Scanner(System.in);
    private static Scanner answer = new Scanner (System.in);
    private static final int ATTEMPTS = 11;
    private static int chosenNumber;
    private static String proceed;
    
    public static void main(String[] args) {
        
        do {
        Guess guess = new Guess();
        guess.play();
                
        for (int i = 1; i <= 10; i++) {
        System.out.println("Guess a number between 1 and 1000");
        System.out.println("You have " + (ATTEMPTS - i) + " attempts remaining.");
        
            if ((chosenNumber = input.nextInt()) >= 1 && chosenNumber <= 1000) {
                
                System.out.println(guess.getRandom());
                
                if (chosenNumber < guess.getRandom() && i < ATTEMPTS)
                    System.out.println("Too low. Try again.");
                else if (chosenNumber > guess.getRandom() && i < ATTEMPTS)
                    System.out.println("Too high. Try again.");
                else if (chosenNumber == guess.getRandom()) {
                    System.out.println("Congratulations!");
                    break;
                }         
            } else {
                System.out.println("Please enter a valid number.");
                i--;
            } 
            
            if (chosenNumber != guess.getRandom() && i == 10)
                System.out.println("You should be able to do better!");
        }
        
        
        System.out.println("Would you like to play again (yes to continue)? ");
        proceed = answer.nextLine();
        } while (proceed.equals("yes"));
        closeProgram();
    }
    
    public static void closeProgram() {
        input.close();
        System.exit(0);
    }
    
}
