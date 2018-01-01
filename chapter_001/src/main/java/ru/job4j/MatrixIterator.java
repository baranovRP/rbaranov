package ru.job4j;

/**
 * An iterator over a two-dimensional array (matrix).
 *
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class MatrixIterator implements Iterator {
    private static final int LINE = 0;
    private static final int COLUMN = 1;

    private int[][] items;
    private int[] position = new int[]{0, 0};

    public MatrixIterator(int[][] items) {
        this.items = items;
    }

    @Override
    public Object next() {
        int item = items[position[LINE]][position[COLUMN]];
        setPosition();
        return item;
    }

    @Override
    public boolean hasNext() {
        return position[LINE] <= items.length
            && (position[LINE] != items.length || position[COLUMN] < items[LINE].length)
            && Integer.valueOf(items[position[LINE]][position[COLUMN]]) != null;
    }

    private void setPosition() {
        if (position[COLUMN] < items[position[LINE]].length) {
            position[COLUMN]++;
        } else {
            position[LINE]++;
            position[COLUMN] = 0;
        }
    }
}
