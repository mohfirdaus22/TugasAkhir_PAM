package com.example.tugasakhir_pam.ui.Penghuni.EditPenghuni

import com.example.tugasakhir_pam.navigation.DestinasiNavigasi

object EditDestinationPenghuni : DestinasiNavigasi {
    override val route = "item edit penghuni"
    override val titleRes ="Edit Penghuni"
    const val penghuniId = "penghuniId"
    val routeWithArgs = "${EditDestinationPenghuni.route}/{${penghuniId}"
}

