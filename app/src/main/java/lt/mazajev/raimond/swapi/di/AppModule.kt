package lt.mazajev.raimond.swapi.di

import android.content.Context
import dagger.Module
import dagger.Provides
import lt.mazajev.raimond.swapi.SwapiApp

@Module
object AppModule {

    @JvmStatic
    @Provides
    @ApplicationScope
    fun provideAppContext(app: SwapiApp): Context = app

}