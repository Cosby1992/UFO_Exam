package original;

import java.util.PriorityQueue;

/**
 * This class demonstrates a minimum example of 
 * the Java PriorityQueue Class.
 */
public class Main {

    public static void main(String[] args) {
        
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(10);

        int[] queueItems = {1,7,3,0,8,34,76,54,12,89};

        // Filling the queue
        for (int i = 0; i < 10; i++) {
            pQueue.add(queueItems[i]);
        }

        // Getting the values from the queue with lowest value
        // (highest priority) value in the top.
        while(!pQueue.isEmpty()){
            System.out.println(pQueue.poll());
        }

    }

}