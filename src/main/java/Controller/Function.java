package Controller;

/**
 * This Functional Interface has one Method tht takes to Arguments and returns what ever you see fit.
 *
 * @param <T> Some kind of Type you see fit.
 * @param <S> Some kind of lambda.
 * @param <R> What ever you want to return.
 *
 * @author Manuel Werder
 * @version 0.1
 */
@FunctionalInterface
public interface Function<T, S, R> {
	R execute(T name, S userInterface);
}
