package com.apiumhub.wordle_compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apiumhub.wordle_compose.domain.LetterState
import com.apiumhub.wordle_compose.domain.WordleLetter

@Composable
fun LetterBox(letter: WordleLetter, state: LetterState) {
    Text(
        text = letter.letter,
        modifier = Modifier
            .background(color = Color.White)
            .padding(8.dp)
            .border(width = 1.dp, color = Color.Black)
            .background(color = calculateState(state), shape = RectangleShape)
            .padding(8.dp),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun Preview() {
    Column {
        LetterBox(letter = WordleLetter("A"), state = LetterState.INCLUDED)
        LetterBox(letter = WordleLetter("B"), state = LetterState.NOT_INCLUDED)
        LetterBox(letter = WordleLetter("C"), state = LetterState.MATCH)
    }
}

private fun calculateState(state: LetterState): Color =
    when (state) {
        LetterState.NOT_INCLUDED -> Color.Gray
        LetterState.INCLUDED -> Color.Yellow
        LetterState.MATCH -> Color.Green
    }

