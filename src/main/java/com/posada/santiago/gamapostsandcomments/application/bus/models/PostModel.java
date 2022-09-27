package com.posada.santiago.gamapostsandcomments.application.bus.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PostModel {

    private String id;
    private String aggregateId;
    private String author;
    private String title;
    private String photoUrl;
    private String relevanceVote;
    private String participantId;
    private Boolean deleted;
    private List<CommentModel> comments;
    private List<String> reactions;
    private LocalDateTime creationDate;
    private String dateFormated;


    public PostModel() {
        this.comments = new ArrayList<>();
    }
}
