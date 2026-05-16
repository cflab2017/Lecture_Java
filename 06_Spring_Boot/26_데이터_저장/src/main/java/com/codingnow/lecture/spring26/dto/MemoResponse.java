package com.codingnow.lecture.spring26.dto;

import com.codingnow.lecture.spring26.entity.Memo;

public record MemoResponse(Long id, String title, String body) {
    public static MemoResponse from(Memo m) {
        return new MemoResponse(m.getId(), m.getTitle(), m.getBody());
    }
}
