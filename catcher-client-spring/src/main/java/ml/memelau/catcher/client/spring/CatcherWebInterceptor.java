package ml.memelau.catcher.client.spring;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import ml.memelau.catcher.client.Additioner;
import ml.memelau.catcher.client.CatcherClient;
import ml.memelau.catcher.event.ErrorEvent;
import ml.memelau.catcher.event.java.WebErrorEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CatcherWebInterceptor extends AbstractHandlerExceptionResolver {

    private final String env;

    private final String appName;

    private final String ip = Try.of(() -> InetAddress.getLocalHost().getHostAddress())
                                 .getOrElse("unknown");

    private final String hostname = Try.of(() -> InetAddress.getLocalHost().getHostName())
                                       .getOrElse("unknown");

    private final CatcherClient catcherClient;

    private final List<Additioner<? super ErrorEvent>> additioners;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        WebErrorEvent event = ErrorEvent.newEvent(WebErrorEvent.class, appName, env, hostname, ip);
        event.setThrowable(ex);
        event.setRequestDetails(resolveRequestDetails(request));
        catcherClient.send(event, additioners);
        return null;
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
