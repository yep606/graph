package base;

import java.util.EmptyStackException;

public class Stack<E> {

    private int mSize; //mSize - максимальный размер
    private Object[] stackArray;
    private int top;

    public Stack(int m) {
        this.mSize = m;
        stackArray = new Object[mSize];
        top = -1;
    }

    public void push(E element) {
        if (!this.isFull()) {
            ++top;
            stackArray[top] = element;
        }
        else
            System.out.println("Ошибка: стек заполнен");
    }

    public E pop() {
        if (this.empty())
            throw new EmptyStackException();
        return (E) stackArray[top--];
    }

    public boolean empty() {
        return (top == -1);
    }

    public void printStack() {
        if (this.empty()) {
            return;
        }
        E elem = this.pop();
        System.out.println(elem);
        printStack();
        this.push(elem);
    }

    public boolean isFull() {
        return top == mSize - 1;
    }
}
