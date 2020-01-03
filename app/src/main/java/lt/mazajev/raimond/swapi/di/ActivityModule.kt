package lt.mazajev.raimond.swapi.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.mazajev.raimond.swapi.MainActivity


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}