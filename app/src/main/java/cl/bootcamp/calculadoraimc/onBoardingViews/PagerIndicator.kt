package cl.bootcamp.calculadoraimc.onBoardingViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(size: Int, currentPage: Int) {
   Row (
       horizontalArrangement = Arrangement.SpaceBetween,
       modifier = Modifier
           .padding(top = 60.dp)
       ){
       repeat(size){
           Indicator(isSelect = it == currentPage)
       }
   }
}

@Composable
fun Indicator (isSelect: Boolean) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .height(15.dp)
            .width(15.dp) //Si le ponemos el mismo dp del height, el diseño se vuelve un circulo
            .clip(CircleShape) // circular
            .background(if (isSelect) Color(0xFFB388FF) else Color.LightGray)

    )
}