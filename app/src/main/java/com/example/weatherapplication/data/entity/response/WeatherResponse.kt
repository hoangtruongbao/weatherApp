import com.example.weatherapplication.data.base.ResponseObject
import com.example.weatherapplication.domain.entity.weather.WeatherResult
import com.google.gson.annotations.SerializedName
import com.example.weatherapplication.domain.entity.weather.*
import com.example.weatherapplication.domain.entity.weather.Coord
import com.example.weatherapplication.domain.entity.weather.Weather
import com.example.weatherapplication.domain.entity.weather.Main
import com.example.weatherapplication.domain.entity.weather.Wind
import com.example.weatherapplication.domain.entity.weather.Rain
import com.example.weatherapplication.domain.entity.weather.Clouds
import com.example.weatherapplication.domain.entity.weather.Sys

/*
Copyright (c) 2024 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class WeatherResponse (

	@SerializedName("coord") val coord : Coord,
	@SerializedName("weather") val weather : List<Weather>,
	@SerializedName("base") val base : String,
	@SerializedName("main") val main : Main,
	@SerializedName("visibility") val visibility : Int,
	@SerializedName("wind") val wind : Wind,
	@SerializedName("rain") val rain : Rain?,
	@SerializedName("clouds") val clouds : Clouds,
	@SerializedName("dt") val dt : Int,
	@SerializedName("sys") val sys : Sys,
	@SerializedName("timezone") val timezone : Int,
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("cod") val cod : Int
) : ResponseObject<WeatherResult> {
	override fun toDomain(): WeatherResult {
		return WeatherResult(coord,weather, base, main,visibility,wind,rain, clouds,dt,sys,timezone, id, name, cod)
	}

}