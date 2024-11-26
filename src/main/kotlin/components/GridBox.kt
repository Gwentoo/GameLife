package components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import game.abstract.Game
import game.type.CyclicGame


@Composable
fun gridBox(cellsize: Int, game:  Game, row: Int, col: Int) {

    Box(
        modifier = androidx.compose.ui.Modifier
            .height(cellsize.dp)
            .width(cellsize.dp)
            .border(width = 1.dp, color = Color.Black)
            .background(if (game.matrix[row][col]==1) Color.Black else Color.White)
            .clickable {

                game.matrix[row][col] = if (game.matrix[row][col] == 1) 0 else 1


                    }

                )
            }



