package grid
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import java.lang.Integer.max
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import components.gridBox
import game.abstract.Game


@Composable
fun gridDisplay(game: Game) {
    val rows = game.matrix.size
    val cols = game.matrix[0].size

    val cellsize = (450 / max(rows, cols))

    var color = remember { mutableStateOf(Array(rows) { Array(cols) { Color.White } }) }
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            color.value[i][j] = if (game.matrix[i][j] == 0) Color.White else Color.Black

        }
    }
    Column(modifier = Modifier.height((rows * cellsize).dp)) {
        for (row in 0 until rows) {
            Row(modifier = Modifier.width((cols * cellsize).dp)) {
                for (col in 0 until cols) {

                    Box(
                        modifier = Modifier
                            .height(cellsize.dp)
                            .width(cellsize.dp)
                            .border(width = 1.dp, color = Color.Black)
                            .background(color.value[row][col])
                            .clickable {

                                game.matrix[row][col] = if (game.matrix[row][col] == 1) 0 else 1
                                color.value =
                                    Array(rows) { Array(cols) { if (game.matrix[row][col] == 0) Color.White else Color.Black } }


                            }

                    )

                }
            }
        }

    }
}
