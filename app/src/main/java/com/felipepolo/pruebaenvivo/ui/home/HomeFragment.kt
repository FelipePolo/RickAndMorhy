package com.felipepolo.pruebaenvivo.ui.home

import android.os.Bundle
import android.view.*
import android.widget.CompoundButton
import android.widget.Switch
import androidx.core.view.get
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.felipepolo.pruebaenvivo.R
import com.felipepolo.pruebaenvivo.data.Result
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import com.felipepolo.pruebaenvivo.data.local.models.toFavorite
import com.felipepolo.pruebaenvivo.databinding.FragmentHomeBinding
import com.felipepolo.pruebaenvivo.utils.*
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment(), CharacterAdapter.OnCharacterClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val homeVM: HomeVM by viewModels { viewModelFactory }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterAdapter = CharacterAdapter(requireContext(),this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true);
        navController = findNavController()

        setupObservers()
        setupRecyclerView()
        setupNestedScrollView()
    }

    private fun setupNestedScrollView() {
        binding.svCharacters.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v[0].measuredHeight - v.measuredHeight && !homeVM.isFavoriteList) {
                homeVM.getCharacters()
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvCharacterList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacterList.adapter = characterAdapter
    }

    private fun setupObservers() {
        homeVM.characterList.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Result.Loading -> {
                    binding.pbCharacters.show()
                }
                is Result.Success -> {
                    binding.pbCharacters.hide()
                    if (homeVM.isFavoriteList){
                        characterAdapter.setCharacterList(result.data)
                    }else{
                        characterAdapter.addToCharacterList(result.data)
                    }
                }
                is Result.Failure -> {
                    binding.pbCharacters.hide()
                    requireContext().showErrorToast(result.exception.message!!)
                }
            }
        })

        homeVM.saveCharacterEvent.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Result.Loading -> { }
                is Result.Success -> {
                    requireContext().showToast("Item Added")
                }
                is Result.Failure -> {
                    requireContext().showErrorToast(result.exception.message!!)
                }
            }
        })

        homeVM.deleteCharacterEvent.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Result.Loading -> { }
                is Result.Success -> {
                    requireContext().showToast("Item Removed")
                    characterAdapter.removeToCharacterList(result.data)
                }
                is Result.Failure -> {
                    requireContext().showErrorToast(result.exception.message!!)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

        val swFavorite = (menu.findItem(R.id.swFavorite)).actionView as Switch
        swFavorite.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, isCheked: Boolean) {
                homeVM.switchList(isCheked)
            }
        })

        (menu.findItem(R.id.btLogOut)).setOnMenuItemClickListener {
            homeVM.logOutUser()
            navController.popBackStack(R.id.introFragment,false)
        }
    }

    override fun onCharacterLikeClick(character: CharactersEntity, position: Int) {
        homeVM.saveFavoriteCharacter(character.toFavorite())
    }

    override fun onCharacterDeleteClick(character: CharactersEntity, position: Int) {
        homeVM.deleteFavoriteCharacter(character)
    }
}