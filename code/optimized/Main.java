package optimized;

public class Main {

    public static void main(String[] args) {
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(10);

        int[] nodes = {30,35,34,12,100,23498,0,5151,51,50};

        for (int node : nodes) {
            queue.enqueue(node);
        }

        System.out.println(queue);

        System.out.println(queue.retrieve(23498));


    }
    
}
