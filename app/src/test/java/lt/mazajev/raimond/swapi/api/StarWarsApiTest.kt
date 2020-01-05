package lt.mazajev.raimond.swapi.api

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import lt.mazajev.raimond.swapi.rules.CoroutinesTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StarWarsApiTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val characterName = "Test Name"
    private val characterJson = CharacterJson(
        name = characterName,
        birthYear = "birthYear",
        eyeColor = "eyeColor",
        gender = "gender",
        height = "height",
        mass = "mass",
        skinColor = "skinColor",
        hairColor = "hairColor"
    )

    private val tesPeopleJson = PeopleJson(
        count = 1,
        results = listOf(characterJson)
    )

    private val starWarsRetrofitApi = mock<StarWarsRetrofitApi> {
        onBlocking { getPeople() } doReturn tesPeopleJson
    }

    private lateinit var starWarsApi: StarWarsApi

    @Before
    fun setup() {
        starWarsApi = StarWarsApiImpl(starWarsRetrofitApi)
    }

    @Test
    fun `should return valid characters list - when api is called`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val result = starWarsApi.getCharacters()
        assert(result.isNotEmpty())
        assert(result[0].name == characterName)
    }
}