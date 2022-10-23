package com.apiumhub.wordle_compose.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.apiumhub.wordle_compose.domain.LetterState
import com.apiumhub.wordle_compose.domain.WordleLetter

@Composable
fun WordBox() {
    Row {
        repeat(5) {
            LetterBox(letter = WordleLetter("A"), state = LetterState.MATCH)
        }
    }
}

@Composable
@Preview
fun WordBoxPreview() {
    WordBox()
}