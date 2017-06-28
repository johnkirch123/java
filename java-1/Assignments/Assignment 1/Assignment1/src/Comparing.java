/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author John Kirch
 */
import java.util.Scanner; // program uses class Scanner

public class Comparing 
{
   
   public static void main(String[] args)
   {
      // Scanner for user input
      Scanner input = new Scanner(System.in);

      int number1; 
      int number2; 

      System.out.print("Enter first integer: "); 
      number1 = input.nextInt();  

      System.out.print("Enter second integer: ");  
      number2 = input.nextInt();  
      
      if (number1 == number2) 
         System.out.print("These numbers are equal.");

      if (number1 > number2)
         System.out.printf("%d is larger.\n", number1);

      if (number1 < number2)
         System.out.printf("%d is larger.\n", number2);
   } 
} 