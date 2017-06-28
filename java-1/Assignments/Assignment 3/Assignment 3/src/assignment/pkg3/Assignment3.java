/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg3;

/**
 *
 * @author John
 */
public class Assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        float cent = 0.01F;
        float dollar = 0.0F;
        for( int i = 0; i != 100; ++i ) dollar += cent;
        System.out.println(dollar);
        System.out.println(dollar == 1.00F);
    }
    
}
