package com.apiumhub.wordle_compose.domain.board

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class BoardRowTest {

    @ParameterizedTest
    @MethodSource("rowCompletedSource")
    internal fun `row should not be completed if there are not five letters in it`(
        size: Int,
        assertion: Boolean
    ) {
        var actual = BoardRow.empty()
        repeat(size) {
            actual = actual.addLetter("A")
        }
        assertEquals(assertion, actual.isCompletedRow)
    }

    @Test
    internal fun `should delete last letter in row`() {
        val actual = BoardRow.empty().addLetter("A").deleteLastLetter()
        assertTrue(actual.isEmpty)
    }

    @Test
    internal fun `should throw when trying to delete a letter on an empty row`() {
        val actual = BoardRow.empty()
        assertThrows<NoSuchElementException> {
            actual.deleteLastLetter()
        }
    }

    companion object {
        @JvmStatic
        private fun rowCompletedSource() =
            listOf(
                Arguments.of(4, false),
                Arguments.of(5, true),
            )
    }
}