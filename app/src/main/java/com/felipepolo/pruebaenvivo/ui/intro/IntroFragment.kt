package com.felipepolo.pruebaenvivo.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.felipepolo.pruebaenvivo.R
import com.felipepolo.pruebaenvivo.data.Result
import com.felipepolo.pruebaenvivo.databinding.FragmentIntroBinding
import com.felipepolo.pruebaenvivo.utils.ViewModelFactory
import com.felipepolo.pruebaenvivo.utils.showErrorToast
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class IntroFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val introVM: IntroVM by viewModels { viewModelFactory }

    private lateinit var binding: FragmentIntroBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        introVM.checkUserLog()
        setupView()
        setupObservers()
    }

    private fun setupObservers() {
        introVM.user.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                }
                is Result.Success -> {
                    navController.navigate(R.id.action_introFragment_to_homeFragment)
                }
                is Result.Failure -> {
                    requireContext().showErrorToast(result.exception.message!!)
                }
            }
        })
    }

    private fun setupView() {
        binding.btLogin.setOnClickListener {
            navController.navigate(R.id.action_introFragment_to_loginFragment)
        }
        binding.btRegister.setOnClickListener {
            navController.navigate(R.id.action_introFragment_to_signUpFragment)
        }
    }
}