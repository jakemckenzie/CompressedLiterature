import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.PriorityQueue;

//cd C:\Users\Epimetheus\Documents\GitHub\CompressedLiterature
//javac CodingTree.java Main.java MyPriorityQueue.java -Xlint:unchecked

/**
 * @author Jake McKenzie
 * 
 * This was the third assignment for TCSS 341-Data Structures.
 * I worked alone this assignment. In the assignment we 
 * developed huffman trees for compressing famous works of literature.
 * 
 * The books I used for testing and my thought process for choosing them:
 * 
 * 1. War and Peace - This was the required text for compression. It is
 * a long text and showcases the compression technique well. I consider compression
 * of this text to be the baseline to meet. If I can hit this then I will test on
 * progressively harder texts to compress and decompress.
 * 
 * 2. The Story of Gosta Berling - This was written by the first women nobel
 * laureate. It is a swedish novel translated into english and should allow
 * for more range of UTF-8 characters to test from. This is more modern swedish
 * that has been translated to english so it should be harder to compress and decompress.
 * 
 * 3. Epic of Gilgamesh - This is an epic from ancient mesopotamia that is a 
 * story of the unbreakable friendship of Gilgamesh and Enkidu. This 
 * text includes tablets with weird spacing mostly translated into ond english. 
 * It offers different challenges in testing to The Story of Gosta Berling. 
 * It does not have as large a range of UTF-8 characters but it does offer 
 * spacing constraints. This text is shorter than War and Peace and allows 
 * to be ran more often in testing.
 * 
 * 4. Os Maias - A portuguese satirical comedy. This book is in the original
 * portuguese, as such it offers a wide range of possible UTF-8 characters 
 * to be compressed. This should be, theoretically, one of the hardger texts 
 * to compress due to it being in portuguese. This text is nearly as long as War 
 * and Peace but due to it being written in a romantic language is quite varied
 * despite that length.
 * https://english.stackexchange.com/questions/2998/do-most-languages-need-more-space-than-english/3022#3022
 * 
 * 5. The Valsung Saga - an icelandic saga that inspired Tolkein and Andrew 
 * Lang. This book was translated fairly recently into english and was 
 * included due to the songs included. I'm using this mostly to test for spacing
 * and add variety to the testing as it uses a wide array of UTF-8 and 
 * includes a lot of spacing.
 * 
 * All of these books happen to be on my current to read pile.
 */
public class CodingTree {
    /**
     * A constructor that takes the text of a message to be compressed. 
     * The constructor is responsible for calling all private methods 
     * that carry out the Huffman coding algorithm.
     * @param message the message encoded by the huffman tree
     * TODO: fill in the rest of the Coding Tree constructor
     */

    public CodingTree(String message) {
        node = null;
        codes = new HashMap<Character,String>();
        Map<Character,Integer> count = tallyChar(message);
        MyPriorityQueue<Node> queue = new MyPriorityQueue<Node>();
        for (char c:count.keySet()) queue.offer(new Node(c,count.get(c)));
        //bits = new ArrayList<Byte>();
        
    }
    /**
     * @param node the Huffman Tree node.
     */
    public Node node;
    /**
     * @param codes A map of characters in the message with their binary codes.
     */

    public HashMap<Character,String> codes;

    /**
     * @param bits A message encoded using Huffman codes.
     */

    public ArrayList<Byte> bits;

    /**
     * ******************************EXTRA CREDIT**************************** 
     * This method will take the output of Huffman’s encoding and produce the original text.
     * @param bits A message encoded using Huffman codes.
     * @param codes A map of characters in the message with their binary codes.
     * TODO: Fill in the rest of the decode function.
     */

    public String decode(String bits, Map<Character,String> codes) {
        String temp = "";

        return temp;
    }

    /**
     * Builds a Huffman tree given some weights and an alphabet.
     * @param priQueue A priority queue of
     * TODO: fill in the rest of buildHuffmanTree function
     */
    public void buildHuffmanTree(MyPriorityQueue<Node> queue) {

    }

    /**
     * This method will count the characters in my string
     * @param message the message encoded by the huffman tree
     */
    public Map<Character,Integer> tallyChar(String message) {
        HashMap<Character,Integer> count = new HashMap<>();
        message.chars().forEachOrdered(c -> {if (count.containsKey((char)c)) count.put((char)c,count.get((char)c)+1);else count.put((char)c,1);});
        return count;
    }
    /**
         * This returns the maximum codelength of the current huffman tree.
         * 
         * Used for testing.
         * 
         * 1. No huffman code can be longer than alphabetsize-1.
         * 2. The maximum length of the code also depends on the 
         * number of samples you use to derive your statistics from; 
         * the sequence is as follows (the samples include the fake 
         * samples to give each symbol a nonzero probability!):
         * TODO: look into ceil(log(alphabetsize))
         * http://www.compressconsult.com/huffman/#maxlength
         * https://stackoverflow.com/questions/43809659/get-average-value-of-two-hashmap-using-java-8
         * https://stackoverflow.com/questions/42556953/java8-calculate-average-of-list-of-objects-in-the-map
         */
        public int getMax() {
            int max = 0;
            Object[] keys = codes.values().toArray();
            //keys.ints.forEachOrdered(c-> {if (max < keys[i].toString().length()) max = keys[i].toString().length();});
            //return Arrays.stream(codes.values().toArray());
            for (int i = 0; i < keys.length; i++) if (max < keys[i].toString().length()) max = keys[i].toString().length();
            return max; 
        }
        /**
         * This returns the average codelength of the current huffman tree.
         * 
         * Used for testing.
         * 
         * "To precisely compare the new code with the standard encoding,
         * we can compute the average number of bits/symbol of the codes.
         * The standard coding always uses 2 bits, so obviously the average 
         * number of bits per symbol is also 2"
         * https://www.princeton.edu/~cuff/ele201/kulkarni_text/information.pdf
         * https://softwareengineering.stackexchange.com/questions/237543/how-do-i-find-average-bits-per-symbol-using-huffman-code
         */
        public double getAvg() {
            double sum = 0.0d;
            Object[] keys = codes.values().toArray();
            for (int i = 0; i < keys.length; i++) sum+=(double)keys[i].toString().length();
            return (sum / keys.length);
        }

    /**
     * Huffman tree node.
     */
    public class Node implements Comparable<Node> {
        /**
        * @param key the char associated with the node.
        */
        public char key;
        /**
        * @param count the frequency of the character
        */
        public int count;
        /**
        * @param L the left node of the huffman tree.
        */
        public Node L;
        /**
        * @param R the right node of the huffman tree.
        */
        public Node R;
        
        
        /**
        * @param nodeChar key being sent into a node.
        * @param nodeCount count being sent into a node.
        */
        public Node(char nodeChar, int nodeCount) {
            key = nodeChar;
            count = nodeCount;
            L = null;
            R = null;
        }
        /**
        * @param left left node
        * @param right right node
        */
        public Node(Node left, Node right) {
            L = left;
            R = right;
            count = left.count + right.count;
        }
        /**
        * Returns true if the receiver is a leaf.
        */
        public boolean isLeaf() {
            assert ((L == null) && (R == null)||((L != null) && (R != null)));//Should never trigger. For testing.
            return ((L == null) && (R == null));
        }
        /**
        * Returns a string representation of the node.
        */
        public String toString() {
            return (key + " | " + count);
        }

        /**
        * Compares this node with the specified node for order.
        */
        @Override
        public int compareTo(Node that){
            return this.count - that.count;
        }
    }
}