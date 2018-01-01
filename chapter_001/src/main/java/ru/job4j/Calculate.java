package ru.job4j;

/**
 * This class prints text messages to console
 *
 * @author Roman Baranov (baranov.rp@gmail.com)
 * @version 1
 */

public class Calculate {
    /**
     * Print text message to console
     *
     * @param args the args to output
     */
    public static void main(final String[] args) {
        System.out.println("Hello world!");
    }

    /**
     * Method echo.
     *
     * @param name Your name.
     * @return Echo plus your name.
     */
    public String echo(String name) {
        return "Echo, echo, echo : " + name;
    }
}