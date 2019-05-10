package fi.zudoku.hydraulic.domain.generic;

public class LinkedListNode<T> {
    
    private final T value;
    private LinkedListNode<T> next = null;
    
    public LinkedListNode(T value){
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
