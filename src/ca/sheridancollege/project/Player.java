package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public abstract class Player {
    
    private String name;
    private int playerID;
    protected List<Card> hand;
    private boolean isComputer;
    
    public Player(String name, boolean isComputer, int id) {
        this.name = name;
        this.isComputer = isComputer;
        this.playerID = id;
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
    
    public int getIndexOfCard(Suit suit, Rank rank) {
        return IntStream.range(0, hand.size())
                    .filter(i -> hand.get(i).getSuit() == suit && hand.get(i).getRank() == rank)
                    .findFirst()
                    .orElse(-1);
    }
    
    public List<Card> getCardsBySuit(Suit suit) {
        if(suit == null) {
            return hand;
        } else {
               return hand.stream()
               .filter(card -> card.getSuit() == suit)
               .collect(Collectors.toList());
        }
     
    }

    public Card playAceOfSpades() {
        Card aceOfSpades = hand.get(getIndexOfCard(Suit.SPADES, Rank.ACE));
        hand.remove(getIndexOfCard(Suit.SPADES, Rank.ACE));
        return aceOfSpades;
    }
    
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public abstract boolean playTurn(TurnManager turnManager, InputHandler inputHandler, List<Map<Player, Card>> allRounds);
}
