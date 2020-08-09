package Tree;

import java.util.ArrayList;
import java.util.Iterator;

public class FenWickTree<T>  implements Iterable<T> {
    ArrayList<T> fenTree; // Pass the array from constructor for Naive O(n) tree construction
    FenWickTree(T[] array){
        fenTree = new ArrayList<>(array.length);
        optimalConstruct(array);
    }
    void optimalConstruct(T[] array){
    }

    int getLSB(int index){
        if(index > fenTree.size()) throw new IllegalArgumentException("Index out of bounds!");
        return Integer.lowestOneBit(index);
    }
    T getPrefixSum(int index){return null;}
    T getRangeSum(int fromIdx, int toIdx){return null;}
    void add(T value, int index){}
    T get(int index){return null;}
    void set(T value, int index){}

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
