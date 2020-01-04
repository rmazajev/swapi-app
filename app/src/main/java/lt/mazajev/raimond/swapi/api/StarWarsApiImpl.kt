package lt.mazajev.raimond.swapi.api

import lt.mazajev.raimond.swapi.charactersList.Character
import javax.inject.Inject

class StarWarsApiImpl @Inject constructor(
    private val starWarsRetrofitApi: StarWarsRetrofitApi
) : StarWarsApi {

    override suspend fun getCharacters(): List<Character> = starWarsRetrofitApi.getPeople().toCharacters()
}