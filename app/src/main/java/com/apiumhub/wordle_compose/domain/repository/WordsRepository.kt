package com.apiumhub.wordle_compose.domain.repository

interface WordsRepository {
    fun getTodaysWord(): String
}