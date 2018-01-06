package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterable linked list collection with fail-fast behavior.
 *
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class LinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int modCount;

    public LinkedList() {
        this.head = new Node<>(null, null);
        size = 0;
    }

    public LinkedList(E... args) {
        this();
        for (int i = 0; i < args.length; i++) {
            add(args[i]);
        }
    }

    public void add(E value) {
        Node<E> temp = new Node<>(value, null);
        if (size == 0) {
            head = temp;
            tail = temp;
        } else {
            Node<E> current = tail;
            tail = temp;
            current.next = tail;
        }
        modCount++;
        size++;
    }

    public E get(int index) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return (E) current.value;
            }
            current = current.next;
        }
        throw new NoSuchElementException();
    }

    public int size() {
        return size;
    }


    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;

        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                checkContainerModification();
                return current != null;
            }

            @Override
            public E next() {
                checkContainerModification();
                if (hasNext()) {
                    E value = current.value;
                    current = current.next;
                    return (E) value;
                }
                throw new NoSuchElementException();
            }

            private void checkContainerModification() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private class Node<E> {

        private E value;
        private Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
