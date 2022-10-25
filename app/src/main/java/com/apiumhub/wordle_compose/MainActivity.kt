package com.apiumhub.wordle_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.apiumhub.wordle_compose.domain.LetterState
import com.apiumhub.wordle_compose.domain.WordleLetter
import com.apiumhub.wordle_compose.ui.components.LetterBox
import com.apiumhub.wordle_compose.ui.theme.WordleComposeTheme
import com.apiumhub.wordle_compose.ui.viewmodel.WordleViewmodel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val wordsViewModel: WordleViewmodel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wordsViewModel.hello()
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

@Composable
fun WordleGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        content = {
            items(25) {
                LetterBox(letter = WordleLetter.EmptyWordleLetter, state = LetterState.EMPTY)
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