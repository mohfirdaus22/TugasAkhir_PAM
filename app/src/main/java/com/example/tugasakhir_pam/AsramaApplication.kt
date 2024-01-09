package com.example.tugasakhir_pam

import android.app.Application
import com.example.tugasakhir_pam.data.AppContainer
import com.example.tugasakhir_pam.data.AsramaContainer

class AsramaApplication : Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = AsramaContainer()
    }
}