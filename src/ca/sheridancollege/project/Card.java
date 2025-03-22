/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 * @modifier Shehbaz Singh
 */
public class Card {
    
    //default modifier for child classes
    private Suit suit;  // e.g., "Spades", "Hearts"
    private Rank rank;     // 2-14 (Ace being 14)
    
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    public Suit getSuit() { return suit; }
    public Rank getRank() { return rank; }
    
    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. 
     */
    @Override
    public String toString() {
        return suit + " of " + rank;
    }

}
