package lt.mazajev.raimond.swapiapp.api

import lt.mazajev.raimond.swapiapp.charactersList.Character

interface StarWarsApi {
    suspend fun getCharacters(): List<Character>
}