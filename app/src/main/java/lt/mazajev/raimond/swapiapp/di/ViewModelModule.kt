package lt.mazajev.raimond.swapiapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import lt.mazajev.raimond.swapiapp.characterDetails.CharacterDetailsViewModel
import lt.mazajev.raimond.swapiapp.charactersList.CharactersListViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @ApplicationScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharactersListViewModel::class)
    internal abstract fun bindCharactersListViewModel(viewModel: CharactersListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    internal abstract fun bindCharacterDetailsViewModel(viewModel: CharacterDetailsViewModel): ViewModel
}