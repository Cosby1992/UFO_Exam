package optimized;

import java.io.IOException;

import timer.TimeTracker;
import timer.Timer;

public class Benchmark {
    
    private static final int WARMUP_ITERATIONS = 200;
    private static final int ITERATIONS = 5000;
    private static final int QUEUE_LENGTH = 500_000;

    public static void main(String[] args) {
        TimeTracker tracker = new TimeTracker(WARMUP_ITERATIONS, ITERATIONS);

        benchmarkPriorityQueue(WARMUP_ITERATIONS, ITERATIONS, tracker);

        System.out.println("Benchmark done.. ");
        System.out.println("Writing results to file");
        try {
            tracker.writeToCSV("C:\\code\\Exploration and Presentation - Exam Task\\code\\data_output\\optimized_times.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done... ");

    }

    private static void benchmarkPriorityQueue(int warmupIterations, int iterations, TimeTracker tracker) {

        System.out.println("Initializing Benchmark...");
        System.out.println("Warmup iterations: " + warmupIterations);
        System.out.println("       Iterations: " + iterations);

        // warmup
        for (int i = 0; i < warmupIterations; i++) {
            tracker.addWarmupTime(pQueueRun());
        }

        System.out.println("Warmup finished - moving to real benchmark");

        // warmup
        for (int i = 0; i < iterations; i++) {
            tracker.addTime(pQueueRun());
        }

    }

    private static long pQueueRun() {

        PriorityQueue<Node> queue = new PriorityQueue<>(QUEUE_LENGTH);

        // Filling the queue with Nodes
        for (int i = 0; i < QUEUE_LENGTH; i++) {
            queue.enqueue(new Node(i, i + 1, i));
        }

        Timer timer = new Timer();
        long time = 0L;
        
        timer.start();
        Node n = queue.retrieve(new Node(500, 1000, QUEUE_LENGTH-1));
        n.verticeTo = 101;
        time = timer.step();

        return time;

    }


    private static class Node implements Comparable<Node> {

        int verticeFrom;
        int verticeTo;
        int weight;

        public Node(int verticeFrom, int verticeTo, int weight) {
            this.verticeFrom = verticeFrom;
            this.verticeTo = verticeTo;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Vertice from: ");
            builder.append(verticeFrom).append("\n");
            builder.append("Vertice to  : ");
            builder.append(verticeTo).append("\n");
            builder.append("Weight      : ");
            builder.append(weight).append("\n");

            return builder.toString();
        }

    }

}
