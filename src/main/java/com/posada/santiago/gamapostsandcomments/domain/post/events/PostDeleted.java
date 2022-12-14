package com.posada.santiago.gamapostsandcomments.domain.post.events;

import co.com.sofka.domain.generic.DomainEvent;

public class PostDeleted extends DomainEvent {

    private String postId;

    public PostDeleted(String postId){
        super("com.alpha.postandcomments.domain.post.events.PostDeleted");
        this.postId = postId;
    }
}
