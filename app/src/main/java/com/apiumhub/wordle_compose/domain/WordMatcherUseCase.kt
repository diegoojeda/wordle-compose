package com.apiumhub.wordle_compose.domain

import com.apiumhub.wordle_compose.data.WordsRepository
import com.apiumhub.wordle_compose.domain.board.BoardLetter
import com.apiumhub.wordle_compose.domain.board.BoardRow

class WordMatcherUseCase(private val wordsRepository: WordsRepository) {
    operator fun invoke(typedWord: String): WordMatchState {
        val todaysWord = wordsRepository.getTodaysWord()
        return WordMatchState(
            BoardRow(typedWord.mapIndexed { index, char ->
                val foundIndex = todaysWord.indexOf(char, ignoreCase = true)
                BoardLetter(
                    WordleLetter.FilledWordleLetter(char.toString()),
                    calculateIndexState(index, foundIndex)
                )
            })
        )
    }

    private fun calculateIndexState(index: Int, foundIndex: Int) =
        when (foundIndex) {
            -1 -> LetterState.NOT_INCLUDED
            index -> LetterState.MATCH
            else -> LetterState.INCLUDED
        }
}