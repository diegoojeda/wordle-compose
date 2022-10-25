package com.apiumhub.wordle_compose.domain

import com.apiumhub.wordle_compose.data.WordsRepository

class WordMatcherUseCase(private val wordsRepository: WordsRepository) {
    operator fun invoke(typedWord: String): WordMatchState.FilledWordMatchState {
        val todaysWord = wordsRepository.getTodaysWord()
        return WordMatchState.FilledWordMatchState(typedWord.mapIndexed { index, char ->
            val foundIndex = todaysWord.indexOf(char, ignoreCase = true)
            Pair(
                WordleLetter.FilledWordleLetter(char.toString()),
                calculateIndexState(index, foundIndex)
            )
        })
    }

    private fun calculateIndexState(index: Int, foundIndex: Int) =
        when (foundIndex) {
            -1 -> LetterState.NOT_INCLUDED
            index -> LetterState.MATCH
            else -> LetterState.INCLUDED
        }
}