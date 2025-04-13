/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Shehbaz Singh
 */
public final class BhabhiGame extends Game {
    private List<Map<Player, Card>> allRounds = new ArrayList<>();
    private DeckOfCards deck;

    private TurnManager turnManager;
    private InputHandler inputHandler;
    
    
    public BhabhiGame(ArrayList<Player> players, InputHandler scanner) {
        super("Bhabhi Card Game");
        this.deck = new DeckOfCards();
        
        this.inputHandler = scanner;
        this.turnManager = new TurnManager(allRounds, players);
        distributeCards();
    }

    @Override
    public boolean play() {
        // Play game
        System.out.println("Game Started!!");
      
       
        turnManager.playFirstTurn();
      
        while(true){
            
            if(turnManager.getPlayers().size() == 1){
                System.out.println("\n===========================");
                System.out.println("🏆  GAME COMPLETED  🏆");

                declareWinner();
                declareLoser();
                
//                reset player
                
                
                return true;
            }
            
            System.out.println("\n");
            System.out.println("---------- Now, It's " + turnManager.getCurrentPlayer().getName() + "'s turn. ----------");
            System.out.println("\n");
            try {
                boolean continueGame = turnManager.getCurrentPlayer().playTurn(turnManager, inputHandler, allRounds);
                if (!continueGame) {
                    break; // Exit the loop
                }    
            } catch(NullPointerException e){
                System.out.println(e);
            }
                 
        }  
        
        return false;
    }

    private void distributeCards() {
        while(!this.deck.getCards().isEmpty()){
            for (int i = 0; i <  turnManager.getPlayers().size(); i++) {
               Card dealtCard = deck.dealCard();
              
               if(dealtCard != null) {
                    if(dealtCard.getRank() == Rank.ACE && dealtCard.getSuit() == Suit.SPADES){
                        turnManager.setCurrentPlayerIndex(i);
                    }
                   turnManager.getPlayers().get(i).receiveCard(dealtCard);
               }
               
            }
        }
    }


    @Override
    public void declareWinner() {
         Player winner = turnManager.getWinnerList().get(0); // Assuming first player in winner list is the winner


        System.out.println("===========================");
        System.out.println("🎉 The winner of the game is: **" + winner.getName() + "**");
        System.out.println("===========================\n");
    }
    
   @Override
    public void declareLoser() {

        Player loser = turnManager.getPlayers().get(0); // Assuming first player in list is the loser
        System.out.println("===========================");
        System.out.println("💥 The loser of the game is: **" + loser.getName() + "**");
        System.out.println("===========================\n");

    }
}
