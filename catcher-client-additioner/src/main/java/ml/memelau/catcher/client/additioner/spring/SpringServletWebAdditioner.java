package ml.memelau.catcher.client.additioner.spring;

import io.vavr.control.Try;
import ml.memelau.catcher.client.Additioner;
import ml.memelau.catcher.event.ErrorEvent;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class SpringServletWebAdditioner implements Additioner {

    @Override
    public void addTo(ErrorEvent errorEvent) {
        Optional.of(RequestContextHolder.currentRequestAttributes())
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .ifPresent(request -> errorEvent.getAdditions().put("requestDetails", resolveRequestDetails(request)));
    }


    private String resolveRequestDetails(HttpServletRequest request) {
        return String.format("%s\n%s\n\n%s", resolveRequestLine(request), resolveHeaders(request), resolveRequestBody(request));
    }

    private String resolveRequestBody(HttpServletRequest request) {
        return Try.of(() -> StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8))
                  .getOrElse("");
    }

    private String resolveHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                          .stream()
                          .flatMap(name -> Collections.list(request.getHeaders(name))
                                                      .stream()
                                                      .map(value -> String.format("%s: %s", name, value)))
                          .collect(joining("\n"));
    }

    private String resolveRequestLine(HttpServletRequest request) {
        return String.format("%s %s%s %s",
                request.getMethod(),
                request.getRequestURI(),
                Objects.nonNull(request.getQueryString()) ? "?".concat(request.getQueryString()) : "",
                request.getProtocol());
    }
}
