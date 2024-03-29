import java.io.IOException;

/**
 * { your description of the project here }
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * The class containing the main method, the entry point of the application.
 * 
 * @author { your name here }
 * @version { put something here }
 */
public class VirtualSort {

    /**
     * This method is used to generate a file of a certain size, containing a
     * specified number of records.
     *
     * @param filename
     *            the name of the file to create/write to
     * @param blockSize
     *            the size of the file to generate
     * @param format
     *            the format of file to create
     * @throws IOException
     *             throw if the file is not open and proper
     */
    public static void generateFile(
        String filename,
        String blockSize,
        char format)
        throws IOException {
        FileGenerator generator = new FileGenerator();
        String[] inputs = new String[3];
        inputs[0] = "-" + format;
        inputs[1] = filename;
        inputs[2] = blockSize;
        generator.generateFile(inputs);
    }


    /**
     * The entry point of the application
     * 
     * @param args
     */
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Hello, World");
        }
        else {
            String dataFile = args[0];
            String statFile = args[2];
            int numBuffers = Integer.parseInt(args[1]);

            if (numBuffers > 1 && numBuffers < 20) {
                DataArray array = new DataArray(dataFile, numBuffers);
                VSort quick = new VSort(array);
                Statistics stat = new Statistics(dataFile, statFile);

                stat.setStartTime(System.currentTimeMillis());
                quick.sort();
                stat.setEndTime(System.currentTimeMillis());

                array.flush();
                stat.writeToFile();
            }
        }
    }
}
