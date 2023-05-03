package com.example.urlshortener.urlpair.dto

data class ShortenRequest(
    val longUrl: String
) {
    private constructor() : this("")
}
