package com.url.shortner.urlshortner.respository;

import com.url.shortner.urlshortner.domain.ShortedUrl;
import com.url.shortner.urlshortner.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findOriginalUrlByShortedUrl(ShortedUrl shortedUrl);

}
