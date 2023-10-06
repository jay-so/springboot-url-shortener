package com.url.shortner.urlshortner.respository;

import com.url.shortner.urlshortner.domain.ShortedUrl;
import com.url.shortner.urlshortner.domain.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UrlRepositoryTest {

    @Autowired
    UrlRepository urlRepository;

    @Test
    @DisplayName("생성된 단축 URL을 통해 원래의 URL을 정상적으로 찾는지 테스트한다.")
    void findOriginUrlByShortedUrl() {
        //given
        ShortedUrl shortedUrl = new ShortedUrl("4btrqh");
        Url originUrl = new Url(1L, "https://jay-so.tistory.com/75", shortedUrl);
        urlRepository.save(originUrl);

        //when
        Optional<Url> findOriginalUrl = urlRepository.findOriginalUrlByShortedUrl(shortedUrl);

        //then
        assertThat(findOriginalUrl).isNotNull()
            .get()
            .usingRecursiveComparison()
            .ignoringFields("id", "createdAt")
            .isEqualTo(originUrl);
    }
}
