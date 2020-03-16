package ml.memelau.catcher.client.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author meme
 */
public class CatcherFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new BodyForCatcherHttpServletRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {}
}
