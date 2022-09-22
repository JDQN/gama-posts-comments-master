package com.posada.santiago.gamapostsandcomments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class GamaPostsAndCommentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamaPostsAndCommentsApplication.class, args);
	}

}
