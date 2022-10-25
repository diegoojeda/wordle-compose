package com.apiumhub.wordle_compose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.apiumhub.wordle_compose.domain.WordMatchState
import com.apiumhub.wordle_compose.domain.WordMatcherUseCase
import com.apiumhub.wordle_compose.domain.board.BoardState

class WordleViewmodel(
    val matcherUseCase: WordMatcherUseCase
) : ViewModel() {

    private val matcherState = mutableStateOf<WordMatchState>(WordMatchState.EmptyMatchState)
    var boardState by mutableStateOf(BoardState.empty())
        private set

    fun onLetterPressed(letter: String) {
        boardState = boardState.copy(state = boardState.addLetter(letter).state)
    }

    fun onDelPressed() {
        boardState.deleteLastLetter()
    }

    fun onSendPressed() {
        //matcherState.value = matcherUseCase(word)
    }
}