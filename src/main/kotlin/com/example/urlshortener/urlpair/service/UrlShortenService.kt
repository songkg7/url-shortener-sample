package com.example.urlshortener.urlpair.service

import com.example.urlshortener.conversion.Base62Conversion
import com.example.urlshortener.conversion.Conversion
import com.example.urlshortener.urlpair.entity.UrlPair
import com.example.urlshortener.urlpair.repository.UrlPairRepository
import com.github.f4b6a3.ulid.Ulid
import org.springframework.stereotype.Service

@Service
class UrlShortenService(
    private val urlPairRepository: UrlPairRepository,
) {

    private val conversion: Conversion = Base62Conversion()

    fun shorten(longUrl: String): String {
        return urlPairRepository.findByLongUrl(longUrl)
            .orElseGet {
                val id = Ulid.fast().mostSignificantBits
                val shortUrl = conversion.encode(id)
                urlPairRepository.save(UrlPair(id, shortUrl, longUrl))
            }.shortUrl
    }

    fun findOriginalUrl(shortUrl: String): String {
        val id = conversion.decode(shortUrl)
        return urlPairRepository.findById(id)
            .map { it.longUrl }
            .orElseThrow()
    }
}
