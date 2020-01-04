package lt.mazajev.raimond.swapi.api

import lt.mazajev.raimond.swapi.charactersList.Character

interface StarWarsApi {
    suspend fun getCharacters(): List<Character>
}