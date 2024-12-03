package view
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import components.MediumBtn
import components.SmallBtn
import components.coolButton
import game.type.CyclicGame
import game.type.UnCyclicGame
import grid.gridUpdate
import gridDisplay
import utils.*
import kotlin.math.max
import kotlin.random.Random


@Composable
fun myScreen() {

    var type by remember { mutableStateOf("UnCyclic") }
    var sliderValue by remember { mutableStateOf(100f) }
    val valueRange = 1f..300f
    val colorStop = Color(217, 43, 43)
    val colorStart = Color(29, 166, 27)
    var newConfig by remember { mutableStateOf(json2matrixDefault("src/main/kotlin/io/start.json")) }
    var game by remember { mutableStateOf(if (type == "UnCyclic") UnCyclicGame(newConfig) else CyclicGame(newConfig)) }
    var rows by remember { mutableStateOf(newConfig.size.toString()) }
    var cols by remember { mutableStateOf(newConfig[0].size.toString()) }
    var showErrorDialogDim by remember { mutableStateOf(false) }
    var showErrorDialogData by remember { mutableStateOf(false) }
    var changeCells by remember { mutableStateOf(game.newState()) }
    var cellSize = 500 / max(rows.toInt(), cols.toInt())
    var clickCoordinates by remember { mutableStateOf<Pair<Float, Float>?>(null) }
    var isRun by remember { mutableStateOf(false) }
    var loadConfig by remember { mutableStateOf(false) }
    val jsonFiles = remember { loadJsonFileNamesFromAssets() }
    var selectedFile by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var rate by remember { mutableStateOf("0.5") }





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
                    label = { Text("Rows count") },
                    modifier = Modifier.width(243.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                TextField(
                    value = cols,
                    onValueChange = { newValue -> cols = newValue },
                    label = { Text("Cols count") },
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
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {

                RadioButton(
                    selected = (type == "Cyclic"),
                    onClick = { type = "Cyclic" },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Black),
                    enabled = !isRun
                )
                Text(text = "Cyclic")
                Spacer(modifier = Modifier.width(100.dp))
                RadioButton(
                    selected = (type == "UnCyclic"),
                    onClick = { type = "UnCyclic" },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Black),
                    enabled = !isRun
                )
                Text(text = "UnCyclic")
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(90.dp),
            horizontalAlignment = Alignment.End

        ) {
            coolButton(
                onClick = {

                    changeCells = game.newState()

                },

                size = MediumBtn,
                text = "Next turn",
            )

            Spacer(modifier = Modifier.height(16.dp))
            coolButton(
                onClick = {
                    isRun = true
                },
                size = MediumBtn,
                text = "Start",
                backgroundColor = colorStart,
                enabled = !isRun
            )
            Spacer(modifier = Modifier.height(16.dp))
            coolButton(
                onClick = {
                    isRun = false
                },
                size = MediumBtn,
                text = "Stop",
                enabled = isRun,
                backgroundColor = colorStop
            )
            Spacer(modifier = Modifier.height(16.dp))
            coolButton(
                onClick = {
                    if (type == "Cyclic") {
                        game = CyclicGame(Array(rows.toInt()) { IntArray(cols.toInt()) { 0 } })
                    } else {
                        game = UnCyclicGame(Array(rows.toInt()) { IntArray(cols.toInt()) { 0 } })
                    }

                },
                size = MediumBtn,
                text = "Clear field"

            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.width(270.dp)) {
                coolButton(
                    onClick = {
                        for (row in 0 until rows.toInt()){
                            for (col in 0 until cols.toInt()){
                                val randomNum = Random.nextDouble()
                                if (randomNum <  rate.toDouble()){
                                    game.matrix[row][col] = 1
                                }
                                else {
                                    game.matrix[row][col] = 0
                                }

                            }
                        }

                    }, size = SmallBtn,
                    text = "Random fill"
                )
                Spacer(modifier = Modifier.width(15.dp))
                TextField(
                    value = rate,
                    onValueChange = { newValue -> rate = newValue },
                    modifier = Modifier.width(75.dp).height(45.dp)
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            dimError(showErrorDialogDim) { showErrorDialogDim = false }
            incorrectDataError(showErrorDialogData) { showErrorDialogData = false }

            coolButton(
                onClick = {

                    matrix2json(game)


                }, size = MediumBtn,
                text = "Save configuration"
            )

            Spacer(modifier = Modifier.height(16.dp))

            coolButton(
                onClick = {
                    loadConfig = true
                    newConfig = json2matrix()
                    if (type == "Cyclic") {
                        game = CyclicGame(newConfig)
                    } else {
                        game = UnCyclicGame(newConfig)
                    }
                    rows = newConfig.size.toString()
                    cols = newConfig[0].size.toString()


                }, size = MediumBtn,
                text = "Load configuration"
            )




            Spacer(modifier = Modifier.height(8.dp))
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.width(285.dp)
            ) {

                TextField(
                    readOnly = true,
                    value = selectedFile,
                    onValueChange = {},
                    label = { Text("Select pattern") },
                    modifier = Modifier
                        .clickable { expanded = true }
                        .padding(16.dp),


                    trailingIcon = {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Arrow",
                            modifier = Modifier.clickable { expanded = !expanded }
                        )
                    }
                )



                DropdownMenu(

                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    jsonFiles.forEach { fileName ->
                        DropdownMenuItem(onClick = {
                            loadConfig = true
                            selectedFile = fileName
                            expanded = false
                            newConfig = json2matrixDefault("src/main/kotlin/assets/patterns/${fileName}.json")
                            if (type == "Cyclic") {
                                game = CyclicGame(newConfig)
                            } else {
                                game = UnCyclicGame(newConfig)
                            }
                            rows = newConfig.size.toString()
                            cols = newConfig[0].size.toString()
                        }) {
                            Text(text = fileName)
                        }
                    }
                }
            }




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
                if (!loadConfig) {
                    if (type == "Cyclic") {
                        game = CyclicGame(rows.toInt(), cols.toInt())
                    } else {
                        game = UnCyclicGame(rows.toInt(), cols.toInt())
                    }

                } else {
                    if (type == "Cyclic") {
                        game = CyclicGame(newConfig)
                    } else {
                        game = UnCyclicGame(newConfig)
                    }
                    loadConfig = false
                }

            }

            LaunchedEffect(type) {
                if (type == "Cyclic") {
                    game = CyclicGame(game.matrix)
                } else {
                    game = UnCyclicGame(game.matrix)
                }
            }


        }

    }
}






