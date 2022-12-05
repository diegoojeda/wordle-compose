package com.apiumhub.wordle_compose.data

import com.apiumhub.wordle_compose.domain.repository.WordsRepository

//Local repository just for quick testing purposes, the word typed here is the one that you'll need to guess
class LocalWordsRepository : WordsRepository {
    override var currentWord: String = "leona"

    override suspend fun loadNextWord() {
        //NoOp
    }
}