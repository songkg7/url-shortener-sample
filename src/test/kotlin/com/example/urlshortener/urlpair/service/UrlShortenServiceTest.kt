package com.example.urlshortener.urlpair.service

import com.example.urlshortener.urlpair.entity.UrlPair
import com.example.urlshortener.urlpair.repository.UrlPairRepository
import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class UrlShortenServiceTest : FreeSpec() {

    override fun extensions() = listOf(SpringExtension)
    private val urlPairRepository = mockk<UrlPairRepository>()

    init {
        val urlShortenService = UrlShortenService(urlPairRepository)
        "longUrl 이 이미 DB 에 존재하면" - {
            val urlPair = UrlPair(1, "2TX", "https://www.naver.com")
            every { urlPairRepository.findByLongUrl(any()) } returns Optional.of(urlPair)
            "shortUrl 을 반환한다." {
                val shortUrl = urlShortenService.shorten("https://www.naver.com")
                shortUrl shouldBe urlPair.shortUrl
            }
        }

        "longUrl 이 DB 에 존재하지 않는다면" - {
            every { urlPairRepository.findByLongUrl(any()) } returns Optional.empty()
            "새로운 UrlPair 를 생성하고 shortUrl 을 반환한다." {
                every { urlPairRepository.save(any<UrlPair>()) } returns UrlPair(1, "2TX", "https://www.naver.com")
                val shortUrl = urlShortenService.shorten("https://www.naver.com")
                shortUrl.length shouldBeLessThanOrEqual 7
                verify(exactly = 1) { urlPairRepository.save(any<UrlPair>()) }
            }
        }
    }

}
