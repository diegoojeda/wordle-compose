package com.apiumhub.wordle_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: WordleViewmodel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleComposeTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        content = { paddingValues ->
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .padding(24.dp)
                                    .fillMaxSize()
                                    .padding(paddingValues)
                            ) {
                                ErrorSnackbar(viewModel.errorState, scope, snackbarHostState, viewModel::dismissError)
                                WordleGrid(viewModel.boardState)
                                Keyboard(modifier = Modifier.padding(bottom = 48.dp)) {
                                    when (it) {
                                        is KeyEvent.Letter -> viewModel.onLetterPressed(it.letter)
                                        KeyEvent.Delete -> viewModel.onDelPressed()
                                        KeyEvent.Send -> viewModel.onSendPressed()
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ErrorSnackbar(
    errorState: ErrorState,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    dismissError: () -> Unit
) {
    if (errorState == ErrorState.WordNotInDictionaryError) {
        scope.launch {
            snackbarHostState.showSnackbar("Word does not exists")
            dismissError()
        }
    }
}

@Composable
fun WordleGrid(boardState: BoardState) {
    Column(
        modifier = Modifier.fillMaxWidth(1.0f).padding(top = 16.dp)
    ) {
        boardState.state.forEach { row ->
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(48.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.row.forEach { letter ->
                    LetterBox(letter = letter.letter, state = letter.state)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
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