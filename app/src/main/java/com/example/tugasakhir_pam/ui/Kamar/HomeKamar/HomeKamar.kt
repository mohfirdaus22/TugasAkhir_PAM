package com.example.tugasakhir_pam.ui.Kamar.HomeKamar

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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
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
import com.example.tugasakhir_pam.ui.KamarTopAppBar
import com.example.tugasakhir_pam.ui.PenyediaViewModel
import com.example.tugasakhir_pam.model.Kamar
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi

object DestinasiHomeKamar : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Kamar"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KamarScreen(
    navigateToItemEntryKamar: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClickKamar: (String) -> Unit = {},
    viewModel: HomeKamarViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            KamarTopAppBar(
                title = "Kamar",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick ={ navigateToItemEntryKamar() },

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
        val uiStateKamar by viewModel.homeUIStateKamar.collectAsState()
        BodyHomeKamar(
            itemKamar = uiStateKamar.listKamar,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onKamarClick = onDetailClickKamar
        )
    }
}

@Composable
fun BodyHomeKamar(
    itemKamar: List<Kamar>,
    modifier: Modifier = Modifier,
    onKamarClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemKamar.isEmpty()) {
            Text(
                text = "Tidak Ada Data Kamar",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListKamar(
                itemKamar = itemKamar,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onKamarClick(it.nokamar) }
            )
        }
    }
}
@Composable
fun ListKamar(
    itemKamar: List<Kamar>,
    modifier: Modifier = Modifier,
    onItemClick: (Kamar) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemKamar, key = {it.nokamar}){kamar ->
            DataKamar(
                kamar = kamar,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(kamar) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }

    }
}


@Composable
fun DataKamar(
    kamar: Kamar,
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
                    text = kamar.nokamar,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                )
                Text(
                    text = kamar.tipe,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                )
                Text(
                    text = kamar.kapasitas,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = kamar.harga,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}