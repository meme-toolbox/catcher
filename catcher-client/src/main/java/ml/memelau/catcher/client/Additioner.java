package ml.memelau.catcher.client;

import ml.memelau.catcher.event.ErrorEvent;

public interface Additioner<T extends ErrorEvent> {

    void addTo(T errorEvent);

}
