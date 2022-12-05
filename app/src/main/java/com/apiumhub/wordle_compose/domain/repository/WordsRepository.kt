package com.apiumhub.wordle_compose.domain.repository

interface WordsRepository {
    suspend fun loadNextWord()
    var currentWord: String
}