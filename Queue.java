// Class representing an element in the queue, specifically designed for storing a card's value.
class ElementInQueue {
    int cardValue;
    ElementInQueue next;

    // Constructor to initialize the card value of a queue element and set the next element to null.
    public ElementInQueue(int cardValue) {
        this.cardValue = cardValue;
        this.next = null;
    }
}

// Class representing a queue data structure for storing elements in a first-in-first-out (FIFO) manner.
public class Queue {
    ElementInQueue head;
    ElementInQueue tail;
    int size;

    // Constructor to create an empty queue with no elements.
    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Method to add an element with the specified card value to the end of the queue.
    public void push(int cardValue) {
        ElementInQueue newCard = new ElementInQueue(cardValue);
        if (this.size == 0) {
            this.head = newCard;
            this.tail = newCard;
        } else {
            this.tail.next = newCard;
            this.tail = newCard;
        }
        this.size++;
    }

    // Method to remove and return the card value of the element at the front of the queue.
    // Returns -1 if the queue is empty.
    public int pop() {
        if (this.size == 0) {
            return -1;
        }

        int topCard = this.head.cardValue;
        this.head = this.head.next;
        this.size--;
        if (this.size == 0) {
            this.tail = null;
        }

        return topCard;
    }

    // Method to return the card value of the element at the front of the queue without removing it.
    // Returns -1 if the queue is empty.
    public int front() {
        if (this.size == 0) {
            return -1;
        }
        return this.head.cardValue;
    }

    // Method to return the number of elements currently in the queue.
    public int size() {
        return this.size;
    }
}
