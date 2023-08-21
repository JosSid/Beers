package com.jossidfactory.beers

import android.app.Application
import com.jossidfactory.beers.di.DataModule
import com.jossidfactory.beers.di.ViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class BeersApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@BeersApplication)
            modules(
                DataModule,
                ViewModule
            )
        }
    }
}