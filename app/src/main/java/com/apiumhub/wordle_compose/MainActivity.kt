package com.apiumhub.wordle_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apiumhub.wordle_compose.domain.LetterState
import com.apiumhub.wordle_compose.domain.WordleLetter
import com.apiumhub.wordle_compose.domain.board.BoardState
import com.apiumhub.wordle_compose.ui.components.KeyEvent
import com.apiumhub.wordle_compose.ui.components.Keyboard
import com.apiumhub.wordle_compose.ui.components.LetterBox
import com.apiumhub.wordle_compose.ui.theme.WordleComposeTheme
import com.apiumhub.wordle_compose.ui.viewmodel.WordleViewmodel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: WordleViewmodel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        WordleGrid(viewModel.boardState)
                        Keyboard {
                            when (it) {
                                is KeyEvent.Letter -> viewModel.onLetterPressed(it.letter)
                                KeyEvent.Delete -> viewModel.onDelPressed()
                                KeyEvent.Send -> viewModel.onSendPressed()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WordleGrid(boardState: BoardState) {
    Text(text = boardState.getCurrentRow().row.first().getLetter())
    LazyVerticalGrid(columns = GridCells.Fixed(5), content = {
        items(25) {
            LetterBox(letter = WordleLetter.EmptyWordleLetter, state = LetterState.EMPTY)
        }
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WordleComposeTheme {
        Row {
            //WordleGrid()
            Keyboard {

            }
        }

    }
}