package ml.memelau.catcher.client.spring;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import ml.memelau.catcher.client.Additioner;
import ml.memelau.catcher.client.CatcherClient;
import ml.memelau.catcher.event.ErrorEvent;
import ml.memelau.catcher.event.java.WebErrorEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.List;

@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CatcherExceptionResolver implements HandlerExceptionResolver {

    private final String env;

    private final String appName;

    private final String ip = Try.of(() -> InetAddress.getLocalHost().getHostAddress())
                                 .getOrElse("unknown");

    private final String hostname = Try.of(() -> InetAddress.getLocalHost().getHostName())
                                       .getOrElse("unknown");

    private final CatcherClient catcherClient;

    private final List<Additioner> additioners;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        WebErrorEvent event = ErrorEvent.newEvent(WebErrorEvent.class, appName, env, hostname, ip);
        event.setThrowable(ex);
        catcherClient.send(event, additioners);
        return null;
    }


}
