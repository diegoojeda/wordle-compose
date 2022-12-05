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
                val indexes =
                    Regex(char.toString(), RegexOption.IGNORE_CASE)
                        .findAll(currentWord)
                        .map { it.range.first }
                        .toList()
                BoardLetter(
                    WordleLetter.FilledWordleLetter(char.toString()),
                    calculateIndexState(index, indexes)
                )
            })
        )
    }

    private fun calculateIndexState(index: Int, foundIndexes: List<Int>): LetterState =
        when {
            index in foundIndexes -> LetterState.MATCH
            foundIndexes.isNotEmpty() -> LetterState.INCLUDED
            else -> LetterState.NOT_INCLUDED
        }
}