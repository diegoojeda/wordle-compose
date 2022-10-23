package com.apiumhub.wordle_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.apiumhub.wordle_compose.domain.LetterState
import com.apiumhub.wordle_compose.domain.WordleLetter
import com.apiumhub.wordle_compose.ui.components.LetterBox
import com.apiumhub.wordle_compose.ui.theme.WordleComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    WordleGrid()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordleGrid() {
    LazyVerticalGrid(
        cells = GridCells.Fixed(5),
        content = {
            items(25) {
                LetterBox(letter = WordleLetter("A"), state = LetterState.MATCH)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WordleComposeTheme {
        WordleGrid()
    }
}