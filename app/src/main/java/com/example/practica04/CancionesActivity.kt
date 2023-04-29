package com.example.practica04

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.practica04.databinding.ActivityCancionesBinding

class CancionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCancionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnConf()

        binding.btn.setOnClickListener { guardar() }

        binding.btnVer.setOnClickListener {
            val intent = Intent(this,CancionesListActivity::class.java)
            startActivity(intent)
        }
    }
    fun guardar(){
        val titulo = binding.editTituloCancion.text.toString()
        val artista = binding.editNombreArtista.text.toString()

        val dbHelper = CancionesHelper(this)
        val baseDatos = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(CancionesContract.FeedEntry.COLUMN_NAME_TITLE, titulo)
            put(CancionesContract.FeedEntry.COLUMN_NAME_ARTIST, artista)
        }
        val idCancion = baseDatos?.insert(CancionesContract.FeedEntry.TABLE_NAME, null, values)
        Toast.makeText(this,idCancion.toString(),Toast.LENGTH_LONG).show()
    }

    fun btnConf(){
        binding.btn.isEnabled = false
        binding.btn.isClickable = false
        textInputWatcher()
    }

    fun textInputWatcher(){
        var flag = false
        var flag2 = false
        binding.editTituloCancion.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                flag = count > 0
                if(flag && flag2){
                    binding.btn.isEnabled = true
                    binding.btn.isClickable = true
                }
                if(!flag || !flag2){
                    binding.btn.isEnabled = false
                    binding.btn.isClickable = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.editNombreArtista.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                flag2 = count > 0
                if(flag && flag2){
                    binding.btn.isEnabled = true
                    binding.btn.isClickable = true
                }
                if(!flag || !flag2){
                    binding.btn.isEnabled = false
                    binding.btn.isClickable = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}