package com.example.tugasakhir_pam.ui.Penghuni.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi

object DestinasiHomePenghuni : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Penghuni"
}
@Composable
fun PenghuniScreen(
    navigateToItemEntryPenghuni: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClickPenghuni: (String) -> Unit = {},
    viewModel: HalamanPenghuniViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {

}