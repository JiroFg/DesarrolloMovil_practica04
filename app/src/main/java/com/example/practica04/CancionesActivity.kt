package com.example.practica04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica04.databinding.ActivityCancionesBinding

class CancionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCancionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}