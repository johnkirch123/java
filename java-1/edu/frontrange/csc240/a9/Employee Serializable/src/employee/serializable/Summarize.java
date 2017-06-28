package summarize;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
/**
 * Main Class with the main entry point. This class creates a file and writes a given number of 
 * randomly generated numbers to it, then closes it. It then reopens the file and makes some calculations 
 * with the contents inside. Lastly, it creates a new file and writes the information from the 
 * calculations to it, then closes that file as well.
 *
 * @author    John Kirch, S01581562
 * @version   4-19-2017, CSC-240 Assignment 11
 */
public class Summarize 
{
    private static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");   // Date format.
    private static Date dateObject = new Date();                                        // Date object.
    private static final Random RANDOM = new Random(-1L);                               // Random object.
    private static Formatter output;                                                    // Output for file creation and writing.
    private static Scanner input;                                                       // Input to Scan new file contents.
    private static final String NAME = "DataValues.txt";                                // DataValues.txt filename String.
    private static double lowestNumber = 1;                                             // Stores lowest number of the contents.
    private static double highestNumber = 0;                                            // Stores highest number of the contents.
    private static double totalOfValues;                                                // Total number of all the values.
    private static int totalNumberOfValues;                                             // Total counted number of random values.
    private static final int COUNT_OF_VALUES = 10000;                                   // Total count of numbers generated.
    private static String newLine = System.getProperty("line.separator");               // String for line separator.
    /**
    * Main entry point.
    * <p>
    * Execute:</p>
    * <pre>java dataminer.DataMiner</pre>
    *
    * @param args   not used.
    */
    public static void main(String[] args) 
    {
        openFile(NAME);
        addRecords();
        closeFile();
        reopenFile(NAME);
        readRecords();
        openFile("Summary.txt");
        addSummary();
        closeFile();
    }
    /**
     * Sets up a file for reading or writing.
     * @param name Name of the file to be formatted.
     */
    public static void openFile(String name)
    {
        try 
        {
            output = new Formatter(name);
        } 
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error opening file. Terminating.");
        }
    }
    /**
     * adds records to the created file, in this case 10,000 random numbers.
     */
    private static void addRecords()
    {
        for(int i = 0; i < COUNT_OF_VALUES; ++i)
        {
            try
            {
                output.format("%.16f%s", RANDOM.nextDouble(), newLine);
            } 
            catch (FormatterClosedException formatterClosedException)
            {
                System.err.println("Error writing to file. Terminating.");
                break;
            }
        }
    }
    /**
     * Closes the file.
     */
    public static void closeFile()
    {
        if(output != null)
            output.close();
    }
    /**
     * Re-Opens a file for data processing.
     * @param name Name of the file to be opened.
     */
    public static void reopenFile(String name)
    {
        try
        {
            input = new Scanner(Paths.get(name));
        }
        catch (IOException ioException)
        {
            System.err.println("Error opening file. Terminating.");
        }
    }
    /**
     * Reads the contents of a file and calculates certain information about
     * the contents.
     */
    public static void readRecords()
    {
        int count = 1;
        double number;
        
        try
        {
            while (input.hasNext())
            {
                number = Double.parseDouble(input.next());
                totalOfValues += number;
                totalNumberOfValues++;
                if (lowestNumber > number)
                    lowestNumber = number;
                
                if (highestNumber < number)
                    highestNumber = number;
                
                if (count == 10)
                {
                    count = 1;
                } else
                {
                    count++;
                }
            }
        }
        catch (NoSuchElementException elementException)
        {
            System.err.println("File improperly formed. Terminating.");
        }
        catch (IllegalStateException stateException)
        {
            System.err.println("Error reading from file. Terminating.");
        }
    }
    /**
     * Adds information to the Summary.txt file about the contents of the DataValues.txt
     * file.
     */
    public static void addSummary()
    {        
        try
        {
            output.format("%s%s%s%s%s%s%s%5d%s%s%.15f%s%s%.15f%s%s%.15f%s%s%.15f", 
                "John Kirch", newLine, "CSC-240-500 Java Programming", newLine, dateFormat.format(dateObject),
                    newLine, "Count of values = ", totalNumberOfValues, newLine, "Sum of Values = ", totalOfValues, 
                        newLine, "Average = ", totalOfValues/totalNumberOfValues, newLine, "Minimum = ", lowestNumber, 
                            newLine, "Maximum = ", highestNumber);
        }
        catch (FormatterClosedException formatterClosedException)
        {
            System.err.println("Error writing to file. Terminating.");
        }
    }
}
