
package edu.frontrange.csc240.a5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author		John Kirch, S01581562
 * @version		02/21/2017, CSC-240, Assignment 5, Exercise 7.21
 */
public class TurtleGraphics1
{
    
private static final int INPUT_WITH_COMMA = 5;
private boolean penPosition = false;
private int currentRowPosition = 0;
private int currentColumnPosition = 0;
private boolean directionX = true;
private boolean directionY;
private int direction = 0;
private final int ROWS = 20;
private final int COLUMNS = 20;
private boolean[][] array;
private int[][] commandArray;


/**
 * Accepts a turtle program from a Scanner.
 *
 * @param source	Scanner that is the source of Turtle commands
 * @return			List of messages about the compilation (not null)
 */
public List<String> enterCommands(Scanner source)
{
    /**for (int row = 0; row < ROWS; row++)
        for (int column = 0; column < COLUMNS; column++)
            commandArray[row][column] = source.nextInt();
    */
    List<String> messages = new ArrayList<>();
    while( source.hasNextInt() )
    {
        int next = 0;
        array = new boolean[ROWS][COLUMNS];


        String input = Integer.toString(next);

        switch (next) 
        {
            case 1:
                penPosition = true;
                break;
            case 2:
                penPosition = false;
                break;
            case 3:
                direction++;
            case 4:
                direction--;
            case 5:
                int steps = source.nextInt();
                movement(boolean directionX, boolean directionY, boolean[][] array, int steps);
            case 6:
               executeCommands();
               break;
            case 9:


        }
        if( next == INPUT_WITH_COMMA && source.hasNextInt() )
            messages.add(input + "," + source.nextInt());
        else
            messages.add(input);

    }
    return messages;
}

/**
 * Execute the commands that have been stored in the command array.
 *
 * @return		false if the program cannot be executed, otherwise true
 */
public boolean executeCommands()
{
    if (true)
    {
        for (int row = 0; row < ROWS; row++)
        for (int column = 0; column < COLUMNS; column++)
        {
            if (array[row][column])
                System.out.println("* ");
            else
                System.out.println("  ");
        }
    }
    
    
    return true;
}

private void movement(boolean x, boolean y, boolean[][] grid, int step)
{
    for (int i = step; i > 0; i--)
    {
        if (x && y == false && penPosition)
        {
            array[currentRowPosition][currentColumnPosition] = true;
            currentColumnPosition++;
        } else if (x && y == false){
            currentColumnPosition++;
        }
        
        if ( x == false && y == false && penPosition)
        {
            array[currentRowPosition][currentColumnPosition] = true;
            currentRowPosition--;
        } else if (x == false && y == false) {
            currentRowPosition--;
        }
        
        if ( x == false && y && penPosition)
        {
            array[currentRowPosition][currentColumnPosition] = true;
            currentColumnPosition--;
        } else if (x && y == false){
            currentColumnPosition--;
        }
        
        if ( x && y && penPosition)
        {
            array[currentRowPosition][currentColumnPosition] = true;
            currentRowPosition++;
        } else if (x && y) {
            currentRowPosition++;
        }
        
        
    }
        
}
}
