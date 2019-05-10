package fi.zudoku.hydraulic.domain.generic;

/**
 * Custom Linked list implementation.
 * @param <T> class that this list contains
 */
public class LinkedList<T> {
    
    private LinkedListNode<T> first;
    private LinkedListNode<T> last;
    private int size = 0;

    /**
     * Adds an element to the end of the list.
     * @param value to add
     */
    public void add(T value) {
        LinkedListNode<T> lastNode = last;
        LinkedListNode<T> newLast = new LinkedListNode<>(value);
        if (lastNode == null) {
            first = newLast;
            last = newLast;
        } else {
            lastNode.setNext(newLast);
            last = newLast;
        }
        size++;
    }
    
    /**
     * How many elements there are in the list.
     * @return size
     */
    public int size() {
        return size;
    }
    
    /**
     * Finds the element in the given index, or null.
     * @param index 0-(size() -1)
     * @return element in the given index or null
     */
    public T get(int index) {
        int currentIndex = index;
        LinkedListNode<T> result = first;
        
        // Traverse the nodes until null, or index is correct.
        while (true) {
            if (currentIndex == 0 || result == null) {
                break;
            } else {
                result = result.getNext();
                currentIndex = currentIndex - 1;
            }
        }
        
        return result != null ? result.getValue() : null;
    }
}
