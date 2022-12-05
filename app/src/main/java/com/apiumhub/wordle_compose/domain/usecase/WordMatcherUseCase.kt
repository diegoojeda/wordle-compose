package com.apiumhub.wordle_compose.domain.usecase

import com.apiumhub.wordle_compose.domain.LetterState
import com.apiumhub.wordle_compose.domain.WordMatchState
import com.apiumhub.wordle_compose.domain.WordleLetter
import com.apiumhub.wordle_compose.domain.board.BoardLetter
import com.apiumhub.wordle_compose.domain.board.BoardRow
import com.apiumhub.wordle_compose.domain.repository.WordsRepository

class WordMatcherUseCase(private val wordsRepository: WordsRepository) {
    operator fun invoke(typedWord: String): WordMatchState {
        val currentWord = wordsRepository.currentWord
        return WordMatchState(
            BoardRow(typedWord.mapIndexed { index, char ->
                val foundIndex = currentWord.indexOf(char, ignoreCase = true)
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