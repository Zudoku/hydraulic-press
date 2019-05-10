package fi.zudoku.hydraulic.domain.generic;

public class LinkedList<T> {
    
    private LinkedListNode<T> first;
    private LinkedListNode<T> last;
    private int size = 0;

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
    
    public int size() {
        return size;
    }
    
    public T get(int index) {
        int currentIndex = index;
        LinkedListNode<T> result = first;
        
        while(true) {
            if(currentIndex == 0 || result == null) {
                break;
            } else {
                result = result.getNext();
                currentIndex = currentIndex - 1;
            }
        }
        
        return result != null ? result.getValue() : null;
    }
}
