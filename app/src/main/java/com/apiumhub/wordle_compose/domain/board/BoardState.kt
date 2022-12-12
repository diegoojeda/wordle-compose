package com.apiumhub.wordle_compose.domain.board

import com.apiumhub.wordle_compose.domain.WordMatchState

data class BoardState private constructor(val state: List<BoardRow>) {

    val currentRow get() = state.first { !it.isMatched }
    val word get() = currentRow.word
    val isOutOfTries get() = !state.last().isEmpty && !state.last().isCorrectWord

    fun addLetter(letter: String): BoardState {
        val currentRow = currentRow
        if (currentRow.isCompletedRow)
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
        val currentRow = currentRow
        val currentRowIndex = state.indexOf(currentRow)
        return this.copy(
            state = state.subList(0, currentRowIndex) +
                    currentRow.deleteLastLetter() +
                    state.subList(currentRowIndex + 1, state.size)
        )
    }

    fun updateWithMatchedWord(result: WordMatchState): BoardState {
        val currentRow = currentRow
        val currentRowIndex = state.indexOf(currentRow)
        return this.copy(
            state = state.subList(0, currentRowIndex) +
                    result.state +
                    state.subList(currentRowIndex + 1, state.size)
        )
    }


    companion object {
        private const val MAX_TRIES = 6
        fun empty() = BoardState(List(MAX_TRIES) { BoardRow.empty() })
    }
}


