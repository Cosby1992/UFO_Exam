package original;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

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
            tracker.writeToCSV("C:\\code\\Exploration and Presentation - Exam Task\\code\\data_output\\orig_times.csv");
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

        // Creating a comparator for Nodes
        Comparator<Node> nodeComp = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.weight - o2.weight;
            }
        };

        PriorityQueue<Node> pQueue = new PriorityQueue<>(QUEUE_LENGTH, nodeComp);

        // Filling the queue with Nodes
        for (int i = 0; i < QUEUE_LENGTH; i++) {
            pQueue.add(new Node(i, i + 1, i));
        }

        // Find and update a value in the queue
        Iterator<Node> it = pQueue.iterator();

        Timer timer = new Timer();
        long time = 0L;
        int count = 0;
        Node n = null;

        timer.start();
        while (it.hasNext()) {
            n = it.next();
            if (count == QUEUE_LENGTH - 1) {
                n.verticeTo = 80085;
                time = timer.step();
                break;
            }
            count++;
        }

        // System.out.println(n);
        // while(!pQueue.isEmpty()){
        //     System.out.println(pQueue.poll().weight);
        // }

        return time;

    }

    private static class Node {

        int verticeFrom;
        int verticeTo;
        int weight;

        public Node(int verticeFrom, int verticeTo, int weight) {
            this.verticeFrom = verticeFrom;
            this.verticeTo = verticeTo;
            this.weight = weight;
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