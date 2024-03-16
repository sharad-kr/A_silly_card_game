// Class representing an element in the stack, specifically designed for storing a card's value.
class ElementInStack {
    int cardValue;
    ElementInStack next;

    // Constructor to initialize the card value of a stack element and set the next element to null.
    public ElementInStack(int cardValue) {
        this.cardValue = cardValue;
        this.next = null;
    }
}

// Class representing a stack data structure for storing elements in a last-in-first-out (LIFO) manner.
public class Stack {
    ElementInStack head;
    int size;

    // Constructor to create an empty stack with no elements.
    public Stack() {
        this.head = null;
        this.size = 0;
    }

    // Method to add an element with the specified card value to the top of the stack.
    public void push(int cardValue) {
        ElementInStack newCard = new ElementInStack(cardValue);
        if (this.size == 0) {
            this.head = newCard;
        } else {
            newCard.next = this.head;
            this.head = newCard;
        }
        this.size++;
    }

    // Method to remove and return the card value of the element at the top of the stack.
    // Returns -1 if the stack is empty.
    public int pop() {
        if (this.size == 0) {
            return -1;
        }
        int topCard = this.head.cardValue;
        this.head = this.head.next;
        this.size--;
        return topCard;
    }

    // Method to return the card value of the element at the top of the stack without removing it.
    // Returns -1 if the stack is empty.
    public int front() {
        if (this.size == 0) {
            return -1;
        }
        return this.head.cardValue;
    }

    // Method to return the number of elements currently in the stack.
    public int size() {
        return this.size;
    }
}
