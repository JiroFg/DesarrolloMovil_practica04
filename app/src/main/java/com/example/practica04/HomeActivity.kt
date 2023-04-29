package com.example.practica04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica04.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    val PREF_NAME = "myPref"
    val SHARED_NAME = "userName"
    //val SHARED_PASS = "userPass"
    //val SHARE_APODO = "userApodo"

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        takePrefs()

        binding.btnCanciones.setOnClickListener {
            intent = Intent(this, CancionesActivity::class.java)
            startActivity(intent)
        }
        binding.btnPeliculas.setOnClickListener {
            intent = Intent(this, PeliculasActivity::class.java)
            startActivity(intent)
        }
    }

    fun takePrefs(){
        val prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val name = prefs.getString(SHARED_NAME,"")
        //val pass = prefs.getString(SHARED_PASS, "")
        //val apodo = prefs.getString(SHARE_APODO, "")

        binding.text.setText("Hola $name!!")
    }

}