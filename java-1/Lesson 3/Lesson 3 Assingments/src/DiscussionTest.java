/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnny
 */
public class DiscussionTest {
    
    public static void main (String[] args) {
        double cent = 0.01;
        double dollar = 0.0;
        for( int i = 0; i != 100; ++i ) dollar += cent;
        System.out.println(dollar == 1.00);
        System.out.println(dollar);
        System.out.println(cent);
    }
    
}
