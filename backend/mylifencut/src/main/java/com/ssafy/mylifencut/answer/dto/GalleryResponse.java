package com.ssafy.mylifencut.answer.dto;

import com.ssafy.mylifencut.answer.controller.IsMine;
import com.ssafy.mylifencut.answer.domain.Answer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GalleryResponse {
    private int userId;
    private int answerId;
    private String imgUrl;
    private String contents;
    private Integer like;
    private IsMine isMine;

    public static GalleryResponse of(Answer answer) {
        return GalleryResponse.builder()
            .answerId(answer.getId())
            .imgUrl(answer.getImgUrl())
            .userId(answer.getArticle().getUser().getId())
            .contents(answer.getContents())
            .like(answer.getLikes().size())
            .isMine(IsMine.FALSE)
            .build();
    }

    public void setIsMine() {
        this.isMine = IsMine.TRUE;
    }
}
