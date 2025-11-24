package es.fjruiz.data.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.fjruiz.data.BuildConfig
import es.fjruiz.data.dao.GameDao
import es.fjruiz.data.database.BaseDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): BaseDatabase =
        Room.databaseBuilder(context, BaseDatabase::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    @Singleton
    fun providesGameDao(
        database: BaseDatabase
    ): GameDao = database.playerDao()
}