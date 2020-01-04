package lt.mazajev.raimond.swapi.charactersList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freetimeprojects.mijiareader.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lt.mazajev.raimond.swapi.api.StarWarsApi
import lt.mazajev.raimond.swapi.charactersList.CharactersListViewModel.State.*
import lt.mazajev.raimond.swapi.di.CONTEXT_IO
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class CharactersListViewModel @Inject constructor(
    @Named(CONTEXT_IO) private val ioCoroutineContext: CoroutineContext,
    private val starWarsApi: StarWarsApi
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    val actionRequest = SingleLiveEvent<ActionRequest>()

    private val handler = CoroutineExceptionHandler { _, _ ->
        _state.value = FailedToLoad
    }

    init {
        loadCharacters()
    }

    fun onRetryClick() {
        loadCharacters()
    }

    fun onItemClick(character: Character) {
        actionRequest.value = ActionRequest.NavigateToCharacterDetails(character)
    }

    private fun loadCharacters() {
        if (_state.value == Loading) return
        _state.value = Loading
        viewModelScope.launch(handler) {
            val result = withContext(ioCoroutineContext) { starWarsApi.getCharacters() }
            _state.value = Success(result)
        }
    }

    sealed class ActionRequest {
        data class NavigateToCharacterDetails(val character: Character) : ActionRequest()
    }

    sealed class State {
        object Loading : State()
        object FailedToLoad : State()
        data class Success(val characters: List<Character>) : State()
    }

    fun State?.isLoading() = this == Loading
    fun State?.getData() = (this as? Success)?.characters ?: emptyList()
    fun State?.failedToLoad() = this == FailedToLoad
}
