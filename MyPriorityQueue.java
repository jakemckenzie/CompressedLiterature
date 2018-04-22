import java.util.*;
/**
 * ******************************EXTRA CREDIT**************************** 
 * @author Jake McKenzie
 */

public class MyPriorityQueue<T> extends AbstractQueue<T> implements Queue<T> {
    /**
     *@param queue the priority queue 
     */
    public ArrayList<T> queue;
    /**
     * @param comparator reference for queue 
     */
    //public int size;
    Comparator<T> comparator = null;
    /**
     * Constructor that initializes the queue
     */
    public MyPriorityQueue() {
        //size = 0;
        queue = new ArrayList<T>();
        //queue.add(null);
    }
    
    @Override
    public Iterator<T> iterator() {
        return null;
    }
    @Override
    public int size() {
        return 0;
    }
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
        int parent = (int)Math.floor(child>>1);//talked about this in seminar
        while (compare(queue.get(parent),queue.get(child)) > 0 && 0 <= parent) {
            swap(child,parent);
            child = parent;
            parent = (int)Math.floor(child>>1);
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
    @Override
    public T peek() {
        return null;
    }
    public int compare(T L, T R) {
        if (comparator != null) {
            return comparator.compare(L,R);
        } else {
            return ((Comparable<T>)L).compareTo(R);
        } 
    }
}