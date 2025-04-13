/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.List;
import java.util.Map;
/**
 *
 * @author Shehbaz Singh
 */
public class HumanPlayer extends Player {
    public HumanPlayer(String name, int id) {
        super(name, false, id);
        
    }

    @Override
    public boolean playTurn(TurnManager turnManager, InputHandler inputHandler, List<Map<Player, Card>> allRounds) {
        if(!hasCards()) {
             return true;
        }
        
        boolean isTrumpCard = false;
//      Print The cards in hand for current round
       
        List<Card> currentSuitCards = this.getCardsBySuit(turnManager.getCurrentTurnRoundSuit());
            
        if(currentSuitCards.isEmpty()) {
            System.out.println("You do not have any card for current SUIT, select one to give.");
            currentSuitCards = this.hand;
            isTrumpCard = true;

        } else {
            System.out.println("Cards you have for current Suit:");
        }


        for (int i = 0; i < currentSuitCards.size(); i++) {
            System.out.println((i + 1) + ". " + currentSuitCards.get(i));
        }
        
        
//      Play Card    
            System.out.print("Select a card to play (1 to " + currentSuitCards.size() + "): ");
            while(true){
                try {
                    Object choice = inputHandler.handleInput(InputType.INTEGER, true, 1, currentSuitCards.size(), 1, 2);
                    if (choice instanceof InputResultHandler) {
                        InputResultHandler res = (InputResultHandler) choice;

                        if (res.getMessage().equalsIgnoreCase("exit")) {
                            System.out.println("Returning to main menu...");
                            return false; // special value to signal exit
                        }
                        
                        Card selected = currentSuitCards.get(res.getNumber() - 1);
                        this.getHand().remove(selected);
                        System.out.println("You played: " + selected);
                        // Proceed with turn logic here
                        

                        if(isTrumpCard) {
                            System.out.println("Trump Played");
                            turnManager.currentRoundLoser().receiveCard(selected);
                            for (Card card : turnManager.getCurrentRoundCards()) {
                                turnManager.currentRoundLoser().receiveCard(card);
                            }
                           
                    
                            turnManager.changeTurn(turnManager.currentRoundLoser().getPlayerID());
                            turnManager.resetCurrentRound();
                           
                            turnManager.setTurnCount(0);
                            isTrumpCard = false;
                            break;
                        }
                        
                        
                        turnManager.addToCurrentRoundPlays(this, selected);
                        System.out.println("Loser so far: " + turnManager.currentRoundLoser().getName());
                        turnManager.increaseTurnCount();

                        break;
                        
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            
                
            }
            return true;
    }
}
