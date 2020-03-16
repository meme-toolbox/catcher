package ml.memelau.catcher.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import ml.memelau.catcher.event.java.JavaErrorEvent;

import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class UncaughtExceptionCatcher {

    private CatcherClient client;

    private ErrorEventFactory errorEventFactory;

    private List<Additioner> additioners;

    {
        Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            alarmUncaughtException(throwable);
            Optional.ofNullable(handler)
                    .ifPresent(__ -> handler.uncaughtException(thread, throwable));
        });
    }

    private void alarmUncaughtException(Throwable throwable) {
        JavaErrorEvent event = errorEventFactory.newEvent(JavaErrorEvent.class);
        event.setThrowable(throwable);
        client.send(event, additioners);
    }


}
