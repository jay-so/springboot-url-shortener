package com.url.shortner.urlshortner.domain;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UrlTest {


    @Test
    @Order(1)
    @DisplayName("정상적인 URL이 들어왔을 때, URL 객체를 생성할 수 있다.")
    void successCreateUrlTest() {
        //given
        String originalUrl = "https://jay-so.tistory.com/75";

        //when
        Url url = new Url(originalUrl);

        //then
        assertThat(url).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("비정상적인 URL이 들어왔을 때, URL 객체를 생성할 수 없다.")
    void failCreateUrlTest() {
        // given
        String originalUrl = "htt://jay-so.tistory.com/75";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Url(originalUrl));
    }
}
