package lt.mazajev.raimond.swapi.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.mazajev.raimond.swapi.characterDetails.CharacterDetailsFragment
import lt.mazajev.raimond.swapi.charactersList.CharactersListFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeCharactersListFragment(): CharactersListFragment

    @ContributesAndroidInjector
    abstract fun contributeCharacterDetailsFragment(): CharacterDetailsFragment
}