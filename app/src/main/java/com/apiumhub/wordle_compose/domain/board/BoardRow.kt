package com.apiumhub.wordle_compose.domain.board

data class BoardRow constructor(val row: List<BoardLetter>) {

    fun isCompletedRow() = row.all { !it.isEmpty() }

    fun addLetter(letter: String): BoardRow {
        val firstEmptyLetterIndex = row.indexOfFirst { it == firstEmptyLetter() }
        return copy(
            row =
            row.subList(0, firstEmptyLetterIndex) +
                    listOf(firstEmptyLetter().setLetter(letter)) +
                    row.subList(firstEmptyLetterIndex + 1, row.size)
        )
    }

    fun isMatched() = row.any { it.isMatched() }

    fun deleteLastLetter(): BoardRow {
        val lastFilledLetter = row.last { !it.isEmpty() }
        val toDeleteIndex = row.lastIndexOf(lastFilledLetter)
        return copy(row =
        row.subList(0, toDeleteIndex) +
                List(LETTERS_PER_WORD - toDeleteIndex) { BoardLetter.empty() }
        )
    }

    private fun firstEmptyLetter() =
        row.first { it.isEmpty() }

    override fun toString(): String {
        return row.joinToString { it.toString() }
    }

    fun isEmpty(): Boolean =
        row.all { it.isEmpty() }

    fun getWord(): String = row.joinToString("") { it.letter.letter }

    companion object {
        private const val LETTERS_PER_WORD = 5
        fun empty() = BoardRow(List(LETTERS_PER_WORD) { BoardLetter.empty() })
    }
}