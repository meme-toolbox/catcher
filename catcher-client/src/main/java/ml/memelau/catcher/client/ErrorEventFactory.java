package ml.memelau.catcher.client;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import ml.memelau.catcher.event.ErrorEvent;
import org.joor.Reflect;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author meme
 */
@AllArgsConstructor
public class ErrorEventFactory {

    private String env;

    private String appName;

    public <T extends ErrorEvent> T newEvent(Class<T> type) {
        T event = Reflect.on(type).create().get();
        Reflect.on(event).set("appName", appName);
        Reflect.on(event).set("env", env);
        Reflect.on(event).set("additions", new HashMap<>());
        Reflect.on(event).set("occurredTime", LocalDateTime.now());
        Reflect.on(event).set("ip", Try.of(() -> InetAddress.getLocalHost().getHostAddress())
                                       .getOrElse("unknown"));
        Reflect.on(event).set("hostname", Try.of(() -> InetAddress.getLocalHost().getHostName())
                                             .getOrElse("unknown"));
        return event;
    }

    public ErrorEvent newEvent(String eventType) {
        ErrorEvent.DefaultErrorEvent event = newEvent(ErrorEvent.DefaultErrorEvent.class);
        event.setEventType(eventType);
        return event;
    }

}
