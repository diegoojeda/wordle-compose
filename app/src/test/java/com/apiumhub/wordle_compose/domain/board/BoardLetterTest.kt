package com.apiumhub.wordle_compose.domain.board

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BoardLetterTest {

    @Test
    internal fun `should be empty if no value is provided`() {
        val actual = BoardLetter.empty()
        assertTrue(actual.isEmpty)
    }

    @Test
    internal fun `should not be empty when value is provided`() {
        val actual = BoardLetter.empty().setLetter("A")
        assertFalse(actual.isEmpty)
    }

    @Test
    internal fun `should be empty when letter is added and then removed`() {
        val actual = BoardLetter.empty().setLetter("A").deleteLetter()
        assertTrue(actual.isEmpty)
    }
}