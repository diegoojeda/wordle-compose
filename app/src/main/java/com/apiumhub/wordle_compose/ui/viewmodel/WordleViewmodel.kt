package com.apiumhub.wordle_compose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.apiumhub.wordle_compose.domain.WordMatcherUseCase
import com.apiumhub.wordle_compose.domain.board.BoardState

class WordleViewmodel(
    val matcherUseCase: WordMatcherUseCase
) : ViewModel() {

    var boardState by mutableStateOf(BoardState.empty())
        private set

    fun onLetterPressed(letter: String) {
        runCatching {
            boardState = boardState.addLetter(letter)
        }
    }

    fun onDelPressed() {
        runCatching {
            boardState = boardState.deleteLastLetter()
        }
    }

    fun onSendPressed() {
        val result = matcherUseCase(boardState.getWord())
        boardState = boardState.updateWithMatchedWord(result)
    }
}