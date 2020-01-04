package lt.mazajev.raimond.swapi.charactersList

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import lt.mazajev.raimond.swapi.BR

class CharacterViewHolder(private val view: ViewDataBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(item: Character) {
        view.setVariable(BR.item, item)
    }
}