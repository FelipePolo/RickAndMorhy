package com.felipepolo.pruebaenvivo.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.felipepolo.pruebaenvivo.R
import com.felipepolo.pruebaenvivo.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        setupView()
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