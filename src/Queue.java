import java.util.Iterator;
import java.util.LinkedList;

public class Queue <T> implements Iterable<T> {
    LinkedList<T> queueList = new LinkedList<T>();

    public Queue(){
    }

    public Queue(T element){
        queueList.addLast(element);
    }

    public void enqueue(T element){
        queueList.addLast(element);
    }
    public void dequeue() throws Exception {
        if (queueList.isEmpty()) throw new Exception("Empty queue! Nothing to dequeue");
        queueList.removeFirst();
    }

    public T poll() throws Exception{
        if (queueList.isEmpty()) throw new Exception("Empty queue! Nothing to dequeue");
        T firstElement = queueList.getFirst();
        queueList.removeFirst();
        return firstElement;
    }

    public int size(){
        return queueList.size();
    }

    // Return an iterator to allow the user to traverse
    // through the elements found inside the queue
    @Override
    public java.util.Iterator<T> iterator() {
        return queueList.iterator();
    }
}
