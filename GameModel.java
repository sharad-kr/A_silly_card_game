import java.util.ArrayList;
import java.util.Random;

// Represents the game model for a card game.
public class GameModel {
    Queue P1 = new Queue(); // Queue for Player 1's cards
    Queue P2 = new Queue(); // Queue for Player 2's cards
    Stack deal = new Stack(); // Stack for the deal pile
    Stack discard = new Stack(); // Stack for the discard pile
    boolean token = false; // Token to track turn
    

    // Constructor for GameModel, initiates shuffling and distribution of cards, and starts the game
    public GameModel() {
        shuffle_and_distribute();
        start_game();
    }

    // Starts the game loop, alternating turns between players until one wins
    public void start_game() {
        while (!(this.P1.size() == 0 || this.P2.size() == 0)) {
            print_status(); // Prints the current status of the game
            if (this.token) {
                System.out.println("Player 1's turn. ");
                play_turn(this.P1);
                if (this.P1.size() == 0) {
                    System.out.println("Player 1 won!\n");
                }
            } else {
                System.out.println("Player 2's turn. ");
                play_turn(this.P2);
                if (this.P2.size() == 0) {
                    System.out.println("Player 2 won!\n");
                }
            }
        }
        
    }

    // Shuffles the deck and distributes cards to both players
    public void shuffle_and_distribute() {
        int top_card;
        int[] ArrayCards = new int[52]; // Array to hold the cards
        for (int i = 0; i < 52; i++) {
            ArrayCards[i] = (i % 13) + 1; // Initialize cards
        }
        Random num = new Random();
        // Shuffle the cards
        for (int i = ArrayCards.length - 1; i > 0; i--) {
            int j = num.nextInt(i);
            int temp = ArrayCards[i];
            ArrayCards[i] = ArrayCards[j];
            ArrayCards[j] = temp;
        }
        // Push shuffled cards to the deal stack
        for (int i = 0; i < 52; i++) {
            this.deal.push(ArrayCards[i]);
        }
        // Distribute cards to both players
        for (int i = 0; i < 14; i++) {
            top_card = this.deal.pop();
            if (i % 2 == 0) {
                this.P1.push(top_card);
            } else {
                this.P2.push(top_card);
            }
        }
        // Place the top card on the discard pile
        top_card = this.deal.pop();
        this.discard.push(top_card);
    }

    // Prints the current status of both players' hands
    public void print_status() {
        System.out.println("Cards in hand.\n--------------\n");
        Queue temp = new Queue();
        System.out.print("Player 1: ");
        int top_card;
        // Display Player 1's hand
        while (this.P1.size() != 0) {
            top_card = this.P1.pop();
            temp.push(top_card);
            System.out.print(String.format(" %d ", top_card));
        }
        System.out.print("\n");
        // Restore Player 1's hand
        while (temp.size() != 0) {
            this.P1.push(temp.pop());
        }
        // Display Player 2's hand
        System.out.print("Player 2: ");
        while (this.P2.size() != 0) {
            top_card = this.P2.pop();
            temp.push(top_card);
            System.out.print(String.format(" %d ", top_card));
        }
        System.out.print("\n");
        // Restore Player 2's hand
        while (temp.size() != 0) {
            this.P2.push(temp.pop());
        }
        System.out.print("\n");
    }

    // Handles the logic for a player's turn
    public void play_turn(Queue P) {
        int discard_top_card = this.discard.front(); // Top card of the discard pile
        System.out.println(String.format("Top of the stack is %d", discard_top_card));
        int my_top_card = P.pop(); // Player's top card
        

        if (my_top_card > discard_top_card) {
            this.discard.push(my_top_card);
            System.out.println(String.format("Your card %d is larger than %d.", my_top_card, discard_top_card));
            System.out.println("Good job!\n");
        } else if (my_top_card < discard_top_card) {
            System.out.println(String.format("Your card %d is less than %d.", my_top_card, discard_top_card));
            System.out.println("You pick 2 cards.\n");
            // Handle empty or almost empty deal stack
            if (this.deal.size() == 0) {
                discard_top_card = this.discard.pop();
                while (!(this.discard.size() == 0)) {
                    this.deal.push(this.discard.pop());
                }
                this.discard.push(discard_top_card);
                P.push(this.deal.pop());
                P.push(this.deal.pop());
            } else if (this.deal.size() == 1) {
                P.push(this.deal.pop());
                discard_top_card = this.discard.pop();
                while (!(this.discard.size() == 0)) {
                    this.deal.push(this.discard.pop());
                }
                this.discard.push(discard_top_card);
                P.push(this.deal.pop());
            } else {
                P.push(this.deal.pop());
                P.push(this.deal.pop());
            }
            this.discard.push(my_top_card);
        } else {
            System.out.println(String.format("Your card is equal to %d", discard_top_card));
            System.out.println("You pick 1 card.\n");
            if (this.deal.size() == 0) {
                discard_top_card = this.discard.pop();
                while (!(this.discard.size() == 0)) {
                    this.deal.push(this.discard.pop());
                }
                this.discard.push(discard_top_card);
            }
            this.discard.push(my_top_card);
            P.push(this.deal.pop());
        }
        this.token = !this.token; // Toggle the turn token
    }
}
