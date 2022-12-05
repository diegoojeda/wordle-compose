package com.apiumhub.wordle_compose.di

import com.apiumhub.wordle_compose.data.DictionaryProvider
import com.apiumhub.wordle_compose.data.KtorApi
import com.apiumhub.wordle_compose.data.LocalDictionaryRepository
import com.apiumhub.wordle_compose.data.RemoteWordsRepository
import com.apiumhub.wordle_compose.domain.repository.DictionaryRepository
import com.apiumhub.wordle_compose.domain.repository.WordsRepository
import com.apiumhub.wordle_compose.domain.usecase.WordMatcherUseCase
import com.apiumhub.wordle_compose.ui.viewmodel.WordleViewmodel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single<WordsRepository> { RemoteWordsRepository(get()) }
    single<DictionaryRepository> { LocalDictionaryRepository(get()) }
    single { DictionaryProvider(androidContext()) }
    single { WordMatcherUseCase(get()) }
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
    single {
        Retrofit
            .Builder()
            .baseUrl("http://127.0.0.1:8080/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build() }
    single { get<Retrofit>().create(KtorApi::class.java) }
    viewModel {
        WordleViewmodel(get(), get(), get())
    }
}