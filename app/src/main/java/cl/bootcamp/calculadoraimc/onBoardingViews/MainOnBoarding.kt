package cl.bootcamp.calculadoraimc.onBoardingViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import cl.bootcamp.calculadoraimc.R
import cl.bootcamp.calculadoraimc.data.PageData
import cl.bootcamp.calculadoraimc.datastore.DataStoreManager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainOnBoarding(modifier: Modifier, navController: NavController, store: DataStoreManager) {
    val items = ArrayList<PageData>()

    items.add(
        PageData(
            R.raw.patient_list,
            "Listado de Pacientes",
            "Crea un registro de todos tus " +
                    " pacientes y sus datos más " +
                    "importantes, para mantener un " +
                    "orden y seguimiento de cada " +
                    "uno."
        )
    )
    items.add(
        PageData(
            R.raw.easy_registration,
            "Facil de usar",
            "Un registro intuitivo y rápido."
        )
    )
    items.add(
        PageData(
            R.raw.weight_scale,
            "Calculadora de IMC",
        "Cuentas con una calculadora " +
                "de indice de masa corporal " +
                "para registrar rápidamente los " +
                "datos de tus paciente."
        )
    )
    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        navController,
        store
    )
}
