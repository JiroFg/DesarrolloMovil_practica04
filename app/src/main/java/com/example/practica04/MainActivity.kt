package com.example.practica04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val PREF_NAME = "myPref"
    val SHARED_NAME = "userName"
    val SHARED_PASS = "userPass"
    val SHARE_APODO = "userApodo"

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnConf()
    }

    fun btnConf(){
        binding.btn.setOnClickListener {
            val name = binding.textEditNombre.text.toString()
            val pass = binding.textEditPassword.text.toString()
            val apodo =binding.textEditApodo.text.toString()
            prefConf(name,pass,apodo)
            startHomeActivity()
        }
    }

    fun prefConf(name:String, pass:String, apodo:String){
        val prefs = this.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        prefs.edit().putString(SHARED_NAME, name)
            .putString(SHARED_PASS, pass)
            .putString(SHARE_APODO, apodo)
            .apply()
    }

    fun startHomeActivity(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}