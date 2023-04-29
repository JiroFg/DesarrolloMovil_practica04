package com.example.practica04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.practica04.databinding.ActivityPeliculasBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PeliculasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculasBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinnerConf()
        btnConf()

        binding.btn.setOnClickListener {
            guardarPelicula()
        }
        binding.btnListar.setOnClickListener {
            intent = Intent(this,PeliculasListActivity::class.java)
            startActivity(intent)
        }
    }

    fun guardarPelicula(){
        val titulo = binding.editPelicula.text.toString()
        val genero = binding.generoSpinner.selectedItem.toString()
        val objPeli = Pelicula(titulo,genero)
        connectDataBase(objPeli)
    }

    fun connectDataBase(objPeli:Pelicula){
        database = Firebase.database.reference
        val id = database.push().key!!
        database.child("peliculas").child(id).setValue(objPeli)
        Toast.makeText(this,"Pelicula agregada correctamente", Toast.LENGTH_SHORT).show()
    }

    fun spinnerConf(){
        val spinner: Spinner = binding.generoSpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.generos,
            android.R.layout.simple_spinner_item
        ).also{ adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun btnConf(){
        binding.btn.isEnabled = false
        binding.btn.isClickable = false
        textInputWatcher()
    }

    fun textInputWatcher(){
        binding.editPelicula.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count>0){
                    binding.btn.isEnabled = true
                    binding.btn.isClickable = true
                }else{
                    binding.btn.isEnabled = false
                    binding.btn.isClickable = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}