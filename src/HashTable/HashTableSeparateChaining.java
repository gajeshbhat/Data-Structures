package HashTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTableSeparateChaining<K,V> implements Iterable<K> {
    ArrayList<LinkedList<KeyValuePair<K,V>>> hTable = new ArrayList<>(10);
    public static double STD_LOAD_FACTOR = 0.8;
    int actualTableSize;
    int minimumCapacity;
    double loadFactor;

    public HashTableSeparateChaining(){
        actualTableSize = 0;
        loadFactor = STD_LOAD_FACTOR;
    }
    public HashTableSeparateChaining(int minimumCapacity){
        this.minimumCapacity = minimumCapacity;
        // Initialize table with Empty objects
        LinkedList<KeyValuePair<K,V>> initLL = new LinkedList<>();
        for (int i = 0; i < minimumCapacity; i++) hTable.add(i,initLL);
        actualTableSize = 0;
        loadFactor = STD_LOAD_FACTOR;
    }

    public class KeyValuePair<K,V>{
        private K key;
        private V value;
        public KeyValuePair(K key,V value){
            this.key = key;
            this.value = value;
        }
        public K getKey(){return key;}
        public V getValue(){return value;}
        public void setValue(V value){this.value =value;}
    }

    void put(K key, V value){
        if(key == null) throw new NullPointerException("Null Key values are not valid.");
        int keyHashCode = key.hashCode();
        int keyPosition = keyHashCode % minimumCapacity; // Zero Based Index


        // If Exists Update Key
        if(containsKey(key)) {
            LinkedList<KeyValuePair<K,V>> keyCheck = hTable.get(keyPosition);
            KeyValuePair<K, V> pairToUpdate = new KeyValuePair<>(key,value);

            int keyTraverseIndex = 0;
            while (keyTraverseIndex < keyCheck.size()){
                if(keyCheck.get(keyTraverseIndex).getKey() == key) break;
                keyTraverseIndex++;
            }
            hTable.get(keyPosition).set(keyTraverseIndex,pairToUpdate);

        }
        // Create new and insert if does not exists.
        else {
            KeyValuePair<K, V> newPair = new KeyValuePair<>(key, value);
            hTable.get(keyPosition).addLast(newPair);
            actualTableSize++;
            updateLoadFactor();
        }
        if(loadFactor() > STD_LOAD_FACTOR) resizeTable();
    }

    public V get(K key){
        if(key == null) throw new NullPointerException("Key cannot be null");
        if(containsKey(key)){
            int keyHashCode = key.hashCode();
            int keyPosition = keyHashCode % minimumCapacity;
            LinkedList<KeyValuePair<K,V>> keyCheck = hTable.get(keyPosition);
            for(int i=0;i<keyCheck.size();i++){
                if(keyCheck.get(i).getKey() == key) return keyCheck.get(i).getValue();
            }
        }
        return null;
    }
    public void resizeTable(){

        // Resize the hash table with a bigger size
        ArrayList<LinkedList<KeyValuePair<K,V>>> tempTable = hTable;
        hTable.clear();
        minimumCapacity *=2;
        hTable.ensureCapacity(minimumCapacity);

        // Insert null elements in the new table to initialize
        LinkedList<KeyValuePair<K,V>> initLL = new LinkedList<>();
        for (int i = 0; i < minimumCapacity; i++) hTable.add(i,initLL);

        // Traverse the existing list and Insert the existing elements into new table
        for (int i = 0; i < actualTableSize; i++) {
            LinkedList<KeyValuePair<K,V>> ithChain = tempTable.get(i);
            // Traverse the Chain and add elements to list
            for (int j = 0; j < ithChain.size(); j++) {
                put(ithChain.get(j).getKey(),ithChain.get(j).getValue());
            }
        }
        updateLoadFactor();
    }


    public boolean containsKey(K key){
        int keyHash = key.hashCode();
        int keyLocation = keyHash % minimumCapacity;;
        LinkedList<KeyValuePair<K,V>> keyCheck = hTable.get(keyLocation);

        for(int i=0;i<keyCheck.size();i++){
            if(keyCheck.get(i).getKey() == key) return true;
        }
        return false;
    }
    public int size(){
        return actualTableSize;
    }

    public int capacity(){
        return minimumCapacity;
    }

    public double loadFactor(){
        return loadFactor;
    }

    void updateLoadFactor(){
        loadFactor = actualTableSize/minimumCapacity;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    public static void main(String[] args) {
        HashTableSeparateChaining<String,Integer> testObj = new HashTableSeparateChaining<>(5);
        testObj.put("A",1);
        testObj.put("B",2);
        testObj.put("C",3);
        testObj.put("D",4);
        testObj.put("E",5);
        testObj.put("F",6);
        testObj.put("G",7);
        testObj.put("H",8);
        testObj.put("I",9);
        testObj.put("J",10);
        testObj.put("K",11);
        testObj.put("L",12);
        testObj.put("M",13);
        testObj.put("N",14);
        testObj.put("O",15);
        testObj.put("P",16);
        testObj.put("Q",17);
        System.out.println(testObj.get("M"));
        //System.out.print(testObj.size());
        System.out.printf("%.2f",testObj.loadFactor());
    }

}
