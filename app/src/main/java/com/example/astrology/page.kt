package com.example.astrology

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.astrology.databinding.MainInformationBinding

class page : AppCompatActivity() {

    private lateinit var binding : MainInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val zodiac = intent.getStringExtra("zodiac")
        val prediction = intent.getStringExtra("prediction")
        val data = intent.getStringExtra("data")
        val imageId = intent.getIntExtra("imageId",R.drawable.oven)

        binding.pageZodiak.text = zodiac
        binding.pageData.text = data
        binding.pagePrediction.text = prediction
        binding.pageImage.setImageResource(imageId)


    }
}