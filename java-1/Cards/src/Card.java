/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author John Kirch
 */
public class Card {
    
    private final String face;
    private final String suit;
    
    public Card(String cardFace, String cardSuit) {
        
        this.face = cardFace;
        this.suit = cardSuit;
        
    }
    
    public String toString() {
        
        return face + " of " + suit;
        
    }
    
}
