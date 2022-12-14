package com.apiumhub.wordle_compose.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apiumhub.wordle_compose.domain.LetterStatus
import com.apiumhub.wordle_compose.domain.LetterStatus.*
import com.apiumhub.wordle_compose.domain.WordleLetter

@Composable
fun LetterBox(
    letter: WordleLetter,
    state: LetterStatus
) {
    val rotation  by animateFloatAsState(
        targetValue = if (state == EMPTY || state == NOT_CHECKED) 0f else 180f,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        )
    )
    if (rotation <= 90f) {
        InternalBox(mapToBackgroundColor(state), rotation, letter)
    } else {
        InternalBox(mapToBackgroundColor(state), rotation, letter)
    }
}

@Composable
private fun InternalBox(
    backgroundColor: Color,
    rotation: Float,
    letter: WordleLetter
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .aspectRatio(1f)
            .graphicsLayer {
                rotationX = rotation
                cameraDistance = 12f * density
            },
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .graphicsLayer {
                    if (rotation > 90) {
                        rotationX = 180f
                    }
                },
            text = letter.letter,
            textAlign = TextAlign.Center
        )
    }
}

private fun mapToBackgroundColor(state: LetterStatus) =
    when (state) {
        EMPTY, NOT_CHECKED -> Color.White
        NOT_INCLUDED -> Color.LightGray
        INCLUDED -> Color.Yellow
        MATCH -> Color.Green
    }

@Preview
@Composable
fun Preview() {
    Column {
        LetterBox(
            letter = WordleLetter.FilledWordleLetter(
                "A"
            ),
            state = INCLUDED
        )
        LetterBox(
            letter = WordleLetter.FilledWordleLetter(
                "B"
            ),
            state = NOT_INCLUDED
        )
        LetterBox(
            letter = WordleLetter.FilledWordleLetter(
                "C"
            ),
            state = MATCH
        )
        LetterBox(
            letter = WordleLetter.EmptyWordleLetter,
            state = EMPTY
        )
    }
}
