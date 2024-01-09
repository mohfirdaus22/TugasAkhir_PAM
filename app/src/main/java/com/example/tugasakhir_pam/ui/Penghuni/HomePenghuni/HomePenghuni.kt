package com.example.tugasakhir_pam.ui.Penghuni.HomePenghuni

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.tugasakhir_pam.PenghuniTopAppBar
import com.example.tugasakhir_pam.PenyediaViewModel
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
    viewModel: HalamanPenghuniViewModel = viewModel(factory = PenyediaViewModel.Factory)
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

    }
}

