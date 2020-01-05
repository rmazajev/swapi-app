package lt.mazajev.raimond.swapi.characterDetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import lt.mazajev.raimond.swapi.charactersList.Character
import lt.mazajev.raimond.swapi.databinding.CharacterDetailsFragmentBinding
import javax.inject.Inject

class CharacterDetailsFragment : Fragment() {

    companion object {
        const val SELECTED_CHARACTER_KEY = "selected_character"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CharacterDetailsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharacterDetailsViewModel::class.java)
        arguments?.getParcelable<Character>(SELECTED_CHARACTER_KEY)?.let {
            viewModel.initWithParams(it)
        } ?: throw IllegalStateException("Couldn't find Character argument")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = CharacterDetailsFragmentBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner
        vm = viewModel
    }.root
}
