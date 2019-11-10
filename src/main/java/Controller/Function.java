package Controller;

@FunctionalInterface
public interface Function<T, S, R> {
	R execute(T name, S userInterface);
}
