/**
 * Stack Generic class implementation
 * 
 * @author Eric Liu
 * @version March 14, 2018
 */
public class Stack<Item> {
    private Node first; // Only need one private field variable
    private int this_size = 0; // Defines the size

    private class Node { // Node class for Linked List
        Item item;
        Node next;
    }

    public boolean isEmpty () { // Is the stack empty?
        return first == null;
    }

    public void push (Item item) { // Push a new Generic item onto the stack
        Node second = first;
        first = new Node();
        first.item = item;
        first.next = second;
        this_size++;
    }

    public Item pop () { // Pop a new Generic item from the stack
        Item item = first.item;
        first = first.next;
        this_size--;
        return item;
    }

    public Item peek () { // Get the topmost item from the stack without popping
        return first.item;
    }

    public int size () { // Returns the size of the stack
        return this_size;
    }
}