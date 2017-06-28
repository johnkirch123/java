
package edu.frontrange.csc240.a5;

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
public enum Direction {RIGHT, DOWN, LEFT, UP}
private static final int INPUT_WITH_COMMA = 5;
private final int ROWS = 20;
private final int COLUMNS = 20;
private int currentX;
private int currentY;
private boolean penState = false;
List<String> commands;
List<String> messages;
private final boolean[][] floor = new boolean[ROWS][COLUMNS];
Direction currentDirection;
/**
 * Accepts a turtle program from a Scanner.
 *
 * @param source	Scanner that is the source of Turtle commands
 * @return			List of messages about the compilation (not null)
 */
public List<String> enterCommands(Scanner source)
{
    commands = new ArrayList<>();
    while( source.hasNextInt() )
    {
        source.useDelimiter("(\\s)+|,(\\s)*");
        int next = source.nextInt();
        if (next == 6)
        {
            executeCommands(); 
            return messages;
        }
        String input = Integer.toString(next);

        if( next == INPUT_WITH_COMMA && source.hasNextInt() )
            commands.add(input + "," + source.nextInt());
        else
            commands.add(input);
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
    currentDirection = Direction.RIGHT;
    
    for (int i = 0; i < commands.size(); i++)
    {
        String cmd = commands.get(i);
	int command = Integer.parseInt(cmd);
        int j = 0;
        
        switch (command)
        {
            case 1:
                penState = true;
                break;
            case 2:
                penState = false;
                break;
            case 3:
                currentDirection = Direction.values()[++j];
                break;
            case 4:
                currentDirection = Direction.values()[--j];
                break;
            case 5:
                i++;
                cmd = messages.get(i);
                command = Integer.parseInt(cmd);
                movement(command);
                break;
            case 6:
                display();
                break;
            case 9:
                return true;
        }
    }
    return false;
}

private void movement(int move) 
{
    switch (currentDirection)
    {
        case UP:
            for (int i = 0; i < move; i++)
                floor[currentX--][currentY] = penState;
            break;
        case DOWN:
            for (int i = 0; i < move; i++)
                floor[currentX++][currentY] = penState;
            break;
        case LEFT:
            for (int i = 0; i < move; i++)
                floor[currentX][currentY--] = penState;
            break;
        case RIGHT:
            for (int i = 0; i < move; i++)
                floor[currentX][currentY++] = penState;
            break;
    }
}
private void display()
{
    for (int k = 0; k < floor.length; k++)
    {
        for (int l = 0; l < floor[k].length; l++)
        {
            if (floor[k][l])
                System.out.print("* ");
            else
                System.out.print("  ");
        }
        System.out.println();
    }
}
}
