public interface IBinarySearchTree < T extends Comparable<T>> {
    public void insert(T data);
    public void remove(T data);
    public boolean containsKey(T data);
    public int height();
    public void printPreorder();
    public void printInorder();
    public void printPostOrder();
    public void printLevelOrder();
    public String toString();
}
