package ml.memelau.catcher.client;

import ml.memelau.catcher.event.ErrorEvent;

/**
 * @author meme
 */
@FunctionalInterface
public interface Additioner {

    void addTo(ErrorEvent errorEvent);

}
