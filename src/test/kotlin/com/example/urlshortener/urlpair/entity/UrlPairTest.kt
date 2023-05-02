package com.example.urlshortener.urlpair.entity

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class UrlPairTest : FreeSpec({

    "UrlPair 를 생성할 때 longUrl 을 전달하면" - {
        val urlPair = UrlPair.from("thisislongurl?param1=1&param2=2")
        "7자리 이하의 shortUrl 이 생성된다." {
            println(urlPair.shortUrl)
            urlPair.shortUrl.length shouldBeLessThanOrEqual 7
            urlPair.longUrl shouldBe "thisislongurl?param1=1&param2=2"
        }
    }

})
