package com.apiumhub.wordle_compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import com.apiumhub.wordle_compose.ui.theme.WordleComposeTheme

//Source https://gist.github.com/nglauber/4cb1573efba9024c008ea71f3320b4d8

@Composable
fun Keyboard(onPressed: (KeyEvent) -> Unit) {
    val keysMatrix = arrayOf(
        arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
        arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
        arrayOf("Del", "Z", "X", "C", "V", "B", "N", "M", "Send")
    )
    val refsMap = mutableMapOf<String, ConstrainedLayoutReference>()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(clip = false)
    ) {
        keysMatrix.forEachIndexed { _, keyRow ->
            keyRow.forEachIndexed { _, key ->
                val ref = createRef()
                refsMap[key] = ref
            }
        }
        keysMatrix.forEachIndexed { row, keyRow ->
            keyRow.forEachIndexed { column, key ->
                val ref = refsMap[key]!!
                val modifier = Modifier.constrainAs(ref) {
                    // Start
                    if (column == 0) {
                        start.linkTo(parent.start)
                    } else {
                        refsMap[keyRow[column - 1]]?.let {
                            start.linkTo(it.end)
                        }
                    }
                    // End
                    if (column == keyRow.lastIndex) {
                        end.linkTo(parent.end)
                    } else {
                        refsMap[keyRow[column + 1]]?.let {
                            end.linkTo(it.start)
                        }
                    }
                    // Top
                    if (row == 0) {
                        top.linkTo(parent.top)
                    } else {
                        refsMap[keysMatrix[row - 1][0]]?.let {
                            top.linkTo(it.bottom)
                        }
                    }
                }

                val modifierPressed = Modifier.constrainAs(createRef()) {
                    start.linkTo(ref.start)
                    end.linkTo(ref.end)
                    bottom.linkTo(ref.bottom)
                }
                KeyboardKey(
                    keyboardKey = key,
                    modifier = modifier,
                    modifierPressed = modifierPressed,
                    pressed = onPressed
                )
            }
        }
    }
}

@Composable
fun KeyboardKey(
    keyboardKey: String,
    modifier: Modifier,
    modifierPressed: Modifier,
    pressed: (KeyEvent) -> Unit
) {
    var isKeyPressed by remember { mutableStateOf(false) }
    Text(keyboardKey, Modifier
        .then(modifier)
        .pointerInput(Unit) {
            detectTapGestures(onPress = {
                isKeyPressed = true
                val success = tryAwaitRelease()
                if (success) {
                    isKeyPressed = false
                    pressed(KeyEvent.create(keyboardKey))
                } else {
                    isKeyPressed = false
                }
            })
        }
        .background(Color.White)
        .padding(
            start = 12.dp,
            end = 12.dp,
            top = 16.dp,
            bottom = 16.dp
        ),
        color = Color.Black
    )
    if (isKeyPressed) {
        Text(
            keyboardKey, Modifier
                .then(modifierPressed)
                .background(Color.White)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 48.dp
                ),
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun keyboardPreview() {
    WordleComposeTheme {
        Keyboard {

        }
    }
}

sealed class KeyEvent {
    object Delete : KeyEvent()
    object Send : KeyEvent()
    data class Letter(val letter: String) : KeyEvent()

    companion object {
        fun create(keyEvent: String) =
            when (keyEvent) {
                "Del" -> Delete
                "Send" -> Send
                else -> Letter(keyEvent)
            }
    }
}