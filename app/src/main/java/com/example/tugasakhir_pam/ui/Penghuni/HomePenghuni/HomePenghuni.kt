package com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasakhir_pam.ui.PenghuniTopAppBar
import com.example.tugasakhir_pam.ui.PenyediaViewModel
import com.example.tugasakhir_pam.model.Penghuni
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
    viewModel: HomePenghuniViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PenghuniTopAppBar(
                title = "Penghuni",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick ={ navigateToItemEntryPenghuni() },

                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    ) { innerPadding ->
        val uiStatePenghuni by viewModel.homeUIStatePenghuni.collectAsState()
        BodyHomePenghuni(
            itemPenghuni = uiStatePenghuni.listPenghuni,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onPenghuniClick = onDetailClickPenghuni
        )
    }
}

@Composable
fun BodyHomePenghuni(
    itemPenghuni: List<Penghuni>,
    modifier: Modifier = Modifier,
    onPenghuniClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemPenghuni.isEmpty()) {
            Text(
                text = "Tidak Ada Data Penghuni",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListPenghuni(
                itemPenghuni = itemPenghuni,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onPenghuniClick(it.id) }
            )
        }
    }
}
@Composable
fun ListPenghuni(
    itemPenghuni: List<Penghuni>,
    modifier: Modifier = Modifier,
    onItemClick: (Penghuni) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemPenghuni, key = {it.id}){penghuni ->
            DataPenghuni(
                penghuni = penghuni,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(penghuni) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }

    }
}


@Composable
fun DataPenghuni(
    penghuni: Penghuni,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = penghuni.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(
                    text = penghuni.nohp,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                )
                Text(
                    text = penghuni.alamat,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = penghuni.email,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
