package com.apiumhub.wordle_compose.domain

import com.apiumhub.wordle_compose.domain.repository.WordsRepository
import com.apiumhub.wordle_compose.domain.usecase.WordMatcherUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class WordMatcherUseCaseTest {

    private val repository = mockk<WordsRepository>()
    private val sut = WordMatcherUseCase(repository)

    @BeforeEach
    internal fun setUp() {
        every { repository.currentWord } returns "oleos"
    }

    @Test
    internal fun `should match all letters in word`() {
        val actual = sut("oleos")
        assert(actual.state.row.all {
            it.state == LetterState.MATCH
        })
    }

    @Test
    internal fun `should not match any letter in word`() {
        val actual = sut("ZZZZZ")
        assert(actual.state.row.all {
            it.state == LetterState.NOT_INCLUDED
        })
    }

    @Test
    internal fun `should find included letters but not matched`() {
        val actual = sut("soslo")
        assert(actual.state.row.all {
            it.state == LetterState.INCLUDED
        })
    }

    @Test
    internal fun `should find all included, not included and matched letters`() {
        val actual = sut("olxls")
        actual.state.row.forEachIndexed { index, letter ->
            when(index){
                0 -> assertEquals(letter.state, LetterState.MATCH)
                1 -> assertEquals(letter.state, LetterState.MATCH)
                2 -> assertEquals(letter.state, LetterState.NOT_INCLUDED)
                3 -> assertEquals(letter.state, LetterState.INCLUDED)
                4 -> assertEquals(letter.state, LetterState.MATCH)
            }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "AAAA", "AAAAAA"])
    internal fun `should fail with less than 5 letters in word`(input: String) {
        assertThrows<IllegalArgumentException> {
            sut(input)
        }
    }
}