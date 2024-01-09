package com.example.tugasakhir_pam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tugasakhir_pam.ui.Halaman.HalamanHome
import com.example.tugasakhir_pam.ui.Penghuni.AddPenghuni.DestinasiEntryPenghuni
import com.example.tugasakhir_pam.ui.Penghuni.DetailPenghuni.DetailDestinationPenghuni
import com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni.DestinasiHomePenghuni
import com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni.PenghuniScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    composable(DestinasiHomePenghuni.route) {
      PenghuniScreen(
            navigateToItemEntryPenghuni = {
                navController.navigate(DestinasiEntryPenghuni.route)
            },
            onDetailClickPenghuni = { itemIdPenghuni ->
                navController.navigate("${DetailDestinationPenghuni.route}/$itemIdPenghuni")
                println("itemIdPenghuni: $itemIdPenghuni")
            }
        )
    }
}