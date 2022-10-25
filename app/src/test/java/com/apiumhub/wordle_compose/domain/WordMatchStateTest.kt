package com.apiumhub.wordle_compose.domain

import com.apiumhub.wordle_compose.domain.WordleLetter.FilledWordleLetter
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class WordMatchStateTest {

    @ParameterizedTest
    @MethodSource("failingInputMethod")
    internal fun `should fail with less than 5 letters in word`(input: List<Pair<WordleLetter, LetterState>>) {
        assertThrows<IllegalArgumentException> {
            WordMatchState.FilledWordMatchState(input.toList())
        }
    }

    @ParameterizedTest
    @MethodSource("successInputMethod")
    internal fun `should not fail with exactly 5 letters in word`(input: List<Pair<WordleLetter, LetterState>>) {
        assertDoesNotThrow {
            WordMatchState.FilledWordMatchState(input.toList())
        }
    }

    companion object {
        private val singleValue = Pair(FilledWordleLetter("A"), LetterState.MATCH)
        private val expectedEmpty = generateItems(0)
        private val expectedOneItem = generateItems(1)
        private val expectedFiveItems = generateItems(5)
        private val expectedSixItems = generateItems(6)

        private fun generateItems(size: Int) = generateSequence { singleValue }.take(size).toList()

        @JvmStatic
        private fun failingInputMethod() =
            listOf(
                Arguments.of(expectedEmpty),
                Arguments.of(expectedOneItem),
                Arguments.of(expectedSixItems)
            )

        @JvmStatic
        private fun successInputMethod() = listOf(Arguments.of(expectedFiveItems))
    }
}