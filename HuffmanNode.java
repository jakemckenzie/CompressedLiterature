import java.util.Comparator;
import java.util.Iterator;
    /**
     * Huffman tree node.
     */
    public class HuffmanNode implements Comparable<HuffmanNode> {
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
        public HuffmanNode(char nodeChar, int nodeCount) {
            key = nodeChar;
            count = nodeCount;
            L = null;
            R = null;
        }
        /**
        * @param left left node
        * @param right right node
        */
        public HuffmanNode(Node left, Node right) {
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
        public int compareTo(HuffmanNode that){
            return this.count - that.count;
        }
    }