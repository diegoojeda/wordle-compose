package com.apiumhub.wordle_compose.domain.board

data class BoardRow private constructor(val row: List<BoardLetter>) {

    fun isCompletedRow() = row.all { !it.isEmpty() }

    fun addLetter(letter: String): BoardRow {
        return this.copy(row = row.apply { firstEmptyLetter().setLetter(letter) })
    }

    fun isMatched() = row.any { it.isMatched() }

    fun deleteLastLetter() =
        runCatching {
            row.last { !it.isEmpty() }
        }
            .getOrElse { throw IllegalStateException("Can't delete a letter on an already empty row") }
            .deleteLetter()

    private fun firstEmptyLetter() =
        row.first { it.isEmpty() }

    override fun toString(): String {
        return row.joinToString { it.toString() }
    }

    companion object {
        private const val LETTERS_PER_WORD = 5
        fun empty() = BoardRow(List(LETTERS_PER_WORD) { BoardLetter.empty() })
    }
}