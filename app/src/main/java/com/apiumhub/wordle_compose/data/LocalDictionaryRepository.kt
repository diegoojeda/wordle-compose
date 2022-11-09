package com.apiumhub.wordle_compose.data

import com.apiumhub.wordle_compose.domain.repository.DictionaryRepository

class LocalDictionaryRepository(private val dictionaryProvider: DictionaryProvider) :
    DictionaryRepository {
    override fun isWordInDictionary(word: String): Boolean =
        dictionaryProvider.dictionary.contains(word, true)

    private fun List<String>.contains(string: String, ignoreCase: Boolean = true): Boolean {
        this.forEach {
            if (it.equals(string, ignoreCase))
                return true
        }
        return false
    }
}