/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author John Kirch
 */
import java.util.Scanner;

public class SmallLarge {
    
    public static void main (String[] args) {
        
        Scanner input = new Scanner (System.in);
        
        int num1, num2, num3, sum, avg, product;
        int smallest = 0;
        int largest = 0;
        
        System.out.println("Please input your first number: ");
        num1 = input.nextInt();
        
        System.out.println("Please input your second number: ");
        num2 = input.nextInt();
        
        System.out.println("Please input your third number: ");
        num3 = input.nextInt();
        
        sum = num1 + num2 + num3;
        avg = sum / 3;
        product = num1 * num2 * num3;
        
        if (num1 < num2 && num1 < num3)
            smallest = num1;
        
        if (num2 < num1 && num2 < num3)
            smallest = num2;
        
        if (num3 < num2 && num3 < num1)
            smallest = num3;
        
        if (num1 > num2 && num1 > num3)
            largest = num1;
        
        if (num2 > num1 && num2 > num3)
            largest = num2;
        
        if (num3 > num2 && num3 > num1)
            largest = num3;
        
        System.out.println("The sum of these numbers is " + sum);
        System.out.println("The average of these numbers is " + avg);
        System.out.println("The product of these numbers is " + product);
        System.out.println("The smallest of these numbers is " + smallest);
        System.out.println("The largest of these numbers is " + largest);
        
    }
    
}
