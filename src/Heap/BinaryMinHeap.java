package Heap;

public class BinaryMinHeap extends BinaryHeap{

    public BinaryMinHeap(){
        super();
    }

    public <T> BinaryMinHeap(T[] elements){
        super((Comparable[]) elements);
    }

    public void bubbleUp(int index) {
        int parentNode = (index -1)/2;
        while (index > 0 && lessTwo(parentNode,index)){
            swapTwoNodes(index,parentNode);
            index = parentNode;
            parentNode = (index -1)/2;
        }
    }
}
