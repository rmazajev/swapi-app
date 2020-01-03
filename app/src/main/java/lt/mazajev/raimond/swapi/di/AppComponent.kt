package lt.mazajev.raimond.swapi.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import lt.mazajev.raimond.swapi.SwapiApp

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<SwapiApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: SwapiApp): Builder

        fun build(): AppComponent
    }

    override fun inject(app: SwapiApp)
}