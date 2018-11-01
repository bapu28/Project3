import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 */

/**
 * @author shaha
 *
 */
public class LRUBufferPool implements BufferPool {

    private RandomAccessFile disk;
    private PriorityQueue<Buffer> priorityQueue;
    private int numBuffers;
    private int blockSize;
    private Buffer[] bufferArray;


    /**
     * 
     * @param file
     * @param numBuffers
     * @param blockSize
     * @throws IOException
     */
    public LRUBufferPool(File file, int numBuffers, int blockSize)
        throws IOException {
        disk = new RandomAccessFile(file, "rw");
        this.numBuffers = (int)(disk.length() / blockSize);
        this.blockSize = blockSize;
        bufferArray = new Buffer[numBuffers];
        priorityQueue = new PriorityQueue<Buffer>(numBuffers);
    }


    /**
     * 
     * @param buffer
     */
    public void insertBuffer(Buffer buffer) {
        Buffer temp = priorityQueue.insert(buffer);
        if (temp != null) {
            temp.flush();
        }
    }


    /**
     * 
     */
    @Override
    public Buffer acquireBuffer(int block) {
        if (bufferArray[block] == null) {
            bufferArray[block] = createNewBuffer(block);
        }
        return bufferArray[block];
    }


    /**
     * 
     */
    @Override
    public int size() {
        return numBuffers;
    }


    /**
     * 
     */
    @Override
    public void flush() {
        for (int i = 0; i < numBuffers; i++) {
            bufferArray[i].flush();
        }
    }


    /**
     * 
     * @param index
     * @return
     */
    private Buffer createNewBuffer(int index) {
        int offset = index * blockSize;
        return new LRUBuffer(disk, this, blockSize, offset);
    }

}
