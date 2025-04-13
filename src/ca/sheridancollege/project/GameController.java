/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
import java.util.ArrayList;
/**
 *
 * @author Shehbaz Singh
 */

public class GameController {
    
    public static int takeUserInputForPlayerCount(InputHandler inputHandler) {
        while(true) {
            try {
                System.out.println("\n");
                System.out.println("👉 Enter number of Players. (Select 1 or 2 or 3)");
               
                Object userInput = inputHandler.handleInput(InputType.INTEGER, true, 0,3,1,1); // Read an integer
             
                if (userInput instanceof InputResultHandler) {
                    InputResultHandler res = (InputResultHandler) userInput;
                   
                    if (res.getMessage().equalsIgnoreCase("exit")) {
                        System.out.println("Returning to main menu...");
                        return -1; // special value to signal exit
                    }
                    
                    
                    int countInput = res.getNumber();
                    
                     if(countInput > 0 && countInput < 4){
                  
                     return countInput;
                    } else {
                        System.out.println("⚠️ Please Select from 1 to 3.");
                    }
                }

              
            } catch(Exception e) {
                System.out.println(e);
                System.out.println("Please enter a number only.");
//                scanner.nextLine();
            }
        }  
    }
    

    public static void registerHumanPlayers(InputHandler scanner, int numberOfPlayers, ArrayList<Player> players) {
            System.out.println("\n---------------------------------------");
            System.out.println("Player Registeration.");
            System.out.println("---------------------------------------\n");
        for (int i = 1; i <= numberOfPlayers; i++) {
            while(true) {            
                try {
                    System.out.println("Enter name of Player " + i);
                    Object playerNameInput = scanner.handleInput(InputType.STRING, true, -1, -1, 3, 20 ); 
                    if (playerNameInput == null) {
                        
                        continue;
                    }
                    if (playerNameInput instanceof InputResultHandler) {
                        InputResultHandler res = (InputResultHandler) playerNameInput;
  
                        if (res.getMessage().equalsIgnoreCase("exit")) {
                            System.out.println("Returning to main menu...");
                            return; // special value to signal exit
                        }
                        players.add(new HumanPlayer(res.getMessage(), i));
                        System.out.println("✅ Player " + i + " (" + res.getMessage() + ") registered successfully!");
                     }
                    break;
                } catch(Exception e) {
                    System.out.println("⚠️  Something went wrong. Please try again.");
                    break;
                }
            }
        }
    }
    
    
    public static void registerComputerPlayers(ArrayList<Player> players) {
        System.out.println("\n🧠 Registering Computer Players...\n");
        for (int i = players.size()+1; i <= 3; i++) {
            String computerPlayerName = "Computer " + i;
            players.add(new ComputerPlayer(computerPlayerName, i));
            System.out.println("✅ " + computerPlayerName + " (ID: " + i + ") added.");
        }
        
        System.out.println("\n✅ All Computer Players have been successfully added.\n");
    }
    
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        
      
        boolean exit = false;
        ArrayList<Player> players = new ArrayList<>();
        
        while (!exit) {
            System.out.println("\n=======================================");
            System.out.println("         Welcome to Bhabhi Game");
            System.out.println("=======================================\n");

            System.out.println("============== Main Menu ==============\n");

            if (players.isEmpty()) {
                System.out.println("1. Register Players");
            } else {
                System.out.println("1. Register Players Again");
            }

            System.out.println("2. Start Game");
            System.out.println("3. Exit");

            System.out.println("\n---------------------------------------");
            System.out.println("Tip: Select or type 'exit' anytime to return to the main menu.");
            System.out.println("---------------------------------------\n");

            System.out.print("👉 Enter your choice: ");

        
        Object input = inputHandler.handleInput(InputType.INTEGER, true, 1,3,1,4);

        if (input instanceof InputResultHandler) {
            InputResultHandler res = (InputResultHandler) input;
            switch (res.getNumber()) {
                    case 1:
    //                  Game registeration
                        players = new ArrayList<>();
                        int humanPlayersCount = takeUserInputForPlayerCount(inputHandler);
                        registerHumanPlayers(inputHandler, humanPlayersCount, players);
                        if(humanPlayersCount > 0 && humanPlayersCount < 3
                                && !players.isEmpty()) {
                            registerComputerPlayers(players);
                        }

                        break;
                    case 2:   
                        if(players.size() == 0) {
                            System.out.println("\n");
                            System.out.println("❌ Please Register the players first.");
                            break;
                        }
    //                  Start the game
                        BhabhiGame game = new BhabhiGame(players, inputHandler);   
                        boolean isGameOver = game.play();
                        
                        if (isGameOver) {
                            
//                             We will reach this statement when someone eexit the game or game is over.. so we will clean the players hand
                            players.forEach(player -> {
                                player.hand = new ArrayList<>();
                            });
                            
                            
                            players = new ArrayList<>();
                        }
                      
                        
                        break;

                    case 3:
    //                  Exit game
                        System.out.println("Exiting game. Goodbye!");
                        exit = true;
                        
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
        }
        }
    }
}
