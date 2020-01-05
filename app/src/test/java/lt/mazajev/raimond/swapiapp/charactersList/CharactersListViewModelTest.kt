package lt.mazajev.raimond.swapiapp.charactersList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import lt.mazajev.raimond.swapiapp.api.StarWarsApi
import lt.mazajev.raimond.swapiapp.charactersList.CharactersListViewModel.*
import lt.mazajev.raimond.swapiapp.rules.CoroutinesTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersListViewModelTest {

    @get:Rule
    var liveDataTestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: CharactersListViewModel

    private val starWarsApi = mock<StarWarsApi>()

    private lateinit var testStateObserver: TestObserver<State>

    private val testCharacter =
        Character(
            name = "Name",
            birthYear = "birthYear",
            eyeColor = "eyeColor",
            gender = "gender",
            height = "height",
            mass = "mass",
            skinColor = "skinColor",
            hairColor = "hairColor"
        )
    private val testCharactersList = listOf(testCharacter)
    private val testFilterName = "Non Existing Name"

    @Test
    fun `should fetch characters list - on init`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        starWarsApi.stub { onBlocking { getCharacters() } doReturn testCharactersList }
        initViewModel()

        testStateObserver.assertValue { it.characters == testCharactersList }
        verify(starWarsApi).getCharacters()
    }

    @Test
    fun `state should have exception value - if failed to fetch characters`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        starWarsApi.stub { onBlocking { getCharacters() } doThrow IllegalStateException() }
        initViewModel()

        testStateObserver.assertValue { it.loadException is IllegalStateException }
    }

    @Test
    fun `should fetch characters - on retry button click`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        starWarsApi.stub { onBlocking { getCharacters() } doReturn testCharactersList }
        initViewModel()

        viewModel.onRetryClick()

        testStateObserver.assertValueHistory(
            State(isLoading = false, characters = testCharactersList, loadException = null, filteredCharacters = testCharactersList),
            State(isLoading = true, characters = testCharactersList, loadException = null, filteredCharacters = testCharactersList),
            State(isLoading = false, characters = testCharactersList, loadException = null, filteredCharacters = testCharactersList)
        )
        verify(starWarsApi, atMost(2)).getCharacters()
    }

    @Test
    fun `should show no results found - when got empty characters list`() {
        starWarsApi.stub { onBlocking { getCharacters() } doReturn emptyList() }
        initViewModel()
        with(viewModel) {
            assert(viewModel.state.value?.isEmpty() == true)
        }
    }

    @Test
    fun `should emit NavigateToCharacterDetails - when character item click`() {
        starWarsApi.stub { onBlocking { getCharacters() } doReturn testCharactersList }
        initViewModel()
        viewModel.onItemClick(testCharacter)
        viewModel.actionRequest.test().assertValue { it == ActionRequest.NavigateToCharacterDetails(testCharacter) }
    }

    @Test
    fun `state should have filtered characters list - if user have entered name in filter input`() {
        starWarsApi.stub { onBlocking { getCharacters() } doReturn testCharactersList }
        initViewModel()

        viewModel.onFilterChange(testFilterName)
        testStateObserver.assertValue { it.filterName == testFilterName.toLowerCase() }
        testStateObserver.assertValue { it.filteredCharacters.isEmpty() }
    }

    private fun initViewModel() {
        viewModel = CharactersListViewModel(
            ioCoroutineContext = Dispatchers.Unconfined,
            starWarsApi = starWarsApi
        ).apply {
            testStateObserver = state.test()
        }
    }
}