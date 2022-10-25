package com.apiumhub.wordle_compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.apiumhub.wordle_compose.data.WordsRepository

class WordleViewmodel(private val wordsRepository: WordsRepository) : ViewModel() {
    fun onKeyPressed(letter: String) {

    }
}