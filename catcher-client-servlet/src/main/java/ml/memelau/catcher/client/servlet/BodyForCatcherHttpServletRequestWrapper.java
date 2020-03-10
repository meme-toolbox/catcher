package ml.memelau.catcher.client.servlet;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author meme
 */
final class BodyForCatcherHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    @SneakyThrows
    public BodyForCatcherHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        copyBody(request);
    }

    private void copyBody(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(request.getInputStream(), outputStream);
        this.body = outputStream.toByteArray();
    }

    @Override
    public ServletInputStream getInputStream() {
        return new ServletInputStreamAdapter(new ByteArrayInputStream(body));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
