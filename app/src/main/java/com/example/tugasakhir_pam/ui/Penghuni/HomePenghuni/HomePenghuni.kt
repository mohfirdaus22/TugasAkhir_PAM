package com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tugasakhir_pam.PenyediaViewModel
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi

object DestinasiHomePenghuni : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Penghuni"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PenghuniScreen(
    navigateToItemEntryPenghuni: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClickPenghuni: (String) -> Unit = {},
    viewModel: HalamanPenghuniViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
}