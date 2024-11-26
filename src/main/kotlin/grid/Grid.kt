package grid
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import java.lang.Integer.max
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import components.gridBox
import game.abstract.Game
import game.type.CyclicGame
import game.type.UnCyclicGame

@Composable
fun gridDisplay(game: Game){
    val rows = game.matrix.size
    val cols = game.matrix[0].size

    val cellsize = (450/max(rows, cols))


    Column(modifier = Modifier.height((rows*cellsize).dp)) {
        for (row in 0 until rows){
            Row(modifier = Modifier.width((cols*cellsize).dp)){
                for (col in 0 until cols){
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
//                    gridBox(cellsize, game, row, col)
//
                    }
                }
            }

        }

    }
