package com.apiumhub.wordle_compose.domain

import com.apiumhub.wordle_compose.domain.board.BoardRow

enum class LetterStatus {
    EMPTY,
    NOT_CHECKED,
    NOT_INCLUDED,
    INCLUDED,
    MATCH
}

sealed class WordleLetter(open val letter: String) {

    object EmptyWordleLetter : WordleLetter("")

    data class FilledWordleLetter(override val letter: String) : WordleLetter(letter) {
        init {
            if (letter.count() != 1) {
                throw IllegalArgumentException("A WordleLetter can have one letter at most")
            }
        }

        override fun equals(other: Any?): Boolean {
            return if (other !is FilledWordleLetter)
                false
            else
                this.letter.equals(other.letter, true)
        }

        override fun hashCode(): Int {
            return letter.hashCode()
        }
    }
}

data class WordMatchState(val state: BoardRow) {
    init {
        if (state.row.size != 5) {
            throw IllegalArgumentException("Only words with exactly 5 letters are accepted")
        }
    }

    val isCorrect get() = state.isCorrectWord
}
