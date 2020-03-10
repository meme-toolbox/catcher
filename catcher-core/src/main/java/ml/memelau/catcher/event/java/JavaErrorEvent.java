package ml.memelau.catcher.event.java;

import io.vavr.collection.Stream;
import ml.memelau.catcher.event.ErrorEvent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * @author meme
 */
public class JavaErrorEvent extends ErrorEvent {

    protected JavaErrorEvent() {
        this.eventType = "java";
    }

    public void setThrowable(Throwable throwable) {
        this.errorType = throwable.getClass().getName();
        this.errorMessage = fetchThrowableMessage(throwable);
        this.additions.put("exceptionClassInherits", fetchThrowableInherits(throwable));
    }


    private String fetchThrowableMessage(Throwable throwable) {
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    @SuppressWarnings("rawtypes")
    private List<String> fetchThrowableInherits(Throwable throwable) {
        return Stream.iterate((Class) throwable.getClass(), Class::getSuperclass)
                     .takeUntil(Object.class::equals)
                     .map(Class::getName)
                     .toJavaList();
    }



}
