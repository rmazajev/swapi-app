package lt.mazajev.raimond.swapiapp.api

import lt.mazajev.raimond.swapiapp.charactersList.Character
import javax.inject.Inject

class StarWarsApiImpl @Inject constructor(
    private val starWarsRetrofitApi: StarWarsRetrofitApi
) : StarWarsApi {

    override suspend fun getCharacters(): List<Character> = starWarsRetrofitApi.getPeople().toCharacters()
}