/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.List;
/**
 *
 * @author Shehbaz Singh
 */
public class TurnManager {

    private List<Player> players;
    private int currentPlayerIndex = 0;

    public TurnManager(List<Player> players) {
        this.players = players;
    }

    public Player getNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return players.get(currentPlayerIndex);
    }
}