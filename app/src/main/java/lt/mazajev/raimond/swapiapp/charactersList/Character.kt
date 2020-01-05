package lt.mazajev.raimond.swapiapp.charactersList

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val name: String,
    val birthYear: String,
    val eyeColor: String,
    val gender: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String
) : Parcelable