package com.example.weatherapplication.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.ActivityMainBinding
import com.example.weatherapplication.presentation.feature.weatherDetail.WeatherDetailFragment
import com.example.weatherapplication.presentation.helper.OnBack

class MainActivity : AppCompatActivity(), OnBack {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun navigate(fragment: Fragment, @IdRes id: Int, arg: Bundle? = null) {
        try {
            NavHostFragment.findNavController(fragment).navigate(id, arg)
        } catch (tr: Throwable) {

        }
    }

    override fun from(fragment: Fragment, to: String) {
        val frs = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.fragments
        if (frs != null) {
            for (fr in frs) {
                (fr as? WeatherDetailFragment)?.onBackPressed()
            }
        }
    }
}