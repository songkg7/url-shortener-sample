package com.example.urlshortener.conversion

interface Conversion {

    fun encode(input: Long): String

    fun decode(input: String): Long

}
