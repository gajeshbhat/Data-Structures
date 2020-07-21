public class BinaryMaxHeap extends  BinaryHeap{
    public BinaryMaxHeap(){
        super();
    }

    public <T> BinaryMaxHeap(T[] elements){
        super((Comparable[]) elements);
    }

    public void bubbleUp(int index) {
        int parentNode = (index -1)/2;
        while (index > 0 && lessTwo(index,parentNode)){
            swapTwoNodes(index,parentNode);
            index = parentNode;
            parentNode = (index -1)/2;
        }
    }
}
