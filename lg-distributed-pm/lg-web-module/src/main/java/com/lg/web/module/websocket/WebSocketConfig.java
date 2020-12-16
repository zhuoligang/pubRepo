package com.lg.web.module.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeauty.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
    	ServerEndpointExporter server = new ServerEndpointExporter();
//    	server.
        return server;
    }
}