/**
 * Queue Generic class implementation
 * 
 * @author Eric Liu
 * @version March 14, 2018 (Modified March 24, 2018)
 */
public class Queue<Item>  {
    
    private Node first, last; // Only need two private field variables!!!
    private int N = 0;
    
    private class Node { // Defines the class for the nodes in the linkedlist
        private Item item; // Use Generics
        private Node next; // Need a pointer to the next node
    }
    
    public boolean isEmpty() { // Returns whether or not the queue is empty
        return first == null;
    }
    
    public void enqueue (Item item) { // Enqueues/pushes a new Generic item into the queue
        Node prevlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else prevlast.next = last;
        N++;
    }
    
    public Item dequeue () { // Dequeues/pops a new Generic item from the queue
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null; // IMPORTANT
        N--;
        return item;
    }

    public int size () {
        return this.N;
    }
}
