package com.apiumhub.wordle_compose.domain.board

import com.apiumhub.wordle_compose.domain.LetterStatus
import com.apiumhub.wordle_compose.domain.WordleLetter

data class BoardLetter(
    val letter: WordleLetter,
    val state: LetterStatus
) {
    val isEmpty get() = letter is WordleLetter.EmptyWordleLetter
    val isCorrect get() = state == LetterStatus.MATCH
    val isMatched get() = state == LetterStatus.MATCH || state == LetterStatus.NOT_INCLUDED || state == LetterStatus.INCLUDED
    val actualLetter get() = letter.letter

    fun setLetter(aLetter: String) =
        copy(letter = WordleLetter.FilledWordleLetter(aLetter), state = LetterStatus.NOT_CHECKED)

    fun deleteLetter() =
        copy(letter = WordleLetter.EmptyWordleLetter, state = LetterStatus.EMPTY)

    override fun toString() = "${actualLetter},${state.name}"

    companion object {
        fun empty() = BoardLetter(WordleLetter.EmptyWordleLetter, LetterStatus.EMPTY)
    }
}