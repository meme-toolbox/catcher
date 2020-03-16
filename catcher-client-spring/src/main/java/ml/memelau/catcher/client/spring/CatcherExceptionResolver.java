package ml.memelau.catcher.client.spring;

import lombok.RequiredArgsConstructor;
import ml.memelau.catcher.client.Additioner;
import ml.memelau.catcher.client.CatcherClient;
import ml.memelau.catcher.client.ErrorEventFactory;
import ml.memelau.catcher.event.java.JavaErrorEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author meme
 */
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CatcherExceptionResolver implements HandlerExceptionResolver {

    private final ErrorEventFactory errorEventFactory;

    private final CatcherClient catcherClient;

    private final List<Additioner> additioners;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        JavaErrorEvent event = errorEventFactory.newEvent(JavaErrorEvent.class);
        event.setThrowable(ex);
        catcherClient.send(event, additioners);
        return null;
    }


}
