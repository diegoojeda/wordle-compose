package com.apiumhub.wordle_compose.data

import com.apiumhub.wordle_compose.domain.repository.WordsRepository

class LocalWordsRepository : WordsRepository {
    override fun getTodaysWord(): String = listOf(
        "leona"
    ).random().uppercase()
}