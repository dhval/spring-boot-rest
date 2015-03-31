package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter implements Filter
{
  private static final Logger LOG = LoggerFactory.getLogger(CORSFilter.class);
  private String mode = "DENY";

  /**
   * Add X-FRAME-OPTIONS response header to tell IE8 (and any other browsers who
   * decide to implement) not to display this content in a frame. For details, please
   * refer to http://blogs.msdn.com/sdl/archive/2009/02/05/clickjacking-defense-in-ie8.aspx.
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse res = (HttpServletResponse)response;
    res.addHeader("X-FRAME-OPTIONS", mode );
    String host = "http://" + request.getServerName() + ":" + request.getServerPort();
    res.addHeader("host", host);
    res.addHeader("Access-Control-Allow-Origin", "*");
    res.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept" );
    chain.doFilter(request, response);
  }

  public void destroy() {
  }

  public void init(FilterConfig filterConfig) {
    String configMode = filterConfig.getInitParameter("mode");
    if ( configMode != null ) {
      mode = configMode;
    }
  }

}
