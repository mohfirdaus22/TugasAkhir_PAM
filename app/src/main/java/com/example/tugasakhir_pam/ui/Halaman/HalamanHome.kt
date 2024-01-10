package com.example.tugasakhir_pam.ui.Halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tugasakhir_pam.R
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi

object DestinasiUtama : DestinasiNavigasi {
    override val route = "Utama"
    override val titleRes = "Pilih"
}

@Composable
fun HalamanUtama(

    onPenghuniClick: () -> Unit,
    onKamarClick: () -> Unit,
    onDetailClick: ()-> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = "MENU", style = MaterialTheme.typography.titleLarge)
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onPenghuniClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    stringResource(id = R.string.data_penghuni),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Button(
                onClick = onKamarClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    stringResource(id = R.string.data_kamar),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Button(
                onClick = onDetailClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    stringResource(id = R.string.detail),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


