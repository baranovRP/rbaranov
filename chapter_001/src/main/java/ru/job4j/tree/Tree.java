package ru.job4j.tree;

import java.util.*;

/**
 * Simple structure of tree.
 *
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public Tree(E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        return isBinary(this.root.leaves());
    }

    private boolean isBinary(List<Node<E>> leaves) {
        if (leaves.size() == 0) {
            return true;
        }

        List<Node<E>> children = new LinkedList<>();
        for (Node<E> leaf : leaves) {
            List<Node<E>> nodes = leaf.leaves();

            if (nodes.size() > 2) {
                return false;
            } else {
                children.addAll(nodes);
            }
        }

        return isBinary(children);
    }

    @Override
    public boolean add(E parent, E child) {
        if (findBy(child).isPresent()) {
            return false;
        }
        if (!findBy(parent).isPresent()) {
            return false;
        }

        Node<E> ancestor = findBy(parent).get();
        ancestor.add(new Node<>(child));
        return true;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
