package com.posada.santiago.gamapostsandcomments.application.controller;


import co.com.sofka.domain.generic.DomainEvent;
import com.google.gson.Gson;
import com.posada.santiago.gamapostsandcomments.application.bus.models.CommentModel;
import com.posada.santiago.gamapostsandcomments.application.bus.models.Message;
import com.posada.santiago.gamapostsandcomments.application.bus.models.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ServerEndpoint("/retrieve/{correlationId}")
public class SocketController {
    private static final Logger logger = Logger.getLogger(SocketController.class.getName());
    private static Map<String, Map<String, Session>> sessions;
    private final Gson gson = new Gson();


    public SocketController() {
        if (Objects.isNull(sessions)) {
            sessions = new ConcurrentHashMap<>();
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("correlationId") String correlationId) {
        logger.info("Connected from " + correlationId);
        var map = sessions.getOrDefault(correlationId, new HashMap<>());
        map.put(session.getId(), session);
        sessions.put(correlationId, map);
    }

    @OnClose
    public void onClose(Session session, @PathParam("correlationId") String correlationId) {
        sessions.get(correlationId).remove(session.getId());
        logger.info("Desconnect by " + correlationId);
    }

    @OnError
    public void onError(Session session, @PathParam("correlationId") String correlationId, Throwable throwable) {
        sessions.get(correlationId).remove(session.getId());
        logger.log(Level.SEVERE, throwable.getMessage());

    }

    public void sendPostCreated(String correlationId, PostModel model) {
        Message messageModel = new Message("PostCreated", gson.toJson(model));
        var message = gson.toJson(messageModel);
        if (Objects.nonNull(correlationId) && sessions.containsKey(correlationId)) {
            logger.info("sent from " + correlationId);
            logger.info("Message: " + message);

            sessions.get(correlationId).values()
                    .forEach(session -> {
                        try {
                            session.getAsyncRemote().sendText(message);
                        } catch (RuntimeException e) {
                            logger.log(Level.SEVERE, e.getMessage(), e);
                        }
                    });
        }
    }

    public void sendCommentAdded(String correlationId, CommentModel model) {
        Message messageModel = new Message("CommentAdded", gson.toJson(model));
        var message = gson.toJson(messageModel);
        if (Objects.nonNull(correlationId) && sessions.containsKey(correlationId)) {
            logger.info("sent from " + correlationId);
            logger.info("Message: " + message);

            sessions.get(correlationId).values()
                    .forEach(session -> {
                        try {
                            session.getAsyncRemote().sendText(message);
                        } catch (RuntimeException e) {
                            logger.log(Level.SEVERE, e.getMessage(), e);
                        }
                    });
        }
    }

    public void sendPostDeleted(String correlationId, String postId) {
        Message messageModel = new Message("PostDeleted", postId);
        var message = gson.toJson(messageModel);
        if (Objects.nonNull(correlationId) && sessions.containsKey(correlationId)) {
            logger.info("sent from " + correlationId);
            logger.info("Message: " + message);

            sessions.get(correlationId).values()
                    .forEach(session -> {
                        try {
                            session.getAsyncRemote().sendText(message);
                        } catch (RuntimeException e) {
                            logger.log(Level.SEVERE, e.getMessage(), e);
                        }
                    });
        }
    }
}
