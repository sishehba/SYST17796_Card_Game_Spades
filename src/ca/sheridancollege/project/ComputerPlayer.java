/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.Random;
/**
 *
 * @author Shehbaz Singh
 */
public class ComputerPlayer extends Player {

    private Random random = new Random();

    public ComputerPlayer(String name) {
        super(name, true);
    }

    @Override
    public Card playTurn(Suit currentSuit) {
        for (Card card : getHand()) {
            if (card.getSuit() == currentSuit) {
                // Logic for which card to throw
                return card;
            }
        }
        
        // If no valid suit, play a random card ( for now)
        return getHand().remove(random.nextInt(getHand().size()));
    }
}