package ml.memelau.catcher.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author meme
 */
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class ErrorEvent {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 错误事件类型
     */
    protected String eventType;

    /**
     * 错误类型
     */
    protected String errorType;

    /**
     * 错误详情
     */
    protected String errorMessage;

    /**
     * 应用名称
     */
    protected String appName;

    /**
     * 环境
     */
    protected String env;

    /**
     * 主机名
     */
    protected String hostname;

    /**
     * ip
     */
    protected String ip;

    /**
     * 额外信息
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    protected Map<String, Object> additions = new HashMap<>();

    @SneakyThrows
    public static <T extends ErrorEvent> T newEvent(Class<T> type, String appName, String env, String hostname, String ip) {
        Constructor<T> constructor = type.getDeclaredConstructor();
        constructor.setAccessible(true);
        T event = constructor.newInstance();
        event.setAppName(appName);
        event.setEnv(env);
        event.setHostname(hostname);
        event.setIp(ip);
        return event;
    }

    @SneakyThrows
    public static ErrorEvent newEvent() {
        return new DefaultErrorEvent();
    }

    @SneakyThrows
    @Override
    public String toString() {
        return OBJECT_MAPPER.writeValueAsString(this);
    }
}
