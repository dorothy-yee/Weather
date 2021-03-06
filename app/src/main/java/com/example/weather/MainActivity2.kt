package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.android.volley.NoConnectionError
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather.weather.Forecast
import com.example.weather.weather.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    val baseURL = "http://t.weather.itboy.net/api/weather/city/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val cityCode = intent.getStringExtra("city_code")
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(baseURL+cityCode,{
            val gson = Gson()
            val WeatherType = object : TypeToken<Weather>(){}.type
            val weather = gson.fromJson<Weather>(it,WeatherType)
            textView_city.text = weather.cityInfo.city
            textView_province.text = weather.cityInfo.parent
            textView_temperature.text = weather.data.wendu
            textView_humidity.text = weather.data.shidu
            val firstday = weather.data.forecast.first()
            when(firstday.type){
                "晴"->imageView.setImageResource(R.drawable.sun)
                "阴"->imageView.setImageResource(R.drawable.cloud)
                "多云"->imageView.setImageResource(R.drawable.mcloud)
                "阵雨"->imageView.setImageResource(R.drawable.rain)
                else->imageView.setImageResource(R.drawable.thunder)
            }

            val adapter= ArrayAdapter<Forecast>(this,android.R.layout.simple_list_item_1,weather.data.forecast)
            listview.adapter=adapter

            Log.d("MainActivity2","${weather.cityInfo.city} ${weather.cityInfo.parent}")
        },{
            Log.d("MainActivity2","$it")
        })
        queue.add(stringRequest)

    }
}