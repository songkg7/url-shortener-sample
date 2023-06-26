package com.example.urlshortener.urlpair.controller

import com.example.urlshortener.urlpair.dto.ShortenRequest
import com.example.urlshortener.urlpair.service.UrlShortenService
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class ShortenControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var urlShortenService: UrlShortenService

    private val objectMapper = ObjectMapper()

    @Test
    fun givenLongUrl_thenReturn_ShortenUrl() {
        val longUrl = "https://www.naver.com"
        val shortenUrl = "2TX"
        every { urlShortenService.shorten(longUrl) } returns shortenUrl

        mockMvc.perform(
            post("/api/v1/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ShortenRequest(longUrl)))
        )
            .andExpect(status().isOk)
            .andExpect(content().json("""{"shortUrl": "$shortenUrl"}"""))
    }

    @Test
    fun givenShortUrl_whenRedirect_thenOriginalUrl() {
        val shortUrl = "2TX"
        val originalUrl = "https://www.naver.com"
        every { urlShortenService.findOriginalUrl(shortUrl) } returns originalUrl

        mockMvc.perform(
            get("/${shortUrl}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().is3xxRedirection)

    }

}
