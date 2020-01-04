package lt.mazajev.raimond.swapi.di

import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import lt.mazajev.raimond.swapi.SwapiApp
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

const val CONTEXT_UI = "UI"
const val CONTEXT_IO = "IO"

@Module
object AppModule {

    @JvmStatic
    @Provides
    @ApplicationScope
    fun provideAppContext(app: SwapiApp): Context = app

    @Provides
    @Named(CONTEXT_UI)
    @ApplicationScope
    fun provideUiCoroutineContext(): CoroutineContext = Dispatchers.Main

    @Provides
    @Named(CONTEXT_IO)
    @ApplicationScope
    fun provideAsyncCoroutineContext(): CoroutineContext = Dispatchers.IO

}