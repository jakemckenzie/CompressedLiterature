import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import java.util.List;
import java.util.PriorityQueue;

//cd C:\Users\Epimetheus\Documents\GitHub\CompressedLiterature
//javac CodingTree.java Main.java MyPriorityQueue.java

/**
 * @author Jake McKenzie
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
        for (char c:count.keySet()) queue.offer(new Node(c,queue.get(c)));
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
        //Node tempL =
    }

    /**
     * This method will count the characters in my string
     * @param message the message encoded by the huffman tree
     */
    public Map<Character,Integer> tallyChar(String message) {
        HashMap<Character,Integer> count = new HashMap<>();
        //for (char c: message.toCharArray()) 
        message.chars().forEachOrdered(c -> {if (count.containsKey((char)c)) count.put((char)c,count.get((char)c)+1);else count.put((char)c,1);});
        return count;
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
         */
        public int getMax() {
            int max = 0;
            Object[] keys = codes.values().toArray();
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
        * Compares this node with the specified node for order.
        */
        @Override
        public int compareTo(Node that){
            return this.count - that.count;
        }
    }
}