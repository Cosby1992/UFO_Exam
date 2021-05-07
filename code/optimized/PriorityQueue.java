package optimized;

public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {

    private T[] queue;
    private int pointer = 0;

    // Constructor
    public PriorityQueue(int size) {
        queue = (T[]) new Comparable[size];
    }
    
    @Override
    public void enqueue(T node) {
        // If the queue is empty, add the element on index 0 and increment the pointer
        if(pointer == 0){
            queue[pointer++] = node; 
            return;
        } 

        // if the node is bigger than or equals the last node, insert in the back of the queue
        if(queue[pointer-1].compareTo(node) <= 0){
            queue[pointer++] = node; 
            return;
        } 

        // insert the node using the bubble sort technique
        queue[pointer++] = node;
        bubbleInsert(node);
        return;

    }

    private void bubbleInsert(T node){

        for (int i = pointer-2; i > -1; i--) {
            if(queue[i].compareTo(node) > 0){
                swab(i, i+1);
            } else return;
        }

    }

    private void swab(int index1, int index2){
        T temp = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = temp;
    }



    @Override
    public T dequeue() {
        
        T temp = queue[0];

        // TODO: Move queue to front and decrement pointer

        return temp;
    }

    @Override
    public T retrieve(T node) {
        
        // this method uses binary search to find the element your are looking for 
        return binarySearch(queue, 0, queue.length, node);

    }

    private T binarySearch(T[] array, int l, int r, T node){

        int m = (l+r)/2;
        if(l < r){
            if(queue[m].compareTo(node) > 0){
                return binarySearch(array, l, m, node);
            } else if(queue[m].compareTo(node) < 0){
                return binarySearch(array, m+1, r, node);
            } else {
                return queue[m];
            }
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    @Override
    public String toString() {
        
        StringBuilder builder = new StringBuilder();
        builder.append("Queue [");
        for (T t : queue) {
            builder.append(t);
            builder.append(" ");
        }
        builder.append("]\n");
        
        return builder.toString();
    }


    
}
