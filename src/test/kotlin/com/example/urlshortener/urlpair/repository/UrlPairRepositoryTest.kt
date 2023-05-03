package com.example.urlshortener.urlpair.repository

import com.example.urlshortener.urlpair.entity.UrlPair
import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UrlPairRepositoryTest(
    private val urlPairRepository: UrlPairRepository
) : FreeSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        "findByLongUrl" - {
            "longUrl 이 존재하면" - {
                urlPairRepository.save(UrlPair(1, "2TX", "https://www.naver.com"))
                "UrlPair 를 반환한다." {
                    val urlPair = urlPairRepository.findByLongUrl("https://www.naver.com")
                    urlPair.isPresent shouldBe true
                    urlPair.get().longUrl shouldBe "https://www.naver.com"
                    urlPair.get().shortUrl shouldNotBe null
                }
            }
            "longUrl 이 존재하지 않으면" - {
                "빈 Optional 을 반환한다." {
                    val urlPair = urlPairRepository.findByLongUrl("https://www.google.com")
                    urlPair.isPresent shouldBe false
                }
            }
        }
    }

}
