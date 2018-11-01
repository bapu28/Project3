import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Akshat Shah (akshat98)
 *
 */
public class Statistics {

    public static int cacheHits = 0;
    public static int cacheMisses = 0;
    public static int diskReads = 0;
    public static int diskWrites = 0;
    public static int swaps = 0;
    private long startTime = 0;
    private long endTime = 0;
    private String dataFileName;
    private String statFileName;


    public Statistics(String dataFile, String statFile) {
        this.dataFileName = dataFile;
        this.statFileName = statFile;
    }


    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    /**
     * 
     * @return
     */
    public String getFormattedOutput() {
        StringBuilder str = new StringBuilder();

        str.append("Sort on: " + dataFileName + "\n");
        str.append("Cache Hits: " + cacheHits + "\n");
        str.append("Cache Misses: " + cacheMisses + "\n");
        str.append("Disk Reads: " + diskReads + "\n");
        str.append("Disk Writes: " + diskWrites + "\n");
        str.append("Num Swaps: " + swaps + "\n");
        str.append("Time is: " + (endTime - startTime) + "\n");

        return str.toString();
    }


    public void writeToFile() {
        File resultFile = new File(statFileName);

        try {
            resultFile.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(
                resultFile, true));
            output.write(this.getFormattedOutput());
            output.flush();
            output.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
