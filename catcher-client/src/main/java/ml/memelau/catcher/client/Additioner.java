package ml.memelau.catcher.client;

import ml.memelau.catcher.event.ErrorEvent;

public interface Additioner {

    void addTo(ErrorEvent errorEvent);

}
