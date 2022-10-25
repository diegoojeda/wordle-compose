package com.apiumhub.wordle_compose.di

import com.apiumhub.wordle_compose.data.LocalWordsRepository
import com.apiumhub.wordle_compose.data.WordsRepository
import com.apiumhub.wordle_compose.domain.WordMatcherUseCase
import com.apiumhub.wordle_compose.ui.viewmodel.WordleViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf<WordsRepository>(::LocalWordsRepository)
    single { WordMatcherUseCase(get()) }
    viewModel {
        WordleViewmodel(get())
    }
}