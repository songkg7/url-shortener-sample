package com.example.urlshortener.urlpair.controller

import com.example.urlshortener.urlpair.dto.ShortenRequest
import com.example.urlshortener.urlpair.dto.ShortenResponse
import com.example.urlshortener.urlpair.service.UrlShortenService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class ShortenController(
    private val urlShortenService: UrlShortenService
) {

    @PostMapping("/shorten")
    fun shorten(@RequestBody request: ShortenRequest): ResponseEntity<ShortenResponse> {
        val url = urlShortenService.shorten(request.longUrl)
        return ResponseEntity.ok(ShortenResponse(url))
    }

    @GetMapping("{shortUrl}")
    fun redirect(@PathVariable shortUrl: String): ResponseEntity<Unit> {
        urlShortenService.redirect(shortUrl)?.let {
            return ResponseEntity.status(302).header(HttpHeaders.LOCATION, it).build()
        }
        return ResponseEntity.notFound().build()
    }

}
