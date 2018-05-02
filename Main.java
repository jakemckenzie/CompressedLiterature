import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.text.DecimalFormat;
import java.util.BitSet;
import java.io.PrintStream;
/**
 * @author Jake McKenzie
 */

public class Main {

    /**
     * @param WarAndPeace- This was the required text for compression. It is
     * a long text and showcases the compression technique well. I consider compression
     * of this text to be the baseline to meet. If I can hit this then I will test on
     * progressively harder texts to compress and decompress.
     * 
     * Entropy = 4.527238256888475
     */

    private static final String WarAndPeace = "WarAndPeace.txt";

    /**
     * @param TheStoryOfGostaBerling - This was written by the first women nobel
     * laureate. It is a swedish novel translated into english and should allow
     * for more range of ASCII characters to test from. This is more modern swedish
     * that has been translated to english so it should be harder to compress and decompress.
     * 
     * Entropy = 4.589713178928968
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
     * 
     * Entropy = 4.8726511687233085
     * */

    private static final String EpicOfGilgamesh = "EpicOfGilgamesh.txt";

    /**
     * @param OsMaias A portuguese satirical comedy. This book is in the original
     * portuguese, as such it offers a wide range of possible ASCII characters 
     * to be compressed. This should be, theoretically, one of the hardger texts 
     * to compress due to it being in portuguese. This text is nearly as long as War 
     * and Peace but due to it being written in a romantic language is quite varied
     * despite that length.
     * 
     * Entropy = 4.630160747136568
     */

    private static final String OsMaias = "OsMaias.txt";

    /**
     * @param TheStoryOfTheVolsungs an icelandic saga that inspired Tolkein and Andrew 
     * Lang. This book was translated fairly recently into english and was 
     * included due to the songs included. I'm using this mostly to test for spacing
     * and add variety to the testing as it uses a wide array of ASCII and 
     * includes a lot of spacing. 
     * 
     * Entropy = 4.488574651647057
     * 
     * All of these books happen to be on my current to read pile.
     */

    private static final String TheStoryOfTheVolsungs = "TheStoryOfTheVolsungs.txt";

    /**
     * @param codes an output file for the codes
     */
    private static final String codes = "codes.txt";

    /**
     * @param compressed a compressed output file for the codes
     */
    private static final String compressed = "compressed.txt";

    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();
        //https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
        //http://www.adam-bien.com/roller/abien/entry/java_8_reading_a_file
        //getBytes("UTF-8")
        //File file = new File(WarAndPeace);
        String message = new String(Files.readAllBytes(Paths.get(WarAndPeace)),"UTF-8");
        //String message = new String(Files.readAllBytes(Paths.get(WarAndPeace)));
        //String message = new String(Files.readAllBytes(Paths.get(WarAndPeace)), "US-ASCII");
        //final byte[] message = Files.readAllBytes(Paths.get(WarAndPeace));
        //System.out.println(message);
        //final int[] frequency = new int[256];
        //bytes
        //for (byte b : message) frequency[b & 0xFF]++;
        //int o = 0;
        //System.out.println("\nChars from War and Peace");
        //System.out.println("#|Char|Count|Entropy\n");
        //int q = 0;
        //DecimalFormat df = new DecimalFormat("#.#####");
        //for (int i:frequency) {
        //    if(i != 0) System.out.println((++q) +" | "+ (char)(o++) + " | " + i + " | " + df.format(((double)i / file.length())));
        //}
        //double entropy = 0.0d;
        //double probability;
        //Claude Shannon - The theory of information
        //for (int f:frequency) {
        //    probability = (double)f / file.length();
        //    if (f != 0) entropy -= (probability) * log2(probability);
        //}
            // calculate the next value to sum to previous entropy calculation
        
        
        //PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(256);
        //for (int i = 0; i < 256; i++) {
        //    if(frequency[i] != 0) pq.offer(new HuffmanNode((char)i,frequency[i]));
        //}
        //System.out.println("\nPriority Queue");
        //System.out.println("#|Char|Count\n");
        //int y = 0;
        //while (!pq.isEmpty()) System.out.println((++y)+ " | " + pq.poll());
        //https://courses.cs.washington.edu/courses/csep521/99sp/lectures/lecture11/sld020.htm
        //System.out.println("\nEntropy of War and Peace " + df.format(entropy) + " bits/symbol");

        CodingTree c = new CodingTree(message);
        PrintStream output1 = new PrintStream(new File(codes));
        FileOutputStream output2 = new FileOutputStream(new File(compressed),false);
        //output1.write(c.codes.toString().getBytes("UTF-8"));
        output1.write(c.codes.toString().getBytes());
        output1.close();
        BitSet bs = new BitSet(c.bits.length());

        for (int o = 0; o < c.bits.length(); o++) if (c.bits.charAt(o) == '1') bs.flip(o); 

        byte[] byteArray = bs.toByteArray();
        output2.write(byteArray);
        output2.close();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        double compressed = Files.size(Paths.get("compressed.txt"));
        double targerCompressed = Files.size(Paths.get("targerCompressed.txt"));
        double difference = (targerCompressed - compressed);
        System.out.println("Compressed file size: " +  compressed + " bytes");
        System.out.println("Target compressed file size: " +  targerCompressed  + " bytes");
        System.out.println("Difference in compressed file sizes of my file vs the target: " + difference  + " bytes");
        System.out.println("Percent Difference between target and my compressed: " + 100 * Math.abs(difference)/targerCompressed  + "%");
        System.out.println("Running Time: " + duration + " milliseconds");
    }   
    public static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }
    
    /**
     * TODO: write test for CodingTree
     */
    /**
     * TODO: write test for MyPriorityQueue
     */
    /**
     * TODO: write unit-test for full file
     */
}