package com.example.tugasakhir_pam.ui.Kamar.EditKAmar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasakhir_pam.ui.PenyediaViewModel
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi
import com.example.tugasakhir_pam.ui.Kamar.AddKamar.EntryBodyKamar
import kotlinx.coroutines.launch

object EditDestinationKamar : DestinasiNavigasi {
    override val route = "item edit kamar"
    override val titleRes = "Edit kamar"
    const val kamarId = "kamarId"
    val routeWithArgs = "${EditDestinationKamar.route}/{$kamarId}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreenKamar(
    navigateBackKamar: () -> Unit,
    onNavigateUpKamar: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditKamarViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()

    Scaffold() { innerPadding ->
        EntryBodyKamar(
            addUIStateKamar = viewModel.kamariUIState,
            onKamarValueChange = viewModel::updateUIStateKamar,
            onSaveClickKamar = { coroutineScope.launch {
                viewModel.updateKamar()
                navigateBackKamar()
            }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}