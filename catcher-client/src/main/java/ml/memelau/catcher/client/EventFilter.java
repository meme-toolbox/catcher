package ml.memelau.catcher.client;

import ml.memelau.catcher.event.ErrorEvent;

import java.util.function.Predicate;

/**
 * @author meme
 * @param <T>
 */
@FunctionalInterface
public interface EventFilter<T extends ErrorEvent> extends Predicate<T> {

    default boolean filter(T event) {
        return test(event);
    }

}
