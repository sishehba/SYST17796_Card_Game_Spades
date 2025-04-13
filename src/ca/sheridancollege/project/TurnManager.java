/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Shehbaz Singh
 */
public class TurnManager {

    private int turnCount = 0;
    private final ArrayList<Player> players;
    private final List<Map<Player, Card>> allRounds;
    private int currentPlayerIndex = -1;
    private Card lastCardPlayed;
    private Suit currentTurnRoundSuit;
    private Player previousRoundLoser;
    private Map<Player, Card> currentRoundPlays = new HashMap<>();
    private ArrayList<Player> winnerList = new ArrayList<>();
    
    public TurnManager(List<Map<Player, Card>> allRounds, ArrayList<Player> players){
        this.allRounds = allRounds;
        this.players = players;
    }
    public Player getCurrentPlayer() {
  
        try {
            return this.players.get(getCurrentPlayerIndex());
        } catch(Exception e) {
//            System.out.println(e);
        }
        return this.players.get(0);
    }
   
    public ArrayList<Player> getPlayers() {
        return this.players;
    }
    
    public void playFirstTurn() {

        System.out.println("🔔 " + getCurrentPlayer().getName() + " has the ♠ Ace of Spades and will start the game.");
        Card playedCard = getCurrentPlayer().playAceOfSpades();

        setCurrentTurnRoundSuit(Suit.SPADES);
        System.out.println(getCurrentPlayer().getName() + " played: " + playedCard);
        addToCurrentRoundPlays(getCurrentPlayer(), playedCard);
        increaseTurnCount();

    }
    
    public Map<Player, Card> getCurrentRound() {
        return currentRoundPlays;
    }
    
    public int addToWinnerList(Player player){
        winnerList.add(player);
        this.players.remove(player);
        System.out.println("Only " + this.players.size() + " Players left");
        setCurrentPlayerIndex(currentRoundLoser().getPlayerID());
        return player.getPlayerID();
    }
    
    public ArrayList<Player> getWinnerList(){
        return winnerList;
    }
    
    public void setWinnerList(ArrayList<Player> winnerList){
        this.winnerList = winnerList;
    }
    
    public Player getPreviousRoundLoser() {
        return previousRoundLoser;
    }
    
    public void setPreviousRoundLoser(Player previousRoundLoser) {
        this.previousRoundLoser = previousRoundLoser;
    }

    
    public void addToCurrentRoundPlays(Player player, Card card) {
        currentRoundPlays.put(player, card);
        lastCardPlayed = card;
    }

    public void resetCurrentRound() {
        currentRoundPlays = new HashMap<>();
        setCurrentTurnRoundSuit();
    }
    
    public ArrayList<Card> getCurrentRoundCards() {
        return new ArrayList<>(currentRoundPlays.values());
    }
    
    public Player currentRoundLoser() {

        return currentRoundPlays.entrySet()
            .stream()
            .max(Comparator.comparing(entry -> entry.getValue().getRank().getValue()))
            .map(Map.Entry::getKey)
            .orElse(null);

    }
    
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
    
    public Suit getCurrentTurnRoundSuit() {
        return currentTurnRoundSuit;
    }
    
    public void setCurrentTurnRoundSuit() {
        this.currentTurnRoundSuit = null;
    }
    
    public void setCurrentTurnRoundSuit(Suit suit) {
        this.currentTurnRoundSuit = suit;
    }
    
    
    public void changeTurn() {
        if(this.currentPlayerIndex < players.size() - 1){
            this.currentPlayerIndex++;
        } else {
            this.currentPlayerIndex = 0;
        }
    }

    public int getTurnCount() {
        return turnCount;
    }
    
    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }
    
    public void checkTurnCount() {
        if(this.turnCount == this.players.size()) {
        allRounds.add(getCurrentRound());
        changeTurn(currentRoundLoser().getPlayerID());

            printPreviousRoundStats();
            resetCurrentRound();
            setTurnCount(0);
        } else {
            changeTurn();
            setCurrentTurnRoundSuit(lastCardPlayed.getSuit());
        }
        if(!getCurrentPlayer().hasCards()) {
            System.out.println("\n*******************");
            System.out.println("\n" + getCurrentPlayer().getName() + " has no cards left in hand.");
            System.out.println("🏁 " + getCurrentPlayer().getName() + " has finished and is added to the Winners List!");
            System.out.println("\n*******************");
            addToWinnerList(getCurrentPlayer());
            
        }
    }
    public void increaseTurnCount() {
        this.turnCount++;
       
        checkTurnCount();
       
    }
    
    public void printPreviousRoundStats() {
        System.out.println("\n=========== Previous Round Summary ===========");

        Player loser = currentRoundLoser();
        if (loser != null) {
            System.out.println("Loser of the Round: " + loser.getName());
        } else {
            System.out.println("No valid plays in the round.");
            return;
        }

        System.out.println("----------------------------------------------");
        System.out.println("Plays This Round:");
        getCurrentRound().forEach((player, card) -> {
            System.out.printf("➤ %-20s played   %-16s%n", player.getName(), card);
        });

        System.out.println("==============================================\n");
    }

    
    public void changeTurn(int currentLoserPlayerIndex) {
        setCurrentPlayerIndex(currentLoserPlayerIndex - 1);
    }
}