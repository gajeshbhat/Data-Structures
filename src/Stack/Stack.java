package Stack;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class Stack <T> {
    LinkedList<T> stackList = new LinkedList<T>();

    // Create an empty stack
    public Stack() {}

    // Stack.Stack with one element.
    public Stack(T firstElement){
        stackList.addLast(firstElement);
    }

    public void push(T element){
        stackList.addLast(element);
    }

    public void pop(){
        if(stackList.isEmpty()) {
            throw new EmptyStackException();
        }
        stackList.removeLast();
    }

    public T top(){
        if(stackList.isEmpty()) {
            throw new EmptyStackException();
        }
        return stackList.peekLast();
    }

    public int size(){
        return stackList.size();
    }

}
