package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity2 : AppCompatActivity() {

    val baseURL = "https://t.weather.sojson.com/api/weather/city/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val cityCode = intent.getStringExtra("city_code")
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(baseURL+cityCode,{
            Log.d("MainActivity2","$it")
        },{
            Log.d("MainActivity2","$it")
        })
        queue.add(stringRequest)
    }
}