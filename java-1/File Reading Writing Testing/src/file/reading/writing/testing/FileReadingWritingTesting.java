package file.reading.writing.testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple writing (Buffered) and reading
 *
 * @author    John Kirch
 * @version 1.0   04-22-2017, File Reading/Writing Testing
 */
public class FileReadingWritingTesting {

    private static final String FILE_NAME = "Testing.txt";
    private static final String FILENAME = "test.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File file = new File(FILENAME);
        openFile(FILE_NAME, true);
        readFile(FILE_NAME);
        // Prints to file via PrintWriter
        pwWriteFile(file);
        // Reads file via Scanner
        scannerReadFile(file);

    }

    private static void openFile(String filename, Boolean value) {

        String[] array = new String[] {"Jack Winters", "1-970-223-3232", "jwinters@hamalama.com"};
        char[] cbuf = new char[] {'p', 'r', 'o', 'g', 'r', 'a', 'm'};

        if (value) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
                bufferedWriter.write("John Kirch 970-286-9066 john.kirch.business@gmail.com ");
                bufferedWriter.write("Bob Jones 734-223-5423 bob.jones@gmail.com ");
                bufferedWriter.write(cbuf);
                for (String s : array) {
                    bufferedWriter.write(" " + s);
                }
            } catch(IOException e) {

            }
        } else {
            System.out.println("File already exists");
        }
    }
    
    private static void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // System.out.println(br.readLine());
            System.out.println();
            for (String s : br.readLine().split(" ")) System.out.println(s);
            // BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            System.err.println("No file found.");
        }
    }
    
    private static void pwWriteFile(File file) {
        try (PrintWriter output = new PrintWriter(file)) {
            output.println("John Kirch");
            output.println(37);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReadingWritingTesting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void scannerReadFile(File file) {
        try (Scanner input = new Scanner(file)){
            String name = input.nextLine();
            System.out.println(name);
            int age = input.nextInt();
            System.out.println(age);
            System.out.printf("Name: %s\nAge: %d\n\n", name, age);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
