package com.apiumhub.wordle_compose.data

import com.apiumhub.wordle_compose.domain.repository.WordsRepository

class RemoteWordsRepository(private val api: KtorApi) : WordsRepository {
    override var currentWord: String = ""

    override suspend fun loadNextWord() {
        currentWord = api.getNextWord().first()
    }
}