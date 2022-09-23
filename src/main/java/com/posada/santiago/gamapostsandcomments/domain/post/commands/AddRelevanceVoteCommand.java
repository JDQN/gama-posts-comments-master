package com.posada.santiago.gamapostsandcomments.domain.post.commands;


import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRelevanceVoteCommand extends Command {

    private String postId;
    private String vote;

}
