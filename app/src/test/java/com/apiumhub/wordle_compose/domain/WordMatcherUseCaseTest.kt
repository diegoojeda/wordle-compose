package com.apiumhub.wordle_compose.domain

import com.apiumhub.wordle_compose.data.WordsRepository
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

    @Test
    internal fun `should find included letters but not matched`() {
        val actual = sut("mupia")
        assert(actual.state.all {
            it.second == LetterState.INCLUDED
        })
    }

    @Test
    internal fun `should find all included, not included and matched letters`() {
        val actual = sut("apoim")
        actual.state.forEachIndexed { index, pair ->
            when(index){
                0 -> assertEquals(pair.second, LetterState.MATCH)
                1 -> assertEquals(pair.second, LetterState.MATCH)
                2 -> assertEquals(pair.second, LetterState.NOT_INCLUDED)
                3 -> assertEquals(pair.second, LetterState.INCLUDED)
                4 -> assertEquals(pair.second, LetterState.MATCH)
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