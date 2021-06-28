package br.com.addressbook.application.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Container for CORS configuration along with methods to check against the
 * actual origin, HTTP methods, and headers of a given request.
 *
 * @author Diego Caldeira
 * @version 1.0 - 26 Jun 2021
 */
@Configuration
public class CorsConfiguration {

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");

        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Accept");

        // Allowed http methods.
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.OPTIONS);

        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);

        return bean;
    }
}


