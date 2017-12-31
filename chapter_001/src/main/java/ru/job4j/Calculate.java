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
     * @param args the args to output
     */
	public static void main(final String[] args) {
		System.out.println("Hello world!");
	}

    /**
     * Triples the text message
     * @param value the value for tripling
     * @return formatted text message
     */
	public final String echo(final String value) {
		if (value != null) {
			value = String.format("%s value = ", value);
		} else {
			value = "value = null";
		}
		return String.format("%s %s %s", value, value, value);
	}
}