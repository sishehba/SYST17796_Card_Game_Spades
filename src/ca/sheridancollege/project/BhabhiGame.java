/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Shehbaz Singh
 */
public class BhabhiGame extends Game {

    private DeckOfCards deck;
    private TurnManager turnManager;
    private Suit currentSuit;
    private List<Card> roundCards;
    
    public BhabhiGame() {
        super("Bhabhi Card Game");
        deck = new DeckOfCards();
        roundCards = new ArrayList<>();
    }

    @Override
    public void play() {
        //
    }

    private void distributeCards() {
        //
    }

    private void determineFirstPlayer() {
        //
    }

    private void playRound() {
        //
    }

    private boolean isGameOver() {
        return true;
    }

    @Override
    public void declareWinner() {
        //
    }
}
