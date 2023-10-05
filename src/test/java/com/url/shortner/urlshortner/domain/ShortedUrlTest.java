package com.url.shortner.urlshortner.domain;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShortedUrlTest {

    @Test
    @Order(1)
    @DisplayName("단축 URL이 정상적으로 생성되는지 테스트한다.")
    void createShortedUrlTest() {
        //given
        String shortUrl = "4btrqh";

        //when
        ShortedUrl shortedUrl = new ShortedUrl(shortUrl);

        //then
        assertThat(shortedUrl).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("생성된 단축 URL은 빈 값일 수 없음을 테스트한다.")
    void failCreateShortedUrlNotNullTest() {
        //given
        String shortUrl = " ";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> new ShortedUrl(shortUrl));
    }

    @Test
    @Order(3)
    @DisplayName("생성된 단축 URL은 최대 8자를 넘을 수 없음을 테스트한다.")
    void failCreateShortedUrlOverMaxLengethTest() {
        //given
        String shortUrl = "123456789";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> new ShortedUrl(shortUrl));
    }
}
