package com.bytecron.cronicle.modal;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Post {
    private String title;
    private String date;
    private String excerpt;
    private String author;
    private String slug;
    private String content;
}
