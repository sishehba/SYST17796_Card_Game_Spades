/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Scanner;
/**
 *
 * @author Shehbaz Singh
 */
public class HumanPlayer extends Player {
    
    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name) {
        super(name, false);
    }

    @Override
    public Card playTurn(Suit currentSuit) {
        System.out.println(getName() + ", it's your turn! Your hand: " + getHand());
        System.out.print("Select a card index to play: ");
        int choice = scanner.nextInt();
        
        if (choice >= 0 && choice < getHand().size()) {
            return getHand().remove(choice);
        }
        
        System.out.println("Invalid choice, try again.");
        return playTurn(currentSuit);
    }
}
