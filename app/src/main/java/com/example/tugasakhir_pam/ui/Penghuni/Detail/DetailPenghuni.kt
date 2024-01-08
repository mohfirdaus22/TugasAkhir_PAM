package com.example.tugasakhir_pam.ui.Penghuni.Detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

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