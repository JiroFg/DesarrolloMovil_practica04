package com.example.practica04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.practica04.databinding.ActivityPeliculasListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PeliculasListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculasListBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculasListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.database.reference.child("peliculas")

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val pelis = mutableListOf<String>()
                p0.children.forEach{
                    val genero = it.child("genero").value.toString()
                    val nombre = it.child("titulo").value.toString()
                    pelis.add("$nombre : $genero")
                }
                addAdapter(pelis)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.w("ERROR", "loadPost:onCancelled", p0.toException())
            }
        })
    }

    fun addAdapter(pelis: MutableList<String>){
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,pelis)
        binding.peliculasList.adapter = arrayAdapter
    }

}