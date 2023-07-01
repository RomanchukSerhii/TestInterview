package com.example.testinterview

import android.app.Application
import com.example.testinterview.di.component.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}