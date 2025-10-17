package es.fjruiz.data.di

import androidx.room.Room
import es.fjruiz.data.BuildConfig
import es.fjruiz.data.database.BaseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), BaseDatabase::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration(true)
            .build()
    }

    single {
        val database = get<BaseDatabase>()
        database.taskDao()
    }
}