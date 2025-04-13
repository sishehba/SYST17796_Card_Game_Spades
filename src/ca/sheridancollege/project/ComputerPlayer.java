/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 *
 * @author Shehbaz Singh
 */
public class ComputerPlayer extends Player {

    private Random random = new Random();

    public ComputerPlayer(String name, int id) {
        super(name, true, id);
    }

   @Override
public boolean playTurn(TurnManager turnManager, InputHandler inputHandler, List<Map<Player, Card>> allRounds) {
    
    if(!hasCards()) {
        return true;
        }
    
    boolean isTrumpCard = false;
    
    List<Card> currentSuitCards = this.getCardsBySuit(turnManager.getCurrentTurnRoundSuit());

    // If no cards of the current suit, play from entire hand (trump)
    if (currentSuitCards.isEmpty()) {
        currentSuitCards = this.hand;
        isTrumpCard = true;
    }

    if (isTrumpCard) {
        Card selected = currentSuitCards.stream()
            .max(Comparator.comparingInt(c -> c.getRank().getValue()))
            .orElse(null);
        System.out.println(getName() + " played a trump card!");
        this.getHand().remove(selected);
        System.out.println(getName() + " played: " + selected);
        
        Player loser = turnManager.currentRoundLoser();
        loser.receiveCard(selected);

        for (Card card : turnManager.getCurrentRoundCards()) {
            loser.receiveCard(card);
        }

        turnManager.changeTurn(loser.getPlayerID());
        turnManager.resetCurrentRound();
        turnManager.setTurnCount(0);
       
    } else {

        // Pick the lowest rank card from the available options
        Card selected = currentSuitCards.stream()
                .min(Comparator.comparingInt(c -> c.getRank().getValue()))
                .orElse(null);

        if (selected == null) return false; 

        this.getHand().remove(selected);
        System.out.println(getName() + " played: " + selected);
        turnManager.addToCurrentRoundPlays(this, selected);
        System.out.println("Loser so far: " + turnManager.currentRoundLoser().getName());
        turnManager.increaseTurnCount();
    }
    
   
    return true;
}

}