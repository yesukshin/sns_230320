package com.sns.like.domain;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class LIKE {

	private int postId;
	private int userId;
	private ZonedDateTime createdAt;

}