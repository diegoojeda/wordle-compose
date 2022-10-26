package com.apiumhub.wordle_compose.domain.board

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardStateTest {

    private val emptyBoard = BoardState.empty()

    @Test
    internal fun `should add letter in first position`() {
        val actual = emptyBoard.addLetter("A").getCurrentRow()
        assertEquals("A", actual.row[0].getLetter())
    }

    @Test
    internal fun `should not add letter if current row already full`() {
        val actual =
            emptyBoard.addLetter("A").addLetter("P").addLetter("I").addLetter("U").addLetter("M")
        assertThrows<IllegalStateException> {
            actual.addLetter("H")
        }
    }

    @Test
    internal fun `should delete letter in last position`() {
        val actual =
            emptyBoard.addLetter("A").addLetter("P").addLetter("I").addLetter("U").addLetter("M")
                .deleteLastLetter()
        assertEquals(4, actual.getCurrentRow().row.fold(0) { acc, next ->
            acc + if (!next.isEmpty())
                1
            else
                0
        })
        assertEquals("U", actual.getCurrentRow().row[3].getLetter())
    }

    @Test
    internal fun `should throw when trying to delete a letter on an empty row`() {

    }
}