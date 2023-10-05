package com.url.shortner.urlshortner.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Url {

    private static final String URL_REGEX = "^(https?:\\/\\/)?([^.][\\da-z\\.-]+\\.[a-z\\.]{2,6}|[\\d\\.]+)([\\/:?=&#]{1}[\\da-z\\.-]+)*[\\/\\?]?$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalUrl;

    @Embedded
    private ShortedUrl shortedUrl;

    @Column(nullable = false)
    private long requestCount = 0L;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Url(String originalUrl) {
        validateUrl(originalUrl);
        this.originalUrl = originalUrl;
    }

    @Builder
    public Url(Long id, String originalUrl, ShortedUrl shortedUrl) {
        validateUrl(originalUrl);
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortedUrl = shortedUrl;
    }

    private void validateUrl(String originalUrl) {
        if (!URL_PATTERN.matcher(originalUrl).matches()) {
            throw new IllegalArgumentException("잘못된 Url 형식입니다." + originalUrl);
        }
    }

    public void countedReqeustCount() {
        this.requestCount++;
    }
}
