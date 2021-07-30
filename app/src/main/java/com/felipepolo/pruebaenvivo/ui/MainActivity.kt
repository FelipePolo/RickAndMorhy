package com.felipepolo.pruebaenvivo.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.felipepolo.pruebaenvivo.R
import com.felipepolo.pruebaenvivo.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    /*
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MainViewModel

     */

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        /*
        viewModel = viewModelFactory.create(MainViewModel::class.java)
         */

        navController = findNavController(R.id.nav_host_fragment_container)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}