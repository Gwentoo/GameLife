package view
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import components.Cell
import components.MediumBtn
import components.coolButton
import game.abstract.Game
import game.type.CyclicGame
import grid.gridUpdate

import gridDisplay
import utils.json2matrix
import utils.matrix2json
import java.io.FileWriter
import java.io.IOException
import kotlin.math.max

@Composable
fun myScreen() {
    var sliderValue by remember { mutableStateOf(100f) }

    val valueRange = 1f..300f
    val colorStop = Color(217, 43, 43)
    val colorStart = Color(29, 166, 27)
    //var loadConfig by remember { mutableStateOf(json2matrix("start.json")) }
    var rows by remember { mutableStateOf("8") }
    var cols by remember { mutableStateOf("8") }


    var game by remember {  mutableStateOf(CyclicGame(rows.toInt(), cols.toInt()))}
    var gridMat by remember { mutableStateOf(game.matrix) }
    var showErrorDialogDim by remember { mutableStateOf(false) }
    var showErrorDialogData by remember { mutableStateOf(false) }
    var changeCells by remember { mutableStateOf(game.newState()) }
    var cellSize = 650/max(rows.toInt(), cols.toInt())
    var clickCoordinates by remember { mutableStateOf<Pair<Float, Float>?>(null) }
    var isRun by remember { mutableStateOf(false) }




    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp),

            ) {
            Row() {
                TextField(
                    value = rows,
                    onValueChange = { newValue -> rows = newValue },
                    label = { Text("Количество строк") },
                    modifier = Modifier.width(215.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                TextField(
                    value = cols,
                    onValueChange = { newValue -> cols = newValue },
                    label = { Text("Количество столбцов") },
                    modifier = Modifier.width(215.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(


                modifier = Modifier.height(650.dp).width(650.dp).pointerInput(Unit) {
                    detectTapGestures { offset ->
                        clickCoordinates = Pair(offset.x, offset.y)
                    }
                }


            ) {

                gridUpdate(clickCoordinates, cellSize, game, changeCells)
                gridDisplay(game, clickCoordinates)




            }
            Slider(
                value = sliderValue,
                onValueChange = { newValue -> sliderValue = newValue },
                valueRange = valueRange,
                steps = 100,
                modifier = Modifier.width(650.dp),
                colors = SliderDefaults.colors(
                    thumbColor = Color.Black,
                    activeTickColor = Color.Black,
                    )
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(92.dp),
            horizontalAlignment = Alignment.End


        ) {
            coolButton(
                onClick = {

                          changeCells = game.newState()

                          },

                size = MediumBtn,
                text = "Следующий шаг",
                )

            Spacer(modifier = Modifier.height(16.dp))
            coolButton(
                onClick = {
                    isRun = true
                },
                size = MediumBtn,
                text = "Запустить игру",
                backgroundColor = colorStart
            )
            Spacer(modifier = Modifier.height(16.dp))
            coolButton(
                onClick = {
                    isRun = false
                },
                size = MediumBtn,
                text = "Остановить игру",
                enabled = isRun,
                backgroundColor = colorStop
            )
            Spacer(modifier = Modifier.height(70.dp))

            dimError(showErrorDialogDim) { showErrorDialogDim = false }
            incorrectDataError(showErrorDialogData) { showErrorDialogData = false }

            coolButton(onClick = {

                matrix2json(game)


            }, size = MediumBtn,
                text = "Сохранить конфигурацию")

            Spacer(modifier = Modifier.height(16.dp))

            coolButton(onClick = {

                //loadConfig = json2matrix("array2d.json")



            }, size = MediumBtn,
                text = "Загрузить конфигурацию")



            LaunchedEffect(isRun) {
                while (isRun) {

                    changeCells = game.newState()
                    if (changeCells.isEmpty()){
                        isRun = false
                    }

                    kotlinx.coroutines.delay(301  - sliderValue.toLong())
                }
            }

            LaunchedEffect(rows, cols){
                game = CyclicGame(rows.toInt(), cols.toInt())
                gridMat = game.matrix
            }


        }
    }
}