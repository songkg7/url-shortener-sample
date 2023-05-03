package com.example.urlshortener.urlpair.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class UrlPair(
    @Id
    val id: Long,
    val shortUrl: String,
    @Column(unique = true)
    val longUrl: String
)
