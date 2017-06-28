package guessing.number.mod;

/**
 *Student: John Kirch
 *ID: S01581562
 *Date: 02-09-2017
 *CSC-240 Assignment 4
 */
import java.util.Random;
import java.util.Scanner;

public class Guess {
    
    private final Scanner input;
    private final Random randomGenerator = new Random();
    
    private final int LOW = 1; // low variable for guessing range.
    private final int HIGH = 1000; // high variable for guessing range.
    private final int ATTEMPTS = 10; // number of user attempts for correct guess.
    private int guess; // stores user guess variable.
    private int randomNumber; // random number generated.
    private Status gameStatus;
    
    
    @SuppressWarnings("NestedAssignment")    
    public Guess (Scanner input) {

        this.input = input;

    }

    public void play() {
        
        random(); // initialize the random variable.
        System.out.println("Guess a number between 1 and 1000: "); // first guess prompt.
        
        guess = input.nextInt(); // user input stored as guess variable.
        validateGuess(guess); 
            
            for (int i = 1; i <= ATTEMPTS; i++) {
                // switch statement for too high, low, or right on the money!
                switch (game()) {

                    case HIGH:
                        System.out.println("Too high. Try again.");
                        break;
                    case LOW:
                        System.out.println("Too low. Try again.");
                        break;
                    case JACKPOT:
                        System.out.println("Congratulations!");
                        if (i < ATTEMPTS)
                            System.out.println("Either you know the secret or you got lucky!");
                        if (i == ATTEMPTS) 
                            System.out.println("Aha! You know the secret!");
                        i = 10;

                }
            if (i == 10) {
                // if user reaches 10 guess and did not guess the correct number, println.
                if (gameStatus != Status.JACKPOT)
                    System.out.println("You should be able to do better!");
                break;
            }
            // shows user number of remaining attempts and stores new guess in guess variable.
            System.out.println("You have " + (ATTEMPTS - i) + " more attempts.");
            guess = input.nextInt();
            validateGuess(guess);
            }
                   
        
        
    }
    // returns enum gameStatus value for switch statement.
    private Status game() {
        
        if (guess > randomNumber) 
            return gameStatus = Status.HIGH;
        if (guess < randomNumber)
            return gameStatus = Status.LOW;
        else return gameStatus = Status.JACKPOT;
            
        
    }
    
    private void random () {
        randomNumber = 1 + randomGenerator.nextInt(1000);
    }
    // validates guess to make sure it is between the high and low values.
    private void validateGuess(int guess) {
        
        if (guess > HIGH || guess < LOW) { 
            
            System.out.println("Please enter a valid number.");
            this.guess = input.nextInt();
            validateGuess(this.guess);
        }
        
    }

    private enum Status {LOW, HIGH, JACKPOT} // enum for status of guesses.
    
}
