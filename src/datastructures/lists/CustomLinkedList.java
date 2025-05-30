package datastructures.lists;

import datastructures.interfaces.LinkedList;

import java.util.NoSuchElementException;

public class CustomLinkedList<T> implements LinkedList<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head, tail;
    private int size = 0;
    private final int capacity = 1000;

    @Override
    public void addFirst(T t) {
        if (size == capacity) {
            throw new IllegalStateException("Capacity exceeded");
        }
        Node<T> temp = new Node<>(t);
        if (head == null) {
            head = tail = temp;
        } else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
        size++;
    }

    @Override
    public void addLast(T t) {
        if (size == capacity) {
            throw new IllegalStateException("Capacity exceeded");
        }
        Node<T> temp = new Node<>(t);
        if (tail == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        size++;
    }

    @Override
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("No such element");
        }
        T result = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return result;
    }

    @Override
    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("No such element");
        }
        T result = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return result;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("No such element");
        }
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("No such element");
        }
        return tail.data;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public Object set(int index, Object element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        T data = temp.data;
        temp.data = (T) element;
        return data;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(Object o) {
        if(size == capacity) {
            throw new IllegalStateException("Capacity exceeded");
        }
        if(o == null) {
            throw new NullPointerException();
        }

        Node<T> temp = new Node<>((T) o);
        if (tail == null) {
            tail = head = temp;
        }
        else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        size++;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> temp = head;
        while (temp != null) {
            if ((o == null && temp.data == null) || (o != null && o.equals(temp.data))) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        Node<T> temp = head;
        while (temp != null) {
            if (o.equals(temp.data)) {
                if (temp == head) {
                    removeFirst();
                } else if (temp == tail) {
                    removeLast();
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                    size--;
                }
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void display() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
