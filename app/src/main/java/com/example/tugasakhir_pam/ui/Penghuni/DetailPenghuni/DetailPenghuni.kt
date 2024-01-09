package com.example.tugasakhir_pam.ui.Penghuni.DetailPenghuni

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
import androidx.compose.material3.FloatingActionButton
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
import com.example.tugasakhir_pam.DetailUIStatePenghuni
import com.example.tugasakhir_pam.PenghuniTopAppBar
import com.example.tugasakhir_pam.PenyediaViewModel
import com.example.tugasakhir_pam.model.Penghuni
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi
import com.example.tugasakhir_pam.toPenghuni
import kotlinx.coroutines.launch

object DetailDestinationPenghuni: DestinasiNavigasi {
    override val route = "item_details_penghuni"
    override val titleRes = "Detail Penghuni"
    const val penghuniId = "itemPenghuniId"
    val routeWithArgs = "$route/{$penghuniId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenPenghuni(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailPenghuniViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiStatePenghuni = viewModel.uiStatePenghuni.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            PenghuniTopAppBar(
                title = DetailDestinationPenghuni.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
    ) { innerPadding ->
        ItemDetailsBodyPenghuni(
            detailUIStatePenghuni = uiStatePenghuni.value,
            onDeletePenghuni = {
                coroutineScope.launch {
                    viewModel.deletePenghuni()
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
private fun ItemDetailsBodyPenghuni(
    detailUIStatePenghuni: DetailUIStatePenghuni,
    onDeletePenghuni: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        var deleteConfirmationRequiredPenghuni by rememberSaveable {
            mutableStateOf(false)
        }
        ItemDetailsPenghuni(
            penghuni = detailUIStatePenghuni.addEventPenghuni.toPenghuni(),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedButton(
            onClick = { deleteConfirmationRequiredPenghuni = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }
        if (deleteConfirmationRequiredPenghuni){
            DeleteConfirmationDialog(
                onDeleteConfirmPenghuni = {
                    deleteConfirmationRequiredPenghuni = false
                    onDeletePenghuni()
                },
                onDeleteCancelPenghuni = {
                    deleteConfirmationRequiredPenghuni = false
                },modifier = Modifier.padding(12.dp)
            )
        }

    }
}


@Composable
fun ItemDetailsPenghuni(
    penghuni: Penghuni,
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
            ItemDetailsRowPenghuni(
                labelResIDPenghuni = "Nama",
                itemDetailPenghuni = penghuni.name,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRowPenghuni(
                labelResIDPenghuni = "Alamat",
                itemDetailPenghuni = penghuni.alamat,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRowPenghuni(
                labelResIDPenghuni = "No Telepon",
                itemDetailPenghuni = penghuni.nohp,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRowPenghuni(
                labelResIDPenghuni = "Email",
                itemDetailPenghuni = penghuni.email,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
        }
    }
}

@Composable
private fun ItemDetailsRowPenghuni(
    labelResIDPenghuni: String,
    itemDetailPenghuni: String,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier) {
        Text(text = labelResIDPenghuni, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetailPenghuni, fontWeight = FontWeight.Bold)
    }
}


@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirmPenghuni: () -> Unit,
    onDeleteCancelPenghuni: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text("Are you sure")},
        text = { Text("Delete")},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancelPenghuni) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirmPenghuni) {
                Text(text = "Yes")
            }
        }
    )
}