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
        node = null;
        codes = new HashMap<Character,String>();
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
     * Builds a Huffman tree given some weights and an alphabet.
     * @param priQueue A priority queue of
     */
    public void buildHuffmanTree(MyPriorityQueue<Node> priQueue) {

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
        private boolean isLeaf() {
            assert ((L == null) && (R == null)||((L != null) && (R != null)));
            return ((L == null) && (R == null));
        }
        /**
        * Returns a string representation of the node.
        */
        public String toString() {
            return (key + ": " + count);
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