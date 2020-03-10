package ml.memelau.catcher.client;

import lombok.Builder;
import lombok.NonNull;
import ml.memelau.catcher.event.ErrorEvent;
import reactor.core.publisher.Flux;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author meme
 */
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

    @SafeVarargs
    public final <T extends ErrorEvent> void send(T event, Additioner<? super ErrorEvent>... additioners) {
        send(event, Arrays.asList(additioners));
    }

    public final <T extends ErrorEvent> void send(T event, @NonNull List<Additioner<? super ErrorEvent>> additioners) {
        Optional.ofNullable(event)
                .filter(filters.stream()
                        .<Predicate<ErrorEvent>>map(Predicate.class::cast)
                        .reduce(Predicate::and)
                        .orElse(__ -> true))
                .ifPresent(__ -> {
                    additioners.forEach(additioner -> additioner.addTo(event));
                    httpClient.baseUrl(endpoint)
                              .port(port)
                              .post()
                              .uri(String.format("?access_key=%s", accessKey))
                              .send(ByteBufFlux.fromString(Flux.just(event)
                                                               .map(Objects::toString)))
                              .response()
                              .block();
                });
    }

}
