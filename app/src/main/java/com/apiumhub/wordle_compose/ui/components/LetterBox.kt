package com.apiumhub.wordle_compose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apiumhub.wordle_compose.domain.LetterState
import com.apiumhub.wordle_compose.domain.WordleLetter

@Composable
fun LetterBox(
    letter: WordleLetter,
    state: LetterState
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = calculateState(state)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.aspectRatio(1f),
    ) {
        Text(
            modifier = Modifier.fillMaxSize().wrapContentHeight(),
            text = letter.letter,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun Preview() {
    Column {
        LetterBox(
            letter = WordleLetter.FilledWordleLetter(
                "A"
            ),
            state = LetterState.INCLUDED
        )
        LetterBox(
            letter = WordleLetter.FilledWordleLetter(
                "B"
            ),
            state = LetterState.NOT_INCLUDED
        )
        LetterBox(
            letter = WordleLetter.FilledWordleLetter(
                "C"
            ),
            state = LetterState.MATCH
        )
        LetterBox(
            letter = WordleLetter.EmptyWordleLetter,
            state = LetterState.EMPTY
        )
    }
}

private fun calculateState(
    state: LetterState
): Color =
    when (state) {
        LetterState.EMPTY -> Color.White
        LetterState.NOT_CHECKED -> Color.White
        LetterState.NOT_INCLUDED -> Color.LightGray
        LetterState.INCLUDED -> Color.Yellow
        LetterState.MATCH -> Color.Green
    }

