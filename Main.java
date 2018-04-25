import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Jake McKenzie
 * TODO: Complete main
 */

public class Main {

    /**
     * @param WarAndPeace- This was the required text for compression. It is
     * a long text and showcases the compression technique well. I consider compression
     * of this text to be the baseline to meet. If I can hit this then I will test on
     * progressively harder texts to compress and decompress.
     */

    private static final String WarAndPeace = "WarAndPeace.txt";

    /**
     * @param TheStoryOfGostaBerling - This was written by the first women nobel
     * laureate. It is a swedish novel translated into english and should allow
     * for more range of ASCII characters to test from. This is more modern swedish
     * that has been translated to english so it should be harder to compress and decompress.
     */

    private static final String TheStoryOfGostaBerling = "TheStoryOfGostaBerling.txt";

    /**
     * @param EpicOfGilgamesh This is an epic from ancient mesopotamia that is a
     * story of the unbreakable friendship of Gilgamesh and Enkidu. This 
     * text includes tablets with weird spacing mostly translated into ond english.
     * It offers different challenges in testing to The Story of Gosta Berling.
     * It does not have as large a range of ASCII characters but it does offer 
     * spacing constraints. This text is shorter than War and Peace and allows 
     * to be ran more often in testing. 
     * */

    private static final String EpicOfGilgamesh = "EpicOfGilgamesh.txt";

    /**
     * @param OsMaias A portuguese satirical comedy. This book is in the original
     * portuguese, as such it offers a wide range of possible ASCII characters 
     * to be compressed. This should be, theoretically, one of the hardger texts 
     * to compress due to it being in portuguese. This text is nearly as long as War 
     * and Peace but due to it being written in a romantic language is quite varied
     * despite that length.
     */

    private static final String OsMaias = "OsMaias.txt";

    /**
     * @param TheStoryOfTheVolsungs an icelandic saga that inspired Tolkein and Andrew 
     * Lang. This book was translated fairly recently into english and was 
     * included due to the songs included. I'm using this mostly to test for spacing
     * and add variety to the testing as it uses a wide array of ASCII and 
     * includes a lot of spacing. 
     */

    private static final String TheStoryOfTheVolsungs = "TheStoryOfTheVolsungs.txt";

    /**
     * @param codes an output file for the codes
     */
    private static final String codes = "codes.txt";

    public static void main(String[] args) throws IOException{
        //https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
        //http://www.adam-bien.com/roller/abien/entry/java_8_reading_a_file
        //getBytes("UTF-8")
        String message = new String(Files.readAllBytes(Paths.get(WarAndPeace)));
        System.out.println(message);
    }
    /**
     * TODO: parse txt file
     */
    /**
     * TODO: output binary file
     */
    /**
     * TODO: decode binary file
     */
    /**
     * TODO: output txt file
     */
    /**
     * TODO: write test for coding tree
     */
    /**
     * TODO: write test for MyPriorityQueue
     */
    /**
     * TODO: write unit-test for full file
     */

}