package com.apiumhub.wordle_compose.domain

enum class LetterState {
    NOT_INCLUDED,
    INCLUDED,
    MATCH
}

data class WordleLetter(val letter: String) {
    init {
        if (letter.count() != 1) {
            throw IllegalArgumentException("A WordleLetter can have one letter at most")
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other !is WordleLetter)
            false
        else
            this.letter.equals(other.letter, true)
    }

    override fun hashCode(): Int {
        return letter.hashCode()
    }
}

data class WordMatchState(val state: List<Pair<WordleLetter, LetterState>>) {
    init {
        if (state.size != 5) {
            throw IllegalArgumentException("Only words with exactly 5 letters are accepted")
        }
    }
}