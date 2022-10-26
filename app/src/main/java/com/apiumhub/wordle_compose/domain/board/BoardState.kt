package com.apiumhub.wordle_compose.domain.board

data class BoardState private constructor(val state: List<BoardRow>) {

    fun addLetter(letter: String): BoardState {
        val currentRow = getCurrentRow()
        if (currentRow.isCompletedRow())
            throw IllegalStateException("Row already full, cannot add more letters to it")
        val index = state.indexOf(currentRow)
        return this.copy(
            state =
            state.subList(0, index) +
                    currentRow.addLetter(letter) +
                    state.subList(index + 1, state.size)
        )
    }

    fun deleteLastLetter(): BoardState {
        val currentRow = getCurrentRow()
        val currentRowIndex = state.indexOf(currentRow)
        return this.copy(
            state = state.subList(0, currentRowIndex) +
                    currentRow.deleteLastLetter() +
                    state.subList(currentRowIndex + 1, state.size)
        )
    }

    fun getCurrentRow() = state.first { !it.isMatched() }

    fun isFullWord() {

    }

    companion object {
        private const val MAX_TRIES = 6
        fun empty() = BoardState(List(MAX_TRIES) { BoardRow.empty() })
    }
}


