package ru.job4j.interview;

import java.util.HashMap;

/**
 * Checker, class check symbol occurrences.
 *
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */
public class Checker {

    private HashMap<Character, Integer> counter = new HashMap<>();

    public boolean checkSymbolOccurrences(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        if (first.length() != second.length()) {
            return false;
        }

        for (char symbol : first.toCharArray()) {
            increaseCounter(symbol);
        }

        for (char symbol : second.toCharArray()) {
            if (!counter.containsKey(symbol)) {
                return false;
            }
            decreaseCounter(symbol);
        }

        return counter.isEmpty();
    }

    private void decreaseCounter(char symbol) {
        int value = counter.get(symbol);
        if (value > 1) {
            counter.put(symbol, counter.get(symbol) - 1);
        } else {
            counter.remove(symbol);
        }
    }

    private void increaseCounter(char symbol) {
        if (!counter.containsKey(symbol)) {
            counter.put(symbol, 1);
        } else {
            counter.put(symbol, counter.get(symbol) + 1);
        }
    }
}
