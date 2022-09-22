package com.posada.santiago.gamapostsandcomments.application.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.ServletContext;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
