package main;

@FunctionalInterface
/**
 * @author Connor Lehmacher
 */
public interface Returnable<T> {
	public T returnValue();
}
