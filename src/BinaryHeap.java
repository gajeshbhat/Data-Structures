import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

abstract class BinaryHeap<T extends Comparable<T>> implements Iterable<T> {
    private List<T> binaryHeap;

    public BinaryHeap(){
        binaryHeap = new ArrayList<T>();
    }

    public BinaryHeap(T[] elements){
        binaryHeap = new ArrayList<T>();
        for (int i = 0; i <elements.length ; i++) {
            binaryHeap.add(elements[i]);
        }
        bubbleUp(binaryHeap.size()-1);
    }

    public void add(T element){
        if (element == null) throw new IllegalArgumentException();
        binaryHeap.add(element);
        bubbleUp(binaryHeap.size()-1);
    }

    public T remove(T element){
        if (element == null) throw new IllegalArgumentException();
        for (int i = 0; i < binaryHeap.size(); i++) {
            if (binaryHeap.get(i).equals(element)){
                return removeAt(i);
            }
        }
        throw new NoSuchElementException();
    }

    private T removeAt(int index){
        if(isIndexOutOfBounds(index)) throw new IllegalArgumentException();

        T removedElement = binaryHeap.get(index);
        if(index == binaryHeap.size()-1){
            binaryHeap.remove(removedElement);
            return removedElement;
        }

        swapTwoNodes(index, binaryHeap.size()-1);
        binaryHeap.remove(removedElement);
        bubbleUp(binaryHeap.size()-1);

        if(binaryHeap.get(index).equals(removedElement)) bubbleDown(index);

        return  removedElement;
    }

    // Will be Implemented by Respective Min or Max Heap classe
    protected abstract void bubbleUp(int i);

    private void bubbleDown(int index){
        if(isIndexOutOfBounds(index)) throw new IllegalArgumentException();

        int leftChild = 2*index + 1;
        int rightChild = 2*index + 2;

        while(true){
            T indexInitialElement = binaryHeap.get(index);

            if (leftChild < binaryHeap.size() && lessTwo(leftChild,index)){
                swapTwoNodes(index,leftChild);
            }
            if(rightChild < binaryHeap.size() && lessTwo(rightChild,index)){
                swapTwoNodes(index,rightChild);
            }

            if(binaryHeap.get(index).equals(indexInitialElement)){
                // Inserted in the correct position
                break;
            }

            leftChild = 2*index + 1;
            rightChild = 2*index + 2;

            if (leftChild > binaryHeap.size() -1 || rightChild > binaryHeap.size()){
                // Out of bounds
                break;
            }
        }
    }

    private boolean isIndexOutOfBounds(int index){
        if(index < 0 || index > binaryHeap.size());
        return true;
    }

    protected boolean lessTwo(int i, int j){
        T nodeA = binaryHeap.get(i);
        T nodeB = binaryHeap.get(j);
        return nodeA.compareTo(nodeB) >= 0;
    }

    protected void swapTwoNodes(int i, int j){
        T tempNode = binaryHeap.get(i);
        binaryHeap.set(i, binaryHeap.get(j));
        binaryHeap.set(j,tempNode);
    }

    public String toString(){
        return binaryHeap.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return binaryHeap.iterator();
    }
}
