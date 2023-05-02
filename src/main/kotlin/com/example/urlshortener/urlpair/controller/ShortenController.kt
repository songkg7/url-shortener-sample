package com.example.urlshortener.urlpair.controller

import com.example.urlshortener.urlpair.service.UrlShortenService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ShortenController(
    private val urlShortenService: UrlShortenService
) {

    @PostMapping("/shorten")
    fun shorten(@RequestBody longUrl: String): String {
        return urlShortenService.shorten(longUrl)
    }

}
