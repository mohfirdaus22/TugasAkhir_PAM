package com.example.tugasakhir_pam.ui.Penghuni.Add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tugasakhir_pam.AddEventPenghuni
import com.example.tugasakhir_pam.AddUIStatePenghuni
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi

object DestinasiEntryPenghuni : DestinasiNavigasi{
    override val route = "item_entry"
    override val titleRes = "Entry_Penghuni"
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