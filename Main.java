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
     * @param A_PAINLESS_GUIDE_TO_CRC_ERROR_DETECTION_ALGORITHMS A guide for cyclic
     * redundancy check. When we send a packet we want to guarantee the receiver received
     * the messeage without any errors so we need to detect errors. Then we deploy different
     * algorithms to solve the problem. This is a very famous paper the author made publically
     * available that is shared widely in embedded systems. This txt file was included because 
     * it has many spaces and handcrafted formatting to illustrated examples.
     * 
     * Entropy = 4.756447913333198
     * 
     * All of these books happen to be on my current to read pile.
     */

    private static final String A_PAINLESS_GUIDE_TO_CRC_ERROR_DETECTION_ALGORITHMS = "A_PAINLESS_GUIDE_TO_CRC_ERROR_DETECTION_ALGORITHMS.txt";
    /**
     * @param codes an output file for the codes
     */
    private static final String codes = "./codes.txt";

    /**
     * @param compressed a compressed output file for the codes
     */
    private static final String compressed = "./compressed.txt";

    /**
     * @param decompressed a compressed output file for the codes
     */
    private static final String decompressed = "./decompressed.txt";

    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();
        //https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
        //http://www.adam-bien.com/roller/abien/entry/java_8_reading_a_file
        //String message = new String(Files.readAllBytes(Paths.get(WarAndPeace)),"UTF-8");
        String message = new String(Files.readAllBytes(Paths.get(WarAndPeace)));
        //String message = new String(Files.readAllBytes(Paths.get(WarAndPeace)), "US-ASCII");
        CodingTree c = new CodingTree(message);
        Files.write(Paths.get(codes), c.codes.toString().getBytes());
        BitSet bs = new BitSet(c.bits.length());
        for (int o = 0; o < c.bits.length(); o++) if (c.bits.charAt(o) == '1') bs.flip(o);
        Files.write(Paths.get(compressed), bs.toByteArray());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        Files.write(Paths.get(decompressed), c.decoded.getBytes());
        double compressed = Files.size(Paths.get("compressed.txt"));
        double targerCompressed = Files.size(Paths.get("targerCompressed.txt"));
        double difference = (targerCompressed - compressed);
        System.out.println("Compressed file size: " +  compressed + " bytes");
        System.out.println("Target compressed file size: " +  targerCompressed  + " bytes");
        System.out.println("Difference in compressed file sizes of my file vs the target: " + difference  + " bytes");
        System.out.println("Percent Difference between target and my compressed: " + 100 * Math.abs(difference)/targerCompressed  + "%");
        System.out.println("Running Time: " + duration + " milliseconds");
        
        /**
         * This was part of the specified testing that was required for the assignment.
         */
        //testCodingTree();
        
        /**
         * Please leave these commented out when grading the baseline program.
         * They overwrite many of the files included in the assignment.
         */
        //testTheStoryOfGostaBerling();
        //testEpicOfGilgamesh();
        //testOsMaias();
        //testTheStoryOfTheVolsungs();
        //testA_PAINLESS_GUIDE_TO_CRC_ERROR_DETECTION_ALGORITHMS();
    }   
    public static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }
    /**
     * This is a small test of the coding tree. I wrote a helper
     * function inspired by the first chapter of Maxime Crochmore's
     * Algorithm's on Strings. I expect the code length to be just as
     * long for the fibonacci sequence with strings and that is what
     * I obtain from testing. 
     */
    public static void testCodingTree() {
        CodingTree testCodingTree = new CodingTree(fibonacciStrings(15));
        System.out.println(fibonacciStrings(15));
        System.out.println(testCodingTree.codes.toString());
        System.out.println(testCodingTree.bits.toString());
        System.out.println(testCodingTree.decoded.toString());
        System.out.println("This tests the length of the fibonacciStrings(2) through fibonacciStrings(35) are the same length: ");
        for (int i = 2; i < 25; i++) {
            CodingTree testLengthCodingTree = new CodingTree(fibonacciStrings(i));
            // /System.out.println("fibonacciStrings(" + i + ") = " + fibonacciStrings(i) + ", length = " + fibonacciStrings(i).length());
            System.out.println(fibonacciStrings(i).length() == testLengthCodingTree.bits.toString().length() && fibonacciStrings(i).length() == testLengthCodingTree.decoded.toString().length());
        }
        System.out.println("This should always be true, which it is.");
        System.out.println("The entropy of fibonacciStrings(15) of a string is, the encoded bits and decoded bits are: ");
        System.out.println(getEntropy(fibonacciStrings(15)));
        System.out.println(getEntropy(testCodingTree.bits.toString()));
        System.out.println(getEntropy(testCodingTree.decoded.toString()));
        System.out.println("This should be the same number and it is.");
    }
    /**
     * pg 9 of Maxime Crochemore's Algorithms on Strings. 
     * 
     * fibonacciStrings(1)    1   b
     * fibonacciStrings(2)    1   a
     * fibonacciStrings(3)    2   ab
     * fibonacciStrings(4)    3   aba
     * fibonacciStrings(5)    5   abaab
     * fibonacciStrings(6)    8   abaababa
     * fibonacciStrings(7)    13  abaababaabaab
     * fibonacciStrings(8)    21  abaababaabaababaababa
     * fibonacciStrings(9)    34  abaababaabaababaababaabaababaabaab
     * 
     * The lengths of the strings are exactly the same length as the sequence of fibonacci numbers ie Fn = |fibonacciStrings(n)|
     * yet it has many repeats. This makes it good, in my opinion, for testing purposes
     */
    public static String fibonacciStrings(int N){
        return (N == 0) ? "b" : (N == 1) ? "a" : fibonacciStrings(N - 1) + fibonacciStrings(N - 2);
    }
    /**
     * This was used for testing purposes. When I read about the huffman compression
     * entropy kept coming up so I decided to see what the entropy of the files were that
     * I collected for testing. I encluded the entropy of each above in the comments of their file names. 
     * 
     * https://courses.cs.washington.edu/courses/csep521/99sp/lectures/lecture11/sld020.htm
     */
    public static double getEntropy(String s){
        double entropy = 0.0d;
        double probability;
        final int[] frequency = new int[256];
        for (byte b : s.getBytes()) frequency[b & 0xFF]++;
        //Claude Shannon - The theory of information
        for (int f:frequency) {
           probability = (double)f / s.length();
           if (f != 0) entropy -= (probability) * log2(probability);
        }
        return entropy;
    }
    /**
     * TODO: Not all swedish characters are being taken care of.
     */
    public static void testTheStoryOfGostaBerling() throws IOException {
        System.out.println();
        long startTime = System.currentTimeMillis();
        String message = new String(Files.readAllBytes(Paths.get(TheStoryOfGostaBerling)));
        CodingTree c = new CodingTree(message);
        Files.write(Paths.get(codes), c.codes.toString().getBytes());
        BitSet bs = new BitSet(c.bits.length());
        for (int o = 0; o < c.bits.length(); o++) if (c.bits.charAt(o) == '1') bs.flip(o);
        Files.write(Paths.get(compressed), bs.toByteArray());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        Files.write(Paths.get("./decompressed.txt"), c.decoded.getBytes());
        double compressed = Files.size(Paths.get("compressed.txt"));
        System.out.println("Compressed file size: " +  compressed + " bytes");
        System.out.println("Running Time: " + duration + " milliseconds");
        System.out.println("The entropy of the original file is: " + getEntropy(message));
    }
    /**
     * TODO: Fix 5 removals 5 additions. Not taking care of all UTF-8 characters properly
     */
    public static void testEpicOfGilgamesh() throws IOException {
        System.out.println();
        long startTime = System.currentTimeMillis();
        String message = new String(Files.readAllBytes(Paths.get(EpicOfGilgamesh)));
        CodingTree c = new CodingTree(message);
        Files.write(Paths.get(codes), c.codes.toString().getBytes());
        BitSet bs = new BitSet(c.bits.length());
        for (int o = 0; o < c.bits.length(); o++) if (c.bits.charAt(o) == '1') bs.flip(o);
        Files.write(Paths.get(compressed), bs.toByteArray());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        Files.write(Paths.get("./decompressed.txt"), c.decoded.getBytes());
        double compressed = Files.size(Paths.get("compressed.txt"));
        System.out.println("Compressed file size: " +  compressed + " bytes");
        System.out.println("Running Time: " + duration + " milliseconds");
        System.out.println("The entropy of the original file is: " + getEntropy(message));
    }
    /**
     * TODO: Fix 468 removals 512 additions
     */
    public static void testOsMaias() throws IOException {
        System.out.println();
        long startTime = System.currentTimeMillis();
        String message = new String(Files.readAllBytes(Paths.get(OsMaias)));
        CodingTree c = new CodingTree(message);
        Files.write(Paths.get(codes), c.codes.toString().getBytes());
        BitSet bs = new BitSet(c.bits.length());
        for (int o = 0; o < c.bits.length(); o++) if (c.bits.charAt(o) == '1') bs.flip(o);
        Files.write(Paths.get(compressed), bs.toByteArray());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        Files.write(Paths.get("./decompressed.txt"), c.decoded.getBytes());
        double compressed = Files.size(Paths.get("compressed.txt"));
        System.out.println("Compressed file size: " +  compressed + " bytes");
        System.out.println("Running Time: " + duration + " milliseconds");
        System.out.println("The entropy of the original file is: " + getEntropy(message));
    }
    /**
     * The input file and output file are oddly enough identical. I can apparently handle old swedish
     * but modern swedish texts are an issue.
     */
    public static void testTheStoryOfTheVolsungs() throws IOException {
        System.out.println();
        long startTime = System.currentTimeMillis();
        String message = new String(Files.readAllBytes(Paths.get(TheStoryOfTheVolsungs)));
        CodingTree c = new CodingTree(message);
        Files.write(Paths.get(codes), c.codes.toString().getBytes());
        BitSet bs = new BitSet(c.bits.length());
        for (int o = 0; o < c.bits.length(); o++) if (c.bits.charAt(o) == '1') bs.flip(o);
        Files.write(Paths.get(compressed), bs.toByteArray());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        Files.write(Paths.get("./decompressed.txt"), c.decoded.getBytes());
        double compressed = Files.size(Paths.get("compressed.txt"));
        System.out.println("Compressed file size: " +  compressed + " bytes");
        System.out.println("Running Time: " + duration + " milliseconds");
        System.out.println("The entropy of the original file is: " + getEntropy(message));
    }
    /**
     * The input file and output file are oddly enough identical. 
     */
    public static void testA_PAINLESS_GUIDE_TO_CRC_ERROR_DETECTION_ALGORITHMS() throws IOException {
        System.out.println();
        long startTime = System.currentTimeMillis();
        String message = new String(Files.readAllBytes(Paths.get(A_PAINLESS_GUIDE_TO_CRC_ERROR_DETECTION_ALGORITHMS)));
        CodingTree c = new CodingTree(message);
        Files.write(Paths.get(codes), c.codes.toString().getBytes());
        BitSet bs = new BitSet(c.bits.length());
        for (int o = 0; o < c.bits.length(); o++) if (c.bits.charAt(o) == '1') bs.flip(o);
        Files.write(Paths.get(compressed), bs.toByteArray());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        Files.write(Paths.get("./decompressed.txt"), c.decoded.getBytes());
        double compressed = Files.size(Paths.get("compressed.txt"));
        System.out.println("Compressed file size: " +  compressed + " bytes");
        System.out.println("Running Time: " + duration + " milliseconds");
        System.out.println("The entropy of the original file is: " + getEntropy(message));
    }
} 