package guessing.number.mod;

/**
 *Student: John Kirch
 *ID: S01581562
 *Date: 02-09-2017
 *CSC-240 Assignment 4
 */
import java.util.Scanner;

public class GuessingGame1 {
    
    private static Scanner input = new Scanner(System.in);
    private static final int ATTEMPTS = 11;
    
    public static void main(String[] args) {
        
        for (int i = 1; i <= 10; i++) {
        System.out.println("Guess a number between 1 and 1000");
        System.out.println("You have " + (ATTEMPTS - i) + " attempts remaining.");
        
        int chosenNumber;
            if ((chosenNumber = input.nextInt()) >= 1 && chosenNumber <= 1000) {

                System.out.println(Guess.play(chosenNumber));
                
                if (Guess.play(chosenNumber).equals("Congratulations!") && i < 10) {
                    System.out.println("Either you know the secret or you got lucky!");
                    closeProgram();
                }
                else if (Guess.play(chosenNumber).equals("Congratulations!") && i == 10) {
                    System.out.println("Aha! You know the secret!");
                    closeProgram();
                }

            } else {
                System.out.println("Please enter a valid number.");
                i--;
            }
        }
        System.out.println("You should be able to do better!");
        closeProgram();
    }
    
    public static void closeProgram() {
        input.close();
        System.exit(0);
    }
    
}
