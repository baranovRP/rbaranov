package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {

    private Tree<Integer> tree;

    @Before
    public void setUp() {
        tree = new Tree<>(1);
    }

    @Test
    public void when6ElFindLastThen6() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
            tree.findBy(6).isPresent(),
            is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        tree.add(1, 2);
        assertThat(
            tree.findBy(7).isPresent(),
            is(false)
        );
    }

    @Test
    public void treeIsNotBinaryLeftLeaf() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void treeIsNotBinaryRightLeaf() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        tree.add(3, 8);
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void treeIsBinaryLeftLeaf() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void treeIsBinaryRigthLeaf() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(3, 5);
        tree.add(3, 6);
        assertThat(tree.isBinary(), is(true));
    }
}