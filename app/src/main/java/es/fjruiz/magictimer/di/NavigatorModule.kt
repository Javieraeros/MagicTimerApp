package es.fjruiz.magictimer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.fjruiz.magictimer.navigation.navigator.Navigator
import es.fjruiz.magictimer.navigation.navigator.NavigatorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigatorModule {

    @Binds
    @Singleton
    abstract fun providesNavigator(
        navigatorImpl: NavigatorImpl
    ): Navigator

}