import java.util.*;
/**
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
    Comparator<T> comparator = null;
    /**
     * Constructor that initializes the queue
     */
    public MyPriorityQueue() {
        queue = new ArrayList<T>();
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
        int child = queue.size() - 1;
        int parent = Math.floor(child>>1);//talked about this in seminar
        while (compare(queue.get(parent),queue.get(child)) > 0 && 0 <= parent) {
            swap(child,parent);
            child = parent;
            parent = Math.floor(child>>1);
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
}