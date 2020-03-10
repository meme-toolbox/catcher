package ml.memelau.catcher.event.java;

/**
 * @author meme
 */
public class WebErrorEvent extends JavaErrorEvent {

    protected WebErrorEvent() {
        this.eventType = "java_web";
    }

    public void setRequestDetails(String requestDetails) {
        this.additions.put("requestDetails", requestDetails);
    }


}
