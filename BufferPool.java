/**
 * 
 */

/**
 * @author shaha
 *
 */
public interface BufferPool {

    /**
     * 
     * @param block
     * @return
     */
    public Buffer acquireBuffer(int block);


    /**
     * 
     * @return
     */
    public int size();


    /**
     * 
     */
    public void flush();
}
