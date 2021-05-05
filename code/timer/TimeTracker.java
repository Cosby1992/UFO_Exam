package timer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TimeTracker {
    
    private long[] times;
    private int timePointer;
    private long[] warmupTimes;
    private int warmupTimePointer;

    public TimeTracker(int warmupIterations, int iterations){
        times = new long[iterations];
        warmupTimes = new long[warmupIterations];
    }

    public void addTime(long time){
        times[timePointer++] = time;
    }

    public void addWarmupTime(long time){
        warmupTimes[warmupTimePointer++] = time;
    }

    public void writeToCSV(String path) throws IOException{
        FileWriter writer = new FileWriter(path);
        BufferedWriter out = new BufferedWriter(writer);

        out.append("times");

        for (long l : times) {
            out.append("\n");
            out.append(String.valueOf(l));
        }

        out.flush();
        out.close();
    }


}
