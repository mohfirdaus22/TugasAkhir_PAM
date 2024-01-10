package com.example.tugasakhir_pam.ui.Kamar.DetailKamar

import com.example.tugasakhir_pam.navigation.DestinasiNavigasi

object DetailDestinationKamar: DestinasiNavigasi {
    override val route = "item_details_kamar"
    override val titleRes = "Detail Kamar"
    const val kamarId = "itemKamarId"
    val routeWithArgs = "$route/{$kamarId}"
}