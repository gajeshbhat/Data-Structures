import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {
    private List<T> heap = null;

    public PriorityQueue(){
        heap = new ArrayList<T>();
    }

    public PriorityQueue(T[] elements){
        // Add to heap array and make heap
        for (int i = 0; i <elements.length ; i++) {
            heap.add(elements[i]);
        }
        // Write the make heap code below
        bubbleUp(heap.size()-1);
    }

    public void add(T element){
        heap.add(element);
        bubbleUp(heap.size()-1);
    }

    public T remove(T element){
        if (element == null) throw new IllegalArgumentException();
        // Linear scan and remove
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).equals(element)){
                return removeAt(i);
            }
        }
        return null; // If not found
    }

    public T removeAt(int index){
        if(index < 0 || index > heap.size()) throw new IllegalArgumentException();
        T removedElement = heap.get(index);

        if(index == heap.size()-1){
            heap.remove(removedElement);
            return removedElement;
        }
        swapTwoNodes(index,heap.size()-1);
        heap.remove(removedElement);
        bubbleUp(heap.size()-1);

        if(heap.get(index).equals(removedElement)) bubbleDown(index);

        return  removedElement;
    }

    public void bubbleUp(int index) {
        int parentNode = (index -1)/2;
        while (index > 0 && lessTwo(parentNode,index)){ // Current : Min heap , Switch parentNode and Index to make a max heap
            swapTwoNodes(index,parentNode);
            index = parentNode;
            parentNode = (index -1)/2;
        }
    }

    public void bubbleDown(int index){
        if(index < 0 || index > heap.size()) throw new IllegalArgumentException();

        int leftChild = 2*index + 1;
        int rightChild = 2*index + 2;

        while(true){
            T indexInitialElement = heap.get(index);

            if (leftChild < heap.size() && lessTwo(leftChild,index)){
                swapTwoNodes(index,leftChild);
            }
            if(rightChild < heap.size() && lessTwo(rightChild,index)){
                swapTwoNodes(index,rightChild);
            }

            if(heap.get(index).equals(indexInitialElement)){
                // Inserted in the correct position
                break;
            }

            leftChild = 2*index + 1;
            rightChild = 2*index + 2;

            if (leftChild > heap.size() -1 || rightChild > heap.size()){
                // Out of bounds
                break;
            }
        }
    }

    // Helpers
    public boolean lessTwo(int i, int j){
        T nodeA = heap.get(i);
        T nodeB = heap.get(j);
        return nodeA.compareTo(nodeB) >= 0;
    }

    public void swapTwoNodes(int i, int j){
        T tempNode = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,tempNode);
    }

    private void printQueue(){
        for (int i = 0; i <heap.size() ; i++) {
            System.out.print(heap.get(i) + " ");
        }
    }



    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> testPQ = new PriorityQueue<Integer>();
    }
}
