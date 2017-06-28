package employee.serializable;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Formatter;

/**
 * Simple writing of an object to a .dat file and re-opening it to write the 
 * contents to a .txt file.
 *
 * @author    John Kirch, S01581562
 * @version 1.0   04-22-2017, CSC-240 Assignment 11
 */
public class EmployeeDat
{
    // .dat file.
    private static final String EMPLOYEE_OBJECT_FILE = "Employees.dat";   
    // Formatter for the .txt file.
    public static Formatter newFile; 
    // Line separator.
    public static String newline = System.getProperty("line.separator");
    // Output stream for sending the objects to the .dat file.
    private static ObjectOutputStream output;  
    // Input stream for getting the objects from the .dat file.
    private static ObjectInputStream input;                                 
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
        openOutputFile(EMPLOYEE_OBJECT_FILE, true);
        addRecords();
        closeFile();
        openInputFile(EMPLOYEE_OBJECT_FILE);
        openOutputFile("Employees.txt", false);
        readRecords();
        closeFile();
    }
    /**
     * Opens either the ObjectOutputStream or the Formatter for sending objects or
     * data to, respectively.
     * 
     * @param filename  Name of the output file to be opened.
     * @param value     Boolean for selecting which type of output to create.
     */
    public static void openOutputFile(String filename, Boolean value)
    {
        try
        {
            if (value)
                output = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)));
            else
                newFile = new Formatter(filename);
        }
        catch (IOException ioException)
        {
            System.err.println("Error opening file. Terminating");
            System.out.println(ioException);
            System.exit(1);
        }
    }
    /**
     * Creates an ObjectInputStream to receive objects for processing.
     * 
     * @param filename  File name of the .dat file that this program will receive 
     *                  information from.
     */
    public static void openInputFile(String filename)
    {
        try
        {
            input = new ObjectInputStream(Files.newInputStream(Paths.get(filename)));
        }
        catch(IOException ioException)
        {
            System.err.println("Error opening file. Terminating");
            System.out.println(ioException);
            System.exit(1);
        }
    }
    /**
     * List of Employees to add to the .dat file.
     */
    public static void addRecords()
    {
        Employee record1 = new Employee("Bob", "Smith", new Date(12, 13, 1995), new Date(03, 10, 2012));
        Employee record2 = new Employee("Sally", "Jones", new Date(05, 03, 1978), new Date(04, 17, 2013));
        Employee record3 = new Employee("Jackie", "Brown", new Date(07, 22, 1982), new Date(07, 02, 2006));
        Employee record4 = new Employee("Billy", "James", new Date(03, 31, 1958), new Date(11, 01, 2001));
        Employee record5 = new Employee("Suzie", "Queen", new Date(01, 12, 1999), new Date(10, 10, 2016));

        writeEmployeeObjects(record1);
        writeEmployeeObjects(record2);
        writeEmployeeObjects(record3);
        writeEmployeeObjects(record4);
        writeEmployeeObjects(record5);
    }
    /**
     * Closes any open files.
     */
    public static void closeFile()
    {
        try
        {
            if(output != null)
                output.close();
            if(input != null)
                input.close();
            if(newFile != null)
                newFile.close();
        }
        catch(IOException ioException)
        {
            System.err.println("Error closing file. Terminating");
            System.out.println(ioException);
            System.exit(1);
        }
    }
    /**
     * Writes the Employee record information to the .dat file.
     * 
     * @param record    Employee object, contains firstName, lastName, birthDate, 
     *                  and hireDate
     */
    private static void writeEmployeeObjects(Employee record)
    {
        try
        {
            output.writeObject(record);
        }
        catch(IOException ioException)
        {
            System.err.println("Error writing object. Terminating");
            System.out.println(ioException);
            System.exit(1);
        }
    }
    /**
     * Reads the records and prints it to the console as well as sending it to 
     * the .txt file.
     */
    public static void readRecords()
    {
        System.out.printf("%-20s%-20s%-20s%n%n", "Name", "Hire Date", "Birth Date");
        newFile.format("%-20s%-20s%-20s%n", "Name", "Hire Date", "Birth Date");
        try
        {
            while(true)
            {
                Employee record = (Employee) input.readObject();
                newFile.format("%s%s", record.toString(), newline);
                System.out.println(record.toString());
            }
        }
        catch(EOFException endOfFileException)
        {
            System.out.print("\nNo more records\n");
        }
        catch(ClassNotFoundException classNotFoundException)
        {
            System.err.print("Class not found. Terminating");
            System.exit(1);
        }
        catch(NoSuchFileException noSuchFileException)
        {
            System.err.println("No such file exists");
        }
        catch(IOException ioException)
        {
            System.out.println("Error reading from file. Terminating");
            System.exit(1);
        }
    }
}
