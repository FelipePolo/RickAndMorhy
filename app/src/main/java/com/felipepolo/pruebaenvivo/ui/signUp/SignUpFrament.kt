package com.felipepolo.pruebaenvivo.ui.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.felipepolo.pruebaenvivo.R
import com.felipepolo.pruebaenvivo.data.Result
import com.felipepolo.pruebaenvivo.databinding.FragmentSignupBinding
import com.felipepolo.pruebaenvivo.utils.ViewModelFactory
import com.felipepolo.pruebaenvivo.utils.showToast
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SignUpFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val signUpVM: SignUpVM by viewModels { viewModelFactory }

    private lateinit var binding: FragmentSignupBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        signUpVM.userRegistered.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Result.Loading -> {
                    // Do Something for loading process
                }
                is Result.Success -> {
                    navController.navigate(R.id.action_signUpFragment_to_loginFragment)
                }
                is Result.Failure -> {
                    requireContext().showToast(result.exception.message!!)
                }
            }
        })
    }

    private fun setupListeners() {
        binding.btSingUp.setOnClickListener {
            signUpVM.createNewUser(
                binding.edEmail.text.toString(),
                binding.edPassword.text.toString(),
                binding.edRePassword.text.toString()
            )
        }
    }

}