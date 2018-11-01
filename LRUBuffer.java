import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 */

/**
 * @author shaha
 *
 */
public class LRUBuffer implements Buffer {

    private LRUBufferPool pool;
    private RandomAccessFile disk;
    private int offset;
    private int size;
    private boolean isDirty;
    private boolean isLoaded;
    private byte[] byteArray;


    public LRUBuffer(
        RandomAccessFile disk,
        LRUBufferPool pool,
        int size,
        int offset) {
        this.pool = pool;
        this.disk = disk;
        this.size = size;
        this.offset = offset;
        this.isDirty = false;
        this.isLoaded = false;
    }


    /**
     * 
     * @return
     */
    @Override
    public byte[] read() {
        pool.insertBuffer(this);

        if (!isLoaded) {
            Statistics.cacheMisses++;
            this.readToDisk();
        }
        else {
            Statistics.cacheHits++;
        }

        return byteArray;
    }


    /**
     * 
     * @param data
     */
    @Override
    public void write(byte[] data) {
        this.byteArray = data;
        pool.insertBuffer(this);
        isLoaded = true;
        isDirty = true;
    }


    /**
     * 
     * @return
     */
    @Override
    public int getSize() {
        return this.size;
    }


    /*
     * 
     */
    @Override
    public void flush() {
        if (isDirty) {
            this.writeToDisk();
        }

        this.byteArray = null;
        isLoaded = false;
    }


    /**
     * 
     */
    private void readToDisk() {
        this.byteArray = new byte[size];

        try {
            disk.seek(offset);
            disk.read(byteArray);
            Statistics.diskReads++;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        isLoaded = true;
        isDirty = false;
    }


    /**
     * 
     */
    private void writeToDisk() {
        try {
            disk.seek(offset);
            disk.write(byteArray);
            Statistics.diskWrites++;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        isDirty = false;
    }

}
