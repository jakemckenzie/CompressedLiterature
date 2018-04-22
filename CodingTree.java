import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.PriorityQueue;

//cd C:\Users\Epimetheus\Documents\GitHub\CompressedLiterature
//javac CodingTree.java

/**
 * @author Jake McKenzie
 */
public class CodingTree {
    /**
     * A constructor that takes the text of a message to be compressed. 
     * The constructor is responsible for calling all private methods 
     * that carry out the Huffman coding algorithm.
     * @param message the message encoded by the huffman tree
     */

    public CodingTree(String message) {

    }
    /**
     * @param codes A map of characters in the message with their binary codes.
     */

    public Map<Character,String> codes;

    /**
     * @param bits A message encoded using Huffman codes.
     */

    public List<Byte> bits;

    /**
     * ******************************EXTRA CREDIT**************************** 
     * This method will take the output of Huffman’s encoding and produce the original text.
     * @param bits A message encoded using Huffman codes.
     * @param codes A map of characters in the message with their binary codes.
     */

    public String decode(String bits, Map<Character,String> codes) {
        String temp = "";

        return temp;
    }

    /**
     * 
     */
    public void buildHuffmanTree () {

    }

    /**
     * This method will count the characters in my string
     * @param message the message encoded by the huffman tree
     */
    public Map<Character,Integer> tallyChar(String message) {
        HashMap<Character,Integer> count = new HashMap<>();
        //for (char c: message.toCharArray()) 
        message.chars().forEachOrdered(c -> {
            if (!count.containsKey((char)c)) { 
                count.put((char)c,1); 
            } else { 
                count.put((char)c,count.get((char)c)+1);
            }
        });
        return count;
    }
}