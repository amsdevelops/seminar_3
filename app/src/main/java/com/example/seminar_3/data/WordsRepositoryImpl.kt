package com.example.seminar_3.data

class WordsRepositoryImpl : WordsRepository {
    private val words = listOf(
        "каждый",
        "охотник",
        "желает",
        "знать",
        "где",
        "сидит",
        "фазан"
    )

    override fun getAllWords() = words
}