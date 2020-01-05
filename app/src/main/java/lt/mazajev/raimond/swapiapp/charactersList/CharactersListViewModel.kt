package lt.mazajev.raimond.swapiapp.charactersList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lt.mazajev.raimond.swapiapp.api.StarWarsApi
import lt.mazajev.raimond.swapiapp.di.CONTEXT_IO
import lt.mazajev.raimond.swapiapp.utils.SingleLiveEvent
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class CharactersListViewModel @Inject constructor(
    @Named(CONTEXT_IO) private val ioCoroutineContext: CoroutineContext,
    private val starWarsApi: StarWarsApi
) : ViewModel() {

    private val _state = MutableLiveData<State>().apply { value = State() }
    val state: LiveData<State> = _state
    val actionRequest =
        SingleLiveEvent<ActionRequest>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        _state.value = _state.value?.copy(loadException = exception, isLoading = false)
    }

    init {
        loadCharacters()
    }

    fun onRetryClick() {
        loadCharacters()
    }

    fun onItemClick(character: Character) {
        actionRequest.value =
            ActionRequest.NavigateToCharacterDetails(
                character
            )
    }

    fun onFilterChange(text: CharSequence) {
        val filterName = text.toString().toLowerCase(Locale.ENGLISH)
        val filtered = _state.value?.characters
            ?.filter { v -> v.name.toLowerCase(Locale.ENGLISH).indexOf(filterName) > -1 }
            ?: emptyList()
        _state.value = _state.value?.copy(filterName = filterName, filteredCharacters = filtered)
    }

    private fun loadCharacters() {
        if (_state.value?.isLoading == true) return
        _state.value = _state.value?.copy(isLoading = true, loadException = null)
        viewModelScope.launch(handler) {
            val result = withContext(ioCoroutineContext) { starWarsApi.getCharacters() }
            _state.value = _state.value?.copy(characters = result, isLoading = false, filteredCharacters = result)
        }
    }

    sealed class ActionRequest {
        data class NavigateToCharacterDetails(val character: Character) : ActionRequest()
    }

    data class State(
        val isLoading: Boolean = false,
        val filteredCharacters: List<Character> = emptyList(),
        val characters: List<Character> = emptyList(),
        val filterName: String? = null,
        val loadException: Throwable? = null
    )

    fun State.isLoading(): Boolean = isLoading
    fun State.failedToLoad(): Boolean = loadException != null
    fun State.isEmpty(): Boolean = filteredCharacters.isEmpty() && !isLoading && !failedToLoad()
    fun State.shouldShowFilterInput(): Boolean = loadException == null && !isLoading
}
