package com.thudlm.vegetarianres.app

import android.app.Application
import org.koin.core.context.startKoin

class MyApp: Application() {

    fun main(vararg args : String) {
        // start Koin!
        startKoin {
            // your modules
//            modules(myModule)
        }
    }

}