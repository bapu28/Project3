import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 
 */

/**
 * @author shaha
 *
 */
public class DataArray {

    private BufferPool bufferPool;
    private final int BLOCK_SIZE = 4096;
    private final int RECORD_SIZE = 4;


    /**
     * 
     * @param fileName
     * @param numBuffers
     * @throws IOException
     */
    public DataArray(String fileName, int numBuffers) {
        File file = new File(fileName);
        try {
            bufferPool = new LRUBufferPool(file, numBuffers, BLOCK_SIZE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 
     * @param index
     * @return
     */
    public short getKey(int index) {
        int offset = (index * RECORD_SIZE) % BLOCK_SIZE;
        int arrayIndex = (index * RECORD_SIZE) / BLOCK_SIZE;

        byte[] bufferByteArray = bufferPool.acquireBuffer(arrayIndex).read();
        short value = ByteBuffer.wrap(bufferByteArray).getShort(offset);

        return value;
    }


    public void swap(int index1, int index2) {
        int offset1 = (index1 * RECORD_SIZE) % BLOCK_SIZE;
        int offset2 = (index2 * RECORD_SIZE) % BLOCK_SIZE;
        int arrayIndex1 = (index1 * RECORD_SIZE) / BLOCK_SIZE;
        int arrayIndex2 = (index2 * RECORD_SIZE) / BLOCK_SIZE;

        byte[] rec1 = new byte[RECORD_SIZE];
        byte[] rec2 = new byte[RECORD_SIZE];

        Buffer buf1 = bufferPool.acquireBuffer(arrayIndex1);
        Buffer buf2 = bufferPool.acquireBuffer(arrayIndex2);

        byte[] buffer;
        buffer = buf1.read();
        System.arraycopy(buffer, offset1, rec1, 0, RECORD_SIZE);

        buffer = buf2.read();
        System.arraycopy(buffer, offset2, rec2, 0, RECORD_SIZE);

        System.arraycopy(rec1, 0, buffer, offset2, RECORD_SIZE);
        buf2.write(buffer);

        buffer = buf1.read();
        System.arraycopy(rec2, 0, buffer, offset1, RECORD_SIZE);
        buf1.write(buffer);

        Statistics.swaps++;
    }


    /**
     * 
     * @return
     */
    public int size() {
        return bufferPool.size() * (BLOCK_SIZE / RECORD_SIZE);
    }


    /**
     * 
     */
    public void flush() {
        bufferPool.flush();
    }

}
