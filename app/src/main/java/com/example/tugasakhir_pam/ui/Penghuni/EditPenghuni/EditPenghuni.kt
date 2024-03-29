package com.example.tugasakhir_pam.ui.Penghuni.EditPenghuni

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasakhir_pam.model.Kamar
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi
import com.example.tugasakhir_pam.ui.Penghuni.AddPenghuni.EntryBodyPenghuni
import com.example.tugasakhir_pam.ui.PenyediaViewModel
import kotlinx.coroutines.launch

object EditDestinationPenghuni : DestinasiNavigasi {
    override val route = "item_edit_penghuni"
    override val titleRes ="Edit Penghuni"
    const val penghuniId = "penghuniId"
    val routeWithArgs = "${EditDestinationPenghuni.route}/{${EditDestinationPenghuni.penghuniId}}"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPenghuniScreen(
    navigateBackPenghuni: () -> Unit,
    onNavigateUpPenghuni: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditPenghuniViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val kamarList: List<Kamar> = listOf(Kamar())
    var selectedKamar: Kamar? by remember {
        mutableStateOf(null)
    }

    Scaffold() { innerPadding ->
        EntryBodyPenghuni(
            addUIStatePenghuni = viewModel.penghuniUIState,
            onPenghuniValueChange = viewModel::updateUIStatePenghuni,
            onSaveClickPenghuni = {
                coroutineScope.launch {
                    viewModel.updatePenghuni()
                    navigateBackPenghuni()
                }
            },
            kamarList = kamarList,
            onKamarSelected = { selectedKamar = it},
            selectedKamar = selectedKamar,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}