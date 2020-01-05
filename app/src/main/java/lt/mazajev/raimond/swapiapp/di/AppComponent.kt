package lt.mazajev.raimond.swapiapp.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import lt.mazajev.raimond.swapiapp.SwapiApp
import lt.mazajev.raimond.swapiapp.api.ApiModule

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        ApiModule::class
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