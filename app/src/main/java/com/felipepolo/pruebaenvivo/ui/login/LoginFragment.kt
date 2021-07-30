package com.felipepolo.pruebaenvivo.ui.login

import android.os.Bundle
import android.util.Log
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
import com.felipepolo.pruebaenvivo.databinding.FragmentLoginBinding
import com.felipepolo.pruebaenvivo.ui.signUp.SignUpVM
import com.felipepolo.pruebaenvivo.utils.ViewModelFactory
import com.felipepolo.pruebaenvivo.utils.showErrorToast
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val loginVM: LoginVM by viewModels { viewModelFactory }

    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        loginVM.userLogged.observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Result.Loading -> {
                    // show some thing loading
                }
                is Result.Success -> {
                    // navigate to te main list
                    Log.d("debug", "setupObservers: Usuario Logeado correctamente")
                }
                is Result.Failure -> {
                    requireContext().showErrorToast(result.exception.message!!)
                }
            }
        })
    }

    private fun setupListeners() {
        binding.btLogin.setOnClickListener {
            loginVM.logUser(
                binding.edEmail.text.toString(),
                binding.edPassword.text.toString()
            )
        }
    }

}