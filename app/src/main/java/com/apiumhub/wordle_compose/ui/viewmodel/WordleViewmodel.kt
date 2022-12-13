package com.apiumhub.wordle_compose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apiumhub.wordle_compose.domain.board.BoardState
import com.apiumhub.wordle_compose.domain.repository.DictionaryRepository
import com.apiumhub.wordle_compose.domain.repository.WordsRepository
import com.apiumhub.wordle_compose.domain.usecase.WordMatcherUseCase
import kotlinx.coroutines.launch

sealed class FinishedState {
    object NotFinished : FinishedState()
    object Successful : FinishedState()
    object UnSuccessful : FinishedState()
}

sealed class ErrorState {
    object NoError : ErrorState()
    object WordNotInDictionaryError : ErrorState()
}

class WordleViewmodel(
    private val matcherUseCase: WordMatcherUseCase,
    private val dictionaryRepository: DictionaryRepository,
    private val wordsRepository: WordsRepository,
) : ViewModel() {

    var errorState: ErrorState by mutableStateOf(ErrorState.NoError)
        private set

    var boardState: BoardState by mutableStateOf(BoardState.empty())
        private set

    var finishedState: FinishedState by mutableStateOf(FinishedState.NotFinished)
        private set

    init {
        viewModelScope.launch {
            //Load first word
            wordsRepository.loadNextWord()
        }
    }

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
        val word = boardState.word
        if (!dictionaryRepository.isWordInDictionary(word)) {
            errorState = ErrorState.WordNotInDictionaryError
        } else {
            val result = matcherUseCase(word)
            boardState = boardState.updateWithMatchedWord(result)
            if (result.isCorrect)
                finishedState = FinishedState.Successful
            else if (boardState.isOutOfTries)
                finishedState = FinishedState.UnSuccessful
        }
    }

    fun dismissError() {
        errorState = ErrorState.NoError
    }

    fun playAgain() {
        viewModelScope.launch {
            wordsRepository.loadNextWord()
            finishedState = FinishedState.NotFinished
            boardState = BoardState.empty()
        }
    }
}