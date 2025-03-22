package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

    private ArrayList<Card> cards;

    public DeckOfCards() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                if (!(suit == Suit.DIAMONDS && rank == Rank.TWO)) { // Removing 2 of Diamonds
                    cards.add(new Card(suit, rank));
                }
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
