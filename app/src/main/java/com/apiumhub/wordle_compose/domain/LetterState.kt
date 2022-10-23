package com.apiumhub.wordle_compose.domain

enum class LetterState {
    NOT_INCLUDED,
    INCLUDED,
    MATCH
}

@JvmInline
value class WordleLetter(val letter: String) {
    init {
        if (letter.count() != 1) {
            throw IllegalArgumentException("A WordleLetter can have one letter at most")
        }
    }
}