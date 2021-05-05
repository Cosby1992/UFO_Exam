package timer;

public class Timer {

    private long start;

    public void start() {
        start = System.nanoTime();
    }

    public long step() {
        return System.nanoTime() - start;
    }
    
}
