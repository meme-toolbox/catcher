package ml.memelau.catcher.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultErrorEvent extends ErrorEvent {

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
    protected String getEventType() {
        return super.getEventType();
    }

    @Override
    protected String getErrorType() {
        return super.getErrorType();
    }

    @Override
    protected String getErrorMessage() {
        return super.getErrorMessage();
    }

    @Override
    protected Map<String, Object> getAdditions() {
        return super.getAdditions();
    }

    @Override
    protected void setEventType(String eventType) {
        super.setEventType(eventType);
    }

    @Override
    protected void setErrorType(String errorType) {
        super.setErrorType(errorType);
    }

    @Override
    protected void setErrorMessage(String errorMessage) {
        super.setErrorMessage(errorMessage);
    }

    @Override
    protected void setAdditions(Map<String, Object> additions) {
        super.setAdditions(additions);
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
}
