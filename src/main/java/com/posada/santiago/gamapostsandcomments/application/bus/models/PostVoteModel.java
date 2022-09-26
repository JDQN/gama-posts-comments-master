package com.posada.santiago.gamapostsandcomments.application.bus.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostVoteModel {
    private String postId;
    private String relevantVote;
}
