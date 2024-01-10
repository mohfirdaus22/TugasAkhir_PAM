package com.example.tugasakhir_pam.ui.Penghuni.AddPenghuni

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasakhir_pam.ui.AddEventPenghuni
import com.example.tugasakhir_pam.ui.AddUIStatePenghuni
import com.example.tugasakhir_pam.ui.PenghuniTopAppBar
import com.example.tugasakhir_pam.ui.PenyediaViewModel
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi
import kotlinx.coroutines.launch

object DestinasiEntryPenghuni : DestinasiNavigasi{
    override val route = "item_entry"
    override val titleRes = "Entry_Penghuni"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPenghuni(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addPenghuniViewModel: AddPenghuniViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PenghuniTopAppBar(
                title = DestinasiEntryPenghuni.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBodyPenghuni(
            addUIStatePenghuni = addPenghuniViewModel.addUIStatePenghuni,
            onPenghuniValueChange = addPenghuniViewModel::updateAddUIStatePenghuni,
            onSaveClickPenghuni = {
                coroutineScope.launch {
                    addPenghuniViewModel.addPenghuni()
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
fun EntryBodyPenghuni(
    addUIStatePenghuni: AddUIStatePenghuni,
    onPenghuniValueChange: (AddEventPenghuni) -> Unit,
    onSaveClickPenghuni: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPenghuni(
            addEventPenghuni = addUIStatePenghuni.addEventPenghuni,
            onValueChangePenghuni = onPenghuniValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSaveClickPenghuni,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputPenghuni(
    addEventPenghuni: AddEventPenghuni,
    modifier: Modifier = Modifier,
    onValueChangePenghuni: (AddEventPenghuni) -> Unit = {},
    enabled: Boolean = true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = addEventPenghuni.name,
            onValueChange = { onValueChangePenghuni(addEventPenghuni.copy(name = it)) },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = addEventPenghuni.alamat,
            onValueChange = { onValueChangePenghuni(addEventPenghuni.copy(alamat = it)) },
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = addEventPenghuni.nohp,
            onValueChange = { onValueChangePenghuni(addEventPenghuni.copy(nohp = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Nomor Telepon") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = addEventPenghuni.email,
            onValueChange = { onValueChangePenghuni(addEventPenghuni.copy(email = it)) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}