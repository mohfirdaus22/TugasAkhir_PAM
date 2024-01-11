package com.example.tugasakhir_pam.ui.Penghuni.AddPenghuni

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material3.ContentAlpha
import androidx.wear.compose.material3.LocalContentAlpha
import com.example.tugasakhir_pam.model.Kamar
import com.example.tugasakhir_pam.ui.AddEventPenghuni
import com.example.tugasakhir_pam.ui.AddUIStatePenghuni
import com.example.tugasakhir_pam.ui.PenghuniTopAppBar
import com.example.tugasakhir_pam.ui.PenyediaViewModel
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object DestinasiEntryPenghuni : DestinasiNavigasi{
    override val route = "item entry penghuni"
    override val titleRes = "Entry Penghuni"
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

    var kamarList by remember { mutableStateOf<List<Kamar>>(emptyList()) }
    var selectedKamar: Kamar? by remember {
        mutableStateOf(null)
    }

    // LaunchedEffect to fetch data when the composable is first launched
    LaunchedEffect(true) {
        kamarList = fetchKamarFromFirestore(

        )
    }

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
            kamarList = kamarList,
            selectedKamar = selectedKamar,
            onKamarSelected = {selectedKamar = it},
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),

        )
    }
}
suspend fun fetchKamarFromFirestore(): List<Kamar> {
    val firestore = FirebaseFirestore.getInstance()
    return withContext(Dispatchers.IO) {
        try {
            val querySnapshot = firestore.collection("Kamar")
                .get()
                .await()

            val kamarList = mutableListOf<Kamar>()

            for (document in querySnapshot.documents) {
                val nokamar = document.getString("nokamar") ?: ""
                val kamar = Kamar()
                kamarList.add(kamar)
            }

            kamarList
        } catch (e: Exception) {
            // Handle exceptions or errors
            emptyList()
        }
    }
}


@Composable
fun EntryBodyPenghuni(
    addUIStatePenghuni: AddUIStatePenghuni,
    onPenghuniValueChange: (AddEventPenghuni) -> Unit,
    onSaveClickPenghuni: () -> Unit,
    kamarList: List<Kamar>,
    selectedKamar: Kamar?,
    onKamarSelected: (Kamar) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPenghuni(
            addEventPenghuni = addUIStatePenghuni.addEventPenghuni,
            onValueChangePenghuni = onPenghuniValueChange,
            kamarList = kamarList,
            selectedKamar = selectedKamar,
            onKamarSelected= onKamarSelected,
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

@Composable
fun KamarDropdownMenu(
    kamarList: List<Kamar>,
    selectedKamar: Kamar?,
    onKamarSelected: (Kamar) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text("Pilih Kamar", color = MaterialTheme.colorScheme.primary)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(selectedKamar?.nokamar ?: "Pilih Kamar")
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                kamarList.forEach { kamar ->
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        // Menggunakan modifier.clickable pada Text
                        Text(
                            text = kamar.nokamar,
                            modifier = Modifier.clickable {
                                onKamarSelected(Kamar())
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputPenghuni(
    addEventPenghuni: AddEventPenghuni,
    kamarList: List<Kamar>,
    selectedKamar: Kamar?,
    onKamarSelected: (Kamar) -> Unit,
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
        KamarDropdownMenu(
            kamarList = kamarList,
            selectedKamar = selectedKamar,
            onKamarSelected = onKamarSelected)
    }
}