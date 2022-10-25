package com.apiumhub.wordle_compose.domain.board

data class BoardState private constructor(val state: List<BoardRow>) {

    fun addLetter(letter: String): BoardState {
        val currentRow = getCurrentRow()
        if (currentRow.isCompletedRow())
            throw IllegalStateException("Row already full, cannot add more letters to it")
        return this.copy(state = state.apply { getCurrentRow().addLetter(letter) })
    }

    fun deleteLastLetter(): BoardState {
        getCurrentRow().deleteLastLetter()
        return this
    }

    fun getCurrentRow() = state.first { !it.isMatched() }

    fun isFullWord() {

    }

    companion object {
        private const val MAX_TRIES = 6
        fun empty() = BoardState(List(MAX_TRIES) { BoardRow.empty() })
    }
}


