package com.apiumhub.wordle_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apiumhub.wordle_compose.domain.board.BoardState
import com.apiumhub.wordle_compose.ui.components.KeyEvent
import com.apiumhub.wordle_compose.ui.components.Keyboard
import com.apiumhub.wordle_compose.ui.components.LetterBox
import com.apiumhub.wordle_compose.ui.theme.WordleComposeTheme
import com.apiumhub.wordle_compose.ui.viewmodel.ErrorState
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
                        ErrorSnackbar(viewModel.errorState)
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

//@Composable
fun ErrorSnackbar(errorState: ErrorState) {
//    val scaffoldState: ScaffoldState = rememberScaffoldState()
//    val coroutineScope: CoroutineScope = rememberCoroutineScope()
//
//    Scaffold(scaffoldState = scaffoldState) {
//        Button(onClick = {
//            coroutineScope.launch {
//                scaffoldState.snackbarHostState.showSnackbar(
//                    message = "This is your message",
//                    actionLabel = "Do something"
//                )
//            }
//        }) {}
//    }
}

@Composable
fun WordleGrid(boardState: BoardState) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1.0f)
    ) {
        boardState.state.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                row.row.forEach { letter ->
                    LetterBox(letter = letter.letter, state = letter.state)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WordleComposeTheme {
        Row {
            WordleGrid(BoardState.empty())
            Keyboard {

            }
        }

    }
}