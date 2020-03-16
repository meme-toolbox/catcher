package ml.memelau.catcher.client;

import io.vavr.control.Try;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ml.memelau.catcher.event.ErrorEvent;
import reactor.core.publisher.Flux;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author meme
 */
@Slf4j
@Builder
public class CatcherClient {

    @NonNull
    private HttpClient httpClient;

    @NonNull
    private String endpoint;

    @NonNull
    private int port;

    @NonNull
    private String accessKey;

    @Builder.Default
    private List<EventFilter<ErrorEvent>> filters = Collections.emptyList();

    private final Additioner[] emptyAdditioners = new Additioner[0];

    public final <T extends ErrorEvent> void send(T event, Additioner... additioners) {
        send(event, Arrays.asList(Objects.nonNull(additioners) ? additioners : emptyAdditioners));
    }

    public final <T extends ErrorEvent> void send(T event, @NonNull List<Additioner> additioners) {
        Try.run(() -> Optional.ofNullable(event)
                              .filter(filters.stream()
                                      .<Predicate<ErrorEvent>>map(Predicate.class::cast)
                                      .reduce(Predicate::and)
                                      .orElse(__ -> true))
                              .ifPresent(__ -> {
                                  additioners.forEach(additioner -> additioner.addTo(event));
                                  httpClient.baseUrl(endpoint)
                                            .port(port)
                                            .post()
                                            .uri(String.format("/?access_key=%s", accessKey))
                                            .send(ByteBufFlux.fromString(Flux.just(event)
                                                                             .map(Objects::toString)))
                                            .response()
                                            .block();
                              }))
           .onFailure(t -> log.warn("An error occur when catcher client report the error event", t));
    }
}
