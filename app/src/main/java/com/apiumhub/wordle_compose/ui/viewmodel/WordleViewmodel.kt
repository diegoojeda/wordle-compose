package com.apiumhub.wordle_compose.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.apiumhub.wordle_compose.data.WordsRepository

class WordleViewmodel(private val wordsRepository: WordsRepository) : ViewModel() {
    fun hello() {
        Log.d("Wordle", wordsRepository.getTodaysWord())
    }
}