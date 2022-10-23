package com.apiumhub.wordle_compose.data

interface WordsRepository {
    fun getTodaysWord(): String
}

class LocalWordsRepository : WordsRepository {
    override fun getTodaysWord(): String = listOf(
        "Apium"
    ).random().uppercase()
}