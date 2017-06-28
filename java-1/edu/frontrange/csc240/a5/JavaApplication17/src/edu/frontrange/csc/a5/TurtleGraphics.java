package edu.frontrange.csc.a5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author John Kirch, S01581562
 * @version 02-20-2017, CSC-240 Assignment 5, Exercise 7.21
 */
public class TurtleGraphics
{
public enum Direction {RIGHT, DOWN, LEFT, UP}                                   // enum for the directional values.
private static final int INPUT_WITH_COMMA = 5;                                  // Static final variable to keep move,distance together.
private final String OUT_OF_BOUNDS = "Some part of this drawing is outside the floor";          // String for drawing being out of bounds.
private final int ROWS = 20;                                                    // # of rows for floor array.
private final int COLUMNS = 20;                                                 // # of columns for floor array.
private int currentX;                                                           // Current X position on array.
private int currentY ;                                                          // Current Y position on array.
private int command;                                                            // Int variable for processing individual commands.
private int errors;                                                             // Tracks errors from the incoming commands.
private boolean penState;                                                       // Pen state, whether drawing or not.
private boolean error;                                                          // Report if there is an error in the program, OOB.
List<String> commands;                                                          // List for storing all the commands coming from enter commands input.
private final boolean[][] floor = new boolean[ROWS][COLUMNS];                   // Boolean multidimensional array for storing floor penState values.
Direction currentDirection = Direction.RIGHT;                                   // Initialize enum value.
/**
 * Accepts a turtle program from a Scanner.
 * @param source	Scanner that is the source of Turtle commands
 * @return			List of messages about the compilation (not null)
 */
public List<String> enterCommands(Scanner source)
{
    commands = new ArrayList<>(); 
    resetValues();
    /**
     * while loop to store integers as Strings in the commands array.
     */
    while( source.hasNextInt() )
    {
        int next = source.nextInt();
        
        String input = Integer.toString(next);
        
        /* If command is a 5, attach with "," to the next command which is distance.
        Attach error if no distance command after the move [5] command.
        Attach error if command is not a valid command.
        Attach error if program is not properly terminated. */
         
        if( next == INPUT_WITH_COMMA && source.hasNextInt() )
            commands.add(input + "," + source.nextInt());
        else if (next == INPUT_WITH_COMMA && !source.hasNextInt())
        {
            commands.add(input + ", [No distance after move command]");
            errors++;
        } else {
            // <=0, 7, 8, and >=10 are not valid numbers, if any of these numbers exist, report error.
            if (next <= 0 || next == 7 || next == 8 || next >= 10) 
            {
                commands.add(" [" + input + "] Not a valid entry");
                errors++;
            } else 
                commands.add(input);
        }
        // Report error if 9(Terminate Program) is not used as the last entry to properly terminate program.
        if (next != 9 && !source.hasNext()) commands.add(input + "[The program was not terminated properly]");
    }
    // returns list of commands input from Scanner source.
    return commands;
}

/**
 * Execute the commands that have been stored in the command array.
 * @return		false if the program cannot be executed, otherwise true
 */
public boolean executeCommands()
{
    if (errors > 0) return false;                           // False if errors noted above.
    String[] values = new String[2];                        // Temporarily stores the split String values to process move and distance.
    /* Cycle through every element in array list and process the value.
    Create the true or false positions for every row and column of the multidimensional 
    array floor[ROWS][COLUMNS]. */
    for (String cmd : commands)
    {
        if(cmd.contains("5") && cmd.contains(","))
        {
            values = cmd.split(",");
            command = Integer.parseInt(values[0]);
            
        } else
            command = Integer.parseInt(cmd);
        // Performs correct function depending on the command value.
        switch (command)
        {
            case 1:
                // Sets pen state to false to not draw on floor[][].
                penState = false;
                break;
            case 2:
                // Sets pen state to true for drawing on floor[][].
                penState = true;
                break;
            case 3:
                // Rotates clockwise (right turn).
                switch (currentDirection)
                {
                    case RIGHT:
                        currentDirection = Direction.DOWN;
                        break;
                    case LEFT:
                        currentDirection = Direction.UP;
                        break;
                    case UP:
                        currentDirection = Direction.RIGHT;
                        break;
                    case DOWN:
                        currentDirection = Direction.LEFT;
                        break;
                }
                break;
            case 4:
                // Rotates counter-clockwise (left turn).
                switch (currentDirection)
                {
                    case RIGHT:
                        currentDirection = Direction.UP;
                        break;
                    case LEFT:
                        currentDirection = Direction.DOWN;
                        break;
                    case UP:
                        currentDirection = Direction.LEFT;
                        break;
                    case DOWN:
                        currentDirection = Direction.RIGHT;
                        break;
                }
                break;
            case 5:
                // Takes the second value stored in the values array and parses it (int) to pass through the movement method.
                int move;
                move = Integer.parseInt(values[1]);
                movement(move);
                break;
            case 6:
                // Displays the current drawing -- messages -- arraylist.
                display();
                break;
            case 9:
                // Properly terminates program.
                resetValues();
            default:
                System.out.println();
        }
    }
    System.out.println();
    return true;
}

private void movement(int move) 
{ 
    // Uses current direction to change the values of the floor to either true or false.
    switch (currentDirection)
    {   // Moves the turtle UP until it has reached the array boundry, reports if it tries to go past the boundry.
        case UP:
            for (int i = 0; i < move; i++)
            {
                if (currentX == 0 && i + 1 != move || currentX - move + i < 0) 
                {
                    error = true;
                    while (currentX > 0)
                    {
                        if (!floor[currentX][currentY]) floor[currentX--][currentY] = penState;
                        else
                            currentX--;
                    }
                    break;
                }
                if (currentX == 0) break;
                if (currentX != 0 && !floor[currentX][currentY]) floor[currentX--][currentY] = penState;
                else
                    currentX--;
            }
            break;
        // Moves the turtle DOWN until it has reached the array boundry, reports if it tries to go past the boundry.
        case DOWN:
            for (int i = 0; i < move; i++)
            {
                if (ROWS - currentX == 1 && i + 1 != move || currentX + move - i > ROWS) 
                {
                    error = true;
                    while (currentX < ROWS)
                    {
                        if (!floor[currentX][currentY]) floor[currentX++][currentY] = penState;
                        else
                            currentX++;
                    }
                    break;
                }
                if (currentX == ROWS) break;
                if (currentX != ROWS && !floor[currentX][currentY]) floor[currentX++][currentY] = penState; 
                else
                    currentX++;
            }
            break;
        // Moves the turtle LEFT until it has reached the array boundry, reports if it tries to go past the boundry.
        case LEFT:
            for (int i = 0; i < move; i++)
            {
                if (currentY == 0 && i + 1 != move || currentY - move + i < 0) 
                {
                    error = true;
                    while (currentY > 0)
                    {
                        if (!floor[currentX][currentY]) floor[currentX][currentY--] = penState;
                        else
                            currentY--;
                    }
                    break;
                }
                if (currentY == 0) break;
                if (currentY != 0  && !floor[currentX][currentY]) floor[currentX][currentY--] = penState;
                else
                    currentY--;
            }
            break;
        // Moves the turtle RIGHT until it has reached the array boundry, reports if it tries to go past the boundry.
        case RIGHT:
            for (int i = 0; i < move; i++)
            {
                if (COLUMNS - currentY  == 0 && i + 1 != move || currentY + move - i > COLUMNS) 
                {
                    error = true;
                    while (currentY < COLUMNS - 1)
                    {
                        if (!floor[currentX][currentY]) floor[currentX][currentY++] = penState;
                        else
                            currentY++;
                    }
                    break;
                }
                if (currentY == COLUMNS) break;
                if (currentY != COLUMNS  && !floor[currentX][currentY])floor[currentX][currentY++] = penState;
                else
                    currentY++;
            }
            break;
    }
}
// Displays the current floor[][] array as string values.
private void display()
{
    for (int k = 0; k < COLUMNS; k++)
    {
        for (int l = 0; l < ROWS; l++)
        {
            if (floor[k][l])
            {
                System.out.print("* ");
            } else {
                System.out.print("  ");
                
            }
            
        }
        System.out.println();
    }
    if(error) System.out.println(OUT_OF_BOUNDS);
}
// Initializes variables for a fresh set of commands, called at start of enterCommands()and when a 9 is processed.
private void resetValues() 
{
    errors = 0;
    currentX = 0;
    currentY = 0;
    currentDirection = Direction.RIGHT;
    penState = false;
    error = false;
    for (int k = 0; k < COLUMNS; k++)
    {
        for (int l = 0; l < ROWS; l++)
        {
            floor[k][l] = false;
            
        }
    }
}
}
