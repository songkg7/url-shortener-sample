package com.example.urlshortener.urlpair.service

import com.example.urlshortener.urlpair.entity.UrlPair
import com.example.urlshortener.urlpair.repository.UrlPairRepository
import org.springframework.stereotype.Service

@Service
class UrlShortenService(
    private val urlPairRepository: UrlPairRepository,
) {

    fun shorten(longUrl: String): String {
        return urlPairRepository.findByLongUrl(longUrl)
            .orElseGet { urlPairRepository.save(UrlPair.from(longUrl)) }
            .shortUrl
    }
}
