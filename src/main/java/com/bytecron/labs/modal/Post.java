package com.bytecron.labs.modal;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the metadata of a blog post.
 * <p>
 * This class encapsulates common attributes associated with a blog post, such as
 * the title, publication date, excerpt, author, and slug. It can be used for
 * rendering blog previews, generating SEO data, or managing posts in a CMS.
 * </p>
 *
 * <p>
 * This class uses Lombok annotations:
 * <ul>
 *   <li>{@code @Data} – Generates getters, setters, equals, hashCode, and toString methods.</li>
 *   <li>{@code @Builder} – Enables the builder pattern for constructing instances.</li>
 * </ul>
 * </p>
 */
@Data
@Builder
public class Post {
    private String title;
    private String date;
    private String excerpt;
    private String author;
    private String slug;
}
