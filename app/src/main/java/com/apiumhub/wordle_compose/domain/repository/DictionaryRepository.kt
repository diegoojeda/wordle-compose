package com.apiumhub.wordle_compose.domain.repository

interface DictionaryRepository {
    fun isWordInDictionary(word: String): Boolean
}