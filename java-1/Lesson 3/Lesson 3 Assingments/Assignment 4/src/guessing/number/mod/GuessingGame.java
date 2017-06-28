package guessing.number.mod;

/**
 *Student: John Kirch
 *ID: S01581562
 *Date: 02-09-2017
 *CSC-240 Assignment 4
 */
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        try (Scanner input = new Scanner (System.in)){

            do {
                
                Guess game = new Guess(input); // instance of Guess class.
                game.play(); // calls the method play from class Guess.
                System.out.println("Do you want to play again (Y/N)? "); // user prompt to play again.
                
            } while (!input.next().toLowerCase().contains("n")); // user input is condition. while input does not equal n.
            
        }
    }
}
