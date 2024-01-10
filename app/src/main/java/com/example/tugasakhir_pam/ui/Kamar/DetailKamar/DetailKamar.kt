package com.example.tugasakhir_pam.ui.Kamar.DetailKamar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasakhir_pam.DetailUIStateKamar
import com.example.tugasakhir_pam.KamarTopAppBar
import com.example.tugasakhir_pam.PenyediaViewModel
import com.example.tugasakhir_pam.model.Kamar
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi
import com.example.tugasakhir_pam.toKamar
import kotlinx.coroutines.launch

object DetailDestinationKamar: DestinasiNavigasi {
    override val route = "item_details_kamar"
    override val titleRes = "Detail Kamar"
    const val kamarId = "itemKamarId"
    val routeWithArgs = "$route/{$kamarId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenKamar(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailKamarViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiStateKamar = viewModel.uiStateKamar.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            KamarTopAppBar(
                title = DetailDestinationKamar.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
    ) { innerPadding ->
        ItemDetailsBodyKamar(
            detailUIStateKamar = uiStateKamar.value,
            onDeleteKamar = {
                coroutineScope.launch {
                    viewModel.deleteKamar()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        )
    }
}

@Composable
private fun ItemDetailsBodyKamar(
    detailUIStateKamar: DetailUIStateKamar,
    onDeleteKamar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        var deleteConfirmationRequiredKamar by rememberSaveable {
            mutableStateOf(false)
        }
        ItemDetailsKamar(
            kamar = detailUIStateKamar.addEventKamar.toKamar(),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedButton(
            onClick = { deleteConfirmationRequiredKamar = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }
        if (deleteConfirmationRequiredKamar){
            DeleteConfirmationDialog(
                onDeleteConfirmKamar = {
                    deleteConfirmationRequiredKamar = false
                    onDeleteKamar()
                },
                onDeleteCancelKamar = {
                    deleteConfirmationRequiredKamar = false
                }, modifier = Modifier.padding(12.dp)
            )
        }

    }
}

@Composable
fun ItemDetailsKamar(
    kamar: Kamar,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ItemDetailsRowKamar(
                labelResIDKamar = "No Kamar",
                itemDetailKamar = kamar.nokamar,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRowKamar(
                labelResIDKamar = "Tipe Kamar",
                itemDetailKamar = kamar.tipe,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRowKamar(
                labelResIDKamar = "Kapasitas",
                itemDetailKamar = kamar.kapasitas,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRowKamar(
                labelResIDKamar = "Harga",
                itemDetailKamar = kamar.harga,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
        }
    }
}

@Composable
private fun ItemDetailsRowKamar(
    labelResIDKamar: String,
    itemDetailKamar: String,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier) {
        Text(text = labelResIDKamar, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetailKamar, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirmKamar: () -> Unit,
    onDeleteCancelKamar: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text("Are you sure") },
        text = { Text("Delete") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancelKamar) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirmKamar) {
                Text(text = "Yes")
            }
        }
    )
}