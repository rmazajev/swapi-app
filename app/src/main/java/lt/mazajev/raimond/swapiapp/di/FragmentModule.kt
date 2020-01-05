package lt.mazajev.raimond.swapiapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lt.mazajev.raimond.swapiapp.characterDetails.CharacterDetailsFragment
import lt.mazajev.raimond.swapiapp.charactersList.CharactersListFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeCharactersListFragment(): CharactersListFragment

    @ContributesAndroidInjector
    abstract fun contributeCharacterDetailsFragment(): CharacterDetailsFragment
}