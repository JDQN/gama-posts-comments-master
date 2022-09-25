package com.posada.santiago.gamapostsandcomments.application.bus.models;


public class Message {

    private final String type;
    private final String body;


    public Message(String type, String body) {
        this.type = type;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public String getBody() {
        return body;
    }


}
