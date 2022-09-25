package com.posada.santiago.gamapostsandcomments.application.bus.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
    private String id;
    private String postId;
    private String author;
    private String content;
    private String participantId;


}
