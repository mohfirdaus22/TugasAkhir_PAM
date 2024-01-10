package com.example.tugasakhir_pam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugasakhir_pam.ui.Halaman.AwalDestination
import com.example.tugasakhir_pam.ui.Halaman.DestinasiUtama
import com.example.tugasakhir_pam.ui.Halaman.HalamanHome
import com.example.tugasakhir_pam.ui.Halaman.HalamanUtama
import com.example.tugasakhir_pam.ui.Kamar.AddKamar.DestinasiEntryKamar
import com.example.tugasakhir_pam.ui.Kamar.DetailKamar.DetailDestinationKamar
import com.example.tugasakhir_pam.ui.Kamar.DetailKamar.DetailScreenKamar
import com.example.tugasakhir_pam.ui.Kamar.EditKAmar.EditDestinationKamar
import com.example.tugasakhir_pam.ui.Kamar.EditKAmar.EditScreenKamar
import com.example.tugasakhir_pam.ui.Kamar.HomeKamar.DestinasiHomeKamar
import com.example.tugasakhir_pam.ui.Kamar.HomeKamar.KamarScreen
import com.example.tugasakhir_pam.ui.Penghuni.AddPenghuni.AddPenghuni
import com.example.tugasakhir_pam.ui.Penghuni.AddPenghuni.DestinasiEntryPenghuni
import com.example.tugasakhir_pam.ui.Penghuni.DetailPenghuni.DetailDestinationPenghuni
import com.example.tugasakhir_pam.ui.Penghuni.DetailPenghuni.DetailDestinationPenghuni.penghuniId
import com.example.tugasakhir_pam.ui.Penghuni.DetailPenghuni.DetailScreenPenghuni
import com.example.tugasakhir_pam.ui.Penghuni.EditPenghuni.EditDestinationPenghuni
import com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni.DestinasiHomePenghuni
import com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni.PenghuniScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
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
                onPenghuniClick = { navController.navigate(DestinasiHomePenghuni.route) },
                onKamarClick = {navController.navigate(DestinasiHomeKamar.route)},
                onDetailClick = {}
            )
        }
        composable(DestinasiHomePenghuni.route){
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

        composable(
            route = DetailDestinationPenghuni.routeWithArgs,
            arguments = listOf(navArgument(DetailDestinationPenghuni.penghuniId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val penghuniId = backStackEntry.arguments?.getString(DetailDestinationPenghuni.penghuniId)
            penghuniId?.let {
                DetailScreenPenghuni(
                    navigateToEditItemPenghuni = { navController.navigate("${EditDestinationPenghuni.route}/$penghuniId")
                        println("penghuniId: $penghuniId")
                    },
                    navigateBack = {navController.popBackStack() })
            }
        }

        composable(DestinasiEntryPenghuni.route) {
            AddPenghuni(
                navigateBack = { navController.popBackStack() }
            )
        }
        
        composable(
            route = EditDestinationKamar.routeWithArgs,
            arguments = listOf(navArgument(EditDestinationKamar.kamarId){
                type = NavType.StringType
            })
        ){backStackEntry ->  
            val kamarId = backStackEntry.arguments?.getString(EditDestinationKamar.kamarId)
            kamarId?.let { 
                EditScreenKamar(
                    navigateBackKamar = { navController.popBackStack() }, 
                    onNavigateUpKamar = { navController.navigateUp() })
            }
        }

        composable(DestinasiHomeKamar.route){
            KamarScreen(
                navigateToItemEntryKamar = {
                    navController.navigate(DestinasiEntryKamar.route)
                },
                onDetailClickKamar = { itemIdKamar ->
                    navController.navigate("${DetailDestinationKamar.route}/$itemIdKamar")
                    println("itemIdKamar: $itemIdKamar")
                }
            )
        }
        composable(
            route = DetailDestinationKamar.routeWithArgs,
            arguments = listOf(navArgument(EditDestinationKamar.kamarId){
                type = NavType.StringType
            })
        ){backStackEntry ->
            val kamarId = backStackEntry.arguments?.getString(DetailDestinationKamar.kamarId)
            kamarId?.let {
                DetailScreenKamar(navigateToEditItemKamar = {
                    navController.navigate("${EditDestinationKamar.route}/$kamarId")
                    println("kamarId: $kamarId")
                },
                    navigateBack = { navController.popBackStack() })
            }
        }

    }
}