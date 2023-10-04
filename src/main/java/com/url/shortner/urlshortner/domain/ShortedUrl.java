package com.url.shortner.urlshortner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortedUrl {

    private static final int MAX_LENGTH = 8;

    @Column(name = "shorted_url", unique = true, length = MAX_LENGTH)
    private String url;

    public ShortedUrl(String shortedUrl) {
        validateShortUrl(shortedUrl);
        this.url = shortedUrl;
    }

    private void validateShortUrl(String shortedUrl) {
        validateEmpty(shortedUrl);
        validateLength(shortedUrl);
    }

    private void validateEmpty(String shortedUrl) {
        if (shortedUrl.isBlank() || Objects.isNull(shortedUrl)) {
            throw new IllegalArgumentException("단축된 URL은 빈칸일 수 없습니다.");
        }
    }

    private void validateLength(String shortedUrl) {
        if (shortedUrl.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("단축된 URL은 최대 8글자를 넘을 수 없습니다.");
        }
    }
}
