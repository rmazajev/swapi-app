package lt.mazajev.raimond.swapi.characterDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import lt.mazajev.raimond.swapi.R
import lt.mazajev.raimond.swapi.charactersList.Character
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterDetailsViewModelTest {
    @get:Rule
    var liveDataTestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CharacterDetailsViewModel

    private val testCharacter = Character(
        name = "Name",
        birthYear = "birthYear",
        eyeColor = "eyeColor",
        gender = "male",
        height = "height",
        mass = "mass",
        skinColor = "skinColor",
        hairColor = "hairColor"
    )

    @Before
    fun setup() {
        viewModel = CharacterDetailsViewModel()
    }

    @Test
    fun `character live data should be initiated - when initWithParams is called`() {
        viewModel.initWithParams(testCharacter)
        viewModel.selectedCharacter.test().assertValue { it == testCharacter }
    }

    @Test
    fun `should return correct gender icon - when getGenderIcon is called`() {
        viewModel.initWithParams(testCharacter)
        assert(viewModel.getGenderIcon() == R.drawable.ic_male)
    }
}