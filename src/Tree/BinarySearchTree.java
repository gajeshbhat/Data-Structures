package Tree;

import java.util.LinkedList;

public class BinarySearchTree implements IBinarySearchTree {
    
    private BinaryTree binaryTreeRoot;
    
    public BinarySearchTree(T data){
        binaryTreeRoot = new BinaryTree(data);
    }

    public void insert(T data){
        if(containsKey(data)) throw new IllegalArgumentException("Element already exists in the Tree. No Duplicates allowed.");
        BinaryTree traverseNode = binaryTreeRoot;

        while((traverseNode.left != null) || (traverseNode.right != null)){
            if(lessThan(data,traverseNode.data)){
                if(traverseNode.left == null) break; // If only right subtree exists then the this node is first in left subtree
                traverseNode = traverseNode.left;
            }
            else{
                if(traverseNode.right == null) break; // If only left subtree exists then the this node is first in right subtree
                traverseNode = traverseNode.right;
            }
        }
        // Data Insertion
        if(lessThan(data,traverseNode.data)) {
            traverseNode.left = new Node<T>(data);
        }
        else{
            traverseNode.right = new Node<T>(data);
        }
    }

    void removeLeaf(T data){
        Node<T> traverseNode = rootNode;
        Node<T> traverseNodeParent = rootNode; // To make the links to nodes null
        while (traverseNode.data != data){
            traverseNodeParent = traverseNode;
            if(lessThan(data,traverseNode.data)) traverseNode = traverseNode.left;
            else traverseNode = traverseNode.right;
        }
        if(traverseNode == traverseNodeParent.left) traverseNodeParent.left =null;
        else traverseNodeParent.right = null;
        traverseNode = null;
    }
    void removeHalfSubtree(T data){
        Node<T> subTreeTraverseNode = rootNode;
        Node<T> traverseNodeParent = rootNode; // To make the links to nodes null
        while (subTreeTraverseNode.data != data){
            traverseNodeParent = subTreeTraverseNode;
            if(lessThan(data,subTreeTraverseNode.data)) subTreeTraverseNode = subTreeTraverseNode.left;
            else {
                subTreeTraverseNode = subTreeTraverseNode.right;
            }
        }
        traverseNodeParent.left = subTreeTraverseNode.left;
        traverseNodeParent.right = subTreeTraverseNode.right;
        subTreeTraverseNode = null;
    }
    void removeFullSubtree(Node<T> startNode){
        // Chooses smallest from Right subtree i.e Smallest element from the right subtree
        Node<T> subTreeTraverseNode = startNode;
        Node<T> leftMostNode = getLeftMostNode(subTreeTraverseNode);
        T tempData = leftMostNode.data;
        removeLeaf(tempData);
        subTreeTraverseNode.data = tempData;
    }
    void removeNode(T data){
        Node<T> traverseNode = rootNode;
        if(!containsKey(data)) throw new IllegalArgumentException("No such node in the tree");
        while (traverseNode.data != data){
            if(lessThan(data,traverseNode.data)) traverseNode= traverseNode.left;
            else traverseNode = traverseNode.right;
        }

        // 1. If Tree.Node is a left
        if(traverseNode.left == null && traverseNode.right == null) removeLeaf(data);
            // 2. If only one of the subtrees is null
        else if (traverseNode.left == null || traverseNode.right == null) removeHalfSubtree(data);
            // 3. If the Tree.Node has both the subtrees
        else removeFullSubtree(traverseNode.right);
    }

    private Node<T> getLeftMostNode(Node<T> startNode){
        while(startNode.left != null) startNode = startNode.left;
        return startNode;
    }

    private Node<T> getRightMostNode(Node<T> startNode){
        while(startNode.right != null) startNode = startNode.right;
        return startNode;
    }

    // Traversals

    void inOrderTraverseRecursive(Node<T> traverseNode){
        if(traverseNode == null){
            return;
        }
        this.inOrderTraverseRecursive(traverseNode.left);
        System.out.print(traverseNode.data + " ");
        this.inOrderTraverseRecursive(traverseNode.right);
    }

    void preOrderTraverseRecursive(Node<T> traverseNode){
        if(traverseNode == null){
            return;
        }
        System.out.print(traverseNode.data + " ");
        this.preOrderTraverseRecursive(traverseNode.left);
        this.preOrderTraverseRecursive(traverseNode.right);
    }

    void postOrderTraverseRecursive(Node<T> traverseNode){
        if(traverseNode == null){
            return;
        }
        this.postOrderTraverseRecursive(traverseNode.left);
        this.postOrderTraverseRecursive(traverseNode.right);
        System.out.print(traverseNode.data + " ");
    }

    void levelOrderTraverseIterative(){
        if(rootNode == null) throw new NullPointerException("Root Tree.Node is Null! Tree empty.");

        LinkedList<Node<T>> levelQueue = new LinkedList<>();
        levelQueue.addLast(rootNode);

        while (!(levelQueue.isEmpty())){
            Node<T> currentNode = levelQueue.getFirst();
            if(currentNode.left != null){
                levelQueue.addLast(currentNode.left);
            }
            if(currentNode.right != null){
                levelQueue.addLast(currentNode.right);
            }
            System.out.print(currentNode.data+ " ");
            levelQueue.removeFirst();
        }
    }

    // Helpers

    public boolean lessThan(T aValue, T bValue){
        // Duplicate insertions not supported
        return aValue.compareTo(bValue) < 0;
    }

    private boolean containsKey(T data){
        Node<T> traverseNode = rootNode; // Start finding from root
        while (traverseNode != null){
            T nodeData = traverseNode.data;
            if (nodeData == data) return true;

            if( lessThan(data,nodeData)){
                traverseNode = traverseNode.left;
            }
            else{
                traverseNode = traverseNode.right;
            }
        }
        /* O(logn) Always case if checked during every insertion. 
        * */
        return false;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>(10);
        // Insert
        testBST.insertNode(5);
        testBST.insertNode(20);
        testBST.insertNode(9);
        testBST.insertNode(8);
        testBST.insertNode(2);
        testBST.insertNode(18);
        testBST.insertNode(21);
        testBST.insertNode(22);
        // Remove leaves
        testBST.removeNode(2);
        testBST.removeNode(8);
        testBST.removeNode(22);
        // Remove half subtree
        testBST.removeNode(9);
        // Removing Full subtree
        testBST.removeNode(20);
        testBST.levelOrderTraverseIterative();
    }

    @Override
    public void insert(Comparable data) {
        this.insert(data);
    }

    @Override
    public void remove(Comparable data) {
        this.removeNode(data);
    }

    @Override
    public boolean containsKey(Comparable data) {
        this.containsKey(data);
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public void printPreorder() {

    }

    @Override
    public void printInorder() {

    }

    @Override
    public void printPostOrder() {

    }

    @Override
    public void printLevelOrder() {

    }
}
