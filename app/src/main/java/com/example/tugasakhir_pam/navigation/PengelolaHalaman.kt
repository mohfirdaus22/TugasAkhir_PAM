package com.example.tugasakhir_pam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tugasakhir_pam.ui.Halaman.AwalDestination
import com.example.tugasakhir_pam.ui.Halaman.DestinasiUtama
import com.example.tugasakhir_pam.ui.Halaman.HalamanHome
import com.example.tugasakhir_pam.ui.Halaman.HalamanUtama
import com.example.tugasakhir_pam.ui.Penghuni.AddPenghuni.DestinasiEntryPenghuni
import com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni.DestinasiHomePenghuni
import com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni.PenghuniScreen

@Composable
fun PengelolaHalaman(navController: NavHostController ) {
    NavHost(
        navController = navController,
        startDestination = AwalDestination.route,
        modifier = Modifier
    ){
        composable(AwalDestination.route) {
            HalamanHome (
                onNextButtonClicked = {
                    navController.navigate(DestinasiUtama.route)
                },
            )
        }
        composable(DestinasiUtama.route) {
            HalamanUtama(
                onKamarClick = { navController.navigate(DestinasiHomePenghuni.route) },
                onPenghuniClick = { navController.navigate(DestinasiHomePenghuni.route) }
            )
        }
    }


}