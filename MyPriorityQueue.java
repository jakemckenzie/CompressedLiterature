import java.util.Comparator;
import java.util.AbstractQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * ******************************EXTRA CREDIT**************************** 
 * @author Jake McKenzie
 * @param T generic type 
 */

public class MyPriorityQueue<T extends Comparable<T>> {
    /**
     *@param queue the priority queue 
     */
    public ArrayList<T> queue;
    /**
     * @param comparator reference for queue 
     */
    
    Comparator<T> comparator = null;
    /**
     * The size of the queue
     */
    public int size;
    /**
     * Constructor that initializes the queue
     * "UTF-8 is a variable width character encoding 
     * capable of encoding all 1,112,064"
     * https://en.wikipedia.org/wiki/UTF-8
     */
    public MyPriorityQueue() {
        queue = new ArrayList<T>(0x10F800);//1112064
    }
    /**
     * Iterator for the abstract queue.
     * TODO: fill in the rest of the iterator.
     */
    
    //@Override
    //public Iterator<T> iterator() {
    //    return null;
    //}
    /**
     * @return Returns true if, and only if, length() is 0.
     */
    public boolean isEmpty() {
        assert(size !=0 && size == 0);
        return this.size == 0;
    }
    /**
     * Returns back the size of the current queue.
     * TODO: fill in the size() function
     */
    //@Override
    //public int size() {
    //    return 0;
    //}
    /**
     * Constructor that builds queue given a limit.
     * @param limit the maximum size of the queue
     * @param c reference for the queue
     */
    public MyPriorityQueue(int limit, Comparator <T> c) {
        queue = new ArrayList<T>(limit+1);
        comparator = c;
    }
    /**
     * This function takes a node and plops it into the priority queue.
     * @param t node that is inserted into the priority queue
     */
    public boolean offer(T t) {
        queue.add(t);
        //size++;
        int child = queue.size() - 1;
        int parent = parent(child);
        while (compare(queue.get(parent),queue.get(child)) > 0 && 0 <= parent) {
            swap(child,parent);
            child = parent;
            parent = parent(child);
        }
        return true;
    }
    /**
     * Performs a swap on two elements given their indices.
     */
    public void swap(int y, int z) {
        T t = queue.get(y);
        queue.set(y,queue.get(z));
        queue.set(z,t);
    }
    /**
     * Retrieves and removes the head of the queue, or returns null if this queue is empty.
     * @return The smalled value in the queue, if the queue is empty this is null.
     */
    public T poll() {
        if (queue.size() < 1) return null;
        T t = queue.get(0);
        if (queue.size() == 1) {
            queue.remove(0);
            return t;
        }
        queue.set(0,queue.remove(queue.size() - 1));
        int parent = 0;
        while (true) {
            int leftChild = (parent<<1) + 1;
            if (leftChild >= queue.size()) break;
            int rightChild = leftChild + 1;
            int minChild = leftChild;
            if (compare(queue.get(leftChild),queue.get(rightChild))> 0 && rightChild < queue.size()) minChild = rightChild;
            if (compare(queue.get(parent),queue.get(minChild)) > 0) {
                swap(parent,minChild);
                parent = minChild;
            } else {
                break;
            }
        }
        return t;
    }
    /**
     * @return Returns back the current node for peeking.
     */
    public T peek() {
        return (this.size == 0) ? null : queue.get(0);
    }
    /**
     * talked about in seminar
     * @param c child
     * @return index of the parent
     */
    public int parent(int c) {
        return (c - 1) >> 1;
    }
    /**
     * @param p parent index
     * @return left child
     */
    public int leftChild(int p) {
        return (p << 1) + 1;
    }
    /**
     * @param p parent index
     * @return right child
     */
    public int rightChild(int p) {
        return (p << 1) + 2;
    }
    /**
     * Compares values of two nodes.
     * @return the comparison on the left and right child
     * TODO: fix unchecked error with compare
     */
    @SuppressWarnings("unchecked")
    public int compare(T L, T R) {
        if (comparator != null) {
            return comparator.compare(L,R);
        } else {
            return ((Comparable<T>)L).compareTo(R);
        } 
    }
}