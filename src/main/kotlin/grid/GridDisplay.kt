

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import game.abstract.Game




@Composable
fun gridDisplay(game: Game, changeCells: MutableList<IntArray>, gridView: Boolean, cellSize: Int) {
    val rows = game.matrix.size
    val cols = game.matrix[0].size




    Canvas(modifier = Modifier.size(497.dp, 497.dp)) {
        if (gridView) {
            for (col in 0 until cols + 1) {

                drawLine(
                    color = Color.Black,
                    start = Offset((col * cellSize).toFloat(), 0f),
                    end = Offset((col * cellSize).toFloat(), (rows * cellSize).toFloat()),
                    strokeWidth = 1f
                )

            }
            for (row in 0 until rows + 1) {

                drawLine(
                    color = Color.Black,
                    start = Offset(0f, (row * cellSize).toFloat()),
                    end = Offset((cols * cellSize).toFloat(), (row * cellSize).toFloat()),
                    strokeWidth = 1f
                )


            }
        } else {
            drawLine(
                color = Color.Black,
                start = Offset(0f, (rows * cellSize).toFloat()),
                end = Offset((cols * cellSize).toFloat(), (rows * cellSize).toFloat()),
                strokeWidth = 1f
            )
            drawLine(
                color = Color.Black,
                start = Offset(0f, 0f),
                end = Offset(0f, (rows * cellSize).toFloat()),
                strokeWidth = 1f
            )
            drawLine(
                color = Color.Black,
                start = Offset(0f, 0f),
                end = Offset((cols * cellSize).toFloat(), 0f),
                strokeWidth = 1f
            )
            drawLine(
                color = Color.Black,
                start = Offset((cols * cellSize).toFloat(), 0f),
                end = Offset((cols * cellSize).toFloat(), (rows * cellSize).toFloat()),
                strokeWidth = 1f
            )


        }


        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (game.matrix[row][col] == 1) {
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset((col * cellSize - 1).toFloat(), (row * cellSize - 1).toFloat()),
                        size = Size((cellSize + 1).toFloat(), (cellSize + 1).toFloat())

                    )
                } else {
                    drawRect(
                        color = Color.White,
                        topLeft = Offset((col * cellSize + 1).toFloat(), (row * cellSize + 1).toFloat()),
                        size = Size((cellSize - 1).toFloat(), (cellSize - 1).toFloat())

                    )
                }
            }
        }


    }


}










