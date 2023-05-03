package com.example.urlshortener.conversion

import com.example.urlshortener.urlpair.entity.UrlPair
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Base62ConversionTest : FreeSpec({

    val conversion: Conversion = Base62Conversion()

    "숫자를 base62 로 인코딩하여 단축한다" {
        listOf(
            11157L to "2TX",
            110303297108800410L to "89bNoU6w4i"
        ).forEach { (number, expected) ->
            val encode = conversion.encode(number)
            encode shouldBe expected
        }
    }

    "문자열을 base62 로 디코딩하여 원래 숫자를 복원한다" {
        listOf(
            "2TX" to 11157L,
            "89bNoU6w4i" to 110303297108800410L
        ).forEach { (string, expected) ->
            val decode = conversion.decode(string)
            decode shouldBe expected
        }
    }
})
