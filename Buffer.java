/**
 * 
 */

/**
 * @author shaha
 *
 */
public interface Buffer {

    /**
     * 
     * @return
     */
    public byte[] read();


    /**
     * 
     * @param data
     */
    public void write(byte[] data);


    /**
     * 
     * @return
     */
    public int getSize();


    /**
     * 
     */
    public void flush();
}
