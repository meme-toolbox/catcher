package ml.memelau.catcher.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author meme
 */
@Getter
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
     * 发生时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime occurredTime;

    /**
     * 额外信息
     */
    protected Map<String, Object> additions;

    @SneakyThrows
    @Override
    public String toString() {
        return OBJECT_MAPPER.writeValueAsString(this);
    }


    public static class DefaultErrorEvent extends ErrorEvent {
        @Override
        public void setEventType(String eventType) {
            super.setEventType(eventType);
        }

        @Override
        public void setErrorType(String errorType) {
            super.setErrorType(errorType);
        }

        @Override
        public void setErrorMessage(String errorMessage) {
            super.setErrorMessage(errorMessage);
        }

        @Override
        public void setAppName(String appName) {
            super.setAppName(appName);
        }

        @Override
        public void setEnv(String env) {
            super.setEnv(env);
        }

        @Override
        public void setHostname(String hostname) {
            super.setHostname(hostname);
        }

        @Override
        public void setIp(String ip) {
            super.setIp(ip);
        }

        @Override
        public void setOccurredTime(LocalDateTime occurredTime) {
            super.setOccurredTime(occurredTime);
        }

        @Override
        public void setAdditions(Map<String, Object> additions) {
            super.setAdditions(additions);
        }

        @Override
        public String getEventType() {
            return super.getEventType();
        }

        @Override
        public String getErrorType() {
            return super.getErrorType();
        }

        @Override
        public String getErrorMessage() {
            return super.getErrorMessage();
        }

        @Override
        public String getAppName() {
            return super.getAppName();
        }

        @Override
        public String getEnv() {
            return super.getEnv();
        }

        @Override
        public String getHostname() {
            return super.getHostname();
        }

        @Override
        public String getIp() {
            return super.getIp();
        }

        @Override
        public LocalDateTime getOccurredTime() {
            return super.getOccurredTime();
        }

        @Override
        public Map<String, Object> getAdditions() {
            return super.getAdditions();
        }
    }
}
