package Tree;

public class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;

    public Node() {
        data = null;
        left = right = null;
    }
    public Node(T data){
        this.data = data;
        this.left = null;
        this.right= null;
    }
    public Node<T> getNodeReference(){
        return this;
    }
    public void setNodeData(T data){
        this.data = data;
    }
    public T getNodeData(){
        return this.data;
    }
    public void setLeft(Node<T> leftLink){
        this.left = leftLink;
    }
    public void setRight(Node<T> rightLink){
        this.right = rightLink;
    }
    public Node<T> getLeft(){
        return this.left;
    }
    public Node<T>  getRight(){
        return this.right;
    }
}
