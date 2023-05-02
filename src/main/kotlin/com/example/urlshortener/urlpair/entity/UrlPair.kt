package com.example.urlshortener.urlpair.entity

import com.github.f4b6a3.ulid.Ulid
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigInteger

private fun Ulid.toBigInteger(): BigInteger {
    var integer = this.mostSignificantBits.toBigInteger()
    // integer 가 요구사항 최소 조건인 3.5조 보다 크면 작아질 때까지 소수로 나눠준다.
    while (integer > BigInteger.valueOf(62).pow(7)) {
        integer /= BigInteger.valueOf(7)
    }
    return integer
}

private const val BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

@Entity
class UrlPair(
    @Id
    val id: Ulid = Ulid.fast(),
    val shortUrl: String,
    @Column(unique = true)
    val longUrl: String
) {

    companion object {
        fun from(longUrl: String): UrlPair {
            val id = Ulid.fast()
            val shortUrl = generateShortUrl(id)
            return UrlPair(
                id = id,
                shortUrl = shortUrl,
                longUrl = longUrl
            )
        }

        private fun generateShortUrl(id: Ulid): String {
            val sb = StringBuilder()
            var num = id.toBigInteger()
            while (num > BigInteger.ZERO) {
                val remainder = num % BigInteger.valueOf(62)
                sb.append(BASE62[remainder.toInt()])
                num /= BigInteger.valueOf(62)
            }
            return sb.toString()
        }

    }

}
