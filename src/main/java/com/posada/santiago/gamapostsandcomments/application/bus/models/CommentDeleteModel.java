package com.posada.santiago.gamapostsandcomments.application.bus.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteModel {

	private String postId;
	private String commentId;
}
