package com.example.urlshortener.urlpair.repository

import com.example.urlshortener.urlpair.entity.UrlPair
import com.github.f4b6a3.ulid.Ulid
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UrlPairRepository : JpaRepository<UrlPair, Ulid> {
    fun findByLongUrl(longUrl: String): Optional<UrlPair>
}

