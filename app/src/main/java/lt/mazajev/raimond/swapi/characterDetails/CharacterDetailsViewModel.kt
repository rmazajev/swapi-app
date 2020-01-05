package lt.mazajev.raimond.swapi.characterDetails

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.mazajev.raimond.swapi.R
import lt.mazajev.raimond.swapi.charactersList.Character
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor() : ViewModel() {
    private val _selectedCharacter = MutableLiveData<Character>()
    val selectedCharacter: LiveData<Character> = _selectedCharacter

    fun initWithParams(character: Character) {
        if (_selectedCharacter.value != null) return
        _selectedCharacter.value = character
    }

    @DrawableRes
    fun getGenderIcon() = when (_selectedCharacter.value?.gender) {
        "male" -> R.drawable.ic_male
        "female" -> R.drawable.ic_female
        else -> R.drawable.ic_unknown_gender
    }
}
