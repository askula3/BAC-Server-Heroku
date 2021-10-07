package ba.bac.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {
    @Value("${allowed_origin}")
    private String ALLOWED_ORIGIN;
    private String[] ALLOWED_ORIGINS;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        // Check allowed origins
        String origin = request.getHeader("origin");
        for (String allowed_origin : ALLOWED_ORIGINS) {
            if (allowed_origin.equals(origin))
                response.setHeader("Access-Control-Allow-Origin", origin);
        }

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type, Origin, Access-Control-Allow-Headers, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, X-Requested-With, X-Auth-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (!Objects.equals(request.getMethod(), "OPTIONS")) {
            try {
                chain.doFilter(req, res);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }
    }

    public void init(FilterConfig filterConfig) {
        ALLOWED_ORIGINS = ALLOWED_ORIGIN.split(",");
    }

    public void destroy() {
    }
    /*
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new org.springframework.web.filter.CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
    */
}
