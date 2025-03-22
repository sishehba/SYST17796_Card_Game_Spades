package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    
    private String name;
    private List<Card> hand;
    private boolean isComputer;
    
    public Player(String name, boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
        this.hand = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Card> getHand() { return hand; }
    public boolean isComputer() { return isComputer; }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public abstract Card playTurn(Suit currentSuit);
}
