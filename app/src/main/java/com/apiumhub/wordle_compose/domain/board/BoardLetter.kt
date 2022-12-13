package com.apiumhub.wordle_compose.domain.board

import com.apiumhub.wordle_compose.domain.LetterState
import com.apiumhub.wordle_compose.domain.WordleLetter

data class BoardLetter(
    val letter: WordleLetter,
    val state: LetterState
) {
    val isEmpty get() = letter is WordleLetter.EmptyWordleLetter
    val isCorrect get() = state == LetterState.MATCH
    val isMatched get() = state == LetterState.MATCH || state == LetterState.NOT_INCLUDED || state == LetterState.INCLUDED
    val actualLetter get() = letter.letter

    fun setLetter(aLetter: String) =
        copy(letter = WordleLetter.FilledWordleLetter(aLetter), state = LetterState.NOT_CHECKED)

    fun deleteLetter() =
        copy(letter = WordleLetter.EmptyWordleLetter, state = LetterState.EMPTY)

    override fun toString() = "${actualLetter},${state.name}"

    companion object {
        fun empty() = BoardLetter(WordleLetter.EmptyWordleLetter, LetterState.EMPTY)
    }
}