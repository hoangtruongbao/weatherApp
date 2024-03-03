package com.example.weatherapplication.presentation.feature.home

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.text.set
import androidx.core.widget.doOnTextChanged
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.ActivityMainBinding
import com.example.weatherapplication.databinding.FragmentStartBinding
import com.example.weatherapplication.presentation.application.WeatherApplication
import com.example.weatherapplication.presentation.main.MainActivity
import com.example.weatherapplication.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class StartFragment : Fragment() {

    private val viewmodel : WeatherViewModel by viewModel()
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity: MainActivity
    private val bundle : Bundle = Bundle()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var inputText = binding.edtInput.text
        
        binding.btnSearch.setOnClickListener {
            if (inputText.isNullOrEmpty()) {
                Toast.makeText(context, "Please input your country", Toast.LENGTH_LONG).show()

            } else {
                viewmodel.getWeatherByCity(inputText.toString())
            }
        }

        WeatherApplication.prefHelper.defaultPrefs().edit { this.putString("", "") }


        viewmodel.getWeatherByCity.observe(viewLifecycleOwner) {
            if (it != null) {
                hideKeyboard()
                bundle.putSerializable("weather_detail",it)
                activity.navigate(this@StartFragment,R.id.action_startFragment_to_weatherDetailFragment, bundle)
            }
        }

        binding.csloView.setOnClickListener {
            hideKeyboard()
        }

    }

    fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}