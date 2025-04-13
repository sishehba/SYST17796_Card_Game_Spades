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
public class InputHandler {
    private Scanner scanner;
    
    public InputHandler ()
    {
        scanner = new Scanner(System.in);
    }   
    
    
    public Object handleInput(InputType type, boolean isRequired, int minValue, int maxValue, int minLength, int maxLength) {
        String inputString = scanner.nextLine().trim();

        if (inputString.equalsIgnoreCase("exit")) {
            System.out.println("🔙 Exiting and returning to main menu...");
            return new InputResultHandler(-1, "exit");
        }

        if (isRequired && inputString.isEmpty()) {
            System.out.println("Input is required. Please try again..");
            return null;
        }

        // STRING-type input validation
        if (type == InputType.STRING) {
            if (inputString.length() < minLength || inputString.length() > maxLength) {
                if (minLength == maxLength) {
                    System.out.println("⚠️ Input must be exactly " + minLength + " characters long.");
                    
                } else {
                    System.out.println("⚠️ Input length must be between " + minLength + " and " + maxLength + " characters.");
                
                }
                return null;
            }

            return new InputResultHandler(-1, inputString);
        }

        // INTEGER-type input validation
        if (type == InputType.INTEGER) {
            try {
                int number = Integer.parseInt(inputString);

                // Check digit length
                int digitLength = String.valueOf(Math.abs(number)).length();
                if (digitLength < minLength || digitLength > maxLength) {
                    System.out.println("Number must have between " + minLength + " and " + maxLength + " digits.");
                    return null;
                }

                if (number < minValue || number > maxValue) {
                    System.out.println("Number must be between " + minValue + " and " + maxValue + ".");
                    return null;
                }

                return new InputResultHandler(number, "");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter digits only.");
                return null;
            }
        }

        System.out.println("Unsupported input type.");
        return null;
    }

    
}
