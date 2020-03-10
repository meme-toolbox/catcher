package ml.memelau.catcher.client;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import ml.memelau.catcher.event.ErrorEvent;
import ml.memelau.catcher.event.java.JavaErrorEvent;

import java.net.InetAddress;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class UncaughtExceptionCatcher {

    @NonNull
    private CatcherClient client;

    @NonNull
    private String env;

    @NonNull
    private String appName;

    private final String ip = Try.of(() -> InetAddress.getLocalHost().getHostAddress())
                                 .getOrElse("unknown");

    private final String hostname = Try.of(() -> InetAddress.getLocalHost().getHostName())
                                       .getOrElse("unknown");

    {
        Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            alarmUncaughtException(throwable);
            Optional.ofNullable(handler)
                    .ifPresent(__ -> handler.uncaughtException(thread, throwable));
        });
    }

    private void alarmUncaughtException(Throwable throwable) {
        JavaErrorEvent event = ErrorEvent.newEvent(JavaErrorEvent.class, appName, env, hostname, ip);
        event.setThrowable(throwable);
        client.send(event);
    }


}
