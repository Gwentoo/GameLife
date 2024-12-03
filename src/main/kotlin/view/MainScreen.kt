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

import components.MediumBtn
import components.coolButton
import game.type.CyclicGame
import grid.gridUpdate
import gridDisplay
import utils.json2matrix
import utils.json2matrixDefault
import utils.matrix2json
import kotlin.math.max

@Composable
fun myScreen() {

    val defaulConfig = json2matrixDefault("src/main/kotlin/io/schemes/start.json")
    var sliderValue by remember { mutableStateOf(100f) }
    val valueRange = 1f..300f
    val colorStop = Color(217, 43, 43)
    val colorStart = Color(29, 166, 27)

    var newConfig by remember { mutableStateOf(json2matrixDefault("src/main/kotlin/io/schemes/start.json")) }


    var game by remember {  mutableStateOf(CyclicGame(newConfig))}
    var rows by remember { mutableStateOf(newConfig.size.toString()) }
    var cols by remember { mutableStateOf(newConfig[0].size.toString()) }

    var showErrorDialogDim by remember { mutableStateOf(false) }
    var showErrorDialogData by remember { mutableStateOf(false) }
    var changeCells by remember { mutableStateOf(game.newState()) }
    var cellSize = 500/max(rows.toInt(), cols.toInt())
    var clickCoordinates by remember { mutableStateOf<Pair<Float, Float>?>(null) }
    var isRun by remember { mutableStateOf(false) }
    var loadConfig by remember { mutableStateOf(false) }




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
                    modifier = Modifier.width(243.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                TextField(
                    value = cols,
                    onValueChange = { newValue -> cols = newValue },
                    label = { Text("Количество столбцов") },
                    modifier = Modifier.width(243.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.height(500.dp).width(500.dp).pointerInput(Unit) {
                    detectTapGestures { offset ->
                        clickCoordinates = Pair(offset.x, offset.y)
                    }
                }

            ) {

                gridUpdate(clickCoordinates, cellSize, game, changeCells)
                gridDisplay(game, changeCells)

                clickCoordinates = Pair(-1f, -1f)

            }
            Slider(
                value = sliderValue,
                onValueChange = { newValue -> sliderValue = newValue },
                valueRange = valueRange,
                steps = 100,
                modifier = Modifier.width(500.dp),
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
                backgroundColor = colorStart,
                enabled = !isRun
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
            Spacer(modifier = Modifier.height(16.dp))
            coolButton(
                onClick = {
                    game = CyclicGame(Array(rows.toInt()) { IntArray(cols.toInt()) { 0 } })
                },
                size = MediumBtn,
                text = "Очистить поле"

            )
            Spacer(modifier = Modifier.height(70.dp))

            dimError(showErrorDialogDim) { showErrorDialogDim = false }
            incorrectDataError(showErrorDialogData) { showErrorDialogData = false }

            coolButton(
                onClick = {

                    matrix2json(game)


                }, size = MediumBtn,
                text = "Сохранить конфигурацию"
            )

            Spacer(modifier = Modifier.height(16.dp))

            coolButton(
                onClick = {
                    loadConfig = true
                    newConfig = json2matrix()
                    game = CyclicGame(newConfig)
                    rows = newConfig.size.toString()
                    cols = newConfig[0].size.toString()


                }, size = MediumBtn,
                text = "Загрузить свою конфигурацию"
            )



            LaunchedEffect(isRun) {
                while (isRun) {

                    changeCells = game.newState()

                    if (changeCells.isEmpty()) {
                        isRun = false
                    }

                    kotlinx.coroutines.delay(301 - sliderValue.toLong())
                }
            }

            LaunchedEffect(rows, cols) {
                    if (!loadConfig){
                        game = CyclicGame(rows.toInt(), cols.toInt())
                    }else{
                        game = CyclicGame(newConfig)
                        loadConfig = false
                    }

                }







            }

        }

        }




