package Array;

public class DynamicArray <T> {

    private T[] dynamicArray;
    private int dynamicArraySize = 0;
    private int currentIdx = 0;
    private int dynamicArrayCapacity;


    public DynamicArray(int arraySize){
        if (arraySize < 0) throw new IllegalArgumentException("Array size should be greater than zero.");
        if (arraySize == 0) this.dynamicArraySize = 1;
        else this.dynamicArraySize = arraySize;
        this.dynamicArrayCapacity = this.dynamicArraySize * 2;
        this.dynamicArray = (T[]) new Object[this.dynamicArrayCapacity];
    }

    // Class methods

    public void setValue(T value,int index){
        if (index < 0  || index >= dynamicArraySize) throw new IndexOutOfBoundsException("Please insert elements within array index.");
        dynamicArray[index] = value;
    }

    public void append(T value){
        dynamicArray[currentIdx++] = value;
        if(dynamicArraySize + 1 >= dynamicArrayCapacity){
            resizeArray();
        }
    }

    public T getValue(int index){
        if (index < 0  || index >= dynamicArraySize) throw new IndexOutOfBoundsException("Please enter elements within array index.");
        return this.dynamicArray[index];
    }

    public int getSize(){
        return (int) this.dynamicArraySize;
    }

    // Helper Methods
    private void resizeArray() {
        dynamicArrayCapacity = dynamicArrayCapacity * 2;
        T[] resizedDynamicArray = (T[]) new Object[dynamicArrayCapacity];
        for (int i = 0; i < dynamicArraySize; i++) {
            resizedDynamicArray[i] = dynamicArray[i];
        }
        dynamicArray = resizedDynamicArray;
    }

    public void printArray(){
        for (int i = 0; i < this.dynamicArraySize; i++) {
            System.out.println(dynamicArray[i]);
        }
        System.out.println(dynamicArraySize);
    }
}
