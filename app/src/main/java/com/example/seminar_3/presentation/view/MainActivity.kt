package com.example.seminar_3.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seminar_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            
        }
    }
}