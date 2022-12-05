package com.apiumhub.wordle_compose.data

import retrofit2.http.GET

interface KtorApi {
    @GET("/next_word")
    suspend fun getNextWord(): List<String>
}