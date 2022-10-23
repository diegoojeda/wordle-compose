package com.apiumhub.wordle_compose.domain

import com.apiumhub.wordle_compose.data.WordsRepository

class WordMatcherUseCase(private val wordsRepository: WordsRepository) {
    operator fun invoke(typedWord: String): WordMatchState {
        val todaysWord = wordsRepository.getTodaysWord()
        return WordMatchState(typedWord.mapIndexed { index, char ->
            val foundIndex = todaysWord.indexOf(char, ignoreCase = true)
            Pair(WordleLetter(char.toString()), calculateIndexState(index, foundIndex))
        })
    }

    private fun calculateIndexState(index: Int, foundIndex: Int): LetterState = when (foundIndex) {
        -1 -> LetterState.NOT_INCLUDED
        index -> LetterState.MATCH
        else -> LetterState.INCLUDED
    }
}