package com.example.tugasakhir_pam.ui.Kamar.AddKamar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasakhir_pam.ui.AddEventKamar
import com.example.tugasakhir_pam.ui.AddUIStateKamar
import com.example.tugasakhir_pam.ui.KamarTopAppBar
import com.example.tugasakhir_pam.ui.PenyediaViewModel
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi
import kotlinx.coroutines.launch

object DestinasiEntryKamar : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry_Kamar"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddKamar(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addKamarViewModel: AddKamarViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            KamarTopAppBar(
                title = DestinasiEntryKamar.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBodyKamar(
            addUIStateKamar = addKamarViewModel.addUIStateKamar,
            onKamarValueChange = addKamarViewModel::updateAddUIStateKamar,
            onSaveClickKamar = {
                coroutineScope.launch {
                    addKamarViewModel.addKamar()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBodyKamar(
    addUIStateKamar: AddUIStateKamar,
    onKamarValueChange: (AddEventKamar) -> Unit,
    onSaveClickKamar: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputKamar(
            addEventKamar = addUIStateKamar.addEventKamar,
            onValueChangeKamar = onKamarValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSaveClickKamar,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputKamar(
    addEventKamar: AddEventKamar,
    modifier: Modifier = Modifier,
    onValueChangeKamar: (AddEventKamar) -> Unit = {},
    enabled: Boolean = true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = addEventKamar.nokamar,
            onValueChange = { onValueChangeKamar(addEventKamar.copy(nokamar = it)) },
            label = { Text("No Kamar") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = addEventKamar.tipe,
            onValueChange = { onValueChangeKamar(addEventKamar.copy(tipe = it)) },
            label = { Text("Tipe") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = addEventKamar.kapasitas,
            onValueChange = { onValueChangeKamar(addEventKamar.copy(kapasitas = it)) },
            label = { Text("Kapasitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = addEventKamar.harga,
            onValueChange = { onValueChangeKamar(addEventKamar.copy(harga = it)) },
            label = { Text("Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}