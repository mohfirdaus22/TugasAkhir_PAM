package com.example.tugasakhir_pam.ui.Penghuni.Add

import androidx.compose.ui.Modifier
import com.example.tugasakhir_pam.AddEventPenghuni
import com.example.tugasakhir_pam.navigation.DestinasiNavigasi

object DestinasiEntryPenghuni : DestinasiNavigasi{
    override val route = "item_entry"
    override val titleRes = "Entry_Penghuni"
}

fun FormInputPenghuni(
    addEventPenghuni: AddEventPenghuni,
    modifier: Modifier = Modifier,
    onValueChangePenghuni: (AddEventPenghuni) -> Unit = {},
    enabled: Boolean = true
){

}