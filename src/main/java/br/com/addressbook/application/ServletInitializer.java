package br.com.addressbook.application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * It binds Servlet, Filter and ServletContextInitializer beans from
 * the application context to the server.
 * 
 * @author Diego Caldeira
 * @version 1.0 - 26 Jun 2021
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}





