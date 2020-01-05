package lt.mazajev.raimond.swapiapp.charactersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import lt.mazajev.raimond.swapiapp.utils.BindableAdapter
import lt.mazajev.raimond.swapiapp.BR
import lt.mazajev.raimond.swapiapp.databinding.CharacterItemBinding

class CharactersListAdapter(private val viewModel: CharactersListViewModel) :
    ListAdapter<Character, CharacterViewHolder>(COMPARATOR),
    BindableAdapter<Character> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view.setVariable(BR.vm, viewModel)

        return CharacterViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.name == newItem.name
        }
    }
}

