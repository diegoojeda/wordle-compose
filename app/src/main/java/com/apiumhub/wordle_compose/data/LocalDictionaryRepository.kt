package com.apiumhub.wordle_compose.data

import com.apiumhub.wordle_compose.domain.repository.DictionaryRepository

class LocalDictionaryRepository(private val dictionaryProvider: DictionaryProvider) :
    DictionaryRepository {
    override fun isWordInDictionary(word: String): Boolean =
        dictionaryProvider.dictionary.contains(word)
}