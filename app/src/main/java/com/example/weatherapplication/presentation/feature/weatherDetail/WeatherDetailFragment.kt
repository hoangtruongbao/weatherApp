package com.example.weatherapplication.presentation.feature.weatherDetail

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentStartBinding
import com.example.weatherapplication.databinding.FragmentWeatherDetailBinding
import com.example.weatherapplication.domain.entity.weather.WeatherResult
import com.example.weatherapplication.presentation.helper.IOnBackPressed
import com.example.weatherapplication.presentation.main.MainActivity

class WeatherDetailFragment : Fragment(), IOnBackPressed {

    private  var weatherDetail : WeatherResult? = null
    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity: MainActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            weatherDetail = arguments?.getSerializable("weather_detail") as WeatherResult?
        }

        if (weatherDetail != null) {
            binding.tvNameCountry.text = "Country name : " + weatherDetail?.name
            binding.tvMain.text = "Main weather: " + weatherDetail?.weather?.map { it.main }.toString()
            binding.tvHumidity.text = "Humidity: " + weatherDetail?.main?.humidity.toString()
            binding.tvWindSpeed.text = "Wind speed: " + weatherDetail?.wind?.speed.toString()
        }
    }

    override fun onBackPressed() {
        activity.navigate(this@WeatherDetailFragment, R.id.action_weatherDetailFragment_to_startFragment)
    }


}