

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import game.abstract.Game


import kotlin.math.max


@Composable
fun gridDisplay(game: Game, clickCoordinates: Pair<Float, Float>?) {
    print("DIS")
    val rows = game.matrix.size
    val cols = game.matrix[0].size

    val cellSize = 650 / max(rows, cols)




    Canvas(modifier = Modifier.size(650.dp, 650.dp)) {
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




        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (game.matrix[row][col] == 1) {
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset((col * cellSize - 1).toFloat(), (row * cellSize - 1).toFloat()),
                        size = Size((cellSize + 1).toFloat(), (cellSize + 1).toFloat())

                    )
                }else{
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










