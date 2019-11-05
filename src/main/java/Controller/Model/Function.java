package Controller.Model;

@FunctionalInterface
public interface Function<T, S, R> {
	R execute(T name, S userInterface);
}
