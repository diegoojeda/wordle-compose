package com.apiumhub.wordle_compose.domain.board

import com.apiumhub.wordle_compose.domain.LetterState
import com.apiumhub.wordle_compose.domain.WordleLetter

data class BoardLetter(
    val letter: WordleLetter,
    val state: LetterState
) {
    fun isEmpty() = letter is WordleLetter.EmptyWordleLetter

    fun setLetter(aLetter: String): BoardLetter =
        this.copy(
            letter = WordleLetter.FilledWordleLetter(aLetter),
            state = LetterState.NOT_CHECKED
        )

    fun deleteLetter() =
        this.copy(letter = WordleLetter.EmptyWordleLetter, state = LetterState.EMPTY)

    fun isMatched() =
        state == LetterState.MATCH || state == LetterState.NOT_INCLUDED || state == LetterState.INCLUDED

    fun getLetter(): String = letter.letter

    override fun toString(): String {
        return "${letter.letter},${state.name}"
    }

    companion object {
        fun empty() = BoardLetter(WordleLetter.EmptyWordleLetter, LetterState.EMPTY)
    }
}