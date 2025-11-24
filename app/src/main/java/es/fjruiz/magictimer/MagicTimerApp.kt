package es.fjruiz.magictimer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MagicTimerApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}