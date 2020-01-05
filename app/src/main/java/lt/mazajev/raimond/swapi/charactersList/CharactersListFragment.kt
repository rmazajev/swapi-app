package lt.mazajev.raimond.swapi.charactersList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.freetimeprojects.mijiareader.ui.list.CharactersListAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.characters_list_fragment.*
import lt.mazajev.raimond.swapi.R
import lt.mazajev.raimond.swapi.characterDetails.CharacterDetailsFragment.Companion.SELECTED_CHARACTER_KEY
import lt.mazajev.raimond.swapi.charactersList.CharactersListViewModel.ActionRequest.NavigateToCharacterDetails
import lt.mazajev.raimond.swapi.databinding.CharactersListFragmentBinding
import javax.inject.Inject

class CharactersListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CharactersListViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharactersListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = CharactersListFragmentBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner
        vm = viewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CharactersListAdapter(viewModel)
        charactersList.adapter = adapter

        viewModel.actionRequest.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is NavigateToCharacterDetails -> findNavController(this).navigate(
                    R.id.action_charactersListFragment_to_characterDetailsFragment,
                    Bundle().apply {
                        putParcelable(SELECTED_CHARACTER_KEY, action.character)
                    })
            }
        })

    }
}
