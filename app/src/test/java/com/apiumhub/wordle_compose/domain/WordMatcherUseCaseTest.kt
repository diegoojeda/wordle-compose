package com.apiumhub.wordle_compose.domain

import com.apiumhub.wordle_compose.data.WordsRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class WordMatcherUseCaseTest {

    private val repository = mockk<WordsRepository>()
    private val sut = WordMatcherUseCase(repository)

    @BeforeEach
    internal fun setUp() {
        every { repository.getTodaysWord() } returns "Apium"
    }

    @Test
    internal fun `should match all letters in word`() {
        val actual = sut("Apium")
        assert(actual.state.all {
            it.second == LetterState.MATCH
        })
    }

    @Test
    internal fun `should not match any letter in word`() {
        val actual = sut("ZZZZZ")
        assert(actual.state.all {
            it.second == LetterState.NOT_INCLUDED
        })
    }
}