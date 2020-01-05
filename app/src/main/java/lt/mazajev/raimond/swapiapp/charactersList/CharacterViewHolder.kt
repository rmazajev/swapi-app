package lt.mazajev.raimond.swapiapp.charactersList

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import lt.mazajev.raimond.swapiapp.BR

class CharacterViewHolder(private val view: ViewDataBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(item: Character) {
        view.setVariable(BR.item, item)
    }
}