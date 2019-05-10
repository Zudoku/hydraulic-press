package fi.zudoku.hydraulic.domain.generic;

public class LinkedListNode<T> {
    
    private final T value;
    private LinkedListNode<T> next = null;
    
    /**
     * A node in the linked list.
     * @param value value this node contains
     */
    public LinkedListNode(T value) {
        this.value = value;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
