package com.posada.santiago.gamapostsandcomments.application.bus;


import com.google.gson.Gson;
import com.posada.santiago.gamapostsandcomments.application.bus.models.CommentModel;
import com.posada.santiago.gamapostsandcomments.application.bus.models.PostModel;
import com.posada.santiago.gamapostsandcomments.application.bus.models.PostReactionModel;
import com.posada.santiago.gamapostsandcomments.application.controller.SocketController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RabbitMqConsumer {
    private final Gson gson = new Gson();
    private final SocketController controller;

    public static final String PROXY_QUEUE_POST_CREATED = "events.proxy.post.created";
    public static final String PROXY_QUEUE_COMMENT_ADDED = "events.proxy.comment.added";

    public static final String PROXY_QUEUE_POST_DELETED = "events.proxy.post.deleted";
    public static final String PROXY_QUEUE_POST_REACTION_ADDED = "events.proxy.post.reaction.added";


    public RabbitMqConsumer(SocketController controller) {
        this.controller = controller;
    }

    @RabbitListener(queues = PROXY_QUEUE_POST_CREATED)
    public void listenToPostCratedQueue(String message) throws ClassNotFoundException {
			System.out.println(message);
        PostModel post = gson.fromJson(message, PostModel.class);
        controller.sendPostCreated("mainSpace", post);
    }

    @RabbitListener(queues = PROXY_QUEUE_COMMENT_ADDED)
    public void listenToCommentAddedQueue(String message) throws ClassNotFoundException {
        CommentModel comment = gson.fromJson(message, CommentModel.class);
        controller.sendCommentAdded(comment.getPostId(), comment);
    }

    @RabbitListener(queues = PROXY_QUEUE_POST_DELETED)
    public void listenToPostDeletedQueue(String message) throws ClassNotFoundException {
        System.out.println(message);
        String postId = gson.fromJson(message, String.class);
        controller.sendPostDeleted("mainSpace", postId);
    }

    @RabbitListener(queues = PROXY_QUEUE_POST_REACTION_ADDED)
    public void listenToPostReactionAdded(String message) throws ClassNotFoundException {
        System.out.println(message);
        PostReactionModel reaction = gson.fromJson(message, PostReactionModel.class);
        controller.sendPostReaction("mainSpace", reaction);
    }


}
