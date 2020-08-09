package List;

public class DoublyLinkedList<T> {

    Node<T> headNode;
    Node<T> tailNode;
    private int size;

    public DoublyLinkedList(){
        headNode = new Node<>(null,null,null);
        tailNode = new Node<>(null,null,null);
        size = 0;
    }
    public class Node<T>{
        Node<T> next;
        Node<T> prev;
        T data;

        Node(T data,Node<T> next,Node<T> prev){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public Node<T> createNode(T data){
        Node<T> newNode = new Node<>(data,null,null);
        return newNode;
    }

    public void addLast(T data){
        // First element
        if(headNode.next == null){
            Node<T> newNode = createNode(data);
            newNode.prev = headNode;
            newNode.next = tailNode;
            headNode.next = newNode;
            tailNode.prev = newNode;
        }
        else {
            // Append at the end
            Node<T> newNode = createNode(data);
            newNode.prev = tailNode.prev;
            newNode.next = tailNode;
            tailNode.prev.next = newNode;
            tailNode.prev = newNode;
        }
        size++;
    }

    public void addFirst(T data){
        // First element
        if(headNode.next == null){
            Node<T> newNode = createNode(data);
            newNode.prev = headNode;
            newNode.next = tailNode;
            headNode.next = newNode;
            tailNode.prev = newNode;
        }
        else {
            // Append at the end
            Node<T> newNode = createNode(data);
            newNode.prev = headNode;
            newNode.next = headNode.next;
            headNode.next = newNode;
        }
        size++;
    }

    public void add(T element, int position){
        if(position > size || position <= 0){
            throw new IndexOutOfBoundsException("Position Greater than the size of the list");
        }
        else if(position == size){
            this.addLast(element);
        }
        else if(position == 1){
            this.addFirst(element);
        }
        else{
            int travIdx =  0;
            Node<T> travRef = headNode;

            while(travIdx != position){
                travRef = travRef.next;
                travIdx++;
            }
            Node<T> newNode = createNode(element);
            newNode.prev = travRef.prev;
            newNode.next = travRef;
            travRef.prev.next = newNode;
            travRef.prev = newNode;
        }
        size++;
    }

    public void removeFirst(){
        if(headNode.next == null){
            System.out.println("Empty list");
        }
        else{
            Node<T> tempNode = headNode.next.next;
            tempNode.prev = headNode;
            headNode.next = null;
            headNode.next = tempNode;
        }
        size--;
    }
    public void removeLast(){
        if(headNode.next == null){
            System.out.println("Empty list");
        }
        else{
            Node<T> tempTail = tailNode;
            tailNode.prev.next = null;
            tailNode = tempTail.prev;
            tempTail = null;
        }
        size--;
    }

    public void remove(T element){
        Node<T> traverseNode = headNode;
        while(traverseNode.data != element && traverseNode.next != null){
            traverseNode = traverseNode.next;
        }
        if(traverseNode.next == null){
            System.out.println("No such element in the list!");
        }
        else if(traverseNode.prev == headNode){
            removeFirst();
        }
        else if(traverseNode == tailNode){
            removeLast();
        }
        else{
            traverseNode.prev.next = traverseNode.next;
            traverseNode.next.prev = traverseNode.prev;
            traverseNode = null;
        }
        size--;
    }

    public void printList(){
        if(headNode.next == null){
            System.out.println("Empty list!");
        }
        Node<T> traverseNode = headNode.next;
        while(traverseNode.next != null){
            System.out.print(traverseNode.data + " ");
            traverseNode = traverseNode.next;
        }
    }

    public int getSize(){
        return size;
    }

    public static void main(String[] args) {
    }
}
