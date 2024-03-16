import java.util.*;

// Class definition for the SillyCardGame
public class SillyCardGame{
    // Main method where the execution of the program begins
    public static void main(String[] args){
        boolean game_over = false; // A flag to control the game loop
        
       
        System.out.print("Welcome to Silly Little Games' new Card Game!\n\nGood luck!!!!\n\n");
        
        // Create a Scanner object to read input from the console
        Scanner input = new Scanner(System.in);
        
        // The game loop runs as long as game_over is false
        while(!game_over){
            // Create a new game model instance for each game iteration
            GameModel game = new GameModel();
            
            // Prompt the user to decide whether to play again
            System.out.printf("Do you want to play again? (y/n): ");
            
            // Read the user's response and convert it to lowercase to make the check case-insensitive
            String response = input.next().toLowerCase();
            
            // Get the first character of the user's response to simplify the decision-making process
            char verdict = response.charAt(0);
            
            // Check if the user's decision is not to play again
            if(verdict == 'n'){
                game_over = true; // Update the game_over flag to exit the loop
                
                
                System.out.println("\nThanks for playing the Card Game!\n");
            }
            
        }
    }
}
