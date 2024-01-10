package com.example.tugasakhir_pam.ui.Penghuni.EditPenghuni

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi
import com.example.tugasakhir_pam.ui.PenyediaViewModel

object EditDestinationPenghuni : DestinasiNavigasi {
    override val route = "item edit penghuni"
    override val titleRes ="Edit Penghuni"
    const val penghuniId = "penghuniId"
    val routeWithArgs = "${EditDestinationPenghuni.route}/{${penghuniId}"
}

@Composable
fun EditScreenPenghuni(
    navigateBackPenghuni: () -> Unit,
    onNavigateUpPenghuni: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditPenghuniViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {

}