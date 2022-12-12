package com.apiumhub.wordle_compose.domain.board

data class BoardRow constructor(val row: List<BoardLetter>) {

    val isCompletedRow get() = row.all { !it.isEmpty }
    val isMatched get() = row.any { it.isMatched }
    val isCorrectWord get() = row.all { it.isCorrect }
    val isEmpty get() = row.all { it.isEmpty }
    val word get() = row.joinToString("") { it.actualLetter }
    private val firstEmptyLetter get() = row.first { it.isEmpty }

    fun addLetter(letter: String): BoardRow {
        val firstEmptyLetterIndex = row.indexOfFirst { it == firstEmptyLetter }
        return copy(
            row =
            row.subList(0, firstEmptyLetterIndex) +
                    listOf(firstEmptyLetter.setLetter(letter)) +
                    row.subList(firstEmptyLetterIndex + 1, row.size)
        )
    }

    fun deleteLastLetter(): BoardRow {
        val lastFilledLetter = row.last { !it.isEmpty }
        val toDeleteIndex = row.lastIndexOf(lastFilledLetter)
        return copy(row =
        row.subList(0, toDeleteIndex) +
                List(LETTERS_PER_WORD - toDeleteIndex) { BoardLetter.empty() }
        )
    }

    override fun toString(): String {
        return row.joinToString { it.toString() }
    }

    companion object {
        private const val LETTERS_PER_WORD = 5
        fun empty() = BoardRow(List(LETTERS_PER_WORD) { BoardLetter.empty() })
    }
}