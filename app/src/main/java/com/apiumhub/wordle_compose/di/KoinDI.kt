package com.apiumhub.wordle_compose.di

import com.apiumhub.wordle_compose.data.DictionaryProvider
import com.apiumhub.wordle_compose.data.LocalDictionaryRepository
import com.apiumhub.wordle_compose.data.LocalWordsRepository
import com.apiumhub.wordle_compose.domain.repository.DictionaryRepository
import com.apiumhub.wordle_compose.domain.repository.WordsRepository
import com.apiumhub.wordle_compose.domain.usecase.WordMatcherUseCase
import com.apiumhub.wordle_compose.ui.viewmodel.WordleViewmodel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<WordsRepository> { LocalWordsRepository() }
    single<DictionaryRepository> { LocalDictionaryRepository(get()) }
    single { DictionaryProvider(androidContext()) }
    single { WordMatcherUseCase(get()) }
    viewModel {
        WordleViewmodel(get(), get())
    }
}