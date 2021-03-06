package lt.mazajev.raimond.swapiapp.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import lt.mazajev.raimond.swapiapp.charactersList.Character

data class PeopleJson(
    val count: Int,
    val results: List<CharacterJson>
)

@Keep
data class CharacterJson(
    val name: String,
    @SerializedName("birth_year")
    val birthYear: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("skin_color")
    val skinColor: String,
    val gender: String,
    val height: String,
    val mass: String
)

fun PeopleJson.toCharacters() = this.results.map { characterJson ->
    Character(
        name = characterJson.name,
        birthYear = characterJson.birthYear,
        eyeColor = characterJson.eyeColor,
        gender = characterJson.gender,
        height = characterJson.height,
        mass = characterJson.mass,
        hairColor = characterJson.hairColor,
        skinColor = characterJson.skinColor
    )
}