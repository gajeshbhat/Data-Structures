package Tree;

public class BinaryTree< T extends Comparable<T>> {
    private Node<T> rootNode;
    int treeSize;

    public BinaryTree(T data){
        rootNode = new Node<>(data);
        treeSize = 0;
    }

    public BinaryTree() {
        rootNode = new Node<>();
        treeSize = 0;
    }

    public void setRoot(T data){
        rootNode.setNodeData(data);
    }

    public Node<T> getRoot(){
        return rootNode;
    }
    public int getTreeSize(){
        return this.treeSize;
    }
    public void incrementTreeSize(int incrementValue){
        treeSize+=incrementValue;
    }

}
