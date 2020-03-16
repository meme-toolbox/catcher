package ml.memelau.catcher.client.additioner.spring;

import lombok.AllArgsConstructor;
import ml.memelau.catcher.client.Additioner;
import ml.memelau.catcher.event.ErrorEvent;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;

@AllArgsConstructor
public class SpringSleuthTraceAdditioner implements Additioner {

    private Tracer tracer;

    @Override
    public void addTo(ErrorEvent errorEvent) {
        Span currentSpan = tracer.getCurrentSpan();
        errorEvent.getAdditions().put("traceId", currentSpan.traceIdString());
        errorEvent.getAdditions().put("traceSpanId", Span.idToHex(currentSpan.getSpanId()));
        errorEvent.getAdditions().put("traceParentId", currentSpan.getParents().isEmpty() ? "null" : Span.idToHex(currentSpan.getParents().get(0)));
    }
}
