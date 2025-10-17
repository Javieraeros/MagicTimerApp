package es.fjruiz.magictimer

import android.app.Application
import es.fjruiz.data.di.dataModule
import es.fjruiz.domain.di.domainModule
import es.fjruiz.magictimer.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MagicTimerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MagicTimerApp)
            modules(appModule.plus(dataModule).plus(domainModule))
        }
    }
}