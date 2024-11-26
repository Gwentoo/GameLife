package view
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.MediumBtn
import components.coolButton
import game.abstract.Game
import game.type.CyclicGame

import grid.gridDisplay
import java.lang.Exception
import kotlin.reflect.typeOf


@Composable
fun myScreen(){
    var game by remember{ mutableStateOf(CyclicGame())}
    var rows by remember { mutableStateOf(game.matrix.size) }
    var cols by remember {  mutableStateOf(game.matrix[0].size)}
    var Rows by remember { mutableStateOf(rows.toString()) }
    var Cols by remember { mutableStateOf(cols.toString()) }
    var isRun by remember { mutableStateOf(false) }
    var showErrorDialogDim by remember { mutableStateOf(false) }
    var showErrorDialogData by remember { mutableStateOf(false) }


    Row(modifier = Modifier.fillMaxSize()

    ){
      Column(modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .padding(16.dp),



      ){
          Row(){TextField(
              value = Rows,
              onValueChange = { newValue -> Rows = newValue },
              label = { Text("Количество строк") },
              modifier = Modifier.width(215.dp)
          )
              Spacer(modifier = Modifier.width(10.dp))
              TextField(

                  value = Cols,
                  onValueChange = { newValue -> Cols = newValue },
                  label = { Text("Количество столбцов") },
                  modifier = Modifier.width(215.dp)
              )}


          Spacer(modifier = Modifier.height(16.dp))
          Box(
              modifier = Modifier.height(540.dp).width(540.dp)

          ){
              gridDisplay(game)
          }


      }

      Column(modifier = Modifier
          .weight(1f)
          .padding(16.dp)


      ){

          coolButton(
              onClick = {

                  game.newState()
                  for (i in game.matrix){
                      for (p in i){
                          print(p)
                      }
                  }

                        },

              size = MediumBtn,
              text = "Следующий шаг"
          )
          Spacer(modifier = Modifier.height(16.dp))
          coolButton(
              onClick = {
                  isRun = true

              },
              size = MediumBtn,
              text = "Запустить игру"
          )
          Spacer(modifier = Modifier.height(16.dp))
          coolButton(
              onClick = {
                  isRun = false

              },
              size = MediumBtn,
              text = "Остановить игру",
              enabled = isRun
          )
          Spacer(modifier = Modifier.height(16.dp))
          coolButton(
              onClick = {
                  try{
                      rows = Rows.toInt()
                      cols = Cols.toInt()
                  } catch (e: Exception){

                      showErrorDialogData = true

                  }

                  if (rows <= 0 || cols <= 0){
                      showErrorDialogDim = true

                  }

                  else{
                      game = CyclicGame(rows, cols)
                  }



              },
              size = MediumBtn,
              text = "Изменить размер поля"
          )



          dimError(showErrorDialogDim){showErrorDialogDim=false}
          incorrectDataError(showErrorDialogData){showErrorDialogData=false}



      }
        LaunchedEffect(isRun) {
            while (isRun) {
                game.newState()
                kotlinx.coroutines.delay(10)
            }
        }



    }


    }
