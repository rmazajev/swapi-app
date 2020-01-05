package lt.mazajev.raimond.swapi.charactersList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import lt.mazajev.raimond.swapi.api.StarWarsApi
import lt.mazajev.raimond.swapi.charactersList.CharactersListViewModel.State.*
import lt.mazajev.raimond.swapi.rules.CoroutinesTestRule
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

    private lateinit var testObserver: TestObserver<CharactersListViewModel.State>

    private val testList = listOf(
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
    )

    @Test
    fun `should fetch characters list - on init`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        starWarsApi.stub { onBlocking { getCharacters() } doReturn testList }
        initViewModel()

        testObserver.assertValue { it == Success(testList) }
        verify(starWarsApi).getCharacters()
    }

    @Test
    fun `state should be FailedToLoad - if failed to fetch characters`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        starWarsApi.stub { onBlocking { getCharacters() } doThrow IllegalStateException() }
        initViewModel()

        testObserver.assertValue { it is FailedToLoad }
    }

    @Test
    fun `should fetch characters - on retry button click`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        starWarsApi.stub { onBlocking { getCharacters() } doReturn testList }
        initViewModel()

        viewModel.onRetryClick()

        testObserver.assertValueHistory(
            Success(testList),
            Loading,
            Success(testList)
        )
        verify(starWarsApi, atMost(2)).getCharacters()
    }

    private fun initViewModel() {
        viewModel = CharactersListViewModel(
            ioCoroutineContext = Dispatchers.Unconfined,
            starWarsApi = starWarsApi
        ).apply {
            testObserver = state.test()
        }
    }
}