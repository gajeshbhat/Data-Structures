package Other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UnionFind<T> implements Iterable<T> {
    T[] disjointSet; // The elements
    int[] componentElementsSize; // Number of elements in the Component
    HashMap<T,Integer> componentIndexMap;
    int numberOfComponents; // Total number of disjoint components. Max : N and Least 1. Hint: Kruskals algorithm for MST

    public UnionFind(ArrayList<T> elements){
        disjointSet = (T[]) new Object[elements.size()];
        componentElementsSize = new int[elements.size()];
        componentIndexMap = new HashMap<T,Integer>(elements.size());

        for (int i = 0; i <elements.size() ; i++) {
            disjointSet[i] = elements.get(i);
            componentElementsSize[i] = 1;
            componentIndexMap.put(elements.get(i),i); // Keep a Map of Element and its component elementSize Index
            numberOfComponents++; /* Each element is an individual component at the beginning */
        }

    }

    // Union two elements in the set.
    public void unionComponents(T elementOne, T elementTwo){
        if(!componentIndexMap.containsKey(elementOne) || !componentIndexMap.containsKey(elementTwo)){
            throw new IllegalArgumentException("Element does not exist in the set");
        }
        int elementOneIdx = componentIndexMap.get(elementOne);
        int elementTwoIdx = componentIndexMap.get(elementTwo);
        componentIndexMap.put(elementTwo,elementOneIdx);
        componentElementsSize[elementTwoIdx]-=1;
        componentElementsSize[elementOneIdx]+=1;
        numberOfComponents--;

        // This is with Path Compression
    }

    // Find the root of an element
    public T find(T element){
        if(!componentIndexMap.containsKey(element)) throw new IllegalArgumentException("No such element in the set!");
        int elementIdx =  componentIndexMap.get(element);
        return disjointSet[elementIdx];
    }

    public int getNumberOfDistinctComponents() {
        return numberOfComponents;
    }

    public int size(){
        return disjointSet.length;
    }

    public void printElements(){
        for (int i = 0; i < disjointSet.length ; i++) {
            System.out.print(disjointSet[i] + " ");
        }
    }
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testArray = new ArrayList<>();
        testArray.add(3);
        testArray.add(2);
        testArray.add(4);
        testArray.add(1);
        testArray.add(5);

        UnionFind<Integer> testUnion = new UnionFind<Integer>(testArray);
        testUnion.unionComponents(3,2);
        testUnion.unionComponents(2,4);
        testUnion.unionComponents(1,5);
        testUnion.unionComponents(5,3);
        System.out.println("The Root element for Component 4 is : " + testUnion.find(4));
        System.out.println("Distinct Component in this Set is/are : " + testUnion.getNumberOfDistinctComponents());
    }
}
